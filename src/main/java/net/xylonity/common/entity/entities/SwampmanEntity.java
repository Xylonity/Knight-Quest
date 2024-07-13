package net.xylonity.common.entity.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SwampmanEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SwampmanEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 22.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 0.7D, false));
        this.goalSelector.add(3, new SwimGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 0.7D, false));

        this.targetSelector.add(6, new WanderNearTargetGoal(this, 0.7D, 15));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState event) {

        if (this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        if (this.dead) {
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_AXOLOTL_SWIM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAMEL_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAMEL_DEATH;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }
}
