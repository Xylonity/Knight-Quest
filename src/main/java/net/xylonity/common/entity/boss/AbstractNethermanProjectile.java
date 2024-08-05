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
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.EntityTrackerEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.xylonity.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.common.api.util.ParticleGenerator;
import net.xylonity.common.api.util.TeleportValidator;
import net.xylonity.common.entity.boss.ai.*;
import net.xylonity.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AbstractNethermanProjectile extends ProjectileEntity {
    public double xPower;
    public double yPower;
    public double zPower;
    private double accelerationPower;

    protected AbstractNethermanProjectile(EntityType<? extends ProjectileEntity> pEntityType, World pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractNethermanProjectile(EntityType<? extends ProjectileEntity> pEntityType, double pX, double pY, double pZ, double pOffsetX, double pOffsetY, double pOffsetZ, World pLevel) {
        this(pEntityType, pLevel);
        this.refreshPositionAndAngles(pX, pY, pZ, this.getYaw(), this.getPitch());
        this.refreshPosition();
        double d0 = Math.sqrt(pOffsetX * pOffsetX + pOffsetY * pOffsetY + pOffsetZ * pOffsetZ);
        if (d0 != 0.0D) {
            this.xPower = pOffsetX / d0 * 0.1D;
            this.yPower = pOffsetY / d0 * 0.1D;
            this.zPower = pOffsetZ / d0 * 0.1D;
        }

    }

    public AbstractNethermanProjectile(EntityType<? extends ProjectileEntity> pEntityType, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, World pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getY(), pShooter.getZ(), pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.setOwner(pShooter);
        this.setRotation(pShooter.getYaw(), pShooter.getPitch());
    }

    /**
     * Checks if the entity is in range to render.
     */

    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }

        d *= 64.0;
        return distance < d * d;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) { ;; }

    protected RaycastContext.ShapeType getRaycastShapeType() {
        return RaycastContext.ShapeType.COLLIDER;
    }

    /**
     * Called to update the entity's position/logic.
     */

    public void tick() {
        Entity entity = this.getOwner();
        if (!this.getWorld().isClient && (entity != null && entity.isRemoved() || !this.getWorld().isChunkLoaded(this.getBlockPos()))) {
            this.discard();
        } else {
            super.tick();
            HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit, this.getRaycastShapeType());
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.hitOrDeflect(hitResult);
            }

            this.checkBlockCollision();
            Vec3d vec3d = this.getVelocity();
            double d = this.getX() + vec3d.x;
            double e = this.getY() + vec3d.y;
            double f = this.getZ() + vec3d.z;
            ProjectileUtil.setRotationFromVelocity(this, 0.2F);
            float h;
            if (this.isTouchingWater()) {
                for(int i = 0; i < 4; ++i) {
                    float g = 0.25F;
                    this.getWorld().addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25, e - vec3d.y * 0.25, f - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
                }

                h = this.getDragInWater();
            } else {
                h = this.getDrag();
            }

            this.setVelocity(vec3d.add(vec3d.normalize().multiply(this.accelerationPower)).multiply((double)h));
            ParticleEffect particleEffect = this.getParticleType();
            if (particleEffect != null) {
                this.getWorld().addParticle(particleEffect, d, e + 0.5, f, 0.0, 0.0, 0.0);
            }

            this.setPosition(d, e, f);
        }
    }

    protected float getDragInWater() {
        return 0.8F;
    }

    protected float getDrag() {
        return 0.95F;
    }

    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && !entity.noClip;
    }

    @Nullable
    protected ParticleEffect getParticleType() {
        return ParticleTypes.SMOKE;
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        NbtList powerList = new NbtList();
        powerList.add(NbtDouble.of(this.xPower));
        powerList.add(NbtDouble.of(this.yPower));
        powerList.add(NbtDouble.of(this.zPower));
        nbt.put("power", powerList);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("power", 9)) {
            NbtList powerList = nbt.getList("power", 6);
            if (powerList.size() == 3) {
                this.xPower = powerList.getDouble(0);
                this.yPower = powerList.getDouble(1);
                this.zPower = powerList.getDouble(2);
            }
        }
    }

    /**
     * Returns {@code true} if other Entities should be prevented from moving through this Entity.
     */

    public boolean isPickable() {
        return true;
    }

    public float getPickRadius() {
        return 1.0F;
    }

    /**
     * Called when the entity is attacked.
     */

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getSource();
            if (entity != null) {
                if (!this.getWorld().isClient) {
                    Vec3d vec3 = entity.getRotationVector();
                    this.setVelocity(vec3);
                    this.xPower = vec3.x * 0.1D;
                    this.yPower = vec3.y * 0.1D;
                    this.zPower = vec3.z * 0.1D;
                    this.setOwner(entity);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public float getBrightnessAtEyes() {
        return 1.0F;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket(EntityTrackerEntry entityTrackerEntry) {
        Entity entity = this.getOwner();
        int i = entity == null ? 0 : entity.getId();
        Vec3d vec3d = entityTrackerEntry.getPos();
        return new EntitySpawnS2CPacket(this.getId(), this.getUuid(), vec3d.getX(), vec3d.getY(), vec3d.getZ(), entityTrackerEntry.getPitch(), entityTrackerEntry.getYaw(), this.getType(), i, entityTrackerEntry.getVelocity(), 0.0);
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        Vec3d vec3d = new Vec3d(packet.getVelocityX(), packet.getVelocityY(), packet.getVelocityZ());
        this.setVelocity(vec3d);
    }
}
