package net.xylonity.common.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.xylonity.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.registry.KnightQuestEntities;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class NethermanTeleportChargeEntity extends AbstractNethermanProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean hasCollided = false;
    private int explosionPower = 1;
    private int ticksUntilDiscard = 0;
    private float rotationAngle = 0.0F;

    public NethermanTeleportChargeEntity(EntityType<? extends AbstractNethermanProjectile> entityType, World world) {
        super(entityType, world);
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public NethermanTeleportChargeEntity(World world, LivingEntity owner, double velocityX, double velocityY, double velocityZ, int explosionPower) {
        super(KnightQuestEntities.NETHERMAN_TELEPORT_CHARGE, owner, velocityX, velocityY, velocityZ, world);
        this.explosionPower = explosionPower;
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (hitResult instanceof EntityHitResult entityHitResult && age < 10) {
            Entity hitEntity = entityHitResult.getEntity();

            if (hitEntity instanceof NethermanEntity) return;
        }

        if (!getWorld().isClient && !hasCollided) {
            getWorld().playSound(null, getSteppingPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
            hasCollided = true;
        }

        if (getWorld().isClient && !hasCollided) {
            this.createCustomExplosionParticles();
        }

        this.explode();

    }

    private void explode() {
        if (!this.getWorld().isClient) {

            this.getWorld().createExplosion(this, getDamageSources().generic(), null, this.getX(), this.getY(), this.getZ(), 2F, false, World.ExplosionSourceType.NONE, false, ParticleTypes.SMOKE, ParticleTypes.SMOKE, SoundEvents.BLOCK_NOTE_BLOCK_BELL);

            this.getWorld().getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(4.0), k -> true).forEach(player -> {
                double randomX = this.getX() + (this.random.nextDouble() - 0.5) * 10;
                double randomZ = this.getZ() + (this.random.nextDouble() - 0.5) * 10;
                player.requestTeleport(randomX, player.getY() + new Random().nextInt(15, 25), randomZ);
            });

            ticksUntilDiscard = 2;
        }
    }

    private void createCustomExplosionParticles() {
        ExplosiveConfig.spawnParticles(getWorld(), getX(), getY(), getZ(), 3, false, false, 2);
        hasCollided = !hasCollided;
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksUntilDiscard > 0) {
            ticksUntilDiscard--;
            if (ticksUntilDiscard <= 0) {
                this.discard();
            }
        }

        rotationAngle += 6.0F;
        if (rotationAngle >= 360.0F) {
            rotationAngle -= 360.0F;
        }

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("ExplosionPower", 99)) {
            this.explosionPower = nbt.getByte("ExplosionPower");
        }

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<?> event) {

        event.getController().setAnimation(RawAnimation.begin().then("default", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
