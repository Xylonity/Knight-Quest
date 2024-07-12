package net.xylonity.common.entity.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.xylonity.registry.KnightQuestParticles;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class GhostyEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final World serverWorld;
    private static final double ANGULAR_SPEED = 0.1;
    private double angle;
    private double baseY;
    PlayerEntity target;

    public GhostyEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.serverWorld = world;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.63f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 20F));
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
        if (pSource.getAttacker() instanceof HostileEntity || pSource.getAttacker() instanceof PlayerEntity || pSource.equals(getDamageSources().onFire())) {
            serverWorld.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 1.2d, 0d, 0d);
            serverWorld.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_FOX_SCREECH, SoundCategory.HOSTILE, 1.0F, 1.0F);

            this.remove(RemovalReason.KILLED);
        }

        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (isAffectedByDaylight()) {
            this.setOnFireFor(2);
        }

        if (age % 15 == 0) {
            List<PlayerEntity> nearbyMonsters = serverWorld.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(1), PlayerEntity::isPlayer);
            for (PlayerEntity player : nearbyMonsters) {
                if (!player.isCreative()) {
                    player.damage(getDamageSources().generic(), 1);
                    Vec3d direction = player.getPos().subtract(this.getPos()).normalize().multiply(0.1);
                    player.addVelocity(direction.x, direction.y + 0.5, direction.z);
                }
            }
        }

        List<HostileEntity> nearbyMonsters = serverWorld.getEntitiesByClass(HostileEntity.class, this.getBoundingBox().expand(7), Entity::isLiving);
        for (HostileEntity monster : nearbyMonsters) {
            monster.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1, 4, false, false, false));
            if (!(monster instanceof GhostyEntity))
                for (int i = 0; i < 4 && age % 25 == 0; ++i) {
                    serverWorld.addParticle(KnightQuestParticles.STARSET_PARTICLE, monster.getX(), monster.getY() - 0.48, monster.getZ(), 1.2d, 0d, 0d);
                }
        }

        if (baseY == 0 && getY() != 0) {
            baseY = getY();
        }

        this.target = this.serverWorld.getClosestPlayer(this, 20.0);

        double offsetY = Math.sin(angle * 2) * 0.1;
        double currentX = this.getX();
        double currentY = this.getY();
        double currentZ = this.getZ();

        if (target != null && !target.isCreative()) {

            this.lookAtEntity(target, 360, 360);

            double speed = 0.1;
            double dx = target.getX() - currentX;
            double dy = target.getY() + 0.5 - currentY;
            double dz = target.getZ() - currentZ;
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance > 1.0) {
                double motionX = dx / distance * speed;
                double motionY = dy / distance * speed;
                double motionZ = dz / distance * speed;

                currentX += motionX;
                currentY += motionY + offsetY;
                currentZ += motionZ;
            } else {
                currentY += offsetY;
            }
        } else {
            currentY += offsetY;
        }

        this.setPos(currentX, currentY, currentZ);

        angle += ANGULAR_SPEED;
        if (angle >= Math.PI * 2) {
            angle -= Math.PI * 2;
        }

        DefaultParticleType particleType = KnightQuestParticles.GHOSTY_PARTICLE;
        for (int i = 0; i < 1 && age % 15 == 0; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.serverWorld.addParticle(particleType,
                    this.getX() + (this.random.nextFloat() * this.getWidth() * 1.0F) - this.getWidth(),
                    this.getY() + (this.random.nextFloat() * this.getHeight()),
                    this.getZ() + (this.random.nextFloat() * this.getWidth() * 1.0F) - this.getWidth(),
                    d0, d1, d2);
        }
    }

    @Override public boolean isFallFlying() {
        return super.isFallFlying();
    }
    @Override public boolean isOnFire() {
        return false;
    }
    @Override public boolean isInLava() {
        return false;
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
    @Override protected void playHurtSound(DamageSource pSource) {}
    @Override protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return null;
    }
    @Override public boolean collidesWith(Entity other) {
        return false;
    }
    @Override public boolean isCollidable() {
        return false;
    }
    @Override public boolean hasNoGravity() {
        return true;
    }
    @Override protected void pushAway(Entity entity) {}
    @Override protected void pushOutOfBlocks(double x, double y, double z) {}
}
