package net.xylonity.common.entity.entities;

import net.minecraft.entity.EntityType;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.xylonity.registry.KnightQuestParticles;
import net.xylonity.registry.KnightQuestEntities;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GremlinEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean isHalfHealth;
    private final World serverWorld;
    private int a = 0;
    private static final TrackedData<Boolean> INVULNERABLE = DataTracker.registerData(GremlinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(INVULNERABLE, false);
    }

    public boolean isInvulnerable() {
        return this.dataTracker.get(INVULNERABLE);
    }

    public void setInvulnerable(boolean invulnerable) {
        this.dataTracker.set(INVULNERABLE, invulnerable);
    }
    public GremlinEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.serverWorld = world;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 35.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.63f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.7D, false));
        this.goalSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(4, new SwimGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));

        this.targetSelector.add(2, new WanderNearTargetGoal(this, 0.7D, 15));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
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

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < getMaxHealth() * 0.5) {
            if (!this.isHalfHealth) {
                this.serverWorld.addParticle(KnightQuestParticles.GREMLIN_PARTICLE, this.getX(), getY() - 0.48, getZ(), 2d, 0d, 0d);
                this.serverWorld.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.HOSTILE, 1.0F, 1.0F);
                this.isHalfHealth = true;
                spawnShield();
            }

            if (hasShields()) {
                setInvulnerable(true);
            } else {
                setInvulnerable(false);
            }

            a++;
            if (a % 22 == 0 && a / 25 <= 1) {
                spawnShield();
            }
        }
    }

    private void spawnShield() {
        ShieldEntity entity = KnightQuestEntities.SHIELD.create(serverWorld);
        if (entity != null) {
            entity.refreshPositionAndAngles(this.getSteppingPos(), 1.0F, 0.0F);
            serverWorld.spawnEntity(entity);
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (isInvulnerable())
            return false;
        else
            return super.damage(source, amount);
    }

    private boolean hasShields() {
        double closestDistance = Double.MAX_VALUE;
        ShieldEntity closestGremlin = null;

        for (ShieldEntity entity : serverWorld.getNonSpectatingEntities(ShieldEntity.class, getBoundingBox().expand(5))) {
            if (entity instanceof ShieldEntity) {
                double distance = distanceTo(entity);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestGremlin = entity;
                }
            }
        }

        return closestGremlin != null;
    }

}