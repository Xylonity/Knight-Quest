package net.xylonity.common.entity.entities;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AbstractSwampmanAxeEntity extends ProjectileEntity {
    private static final double field_30657 = 2.0;
    private static final TrackedData<Byte> PROJECTILE_FLAGS;
    private static final TrackedData<Byte> PIERCE_LEVEL;
    private static final int CRITICAL_FLAG = 1;
    private static final int NO_CLIP_FLAG = 2;
    @Nullable
    private BlockState inBlockState;
    protected boolean inGround;
    protected int inGroundTime;
    public int shake;
    private int life;
    private double damage;
    private SoundEvent sound;
    @Nullable
    private IntOpenHashSet piercedEntities;
    @Nullable
    private List<Entity> piercingKilledEntities;
    @Nullable
    private ItemStack weapon;

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> entityType, World world) {
        super(entityType, world);
        this.damage = 2.0;
        this.sound = this.getHitSound();
        this.weapon = null;
    }

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> type, double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        this(type, world);
        this.setCustomName((Text)stack.get(DataComponentTypes.CUSTOM_NAME));
    }

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> type, double pX, double pY, double pZ, World pLevel) {
        this(type, pLevel);
        this.setPosition(pX, pY, pZ);
    }

    protected AbstractSwampmanAxeEntity(EntityType<? extends AbstractSwampmanAxeEntity> type, LivingEntity owner, World world) {
        this(type, owner.getX(), owner.getEyeY() - 0.1F, owner.getZ(), world);
        this.setOwner(owner);
    }

    public void setSound(SoundEvent sound) {
        this.sound = sound;
    }

    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 10.0;
        if (Double.isNaN(d)) {
            d = 1.0;
        }

        d *= 64.0 * getRenderDistanceMultiplier();
        return distance < d * d;
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(PROJECTILE_FLAGS, (byte)0);
        builder.add(PIERCE_LEVEL, (byte)0);
    }

    public void setVelocity(double x, double y, double z, float power, float uncertainty) {
        super.setVelocity(x, y, z, power, uncertainty);
        this.life = 0;
    }

    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps) {
        this.setPosition(x, y, z);
        this.setRotation(yaw, pitch);
    }

    public void setVelocityClient(double x, double y, double z) {
        super.setVelocityClient(x, y, z);
        this.life = 0;
    }

    public void tick() {
        super.tick();
        boolean bl = this.isNoClip();
        Vec3d vec3d = this.getVelocity();
        if (this.prevPitch == 0.0F && this.prevYaw == 0.0F) {
            double d = vec3d.horizontalLength();
            this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875));
            this.setPitch((float)(MathHelper.atan2(vec3d.y, d) * 57.2957763671875));
            this.prevYaw = this.getYaw();
            this.prevPitch = this.getPitch();
        }

        if (!this.inGround) {
            this.prevPitch = this.getPitch() + 60;
        }

        BlockPos blockPos = this.getBlockPos();
        BlockState blockState = this.getWorld().getBlockState(blockPos);
        Vec3d vec3d2;
        if (!blockState.isAir() && !bl) {
            VoxelShape voxelShape = blockState.getCollisionShape(this.getWorld(), blockPos);
            if (!voxelShape.isEmpty()) {
                vec3d2 = this.getPos();
                Iterator var7 = voxelShape.getBoundingBoxes().iterator();

                while(var7.hasNext()) {
                    Box box = (Box)var7.next();
                    if (box.offset(blockPos).contains(vec3d2)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.shake > 0) {
            --this.shake;
        }

        if (this.isTouchingWaterOrRain() || blockState.isOf(Blocks.POWDER_SNOW)) {
            this.extinguish();
        }

        if (this.inGround && !bl) {
            if (this.inBlockState != blockState && this.shouldFall()) {
                this.fall();
            } else if (!this.getWorld().isClient) {
                this.age();
            }

            ++this.inGroundTime;
        } else {
            this.inGroundTime = 0;
            Vec3d vec3d3 = this.getPos();
            vec3d2 = vec3d3.add(vec3d);
            HitResult hitResult = this.getWorld().raycast(new RaycastContext(vec3d3, vec3d2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
            if (((HitResult)hitResult).getType() != HitResult.Type.MISS) {
                vec3d2 = ((HitResult)hitResult).getPos();
            }

            while(!this.isRemoved()) {
                EntityHitResult entityHitResult = this.getEntityCollision(vec3d3, vec3d2);
                if (entityHitResult != null) {
                    hitResult = entityHitResult;
                }

                if (hitResult != null && ((HitResult)hitResult).getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult)hitResult).getEntity();
                    Entity entity2 = this.getOwner();
                    if (entity instanceof PlayerEntity && entity2 instanceof PlayerEntity && !((PlayerEntity)entity2).shouldDamagePlayer((PlayerEntity)entity)) {
                        hitResult = null;
                        entityHitResult = null;
                    }
                }

                if (hitResult != null && !bl) {
                    ProjectileDeflection projectileDeflection = this.hitOrDeflect((HitResult)hitResult);
                    this.velocityDirty = true;
                    if (projectileDeflection != ProjectileDeflection.NONE) {
                        break;
                    }
                }

                if (entityHitResult == null || this.getPierceLevel() <= 0) {
                    break;
                }

                hitResult = null;
            }

            vec3d = this.getVelocity();
            double e = vec3d.x;
            double f = vec3d.y;
            double g = vec3d.z;
            if (this.isCritical()) {
                for(int i = 0; i < 4; ++i) {
                    this.getWorld().addParticle(ParticleTypes.CRIT, this.getX() + e * (double)i / 4.0, this.getY() + f * (double)i / 4.0, this.getZ() + g * (double)i / 4.0, -e, -f + 0.2, -g);
                }
            }

            double h = this.getX() + e;
            double j = this.getY() + f;
            double k = this.getZ() + g;
            double l = vec3d.horizontalLength();
            if (bl) {
                this.setYaw((float)(MathHelper.atan2(-e, -g) * 57.2957763671875));
            } else {
                this.setYaw((float)(MathHelper.atan2(e, g) * 57.2957763671875));
            }

            this.setPitch((float)(MathHelper.atan2(f, l) * 57.2957763671875));
            this.setPitch(updateRotation(this.prevPitch, this.getPitch()));
            this.setYaw(updateRotation(this.prevYaw, this.getYaw()));
            float m = 0.99F;
            if (this.isTouchingWater()) {
                for(int n = 0; n < 4; ++n) {
                    float o = 0.25F;
                    this.getWorld().addParticle(ParticleTypes.BUBBLE, h - e * 0.25, j - f * 0.25, k - g * 0.25, e, f, g);
                }

                m = this.getDragInWater();
            }

            this.setVelocity(vec3d.multiply((double)m));
            if (!bl) {
                this.applyGravity();
            }

            this.setPosition(h, j, k);
            this.checkBlockCollision();
        }
    }

    protected double getGravity() {
        return 0.05;
    }

    private boolean shouldFall() {
        return this.inGround && this.getWorld().isSpaceEmpty((new Box(this.getPos(), this.getPos())).expand(0.06));
    }

    private void fall() {
        this.inGround = false;
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
        this.life = 0;
    }

    public void move(MovementType movementType, Vec3d movement) {
        super.move(movementType, movement);
        if (movementType != MovementType.SELF && this.shouldFall()) {
            this.fall();
        }

    }

    protected void age() {
        ++this.life;
        if (this.life >= 1200) {
            this.discard();
        }

    }

    private void clearPiercingStatus() {
        if (this.piercingKilledEntities != null) {
            this.piercingKilledEntities.clear();
        }

        if (this.piercedEntities != null) {
            this.piercedEntities.clear();
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        float f = (float)this.getVelocity().length();
        double d = this.damage;
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().thrown(this, (Entity)(entity2 != null ? entity2 : this));
        if (this.getWeaponStack() != null) {
            World var9 = this.getWorld();
            if (var9 instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)var9;
                d = (double)EnchantmentHelper.getDamage(serverWorld, this.getWeaponStack(), entity, damageSource, (float)d);
            }
        }

        int i = MathHelper.ceil(MathHelper.clamp((double)f * d, 0.0, 2.147483647E9));
        if (this.getPierceLevel() > 0) {
            if (this.piercedEntities == null) {
                this.piercedEntities = new IntOpenHashSet(5);
            }

            if (this.piercingKilledEntities == null) {
                this.piercingKilledEntities = Lists.newArrayListWithCapacity(5);
            }

            if (this.piercedEntities.size() >= this.getPierceLevel() + 1) {
                this.discard();
                return;
            }

            this.piercedEntities.add(entity.getId());
        }

        if (this.isCritical()) {
            long l = (long)this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(l + (long)i, 2147483647L);
        }

        if (entity2 instanceof LivingEntity livingEntity) {
            livingEntity.onAttacking(entity);
        }

        boolean bl = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTicks();
        if (this.isOnFire() && !bl) {
            entity.setOnFireFor(5.0F);
        }

        if (entity.damage(damageSource, (float)i)) {
            if (bl) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity2 = (LivingEntity)entity;
                if (!this.getWorld().isClient && this.getPierceLevel() <= 0) {
                    livingEntity2.setStuckArrowCount(livingEntity2.getStuckArrowCount() + 1);
                }

                this.knockback(livingEntity2, damageSource);
                World var13 = this.getWorld();
                if (var13 instanceof ServerWorld) {
                    ServerWorld serverWorld2 = (ServerWorld)var13;
                    EnchantmentHelper.onTargetDamaged(serverWorld2, livingEntity2, damageSource, this.getWeaponStack());
                }

                this.onHit(livingEntity2);
                if (livingEntity2 != entity2 && livingEntity2 instanceof PlayerEntity && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity)entity2).networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, 0.0F));
                }

                if (!entity.isAlive() && this.piercingKilledEntities != null) {
                    this.piercingKilledEntities.add(livingEntity2);
                }

                if (!this.getWorld().isClient && entity2 instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity2;
                    if (this.piercingKilledEntities != null && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, this.piercingKilledEntities);
                    } else if (!entity.isAlive() && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, Arrays.asList(entity));
                    }
                }
            }

            this.playSound(this.sound, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            entity.setFireTicks(j);
            this.deflect(ProjectileDeflection.SIMPLE, entity, this.getOwner(), false);
            this.setVelocity(this.getVelocity().multiply(0.2));
            if (!this.getWorld().isClient && this.getVelocity().lengthSquared() < 1.0E-7) {
                this.discard();
            }
        }

    }

    protected void knockback(LivingEntity target, DamageSource source) {
        float var10000;
        label18: {
            if (this.weapon != null) {
                World var6 = this.getWorld();
                if (var6 instanceof ServerWorld) {
                    ServerWorld serverWorld = (ServerWorld)var6;
                    var10000 = EnchantmentHelper.modifyKnockback(serverWorld, this.weapon, target, source, 0.0F);
                    break label18;
                }
            }

            var10000 = 0.0F;
        }

        double d = (double)var10000;
        if (d > 0.0) {
            double e = Math.max(0.0, 1.0 - target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
            Vec3d vec3d = this.getVelocity().multiply(1.0, 0.0, 1.0).normalize().multiply(d * 0.6 * e);
            if (vec3d.lengthSquared() > 0.0) {
                target.addVelocity(vec3d.x, 0.1, vec3d.z);
            }
        }

    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.inBlockState = this.getWorld().getBlockState(blockHitResult.getBlockPos());
        super.onBlockHit(blockHitResult);
        Vec3d vec3d = blockHitResult.getPos().subtract(this.getX(), this.getY(), this.getZ());
        this.setVelocity(vec3d);
        ItemStack itemStack = this.getWeaponStack();
        World var5 = this.getWorld();
        if (var5 instanceof ServerWorld serverWorld) {
            if (itemStack != null) {
                this.onBlockHitEnchantmentEffects(serverWorld, blockHitResult, itemStack);
            }
        }

        Vec3d vec3d2 = vec3d.normalize().multiply(0.05000000074505806);
        this.setPos(this.getX() - vec3d2.x, this.getY() - vec3d2.y, this.getZ() - vec3d2.z);
        this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shake = 7;
        this.setCritical(false);
        this.setPierceLevel((byte)0);
        this.setSound(SoundEvents.ENTITY_ARROW_HIT);
        this.clearPiercingStatus();
    }

    protected void onBlockHitEnchantmentEffects(ServerWorld world, BlockHitResult blockHitResult, ItemStack weaponStack) {
        Vec3d vec3d = blockHitResult.getBlockPos().clampToWithin(blockHitResult.getPos());
        Entity var6 = this.getOwner();
        LivingEntity var10002;
        if (var6 instanceof LivingEntity livingEntity) {
            var10002 = livingEntity;
        } else {
            var10002 = null;
        }

        EnchantmentHelper.onHitBlock(world, weaponStack, var10002, this, (EquipmentSlot)null, vec3d, world.getBlockState(blockHitResult.getBlockPos()), (item) -> {
            this.weapon = null;
        });
    }

    public ItemStack getWeaponStack() {
        return this.weapon;
    }

    protected SoundEvent getHitSound() {
        return SoundEvents.ENTITY_ARROW_HIT;
    }

    protected final SoundEvent getSound() {
        return this.sound;
    }

    protected void onHit(LivingEntity target) {
    }

    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return ProjectileUtil.getEntityCollision(this.getWorld(), this, currentPosition, nextPosition, this.getBoundingBox().stretch(this.getVelocity()).expand(1.0), this::canHit);
    }

    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && (this.piercedEntities == null || !this.piercedEntities.contains(entity.getId()));
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putShort("life", (short)this.life);
        if (this.inBlockState != null) {
            nbt.put("inBlockState", NbtHelper.fromBlockState(this.inBlockState));
        }

        nbt.putByte("shake", (byte)this.shake);
        nbt.putBoolean("inGround", this.inGround);
        nbt.putDouble("damage", this.damage);
        nbt.putBoolean("crit", this.isCritical());
        nbt.putByte("PierceLevel", this.getPierceLevel());
        nbt.putString("SoundEvent", Objects.requireNonNull(Registries.SOUND_EVENT.getId(this.sound)).toString());
        if (this.weapon != null) {
            nbt.put("weapon", this.weapon.encode(this.getRegistryManager(), new NbtCompound()));
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.life = nbt.getShort("life");
        if (nbt.contains("inBlockState", 10)) {
            this.inBlockState = NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound("inBlockState"));
        }

        this.shake = nbt.getByte("shake") & 255;
        this.inGround = nbt.getBoolean("inGround");
        if (nbt.contains("damage", 99)) {
            this.damage = nbt.getDouble("damage");
        }

        this.setCritical(nbt.getBoolean("crit"));
        this.setPierceLevel(nbt.getByte("PierceLevel"));
        if (nbt.contains("SoundEvent", 8)) {
            this.sound = (SoundEvent)Registries.SOUND_EVENT.getOrEmpty(Identifier.of(nbt.getString("SoundEvent"))).orElse(this.getHitSound());
        }

    }

    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

    public boolean isAttackable() {
        return this.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE);
    }

    public void setCritical(boolean critical) {
        this.setProjectileFlag(1, critical);
    }

    private void setPierceLevel(byte level) {
        this.dataTracker.set(PIERCE_LEVEL, level);
    }

    private void setProjectileFlag(int index, boolean flag) {
        byte b = (Byte)this.dataTracker.get(PROJECTILE_FLAGS);
        if (flag) {
            this.dataTracker.set(PROJECTILE_FLAGS, (byte)(b | index));
        } else {
            this.dataTracker.set(PROJECTILE_FLAGS, (byte)(b & ~index));
        }

    }

    public boolean isCritical() {
        byte b = (Byte)this.dataTracker.get(PROJECTILE_FLAGS);
        return (b & 1) != 0;
    }

    public boolean isShotFromCrossbow() {
        return this.weapon != null && this.weapon.isOf(Items.CROSSBOW);
    }

    public byte getPierceLevel() {
        return (Byte)this.dataTracker.get(PIERCE_LEVEL);
    }

    public void applyDamageModifier(float damageModifier) {
        this.setDamage((double)(damageModifier * 2.0F) + this.random.nextTriangular((double)this.getWorld().getDifficulty().getId() * 0.11, 0.57425));
    }

    protected float getDragInWater() {
        return 0.6F;
    }

    public void setNoClip(boolean noClip) {
        this.noClip = noClip;
        this.setProjectileFlag(2, noClip);
    }

    public boolean isNoClip() {
        if (!this.getWorld().isClient) {
            return this.noClip;
        } else {
            return ((Byte)this.dataTracker.get(PROJECTILE_FLAGS) & 2) != 0;
        }
    }

    public boolean canHit() {
        return super.canHit() && !this.inGround;
    }

    static {
        PROJECTILE_FLAGS = DataTracker.registerData(AbstractSwampmanAxeEntity.class, TrackedDataHandlerRegistry.BYTE);
        PIERCE_LEVEL = DataTracker.registerData(AbstractSwampmanAxeEntity.class, TrackedDataHandlerRegistry.BYTE);
    }

}
