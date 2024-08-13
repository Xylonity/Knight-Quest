package net.xylonity.common.entity.boss.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.xylonity.common.entity.boss.NethermanEntity;
import net.xylonity.common.entity.boss.NethermanTeleportChargeEntity;

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

    public boolean canStart() {
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

    //@Override public boolean shouldRunEveryTick() { return true; }
    public boolean shouldRunEveryTick() { return true; }

    /**
     * Keep ticking a continuous task that has already been started
     */

    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null && this.netherman.getPhase() == 3) {
            if (livingentity.distanceTo(this.netherman) < 4096.0D && this.netherman.canSee(livingentity)) {
                World level = this.netherman.getWorld();

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (this.chargeTime == 30) {
                    this.netherman.setNoMovement(true);
                    this.netherman.setIsAttacking(true);
                }

                if (this.chargeTime == 20 && !this.netherman.isSilent()) {
                    level.playSound(null, this.netherman.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_TWINKLE, SoundCategory.BLOCKS, 1f, 1f);
                }

                if (this.chargeTime == 10) {
                    Vec3d vec3 = this.netherman.getCameraPosVec(1.0F);
                    double d2 = livingentity.getX() - (this.netherman.getX() + vec3.x * 4.0D);
                    double d3 = livingentity.getYaw(0.5F) - (0.5D + this.netherman.getYaw(0.5F));
                    double d4 = livingentity.getZ() - (this.netherman.getZ() + vec3.z * 4.0D);

                    NethermanTeleportChargeEntity nethermanTeleportChargeEntity = new NethermanTeleportChargeEntity(level, this.netherman, d2, d3, d4, this.netherman.getExplosionPower());
                    nethermanTeleportChargeEntity.setPos(this.netherman.getX() + vec3.x, this.netherman.getEyeY() + 1.5, nethermanTeleportChargeEntity.getZ() + vec3.z);

                    if (!this.netherman.isSilent()) {
                        level.playSound(null, this.netherman.getBlockPos(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1f, 1f);
                    }

                    level.spawnEntity(nethermanTeleportChargeEntity);
                }

                if (this.chargeTime == 0) {
                    this.netherman.setNoMovement(false);
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