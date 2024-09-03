package net.xylonity.common.entity.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.xylonity.common.entity.entities.ai.NearestAttackableTargetGoal;
import net.xylonity.config.values.KQConfigValues;
import net.xylonity.registry.KnightQuestEntities;
import net.xylonity.registry.KnightQuestParticles;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;
import java.util.Random;

public class GremlinEntity extends HostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Boolean> IS_PASSIVE = DataTracker.registerData(GremlinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> SHOULD_TAKE_COIN = DataTracker.registerData(GremlinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> GOLD_VARIATION = DataTracker.registerData(GremlinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> PHASE = DataTracker.registerData(GremlinEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> ATTACK = DataTracker.registerData(GremlinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int tickCounterShield = 0;
    private int tickCounter = 0;
    private boolean isHalfHealth;

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_PASSIVE, false);
        builder.add(SHOULD_TAKE_COIN, false);
        builder.add(GOLD_VARIATION, 0);
        builder.add(PHASE, 1);
        builder.add(ATTACK, false);
    }

    public boolean getIsPassive() { return this.dataTracker.get(IS_PASSIVE); }
    public boolean getShouldTakeCoin() { return this.dataTracker.get(SHOULD_TAKE_COIN); }
    public int getGoldVariation() { return this.dataTracker.get(GOLD_VARIATION); }
    public int getPhase() { return this.dataTracker.get(PHASE); }
    public boolean getAttack() {
        return this.dataTracker.get(ATTACK);
    }

    public void setIsPassive(boolean isPassive) { this.dataTracker.set(IS_PASSIVE, isPassive); }
    public void setShouldTakeCoin(boolean shouldTakeCoin) { this.dataTracker.set(SHOULD_TAKE_COIN, shouldTakeCoin); }
    public void setGoldVariation(int goldVariation) { this.dataTracker.set(GOLD_VARIATION, goldVariation); }
    public void setPhase(int phase) { this.dataTracker.set(PHASE, phase); }
    public void setAttack(boolean attack) { this.dataTracker.set(ATTACK, attack); }

    public GremlinEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        if (!getWorld().isClient()) {
            boolean attack1 = new Random().nextBoolean();
            this.dataTracker.set(ATTACK, attack1);
        }
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 35.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.63f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0);
    }

    private void updateAttributes() {
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue(this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * KQConfigValues.MULTIPLIER_GREMLIN_ATTACK_DAMAGE);
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).setBaseValue(this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * KQConfigValues.MULTIPLIER_GREMLIN_MOVEMENT_SPEED);
        Objects.requireNonNull(this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED)).setBaseValue(this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * KQConfigValues.MULTIPLIER_GREMLIN_ATTACK_SPEED);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.5, true));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new TemptGoal(this, 0.5, Ingredient.ofItems(Items.WHEAT), false));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.5));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "deadcontroller", 0, this::deadPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "coincontroller", 0, this::coinPredicate));
    }

    private PlayState coinPredicate(AnimationState<?> event) {

        if (getIsPassive() && getShouldTakeCoin()) {
            event.getController().forceAnimationReset();
            String goldVariation = getGoldVariation() == 0 ? "gold" : "gold2";
            event.getController().setAnimation(RawAnimation.begin().then(goldVariation, Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState deadPredicate(AnimationState<?> event) {

        if (this.isDead()) {
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationState<?> event) {

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
    public void tick() {
        super.tick();
        if (this.getHealth() < getMaxHealth() * 0.5) {

            if (!this.isHalfHealth) {
                this.getWorld().addParticle(KnightQuestParticles.GREMLIN_PARTICLE, this.getX(), getY() - 0.48, getZ(), 2d, 0d, 0d);
                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.HOSTILE, 1.0F, 1.0F);
                this.isHalfHealth = true;

                if (getAttack())
                    this.spawnShield();
                else
                    this.updateAttributes();

                setPhase(2);
            }

            if (getAttack()) {
                tickCounterShield++;
                if (tickCounterShield % 22 == 0 && tickCounterShield / 25 <= 1) {
                    spawnShield();
                }
            }

        }

        if (getIsPassive()) {
            tickCounter++;

            if (tickCounter == 1) {
                setShouldTakeCoin(true);
            } else if (tickCounter == 3) {
                setShouldTakeCoin(false);
            } else if (tickCounter == 80) {
                setIsPassive(false);
                setShouldTakeCoin(false);
                tickCounter = 0;
            }
        }
    }

    private void spawnShield() {
        GhastlingEntity entity = KnightQuestEntities.SHIELD.create(getWorld());
        if (entity != null) {
            entity.refreshPositionAndAngles(this.getSteppingPos(), 1.0F, 0.0F);
            getWorld().spawnEntity(entity);
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {

        if (KQConfigValues.CAN_TAKE_GOLD_GREMLIN) {

            ItemStack itemstack = player.getStackInHand(hand);
            Item desiredItem = Items.GOLD_INGOT;
            Item item = itemstack.getItem();

            if (item.equals(desiredItem)) {
                this.setTarget(null);
                this.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
                this.getBrain().forget(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
                this.setAttacking(null);
                this.setAttacking(false);
                this.setAttacker(null);
                this.attackingPlayer = null;
                setGoldVariation(getRandom().nextInt());
                this.setIsPassive(true);
            }

        }

        return super.interactMob(player, hand);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (tickCounter != 0)
            return false;
        else
            return super.damage(source, amount);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_VEX_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VEX_DEATH;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }

}