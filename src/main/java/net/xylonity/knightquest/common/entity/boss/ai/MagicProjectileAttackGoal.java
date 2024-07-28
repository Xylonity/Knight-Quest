package net.xylonity.knightquest.common.entity.boss.ai;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;

public class MagicProjectileAttackGoal extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public MagicProjectileAttackGoal(NethermanEntity netherman) {
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
        this.chargeTime = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.netherman.setCharging(false);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null) {
            double d0 = 64.0D;
            if (livingentity.distanceToSqr(this.netherman) < 4096.0D && this.netherman.hasLineOfSight(livingentity)) {
                Level level = this.netherman.level();
                ++this.chargeTime;

                if (this.chargeTime == 10 && !this.netherman.isSilent()) {
                    level.playSound(null, this.netherman.getOnPos(), SoundEvents.FIREWORK_ROCKET_TWINKLE, SoundSource.BLOCKS, 1f, 1f);
                }

                if (this.chargeTime == 20) {
                    Vec3 vec3 = this.netherman.getViewVector(1.0F);
                    double d2 = livingentity.getX() - (this.netherman.getX() + vec3.x * 4.0D);
                    double d3 = livingentity.getY(0.5D) - (0.5D + this.netherman.getY(0.5D));
                    double d4 = livingentity.getZ() - (this.netherman.getZ() + vec3.z * 4.0D);

                    NethermanTeleportChargeEntity nethermanTeleportChargeEntity = new NethermanTeleportChargeEntity(level, this.netherman, d2, d3, d4, this.netherman.getExplosionPower());
                    nethermanTeleportChargeEntity.setPos(this.netherman.getX() + vec3.x, this.netherman.getEyeY(), nethermanTeleportChargeEntity.getZ() + vec3.z);

                    if (!this.netherman.isSilent()) {
                        level.playSound(null, this.netherman.getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1f, 1f);
                    }

                    level.addFreshEntity(nethermanTeleportChargeEntity);
                    this.chargeTime = -40;
                }
            } else if (this.chargeTime > 0) {
                --this.chargeTime;
            }

            this.netherman.setCharging(this.chargeTime > 1000);
        }
    }
}
