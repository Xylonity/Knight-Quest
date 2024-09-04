package net.xylonity.knightquest.common.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import org.jetbrains.annotations.NotNull;
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

public class NethermanTeleportChargeEntity extends AbstractNethermanProjectile implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private int explosionPower = 1;
    private boolean hasCollided = false;
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

       if (pResult instanceof EntityHitResult entityHitResult && tickCount < 10) {
           Entity hitEntity = entityHitResult.getEntity();

           if (hitEntity instanceof NethermanEntity) return;
       }

       if (!level.isClientSide && !hasCollided) {
           level.playSound(null, getOnPos(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
           hasCollided = true;
       }

       if (level.isClientSide && !hasCollided) {
           this.createCustomExplosionParticles();
       }

       this.explode();
   }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putByte("ExplosionPower", (byte)this.explosionPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("ExplosionPower", 99)) {
            this.explosionPower = pCompound.getByte("ExplosionPower");
        }
    }

    private void explode() {
        if (!this.level.isClientSide) {

            //this.level.explode(this, DamageSource.GENERIC, null, this.getX(), this.getY(), this.getZ(), 2F, false, Explosion.BlockInteraction.NONE);
            Explosion explosion = new Explosion(level, this, getX(), getY(), getZ(), 2F, false, Explosion.BlockInteraction.NONE);
            explosion.explode();
            explosion.finalizeExplosion(false);

            this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(4.0)).forEach(player -> {
                double randomX = this.getX() + (this.random.nextDouble() - 0.5) * 10;
                double randomZ = this.getZ() + (this.random.nextDouble() - 0.5) * 10;
                player.teleportTo(randomX, player.getY() + new Random().nextInt(15, 25), randomZ);
            });

            ticksUntilDiscard = 2;
        }
    }

    private void createCustomExplosionParticles() {
        ExplosiveConfig.spawnParticles(level, getX(), getY(), getZ(), 3, false, false, 2);
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
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return (Packet<ClientGamePacketListener>) NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationEvent<?> event) {

        event.getController().setAnimation((new AnimationBuilder()).addAnimation("default", ILoopType.EDefaultLoopTypes.LOOP));

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

}
