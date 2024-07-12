package net.xylonity.common.entity.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
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
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class RatmanEntity extends SkeletonEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    World serverWorld;
    private boolean summoned = false;
    private int counter = 0;
    private int arrowRotation = 50;
    private static final TrackedData<Boolean> ATTACK1 = DataTracker.registerData(RatmanEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACK1, false);
    }

    public boolean getAttack1() {
        return this.dataTracker.get(ATTACK1);
    }

    public void setAttack1(boolean invulnerable) {
        this.dataTracker.set(ATTACK1, invulnerable);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound pCompound) {
        super.writeCustomDataToNbt(pCompound);
        pCompound.putBoolean("attack1", this.getAttack1());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound compound) {
        super.readCustomDataFromNbt(compound);
        this.setAttack1(compound.getBoolean("attack1"));
    }

    public RatmanEntity(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
        this.serverWorld = world;
        if (!serverWorld.isClient()) {
            boolean attack1 = new Random().nextBoolean();
            this.setAttack1(attack1);
        }
    }

    private void shootArrow(int angle) {

        double arrowX = Math.cos(Math.toRadians(angle));
        double arrowY = getEyeY();
        double arrowZ = Math.sin(Math.toRadians(angle));

        ArrowEntity arrow = new ArrowEntity(serverWorld, this);
        arrow.setPos(getX() + arrowX, arrowY - 1.5, getZ() + arrowZ);
        arrow.setShotFromCrossbow(false);

        double velX = Math.cos(Math.toRadians(angle));
        double velZ = Math.sin(Math.toRadians(angle));
        arrow.setVelocity(velX, 0.3, velZ);

        arrow.addEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));
        serverWorld.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.HOSTILE, 0.75F, 1.0F);

        serverWorld.spawnEntity(arrow);

    }

    private void smokeBomb() {

        double radius = 2.5;
        serverWorld.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_SPLASH_POTION_BREAK, SoundCategory.HOSTILE, 1.0F, 1.0F);

        for (int i = 0; i < 450; i++) {
            double u = random.nextDouble();
            double v = random.nextDouble();
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);

            double scale = Math.cbrt(random.nextDouble());

            double x = getX() + (radius * scale * Math.sin(phi) * Math.cos(theta));
            double y = getY() + (radius * scale * Math.sin(phi) * Math.sin(theta));
            double z = getZ() + (radius * scale * Math.cos(phi));

            serverWorld.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, x, y, z, 0, 0.0, 0.0);
        }

        this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100, 1));

    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
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

    @Override
    protected void initGoals() {

        this.goalSelector.add(1, new LookAroundGoal(this));
        this.goalSelector.add(2, new BowAttackGoal<>(this, 0.6D, 10, 15.0f));
        this.goalSelector.add(3, new EscapeSunlightGoal(this, 0.6D));
        this.goalSelector.add(4, new SwimGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController(this, "attackcontroller", 0, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState event) {

        if (isUsingItem()) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
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
        return SoundEvents.ENTITY_TURTLE_SWIM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_FOX_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FOX_AMBIENT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }
}
