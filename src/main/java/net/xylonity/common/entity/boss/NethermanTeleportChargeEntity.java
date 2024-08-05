package net.xylonity.common.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.xylonity.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.common.api.util.ParticleGenerator;
import net.xylonity.common.api.util.TeleportValidator;
import net.xylonity.common.entity.boss.ai.*;
import net.xylonity.registry.KnightQuestEntities;
import net.xylonity.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NethermanTeleportChargeEntity extends AbstractNethermanProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int explosionPower = 1;
    private boolean hasCollided = false;
    private int ticksUntilDiscard = 0;
    private float rotationAngle = 0.0F;

    public NethermanTeleportChargeEntity(EntityType<? extends AbstractNethermanProjectile> entityEntityType, World world) {
        super(entityEntityType, world);
        this.setNoGravity(true);
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public NethermanTeleportChargeEntity(World pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, int pExplosionPower) {
        super(KnightQuestEntities.NETHERMAN_TELEPORT_CHARGE, pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.explosionPower = pExplosionPower;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity hitEntity = entityHitResult.getEntity();

        if (hitEntity instanceof NethermanEntity) return;

        if (!getWorld().isClient && !hasCollided) {
            getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
            hasCollided = true;
        }

        if (getWorld().isClient && !hasCollided) {
            this.createCustomExplosionParticles();
        }

        this.explode();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if (!getWorld().isClient && !hasCollided) {
            getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
            hasCollided = true;
        }

        if (getWorld().isClient && !hasCollided) {
            this.createCustomExplosionParticles();
        }

        this.explode();
    }

    //public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
    //    super.addAdditionalSaveData(pCompound);
    //    pCompound.putByte("ExplosionPower", (byte)this.explosionPower);
    //}

    ///**
    // * (abstract) Protected helper method to read subclass entity data from NBT.
    // */

    //public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
    //    super.readAdditionalSaveData(pCompound);
    //    if (pCompound.contains("ExplosionPower", 99)) {
    //        this.explosionPower = pCompound.getByte("ExplosionPower");
    //    }
    //}

    private void explode() {
        if (!this.getWorld().isClient) {

            this.getWorld().createExplosion(this, getDamageSources().generic(), null, this.getX(), this.getY(), this.getZ(), 2F, false, World.ExplosionSourceType.NONE, false, ParticleTypes.SMOKE, ParticleTypes.SMOKE, SoundEvents.BLOCK_NOTE_BLOCK_BELL);

            this.getWorld().getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(4.0), k -> true).forEach(player -> {
                double randomX = this.getX() + (this.random.nextDouble() - 0.5) * 10;
                double randomZ = this.getZ() + (this.random.nextDouble() - 0.5) * 10;
                player.teleport(randomX, player.getY() + new Random().nextInt(15, 25), randomZ, false);
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
