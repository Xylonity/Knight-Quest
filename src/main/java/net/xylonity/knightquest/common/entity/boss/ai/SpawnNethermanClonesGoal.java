package net.xylonity.knightquest.common.entity.boss.ai;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import net.xylonity.knightquest.registry.KnightQuestEntities;

import java.util.EnumSet;
import java.util.Random;

public class SpawnNethermanClonesGoal extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public SpawnNethermanClonesGoal(NethermanEntity netherman) {
        this.netherman = netherman;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return this.netherman.getTarget() != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.chargeTime = 200;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.netherman.setCharging(false);
        this.chargeTime = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null && this.netherman.getPhase() == 2) {

            if (livingentity.distanceToSqr(this.netherman) < 4096.0D && this.netherman.hasLineOfSight(livingentity)) {

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (this.chargeTime == 0) {
                    Vec3 position = this.netherman.position();

                    this.netherman.level().playSound(null, this.netherman.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.BLOCKS, 1f, 1f);

                    for (int i = 0; i < 4; i++) {
                        double angle = this.netherman.getRandom().nextDouble() * 2 * Math.PI;
                        double distance = this.netherman.getRandom().nextDouble() * 30;

                        double xOffset = Math.cos(angle) * distance;
                        double zOffset = Math.sin(angle) * distance;
                        Vec3 spawnPos = position.add(xOffset, 0, zOffset);

                        NethermanCloneEntity nethermanClone = KnightQuestEntities.NETHERMAN_CLONE.get().create(this.netherman.level());
                        assert nethermanClone != null;
                        nethermanClone.moveTo(spawnPos.x, position.y, spawnPos.z);

                        this.netherman.level().addFreshEntity(nethermanClone);

                        for (Player player : this.netherman.level().players()) {
                            if (player instanceof ServerPlayer serverPlayer) {
                                for(int u = 0; u < 20; ++u) {
                                    serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                            ParticleTypes.PORTAL,
                                            true,
                                            nethermanClone.getRandomX(0.5D),
                                            nethermanClone.getRandomY() - 0.25D,
                                            nethermanClone.getRandomZ(0.5D),
                                            (float) ((nethermanClone.getRandom().nextDouble() - 0.5D) * 2.0D),
                                            (float) -nethermanClone.getRandom().nextDouble(),
                                            0.2f,
                                            0.0f,
                                            1
                                    ));
                                }
                            }
                        }

                        this.netherman.level().playSound(null, nethermanClone.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f);
                    }

                    this.chargeTime = 200;
                }

            } else {
                this.chargeTime = 200;
            }

            this.netherman.setCharging(this.chargeTime > 0);
        } else {
            this.chargeTime = 200;
        }
    }

}
