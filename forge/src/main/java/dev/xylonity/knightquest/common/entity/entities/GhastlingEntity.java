package dev.xylonity.knightquest.common.entity.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Objects;

public class GhastlingEntity extends Monster implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Level serverWorld;
    private static final double RADIUS = 2.0;
    private static final double ANGULAR_SPEED = 0.1;
    private double angle;

    public GhastlingEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.serverWorld = world;
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.63f)
                .add(Attributes.FOLLOW_RANGE, 35.0).build();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        serverWorld.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 1.2d, 0d, 0d);
        level().playSound(null, this.blockPosition(), SoundEvents.VEX_HURT, SoundSource.HOSTILE, 1.0F, 1.0F);

        serverWorld.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(3)).forEach(player -> {
            Vec3 direction = player.position().subtract(this.position()).normalize().scale(0.4);
            player.push(direction.x, direction.y + 0.5, direction.z);
        });

        this.remove(RemovalReason.KILLED);
        return false;
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }


    @Override
    public void tick() {
        super.tick();

        GremlinEntity owner = findClosestGremlin();

        if (owner == null || owner.isDeadOrDying() || owner.getPhase() == 1) {
            this.remove(RemovalReason.KILLED);
            return;
        }

        Vec3 ownerPos = owner.position();
        double offsetX = Math.cos(angle) * RADIUS;
        double offsetZ = Math.sin(angle) * RADIUS;
        double offsetY = Math.sin(angle * 2) * 0.5;
        this.setPos(ownerPos.x() + offsetX, ownerPos.y() + 0.5 + offsetY, ownerPos.z() + offsetZ);
        if (this.level().getNearestPlayer(this, -1.0) != null)
            this.lookAt(Objects.requireNonNull(this.level().getNearestPlayer(this, -1.0)), 360, 360);

        angle += ANGULAR_SPEED;
        if (angle >= Math.PI * 2) {
            angle -= Math.PI * 2;
        }

        ParticleOptions particleType = ParticleTypes.SMOKE;
        for (int i = 0; i < 2; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level().addParticle(particleType, this.getX() + (this.random.nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(), this.getY() + (this.random.nextFloat() * this.getBbHeight()), this.getZ() + (this.random.nextFloat() * this.getBbWidth() * 2.0F) - this.getBbWidth(), d0, d1, d2);
        }
    }

    @Override protected void pushEntities() {}
    @Override public void push(Entity pEntity) {}
    @Override public boolean isOnFire() {
        return false;
    }
    @Override public boolean isInLava() {
        return false;
    }
    @Override public boolean isInWater() {
        return false;
    }
    @Override public boolean isInWall() {
        return false;
    }
    @Override protected void spawnSprintParticle() {}


    private GremlinEntity findClosestGremlin() {
        double closestDistance = Double.MAX_VALUE;
        GremlinEntity closestGremlin = null;

        for (GremlinEntity entity : level().getEntitiesOfClass(GremlinEntity.class, getBoundingBox().inflate(RADIUS * 2))) {
            if (entity instanceof GremlinEntity) {
                double distance = distanceToSqr(entity);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestGremlin = entity;
                }
            }
        }

        return closestGremlin;
    }

    @Override public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    @Override protected SoundEvent getSwimSound() {
        return null;
    }
    @Override protected SoundEvent getDeathSound() {
        return null;
    }
    @Override public void playSound(SoundEvent pSound) {}
    @Override public void playSound(SoundEvent pSound, float pVolume, float pPitch) {}
    @Override public boolean isNoGravity() {
        return true;
    }
    @Nullable @Override protected SoundEvent getAmbientSound() {
        return null;
    }
    @Override protected void playHurtSound(DamageSource pSource) { ;; }
    @Override protected SoundEvent getHurtSound(DamageSource pDamageSource) { return null; }
    @Override protected void playStepSound(BlockPos pPos, BlockState pState) { ;; }

}