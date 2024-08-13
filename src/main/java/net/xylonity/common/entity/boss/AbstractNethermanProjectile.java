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

    protected AbstractNethermanProjectile(EntityType<? extends AbstractNethermanProjectile> pEntityType, World pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractNethermanProjectile(EntityType<? extends AbstractNethermanProjectile> pEntityType, double pX, double pY, double pZ, double pOffsetX, double pOffsetY, double pOffsetZ, World pLevel) {
        this(pEntityType, pLevel);
        this.updatePositionAndAngles(pX, pY, pZ, this.getYaw(), this.getPitch());
        this.refreshPosition();
        double d0 = Math.sqrt(pOffsetX * pOffsetX + pOffsetY * pOffsetY + pOffsetZ * pOffsetZ);
        if (d0 != 0.0D) {
            this.xPower = pOffsetX / d0 * 0.1D;
            this.yPower = pOffsetY / d0 * 0.1D;
            this.zPower = pOffsetZ / d0 * 0.1D;
        }

    }

    public AbstractNethermanProjectile(EntityType<? extends AbstractNethermanProjectile> pEntityType, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, World pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getY(), pShooter.getZ(), pOffsetX, pOffsetY, pOffsetZ, pLevel);
        this.setOwner(pShooter);
        this.setRotation(pShooter.getYaw(), pShooter.getPitch());
    }

    /**
     * Checks if the entity is in range to render.
     */

    public boolean shouldRender(double distance) {
        double size = this.getBoundingBox().getAverageSideLength() * 4.0D;
        size = Double.isNaN(size) ? 4.0D : size * 64.0D;
        return distance < size * size;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) { }

    /**
     * Called to update the entity's position/logic.
     */

    public void tick() {
        Entity entity = this.getOwner();
        if (this.getWorld().isClient || (entity == null || !entity.isRemoved()) && this.getWorld().isChunkLoaded(this.getBlockPos())) {
            super.tick();

            HitResult hitresult = ProjectileUtil.getCollision(this, this::canHit);
            if (hitresult.getType() != HitResult.Type.MISS) {
                this.onCollision(hitresult);
            }

            this.checkBlockCollision();
            Vec3d vec3d = this.getVelocity();
            double d0 = this.getX() + vec3d.x;
            double d1 = this.getY() + vec3d.y;
            double d2 = this.getZ() + vec3d.z;
            ProjectileUtil.setRotationFromVelocity(this, 0.2F);
            float f = this.getInertia();
            if (this.isTouchingWater()) {
                for(int i = 0; i < 4; ++i) {
                    this.getWorld().addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25, d1 - vec3d.y * 0.25, d2 - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
                }

                f = 0.8F;
            }

            this.setVelocity(vec3d.add(this.xPower, this.yPower, this.zPower).multiply(f));
            this.getWorld().addParticle(this.getTrailParticle(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
            this.setPos(d0, d1, d2);
        } else {
            this.discard();
        }
    }

    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && !entity.noClip;
    }

    protected ParticleEffect getTrailParticle() {
        return ParticleTypes.SMOKE;
    }

    protected NbtList newDoubleList(double... pNumbers) {
        NbtList listtag = new NbtList();

        for(double d0 : pNumbers) {
            listtag.add(NbtDouble.of(d0));
        }

        return listtag;
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */

    protected float getInertia() {
        return 0.95F;
    }

    public void writeCustomDataToNbt(@NotNull NbtCompound pCompound) {
        super.writeCustomDataToNbt(pCompound);
        pCompound.put("power", this.newDoubleList(this.xPower, this.yPower, this.zPower));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */

    public void readCustomDataFromNbt(@NotNull NbtCompound pCompound) {
        super.readCustomDataFromNbt(pCompound);
        if (pCompound.contains("power", 9)) {
            NbtList listtag = pCompound.getList("power", 6);
            if (listtag.size() == 3) {
                this.xPower = listtag.getDouble(0);
                this.yPower = listtag.getDouble(1);
                this.zPower = listtag.getDouble(2);
            }
        }

    }

    /**
     * Called when the entity is attacked.
     */

    public boolean damage(DamageSource source, float amount) {
        return !this.isInvulnerableTo(source);
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
        double d0 = packet.getVelocityX();
        double d1 = packet.getVelocityY();
        double d2 = packet.getVelocityZ();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        if (d3 != 0.0D) {
            this.xPower = d0 / d3 * 0.1D;
            this.yPower = d1 / d3 * 0.1D;
            this.zPower = d2 / d3 * 0.1D;
        }
    }

}