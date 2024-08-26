package net.xylonity.knightquest.common.entity.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;

public class GremlinEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> IS_PASSIVE = SynchedEntityData.defineId(GremlinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOULD_TAKE_COIN = SynchedEntityData.defineId(GremlinEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> GOLD_VARIATION = SynchedEntityData.defineId(GremlinEntity.class, EntityDataSerializers.INT);
    private boolean isHalfHealth;
    private int tickCounter = 0;

    public GremlinEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_PASSIVE, false);
        this.entityData.define(SHOULD_TAKE_COIN, false);
        this.entityData.define(GOLD_VARIATION, 0);
    }

    public boolean getIsPassive() {
        return this.entityData.get(IS_PASSIVE);
    }
    public boolean getShouldTakeCoin() { return this.entityData.get(SHOULD_TAKE_COIN); }
    public int getGoldVariation() { return this.entityData.get(GOLD_VARIATION); }

    public void setIsPassive(boolean isPassive) {
        this.entityData.set(IS_PASSIVE, isPassive);
    }
    public void setShouldTakeCoin(boolean shouldTakeCoin) { this.entityData.set(SHOULD_TAKE_COIN, shouldTakeCoin);}
    public void setGoldVariation(int goldVariation) { this.entityData.set(GOLD_VARIATION, goldVariation);}

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 35.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.63f)
                .add(Attributes.FOLLOW_RANGE, 35.0).build();
    }

    private void updateAttributes() {
        Objects.requireNonNull(getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(7f);
        Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.75f);
        Objects.requireNonNull(getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(1.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5f, true));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 0.5f, Ingredient.of(Items.WHEAT), false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.5f));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
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

        if (this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < getMaxHealth() * 0.5) {

            if (!this.isHalfHealth) {
                this.level().addParticle(KnightQuestParticles.GREMLIN_PARTICLE.get(), this.getX(), getY() - 0.48, getZ(), 2d, 0d, 0d);
                this.level().playSound(null, this.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.0F, 1.0F);
                this.isHalfHealth = true;
                this.updateAttributes();
            }
        }

        if (getIsPassive()) {
            tickCounter++;

            if (tickCounter == 1) {
                setShouldTakeCoin(true);
                this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            } else if (tickCounter == 3) {
                setShouldTakeCoin(false);
            } else if (tickCounter == 80) {
                setIsPassive(false);
                setShouldTakeCoin(false);
                tickCounter = 0;
                this.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
            }
        }

    }

    @Override
    protected @NotNull InteractionResult mobInteract(Player pPlayer, @NotNull InteractionHand pHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item desiredItem = Items.GOLD_INGOT;
        Item item = itemstack.getItem();

        if (item.equals(desiredItem)) {
            this.setTarget(null);
            setGoldVariation(getRandom().nextInt(0, 2));
            this.setIsPassive(true);
        }

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected @NotNull SoundEvent getSwimSound() {
        return SoundEvents.AXOLOTL_SWIM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VEX_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.VEX_HURT;
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

}
