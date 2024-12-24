package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.config.values.KQConfigValues;
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


public class NethermanIceGoal extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public NethermanIceGoal(NethermanEntity netherman) {
        this.netherman = netherman;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */

    public boolean canUse() {
        return this.netherman.getTarget() != null && this.netherman.getPhase() == 2 && this.netherman.getCounterSwitchPhase2() == 130;
    }

    public void start() {
        this.chargeTime = 200;
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
                Level level = this.netherman.level();

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (chargeTime == 35) {
                    teleportToRandomLocationAroundTarget(netherman.getTarget());
                }

                if (chargeTime == 30) {
                    netherman.setNoMovement(true);
                    netherman.setIsDoingFlameAttack(true);
                }

                if (this.chargeTime == 20 && !this.netherman.isSilent()) {
                    level.playSound(null, this.netherman.getOnPos(), SoundEvents.POWDER_SNOW_BREAK, SoundSource.BLOCKS, 1f, 1f);
                }

                if (this.chargeTime == 10) {
                    Vec3 look = this.netherman.getLookAngle();
                    double offsetX = look.x * 0.5;
                    double offsetY = look.y * 0.5;
                    double offsetZ = look.z * 0.5;

                    for (Player player : this.netherman.level().players()) {
                        if (player instanceof ServerPlayer serverPlayer) {
                            for (int u = 0; u < 20; ++u) {
                                double speed = 0.1 + this.netherman.getRandom().nextDouble() * 0.2;
                                double x = this.netherman.getX() + offsetX + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                                double y = this.netherman.getY() + this.netherman.getEyeHeight() + offsetY + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                                double z = this.netherman.getZ() + offsetZ + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                                double vx = look.x * speed;
                                double vy = look.y * speed;
                                double vz = look.z * speed;

                                serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                        ParticleTypes.SNOWFLAKE,
                                        true,
                                        x, y, z,
                                        (float) vx, (float) vy + 1f, (float) vz,
                                        0.2f,
                                        2
                                ));
                            }
                        }
                    }

                    if (!this.netherman.isSilent()) {
                        level.playSound(null, this.netherman.getOnPos(), SoundEvents.SNOW_GOLEM_AMBIENT, SoundSource.BLOCKS, 1f, 1f);
                    }

                }

                if (chargeTime == 5) {

                    for (Player player : this.netherman.level().players()) {
                        if (player instanceof ServerPlayer serverPlayer) {
                            for (int u = 0; u < 20; ++u) {
                                double speed = 0.1 + this.netherman.getRandom().nextDouble() * 0.2;
                                double x = this.netherman.getTarget().getX() + (this.netherman.getTarget().getRandom().nextDouble() - 0.5) * 0.2;
                                double y = this.netherman.getTarget().getY() + this.netherman.getTarget().getEyeHeight() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                                double z = this.netherman.getTarget().getZ() + (this.netherman.getTarget().getRandom().nextDouble() - 0.5) * 0.2;

                                Vec3 look = this.netherman.getTarget().getLookAngle();
                                double vx = look.x * speed;
                                double vy = look.y * speed;
                                double vz = look.z * speed;

                                serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                        ParticleTypes.SNOWFLAKE,
                                        true,
                                        x, y, z,
                                        (float) vx, (float) vy, (float) vz,
                                        0.2f,
                                        2
                                ));
                            }
                        }
                    }

                    this.netherman.getTarget().setTicksFrozen(this.netherman.getTarget().getTicksFrozen() + KQConfigValues.ICE_ATTACK_FREEZE_TICKS);
                }

                if (this.chargeTime == 0) {
                    this.chargeTime = 200;
                    teleportToRandomLocationAroundTarget(netherman.getTarget());
                }

            } else {
                this.chargeTime = 200;
            }

        } else {
            this.chargeTime = 200;
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

}