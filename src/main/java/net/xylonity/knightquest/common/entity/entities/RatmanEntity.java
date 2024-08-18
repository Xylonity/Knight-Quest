package net.xylonity.knightquest.common.entity.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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

import java.util.Random;

public class RatmanEntity extends Skeleton implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final Level serverWorld;
    private boolean summoned = false;
    private int counter = 0;
    private int arrowRotation = 50;
    private static final EntityDataAccessor<Boolean> ATTACK1 = SynchedEntityData.defineId(RatmanEntity.class, EntityDataSerializers.BOOLEAN);

    public RatmanEntity(EntityType<? extends Skeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        serverWorld = pLevel;
        if (!serverWorld.isClientSide()) {
            boolean attack1 = new Random().nextBoolean();
            this.entityData.set(ATTACK1, attack1);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK1, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("attack1", this.getAttack1());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setAttack1(compound.getBoolean("attack1"));
    }

    public boolean getAttack1() {
        return this.entityData.get(ATTACK1);
    }

    public void setAttack1(boolean attack1) {
        this.entityData.set(ATTACK1, attack1);
    }

    public static AttributeSupplier setAttributes() {
        return Skeleton.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 22.0D)
                .add(Attributes.ATTACK_DAMAGE, 4f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.5f).build();
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new RangedBowAttackGoal<>(this, 0.7D, 10, 15.0f));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    @Override
    public boolean canFireProjectileWeapon(@NotNull ProjectileWeaponItem pProjectileWeapon) {
        return pProjectileWeapon == Items.CROSSBOW;
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
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
        return SoundEvents.FOX_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.FOX_HURT;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        if (getAttack1()) {
            if (!summoned && this.getHealth() < this.getMaxHealth() * 0.5) {

                if (counter < 10 && counter % 2 == 0) {
                    for (int i = 0; i < 2; i++) {
                        shootArrow(arrowRotation += (int) (360f / 10));
                    }
                }
                counter++;
                if (counter >= 10) {
                    summoned = true;
                }
            }
        } else {
            if (!summoned && this.getHealth() < this.getMaxHealth() * 0.5) {
                smokeBomb();
                summoned = true;
            }

        }
    }

    private void shootArrow(int angle) {

        double arrowX = Math.cos(Math.toRadians(angle));
        double arrowY = getEyeY();
        double arrowZ = Math.sin(Math.toRadians(angle));

        Arrow arrow = new Arrow(serverWorld, this);
        arrow.setPos(getX() + arrowX, arrowY - 1.5, getZ() + arrowZ);
        arrow.setShotFromCrossbow(false);

        double velX = Math.cos(Math.toRadians(angle));
        double velZ = Math.sin(Math.toRadians(angle));
        arrow.setDeltaMovement(velX, 0.3, velZ);

        arrow.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
        serverWorld.playSound(null, this.blockPosition(), SoundEvents.DISPENSER_LAUNCH, SoundSource.HOSTILE, 0.75F, 1.0F);

        serverWorld.addFreshEntity(arrow);

    }

    private void smokeBomb() {

        double radius = 2.5;
        serverWorld.playSound(null, this.blockPosition(), SoundEvents.SPLASH_POTION_BREAK, SoundSource.HOSTILE, 1.0F, 1.0F);

        for (int i = 0; i < 450; i++) {
            double u = random.nextDouble();
            double v = random.nextDouble();
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);

            double scale = Math.cbrt(random.nextDouble());

            double x = getX() + (radius * scale * Math.sin(phi) * Math.cos(theta));
            double y = getY() + (radius * scale * Math.sin(phi) * Math.sin(theta));
            double z = getZ() + (radius * scale * Math.cos(phi));

            serverWorld.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 0, 0.0, 0.0);
        }

        this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100, 1));

    }



}
