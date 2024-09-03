package net.xylonity.common.entity.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.xylonity.common.entity.entities.SwampmanEntity;

import java.util.EnumSet;

public class RangedAttackGoal<T extends MobEntity & RangedAttackMob> extends Goal {
    private final T mob;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public RangedAttackGoal(T pMob, double pSpeedModifier, int pAttackIntervalMin, float pAttackRadius) {
        this.mob = pMob;
        this.speedModifier = pSpeedModifier;
        this.attackIntervalMin = pAttackIntervalMin;
        this.attackRadiusSqr = pAttackRadius * pAttackRadius;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (this.mob.getTarget() == null || !this.isHoldingRangedWeapon()) {
            return false;
        }

        if (this.mob instanceof SwampmanEntity swampmanEntity) {
            return swampmanEntity.getPhase() != 1;
        }

        return true;
    }

    protected boolean isHoldingRangedWeapon() {
        return this.mob.isHolding(is -> is.getItem() instanceof RangedWeaponItem);
    }

    @Override
    public boolean shouldContinue() {
        return (this.canStart() || !this.mob.getNavigation().isIdle()) && this.isHoldingRangedWeapon();
    }

    @Override
    public void start() {
        super.start();
        this.mob.setAttacking(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.mob.setAttacking(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.mob.clearActiveItem();
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            double distanceToTargetSq = this.mob.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            boolean canSeeTarget = this.mob.getVisibilityCache().canSee(target);
            boolean previouslyCouldSeeTarget = this.seeTime > 0;

            if (canSeeTarget != previouslyCouldSeeTarget) {
                this.seeTime = 0;
            }

            if (canSeeTarget) {
                ++this.seeTime;
            } else {
                --this.seeTime;
            }

            if (!(distanceToTargetSq > this.attackRadiusSqr) && this.seeTime >= 20) {
                this.mob.getNavigation().stop();
                ++this.strafingTime;
            } else {
                this.mob.getNavigation().startMovingTo(target, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if (this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if (this.mob.getRandom().nextFloat() < 0.3D) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (distanceToTargetSq > this.attackRadiusSqr * 0.75F) {
                    this.strafingBackwards = false;
                } else if (distanceToTargetSq < this.attackRadiusSqr * 0.25F) {
                    this.strafingBackwards = true;
                }

                this.mob.getMoveControl().strafeTo(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                Entity vehicle = this.mob.getVehicle();
                if (vehicle instanceof MobEntity) {
                    MobEntity mobEntity = (MobEntity) vehicle;
                    mobEntity.lookAtEntity(target, 30.0F, 30.0F);
                }

                this.mob.lookAtEntity(target, 30.0F, 30.0F);
            } else {
                this.mob.getLookControl().lookAt(target, 30.0F, 30.0F);
            }

            if (this.mob.isUsingItem()) {
                if (!canSeeTarget && this.seeTime < -60) {
                    this.mob.clearActiveItem();
                } else if (canSeeTarget) {
                    int useTime = this.mob.getItemUseTime();
                    if (useTime >= 20) {
                        this.mob.clearActiveItem();
                        this.mob.shootAt(target, this.getPullProgress(useTime));
                        this.attackTime = this.attackIntervalMin;
                    }
                }
            } else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                this.mob.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(this.mob, Items.BOW));
            }

        }
    }

    private float getPullProgress(int useTicks) {
        float f = (float) useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        return Math.min(f, 1.0F);
    }
}
