package net.xylonity.common.entity.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ShieldEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final World serverWorld;
    private static final double RADIUS = 2.0;
    private static final double ANGULAR_SPEED = 0.1;
    private double angle;

    public ShieldEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.serverWorld = world;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.63f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public boolean damage(DamageSource pSource, float pAmount) {
        if (pSource.getAttacker() instanceof PlayerEntity) {
            serverWorld.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 1.2d, 0d, 0d);
            serverWorld.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_VEX_HURT, SoundCategory.HOSTILE, 1.0F, 1.0F);
            serverWorld.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(3), PlayerEntity::isPlayer).forEach(player -> {
                Vec3d direction = player.getPos().subtract(this.getPos()).normalize().multiply(0.4);
                player.addVelocity(direction.x, direction.y + 0.5, direction.z);
            });
            this.remove(RemovalReason.KILLED);
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        GremlinEntity owner = findClosestGremlin();

        if (owner == null || owner.isDead()) {
            this.remove(RemovalReason.KILLED);
            return;
        }

        Vec3d ownerPos = owner.getPos();
        double offsetX = Math.cos(angle) * RADIUS;
        double offsetZ = Math.sin(angle) * RADIUS;
        double offsetY = Math.sin(angle * 2) * 0.5;
        this.setPos(ownerPos.getX() + offsetX, ownerPos.getY() + 0.5 + offsetY, ownerPos.getZ() + offsetZ);

        angle += ANGULAR_SPEED;
        if (angle >= Math.PI * 2) {
            angle -= Math.PI * 2;
        }

        SimpleParticleType particleType = ParticleTypes.SMOKE;
        for (int i = 0; i < 2; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.serverWorld.addParticle(particleType, this.getX() + (this.random.nextFloat() * this.getWidth() * 2.0F) - this.getWidth(), this.getY() + (this.random.nextFloat() * this.getHeight()), this.getZ() + (this.random.nextFloat() * this.getWidth() * 2.0F) - this.getWidth(), d0, d1, d2);
        }
    }

    private GremlinEntity findClosestGremlin() {
        double closestDistance = Double.MAX_VALUE;
        GremlinEntity closestGremlin = null;

        for (GremlinEntity entity : serverWorld.getEntitiesByClass(GremlinEntity.class, getBoundingBox().expand(RADIUS * 2), Entity::isLiving)) {
            if (entity instanceof GremlinEntity) {
                double distance = distanceTo(entity);
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
    @Nullable @Override protected SoundEvent getAmbientSound() {
        return null;
    }
    @Override public boolean hasNoGravity() {
        return true;
    }
    @Override public boolean collidesWith(Entity other) {
        return false;
    }
    @Override public boolean isCollidable() {
        return false;
    }
    @Override public boolean isOnFire() {
        return false;
    }
    @Override public boolean isInLava() {
        return false;
    }
}
