package net.xylonity.common.entity.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.xylonity.common.entity.entities.ai.RangedAttackGoal;
import net.xylonity.config.values.KQConfigValues;
import net.xylonity.registry.KnightQuestParticles;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SwampmanEntity extends HostileEntity implements GeoEntity, RangedAttackMob {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Integer> PHASE = DataTracker.registerData(SwampmanEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private boolean isHalfHealth;

    public SwampmanEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.initEquipment();
    }

    private void initEquipment() {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6f);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(PHASE, 1);
    }

    public int getPhase() { return this.dataTracker.get(PHASE); }

    public void setPhase(int phase) { this.dataTracker.set(PHASE, phase); }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new RangedAttackGoal<>(this, 0.7D, 10, 15.0f));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.add(3, new WanderAroundGoal(this, 0.6D));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < getMaxHealth() * 0.5) {

            if (!this.isHalfHealth && KQConfigValues.CAN_CHANGE_PHASE_SWAMPMAN) {
                this.getWorld().addParticle(KnightQuestParticles.BLUEBLASTWAVE, this.getX(), getY() - 0.48, getZ(), 2d, 0d, 0d);
                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.HOSTILE, 1.0F, 1.0F);
                this.isHalfHealth = true;

                setPhase(2);
            }
        }
        if (this.getPhase() == 2 && age % 20 == 0) {
            this.heal(KQConfigValues.PHASE_2_HEALING_SWAMPMAN);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (isUsingItem() && this.getMainHandStack().getItem() instanceof RangedWeaponItem) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
        } else if (this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
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

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        SwampmanAxeEntity swampmanAxeEntity = new SwampmanAxeEntity(getWorld(), this);
        double d0 = target.getX() - this.getX();
        double d1 = target.getY() + (target.getHeight() * 0.34D) - swampmanAxeEntity.getY();
        double d2 = target.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        swampmanAxeEntity.setVelocity(d0, d1 + d3 * (double)0.2F, d2, 1.3F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(swampmanAxeEntity);
    }
}
