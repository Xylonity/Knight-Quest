package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public class NethermanClonesGoal extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public NethermanClonesGoal(NethermanEntity netherman) {
        this.netherman = netherman;
    }

    public boolean canUse() {
        return this.netherman.getTarget() != null && this.netherman.getPhase() == 2  && this.netherman.getCounterSwitchPhase2() == 130;
    }

    public void start() {
        this.chargeTime = 300;
    }

    public void stop() {
        this.chargeTime = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null) {

            if (livingentity.distanceToSqr(this.netherman) < 4096.0D && this.netherman.hasLineOfSight(livingentity)) {

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (chargeTime == 35) {
                    teleportToRandomLocationAroundTarget(netherman.getTarget());
                }

                if (chargeTime == 30) {
                    netherman.setNoMovement(true);
                    netherman.setIsDoingSpecialAttack2(true);
                }

                if (this.chargeTime == 20 && !this.netherman.isSilent()) {
                    this.netherman.level().playSound(null, this.netherman.getOnPos(), SoundEvents.POWDER_SNOW_BREAK, SoundSource.BLOCKS, 1f, 1f);
                }

                if (this.chargeTime == 10) {
                    Vec3 position = this.netherman.position();

                    this.netherman.level().playSound(null, this.netherman.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.BLOCKS, 1f, 1f);

                    for (int i = 0; i < KQConfigValues.MAX_NETHERMAN_CLONES.get(); i++) {
                        double angle = this.netherman.getRandom().nextDouble() * 2 * Math.PI;
                        double distance = this.netherman.getRandom().nextDouble() * 30;

                        double xOffset = Math.cos(angle) * distance;
                        double zOffset = Math.sin(angle) * distance;
                        Vec3 spawnPos = position.add(xOffset, 0, zOffset);

                        Vec3 safeSpawnPos = findNearestSafePosition(spawnPos, this.netherman.level());

                        NethermanCloneEntity nethermanClone = KnightQuestEntities.NETHERMAN_CLONE.get().create(this.netherman.level());

                        if (nethermanClone != null) {
                            nethermanClone.moveTo(safeSpawnPos.x, safeSpawnPos.y, safeSpawnPos.z);

                            this.netherman.level().addFreshEntity(nethermanClone);

                            for (Player player : this.netherman.level().players()) {
                                if (player instanceof ServerPlayer serverPlayer) {
                                    for(int u = 0; u < 20; ++u) {
                                        serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                                ParticleTypes.SNOWFLAKE,
                                                true,
                                                nethermanClone.getRandomX(0.5D),
                                                nethermanClone.getRandomY() - 0.25D,
                                                nethermanClone.getRandomZ(0.5D),
                                                (float) ((nethermanClone.getRandom().nextDouble() - 0.5D) * 2.0D),
                                                (float) -nethermanClone.getRandom().nextDouble(),
                                                0.2f,
                                                0.0f,
                                                2
                                        ));
                                    }
                                }
                            }

                        this.netherman.level().playSound(null, nethermanClone.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f);

                        }
                    }

                }

                if (this.chargeTime == 0) {
                    this.chargeTime = 300;
                    teleportToRandomLocationAroundTarget(netherman.getTarget());
                }

            } else {
                this.chargeTime = 300;
            }

        } else {
            this.chargeTime = 300;
        }
    }

    private void teleportToRandomLocationAroundTarget(LivingEntity target) {
        boolean teleported = false;

        this.netherman.level().gameEvent(GameEvent.TELEPORT, this.netherman.position(), GameEvent.Context.of(this.netherman));
        this.netherman.level().playSound(null, this.netherman.xo, this.netherman.yo, this.netherman.zo, SoundEvents.ENDERMAN_TELEPORT, this.netherman.getSoundSource(), 1.0F, 1.0F);
        this.netherman.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);

        for (int i = 0; i < 32 && !teleported; i++) {
            double angle = netherman.getRandom().nextDouble() * 2 * Math.PI;
            double distance = 10 + netherman.getRandom().nextDouble() * 20;
            double dx = target.getX() + distance * Math.cos(angle);
            double dz = target.getZ() + distance * Math.sin(angle);
            double dy = netherman.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos((int) dx, (int) netherman.getY(), (int) dz)).getY();

            if (netherman.randomTeleport(dx, dy, dz, true)) {
                teleported = true;
            }
        }

        if (!teleported) {
            netherman.teleportTo(target.getX(), target.getY(), target.getZ());
        }
    }

    private Vec3 findNearestSafePosition(Vec3 pos, Level level) {
        double x = pos.x;
        double z = pos.z;
        double y = pos.y;

        for (int offset = 0; offset < 256; offset++) {
            double up = y + offset;
            double down = y - offset;

            if (isSafePosition(new Vec3(x, up, z), level)) {
                return new Vec3(x, up, z);
            }

            if (isSafePosition(new Vec3(x, down, z), level)) {
                return new Vec3(x, down, z);
            }
        }

        return pos;
    }

    private boolean isSafePosition(Vec3 pos, Level level) {
        NethermanCloneEntity test = KnightQuestEntities.NETHERMAN_CLONE.get().create(level);
        if (test != null) {
            test.moveTo(pos.x, pos.y, pos.z);
            return level.noCollision(test);
        }

        return false;
    }

}