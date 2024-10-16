package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NethermanFlameGoal extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public NethermanFlameGoal(NethermanEntity netherman) {
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

    /**
     * Keep ticking a continuous task that has already been started
     */

    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null && this.netherman.getPhase() == 1 && this.netherman.getHealth() >= this.netherman.getMaxHealth() * 0.75) {
            if (livingentity.distanceToSqr(this.netherman) < 4096.0D && this.netherman.hasLineOfSight(livingentity)) {
                Level level = this.netherman.level();

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (this.chargeTime == 30) {
                    this.netherman.setNoMovement(true);
                    this.netherman.setIsAttacking(true);
                }

                if (this.chargeTime == 20 && !this.netherman.isSilent()) {
                    level.playSound(null, this.netherman.getOnPos(), SoundEvents.FIREWORK_ROCKET_TWINKLE, SoundSource.BLOCKS, 1f, 1f);
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
                                        ParticleTypes.FLAME,
                                        true,
                                        x, y, z,
                                        (float) vx, (float) vy, (float) vz,
                                        0.2f,
                                        2
                                ));
                            }
                        }
                    }

                    if (!this.netherman.isSilent()) {
                        level.playSound(null, this.netherman.getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1f, 1f);
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
                                        ParticleTypes.FLAME,
                                        true,
                                        x, y, z,
                                        (float) vx, (float) vy, (float) vz,
                                        0.2f,
                                        2
                                ));
                            }
                        }
                    }

                    this.netherman.getTarget().setRemainingFireTicks(this.netherman.getTarget().getRandom().nextInt(3, 7) * 20);
                    this.netherman.setNoMovement(false);
                }

                if (this.chargeTime == 0) {
                    this.netherman.setIsAttacking(false);
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
