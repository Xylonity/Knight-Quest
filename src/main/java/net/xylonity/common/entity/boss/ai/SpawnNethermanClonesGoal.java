package net.xylonity.common.entity.boss.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.xylonity.common.entity.boss.NethermanCloneEntity;
import net.xylonity.common.entity.boss.NethermanEntity;
import net.xylonity.registry.KnightQuestEntities;

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

    public boolean canStart() {
        return this.netherman.getTarget() != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */

    public void start() {
        this.chargeTime = 400;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */

    public void stop() {
        this.netherman.setCharging(false);
        this.chargeTime = 0;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null && this.netherman.getPhase() == 2) {

            if (livingentity.distanceTo(this.netherman) < 4096.0D && this.netherman.canSee(livingentity)) {

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (this.chargeTime == 30) {
                    this.netherman.setNoMovement(true);
                    this.netherman.setIsAttacking(true);
                }

                if (this.chargeTime == 10) {
                    Vec3d position = this.netherman.getPos();

                    this.netherman.getWorld().playSound(null, this.netherman.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.BLOCKS, 1f, 1f);

                    for (int i = 0; i < 4; i++) {
                        double angle = this.netherman.getRandom().nextDouble() * 2 * Math.PI;
                        double distance = this.netherman.getRandom().nextDouble() * 30;

                        double xOffset = Math.cos(angle) * distance;
                        double zOffset = Math.sin(angle) * distance;
                        Vec3d spawnPos = position.add(xOffset, 0, zOffset);

                        NethermanCloneEntity nethermanClone = KnightQuestEntities.NETHERMAN_CLONE.create(this.netherman.getWorld());
                        assert nethermanClone != null;
                        nethermanClone.teleport(spawnPos.x, position.y, spawnPos.z, true);

                        this.netherman.getWorld().spawnEntity(nethermanClone);

                        for (PlayerEntity player : this.netherman.getWorld().getPlayers()) {
                            if (player instanceof ServerPlayerEntity serverPlayer) {

                                double ux = nethermanClone.getParticleX(0.5D);
                                double uy = nethermanClone.getRandomBodyY() - 0.25D;
                                double uz = nethermanClone.getParticleZ(0.5D);

                                float vx = (float) ((nethermanClone.getRandom().nextDouble() - 0.5D) * 2.0D);
                                float vy = (float) -nethermanClone.getRandom().nextDouble();
                                float vz = 0.2f;

                                for(int u = 0; u < 20; ++u) {
                                    serverPlayer.networkHandler.sendPacket(new ParticleS2CPacket(
                                            ParticleTypes.SOUL_FIRE_FLAME,
                                            true,
                                            ux, uy, uz,
                                            vx, vy, vz,
                                            0.0f,
                                            2
                                    ));
                                }
                            }
                        }

                        this.netherman.getWorld().playSound(null, nethermanClone.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
                    }

                }

                if (this.chargeTime == 0) {
                    this.netherman.setNoMovement(false);
                    this.netherman.setIsAttacking(false);
                    this.chargeTime = 400;
                }

            } else {
                this.chargeTime = 400;
            }

            this.netherman.setCharging(this.chargeTime > 0);
        } else {
            this.chargeTime = 400;
        }
    }

}
