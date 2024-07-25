package net.xylonity.knightquest.common.entity.boss;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NethermanTeleportChargeEntity extends AbstractNethermanProjectile implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final double VELOCITY = 0.4;
    private Vec3 targetPosition;
    private int explosionPower = 1;
    private boolean hasCollided = false;
    private boolean hasCheckedCollision = false;
    private int ticksUntilDiscard = 0;

    private float rotationAngle = 0.0F;

    public NethermanTeleportChargeEntity(EntityType<? extends AbstractNethermanProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public float getRotationAngle() {
        return rotationAngle;
    }

    public NethermanTeleportChargeEntity(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, int pExplosionPower) {
        super(KnightQuestEntities.NETHERMAN_TELEPORT_CHARGE.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.explosionPower = pExplosionPower;
    }

   @Override
   protected void onHit(@NotNull HitResult pResult) {
       super.onHit(pResult);

       if (level().isClientSide && !hasCollided)
           this.createCustomExplosionParticles();

       this.explode();
   }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("ExplosionPower", 99)) {
            this.explosionPower = pCompound.getByte("ExplosionPower");
        }

    }

    private void explode() {
        if (!this.level().isClientSide) {

            //this.level().explode(this, this.getX(), this.getY(), this.getZ(), 5F, Level.ExplosionInteraction.MOB);
            this.level().explode(this, damageSources().generic(), null, this.getX(), this.getY(), this.getZ(), 3F, false, Level.ExplosionInteraction.NONE, false);

            //Explosion explosion = new Explosion(this.level(), this, null, null, this.getX(), this.getY(), this.getZ(), 5F, false, Explosion.BlockInteraction.KEEP);
            //explosion.explode();
            //explosion.finalizeExplosion(true);

            this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(4.0)).forEach(player -> {
                double randomX = this.getX() + (this.random.nextDouble() - 0.5) * 40;
                double randomZ = this.getZ() + (this.random.nextDouble() - 0.5) * 40;
                player.teleportTo(randomX, player.getY(), randomZ);
            });

            ticksUntilDiscard = 2;
        }
    }

    private void createCustomExplosionParticles() {
        level().playSound(null, getOnPos(), SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.BLOCKS, 1f, 1f);
        this.level().addParticle(KnightQuestParticles.GREMLIN_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 3, 0, 0);
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

    //@Override
    //public void tick() {
    //    super.tick();
    //    if (this.level().isClientSide) {
    //        this.level().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, this.getX(), this.getY() + 0.2, this.getZ(), 0, 0, 0);
    //    } else {
    //        Vec3 movement = this.getDeltaMovement();
    //        this.targetPosition = this.targetPosition.add(movement.normalize().scale(VELOCITY));

    //        // Interpolación lineal hacia la posición objetivo
    //        Vec3 currentPosition = this.position();
    //        Vec3 newPos = currentPosition.lerp(targetPosition, 0.1);

    //        this.setPos(newPos.x, newPos.y, newPos.z);

    //        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
    //        if (hitResult.getType() != HitResult.Type.MISS) {
    //            this.onHit(hitResult);
    //        }

    //        this.checkInsideBlocks();
    //    }
    //}

    @Override
    protected void defineSynchedData() { ;; }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<?> event) {

        event.getController().setAnimation(RawAnimation.begin().then("default", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
