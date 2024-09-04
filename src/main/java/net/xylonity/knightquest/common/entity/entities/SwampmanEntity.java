package net.xylonity.knightquest.common.entity.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.xylonity.knightquest.common.entity.entities.ai.RangedAttackGoal;
import net.xylonity.knightquest.config.values.KQConfigValues;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SwampmanEntity  extends Monster implements IAnimatable, RangedAttackMob {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(SwampmanEntity.class, EntityDataSerializers.INT);
    private boolean isHalfHealth;

    public SwampmanEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.initEquipment();
    }

    @Override
    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    private void initEquipment() {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PHASE, 1);
    }

    public int getPhase() { return this.entityData.get(PHASE); }

    public void setPhase(int phase) { this.entityData.set(PHASE, phase); }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 22.0D)
                .add(Attributes.ATTACK_DAMAGE, 4f)
                .add(Attributes.ATTACK_SPEED, 0.8f)
                .add(Attributes.MOVEMENT_SPEED, 0.5f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal<>(this, 0.7D, 10, 15.0f));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < getMaxHealth() * 0.5) {

            if (!this.isHalfHealth && KQConfigValues.CAN_CHANGE_PHASE_SWAMPMAN) {
                this.level.addParticle(KnightQuestParticles.BLUEBLASTWAVE.get(), this.getX(), getY() - 0.48, getZ(), 2d, 0d, 0d);
                this.level.playSound(null, this.blockPosition(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.HOSTILE, 1.0F, 1.0F);
                this.isHalfHealth = true;

                setPhase(2);
            }
        }
        if (this.getPhase() == 2 && tickCount % 20 == 0) {
            this.heal(KQConfigValues.PHASE_2_HEALING_SWAMPMAN);
        }
    }

    @Override
    public int getMaxAirSupply() {
        return 1000;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {

        if (this.isUsingItem() && this.getMainHandItem().getItem() instanceof ProjectileWeaponItem) {
            event.getController().markNeedsReload();
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("bow_attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        } else if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        if (this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.AXOLOTL_SWIM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.BLAZE_HURT;
    }

    @Override
    public void performRangedAttack(LivingEntity livingEntity, float v) {
        SwampmanAxeEntity swampmanAxeEntity = new SwampmanAxeEntity(level, this);
        double d0 = livingEntity.getX() - this.getX();
        double d1 = livingEntity.getY(0.34D) - swampmanAxeEntity.getY();
        double d2 = livingEntity.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        swampmanAxeEntity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.3F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        if (KQConfigValues.POISON_PHASE_2_SWAMPMAN)
            swampmanAxeEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, true, true, true));
        this.level.addFreshEntity(swampmanAxeEntity);
    }
}
