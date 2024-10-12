package dev.xylonity.knightquest.common.entity.entities;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class AbstractSwampmanAxeEntity extends Projectile {
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractSwampmanAxeEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(AbstractSwampmanAxeEntity.class, EntityDataSerializers.BYTE);
    @Nullable
    private BlockState lastState;
    protected boolean inGround;
    protected int inGroundTime;
    public AbstractArrow.Pickup pickup = AbstractArrow.Pickup.DISALLOWED;
    public int shakeTime;
    private int life;
    private double baseDamage = 2.0D;
    private int knockback;
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    @Nullable
    private IntOpenHashSet piercingIgnoreEntityIds;
    @Nullable
    private List<Entity> piercedAndKilledEntities;

    private final IntOpenHashSet ignoredEntities = new IntOpenHashSet();

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    public void setSoundEvent(SoundEvent pSoundEvent) {
        this.soundEvent = pSoundEvent;
    }

    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double d0 = this.getBoundingBox().getSize() * 10.0D;
        if (Double.isNaN(d0)) {
            d0 = 1.0D;
        }

        d0 *= 64.0D * getViewScale();
        return pDistance < d0 * d0;
    }

    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS, (byte)0);
        this.entityData.define(PIERCE_LEVEL, (byte)0);
    }

    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
        this.life = 0;
    }

    public void lerpTo(double pX, double pY, double pZ, float pYaw, float pPitch, int pPosRotationIncrements, boolean pTeleport) {
        this.setPos(pX, pY, pZ);
        this.setRot(pYaw, pPitch);
    }

    public void lerpMotion(double pX, double pY, double pZ) {
        super.lerpMotion(pX, pY, pZ);
        this.life = 0;
    }

    public void tick() {
        super.tick();
        boolean noClip = this.isNoPhysics();
        Vec3 motion = this.getDeltaMovement();

        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double horizontalDistance = motion.horizontalDistance();
            this.setYRot((float)(Mth.atan2(motion.x, motion.z) * (180F / Math.PI)));
            this.setXRot((float)(Mth.atan2(motion.y, horizontalDistance) * (180F / Math.PI)));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }

        if (!this.inGround) {
            this.xRotO = this.getXRot() + 60;
        }

        BlockPos blockPos = this.blockPosition();
        BlockState blockState = this.level().getBlockState(blockPos);

        if (!blockState.isAir() && !noClip) {
            VoxelShape voxelShape = blockState.getCollisionShape(this.level(), blockPos);
            if (!voxelShape.isEmpty()) {
                Vec3 position = this.position();

                for (AABB aabb : voxelShape.toAabbs()) {
                    if (aabb.move(blockPos).contains(position)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.shakeTime > 0) {
            --this.shakeTime;
        }

        if (this.isInWaterOrRain() || blockState.is(Blocks.POWDER_SNOW)) {
            this.clearFire();
        }

        if (this.inGround && !noClip) {
            if (this.lastState != blockState && this.shouldFall()) {
                this.startFalling();
            } else if (!this.level().isClientSide()) {
                this.tickDespawn();
            }

            ++this.inGroundTime;
        } else {
            this.inGroundTime = 0;
            Vec3 position = this.position();
            Vec3 nextPosition = position.add(motion);
            HitResult hitResult = this.level().clip(new ClipContext(position, nextPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));

            if (hitResult.getType() != HitResult.Type.MISS) {
                nextPosition = hitResult.getLocation();
            }

            while (!this.isRemoved()) {
                EntityHitResult entityHitResult = this.findHitEntity(position, nextPosition);
                if (entityHitResult != null) {
                    hitResult = entityHitResult;
                }

                if (hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult) hitResult).getEntity();
                    Entity owner = this.getOwner();
                    if (entity instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) entity)) {
                        hitResult = null;
                        entityHitResult = null;
                    }
                }

                if (hitResult != null && !noClip) {
                    this.onHit(hitResult);
                    this.hasImpulse = true;
                }

                if (entityHitResult == null || this.getPierceLevel() <= 0) {
                    break;
                }

                hitResult = null;
            }

            motion = this.getDeltaMovement();
            double dX = motion.x;
            double dY = motion.y;
            double dZ = motion.z;

            if (this.isCritArrow()) {
                for (int i = 0; i < 4; ++i) {
                    this.level().addParticle(ParticleTypes.CRIT, this.getX() + dX * i / 4.0D, this.getY() + dY * i / 4.0D, this.getZ() + dZ * i / 4.0D, -dX, -dY + 0.2D, -dZ);
                }
            }

            double nextX = this.getX() + dX;
            double nextY = this.getY() + dY;
            double nextZ = this.getZ() + dZ;
            double horizontalDistance = motion.horizontalDistance();

            if (noClip) {
                this.setYRot((float)(Mth.atan2(-dX, -dZ) * (180F / Math.PI)));
            } else {
                this.setYRot((float)(Mth.atan2(dX, dZ) * (180F / Math.PI)));
            }

            this.setXRot((float)(Mth.atan2(dY, horizontalDistance) * (180F / Math.PI)));
            this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
            this.setYRot(lerpRotation(this.yRotO, this.getYRot()));

            float drag = 0.99F;
            if (this.isInWater()) {
                for (int j = 0; j < 4; ++j) {
                    this.level().addParticle(ParticleTypes.BUBBLE, nextX - dX * 0.25, nextY - dY * 0.25, nextZ - dZ * 0.25, dX, dY, dZ);
                }
                drag = this.getWaterInertia();
            }

            this.setDeltaMovement(motion.multiply(drag, drag, drag));
            if (!this.isNoGravity() && !noClip) {
                Vec3 newMotion = this.getDeltaMovement();
                this.setDeltaMovement(newMotion.x, newMotion.y - 0.05, newMotion.z);
            }

            this.setPos(nextX, nextY, nextZ);
            this.checkInsideBlocks();
        }
    }


    private boolean shouldFall() {
        return this.inGround && this.level().noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
    }

    private void startFalling() {
        this.inGround = false;
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.multiply((this.random.nextFloat() * 0.2F), (this.random.nextFloat() * 0.2F), (this.random.nextFloat() * 0.2F)));
        this.life = 0;
    }

    public void move(@NotNull MoverType pType, @NotNull Vec3 pPos) {
        super.move(pType, pPos);
        if (pType != MoverType.SELF && this.shouldFall()) {
            this.startFalling();
        }

    }

    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 1200) {
            this.discard();
        }

    }

    private void resetPiercedEntities() {
        if (this.piercedAndKilledEntities != null) {
            this.piercedAndKilledEntities.clear();
        }

        if (this.piercingIgnoreEntityIds != null) {
            this.piercingIgnoreEntityIds.clear();
        }

    }

    protected void onHitEntity(@NotNull EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        float f = (float)this.getDeltaMovement().length();
        this.setXRot(0);
        this.xRotO = 0;
        int i = Mth.ceil(Mth.clamp((double)f * this.baseDamage, 0.0D, Integer.MAX_VALUE));
        if (this.getPierceLevel() > 0) {
            if (this.piercingIgnoreEntityIds == null) {
                this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
            }

            if (this.piercedAndKilledEntities == null) {
                this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
            }

            if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
                this.discard();
                return;
            }

            this.piercingIgnoreEntityIds.add(entity.getId());
        }

        if (this.isCritArrow()) {
            long j = this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(j + (long)i, 2147483647L);
        }

        Entity entity1 = this.getOwner();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = this.damageSources().generic();
        } else {
            damagesource = this.damageSources().generic();
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity)entity1).setLastHurtMob(entity);
            }
        }

        boolean flag = entity.getType() == EntityType.ENDERMAN;
        int k = entity.getRemainingFireTicks();
        if (this.isOnFire() && !flag) {
            entity.setSecondsOnFire(5);
        }

        if (entity.hurt(damagesource, (float)i)) {
            if (flag) {
                return;
            }

            if (entity instanceof LivingEntity livingentity) {
                if (this.knockback > 0) {
                    double d0 = Math.max(0.0D, 1.0D - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
                    Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D * d0);
                    if (vec3.lengthSqr() > 0.0D) {
                        livingentity.push(vec3.x, 0.1D, vec3.z);
                    }
                }

                if (!this.level().isClientSide && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
                }

                this.doPostHurtEffects(livingentity);
                if (livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
                    ((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
                }

                if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
                    this.piercedAndKilledEntities.add(livingentity);
                }

                if (!this.level().isClientSide && entity1 instanceof ServerPlayer serverplayer) {
                    if (this.piercedAndKilledEntities != null && this.shotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, this.piercedAndKilledEntities);
                    } else if (!entity.isAlive() && this.shotFromCrossbow()) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, List.of(entity));
                    }
                }
            }

            this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            entity.setRemainingFireTicks(k);
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
            if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                this.discard();
            }
        }

    }

    protected void onHitBlock(BlockHitResult pResult) {
        this.lastState = this.level().getBlockState(pResult.getBlockPos());
        super.onHitBlock(pResult);
        Vec3 vec3 = pResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vec3);
        Vec3 vec31 = vec3.normalize().scale(0.05F);
        this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
        this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shakeTime = 7;
        this.setCritArrow(false);
        this.setPierceLevel((byte)0);
        this.setSoundEvent(SoundEvents.ARROW_HIT);
        this.setShotFromCrossbow(false);
        this.resetPiercedEntities();
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }

    protected final SoundEvent getHitGroundSoundEvent() {
        return this.soundEvent;
    }

    protected void doPostHurtEffects(LivingEntity pTarget) {
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 pStartVec, Vec3 pEndVec) {
        return ProjectileUtil.getEntityHitResult(this.level(), this, pStartVec, pEndVec, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
    }

    protected boolean canHitEntity(@NotNull Entity pEntity) {
        return super.canHitEntity(pEntity) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(pEntity.getId())) && !this.ignoredEntities.contains(pEntity.getId());
    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putShort("life", (short)this.life);
        if (this.lastState != null) {
            pCompound.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
        }

        pCompound.putByte("shake", (byte)this.shakeTime);
        pCompound.putBoolean("inGround", this.inGround);
        pCompound.putByte("pickup", (byte)this.pickup.ordinal());
        pCompound.putDouble("damage", this.baseDamage);
        pCompound.putBoolean("crit", this.isCritArrow());
        pCompound.putByte("PierceLevel", this.getPierceLevel());
        pCompound.putString("SoundEvent", BuiltInRegistries.SOUND_EVENT.getKey(this.soundEvent).toString());
        pCompound.putBoolean("ShotFromCrossbow", this.shotFromCrossbow());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.life = pCompound.getShort("life");
        if (pCompound.contains("inBlockState", 10)) {
            this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), pCompound.getCompound("inBlockState"));
        }

        this.shakeTime = pCompound.getByte("shake") & 255;
        this.inGround = pCompound.getBoolean("inGround");
        if (pCompound.contains("damage", 99)) {
            this.baseDamage = pCompound.getDouble("damage");
        }

        this.pickup = AbstractArrow.Pickup.byOrdinal(pCompound.getByte("pickup"));
        this.setCritArrow(pCompound.getBoolean("crit"));
        this.setPierceLevel(pCompound.getByte("PierceLevel"));
        if (pCompound.contains("SoundEvent", 8)) {
            this.soundEvent = BuiltInRegistries.SOUND_EVENT.getOptional(new ResourceLocation(pCompound.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
        }

        this.setShotFromCrossbow(pCompound.getBoolean("ShotFromCrossbow"));
    }

    public void setOwner(@Nullable Entity pEntity) {
        super.setOwner(pEntity);
        if (pEntity instanceof Player) {
            this.pickup = ((Player)pEntity).getAbilities().instabuild ? AbstractArrow.Pickup.CREATIVE_ONLY : AbstractArrow.Pickup.ALLOWED;
        }

    }

    public void playerTouch(@NotNull Player pEntity) {
        if (!this.level().isClientSide && (this.inGround || this.isNoPhysics()) && this.shakeTime <= 0) {
            if (this.tryPickup(pEntity)) {
                pEntity.take(this, 1);
                this.discard();
            }

        }
    }

    protected boolean tryPickup(Player pPlayer) {
        if (Objects.requireNonNull(this.pickup) == AbstractArrow.Pickup.CREATIVE_ONLY) {
            return pPlayer.getAbilities().instabuild;
        }
        return false;
    }

    protected @NotNull MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    public boolean isAttackable() {
        return false;
    }

    protected float getEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pSize) {
        return 0.13F;
    }

    public void setCritArrow(boolean pCritArrow) {
        this.setFlag(1, pCritArrow);
    }

    public void setPierceLevel(byte pPierceLevel) {
        this.entityData.set(PIERCE_LEVEL, pPierceLevel);
    }

    private void setFlag(int pId, boolean pValue) {
        byte b0 = this.entityData.get(ID_FLAGS);
        if (pValue) {
            this.entityData.set(ID_FLAGS, (byte)(b0 | pId));
        } else {
            this.entityData.set(ID_FLAGS, (byte)(b0 & ~pId));
        }

    }

    public boolean isCritArrow() {
        byte b0 = this.entityData.get(ID_FLAGS);
        return (b0 & 1) != 0;
    }

    public boolean shotFromCrossbow() {
        byte b0 = this.entityData.get(ID_FLAGS);
        return (b0 & 4) != 0;
    }

    public byte getPierceLevel() {
        return this.entityData.get(PIERCE_LEVEL);
    }

    protected float getWaterInertia() {
        return 0.6F;
    }

    public boolean isNoPhysics() {
        if (!this.level().isClientSide) {
            return this.noPhysics;
        } else {
            return (this.entityData.get(ID_FLAGS) & 2) != 0;
        }
    }

    public void setShotFromCrossbow(boolean pShotFromCrossbow) {
        this.setFlag(4, pShotFromCrossbow);
    }

}
