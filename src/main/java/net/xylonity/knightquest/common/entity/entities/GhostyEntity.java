package net.xylonity.knightquest.common.entity.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import net.xylonity.knightquest.config.values.KQConfigValues;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Objects;

public class GhostyEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final Level serverWorld;
    private static final double ANGULAR_SPEED = 0.1;
    private double angle;
    private double baseY;
    Player target;

    public GhostyEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
        this.serverWorld = world;
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.ATTACK_DAMAGE, 0f)
                .add(Attributes.ATTACK_SPEED, 0f)
                .add(Attributes.MOVEMENT_SPEED, 0.63f)
                .add(Attributes.FOLLOW_RANGE, 35.0).build();
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) { return PlayState.CONTINUE; }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        serverWorld.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 1.2d, 0d, 0d);
        serverWorld.playSound(null, this.blockPosition(), SoundEvents.FOX_SCREECH, SoundSource.HOSTILE, 1.0F, 1.0F);

        this.remove(RemovalReason.KILLED);
        return false;
    }

    /**
     * Handles the logical behavior of the mob, without defining goals or animations.
     * When a player is near the ghosty, it will look at and follow the player,
     * visually enhanced with a sin function that gives it a ghost-like movement.
     * If any hostile entities are nearby, the ghost grants them Protection V,
     * effectively rendering them invulnerable.
     */


    @Override
    public void tick() {
        super.tick();

        if (isSunBurnTick()) {
            this.setSecondsOnFire(2);
        }

        if (tickCount % 15 == 0) {
            List<Player> nearbyMonsters = serverWorld.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(1));
            for (Player player : nearbyMonsters) {
                if (!player.isCreative()) {
                    player.hurt(DamageSource.GENERIC, 1);
                    Vec3 direction = player.position().subtract(this.position()).normalize().scale(0.1);
                    player.push(direction.x, direction.y + 0.5, direction.z);
                }
            }
        }

        List<Monster> nearbyMonsters = serverWorld.getEntitiesOfClass(Monster.class, this.getBoundingBox().inflate(KQConfigValues.INVULNERABILITY_RADIUS_GHOSTY));
        for (Monster monster : nearbyMonsters) {
            monster.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 4, false, false, false));
            if (!(monster instanceof GhostyEntity))
                for (int i = 0; i < 4 && tickCount % 25 == 0; ++i) {
                    serverWorld.addParticle(KnightQuestParticles.STARSET_PARTICLE.get(), monster.getX(), monster.getY() - 0.48, monster.getZ(), 1.2d, 0d, 0d);
                }
        }

        if (baseY == 0 && getY() != 0) {
            baseY = getY();
        }

        this.target = this.serverWorld.getNearestPlayer(this, 20.0);

        double offsetY = Math.sin(angle * 2) * 0.1;
        double currentX = this.getX();
        double currentY = this.getY();
        double currentZ = this.getZ();

        if (target != null && !target.isCreative()) {
            this.lookAt(target, 360, 360);

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

        if (this.serverWorld.getNearestPlayer(this, -1.0) != null) {
            this.lookAt(Objects.requireNonNull(this.serverWorld.getNearestPlayer(this, -1.0)), 360, 360);
        }

        angle += ANGULAR_SPEED;
        if (angle >= Math.PI * 2) {
            angle -= Math.PI * 2;
        }

        ParticleOptions particleType = KnightQuestParticles.GHOSTY_PARTICLE.get();
        for (int i = 0; i < 1 && tickCount % 15 == 0; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.serverWorld.addParticle(particleType,
                    this.getX() + (this.random.nextFloat() * this.getBbWidth() * 1.0F) - this.getBbWidth(),
                    this.getY() + (this.random.nextFloat() * this.getBbHeight()),
                    this.getZ() + (this.random.nextFloat() * this.getBbWidth() * 1.0F) - this.getBbWidth(),
                    d0, d1, d2);
        }
    }

    @Override public boolean isFallFlying() {return super.isFallFlying();}
    @Override public boolean canSpawnSprintParticle() {return false;}
    @Override protected void removeEffectParticles() {}
    @Override public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {return false;}
    @Override public boolean isNoGravity() {return true;}
    @Override public boolean canBeCollidedWith() {return false;}
    @Override protected void pushEntities() {}
    @Override public void push(Entity pEntity) {}
    @Override public void handleInsidePortal(BlockPos pPos) {}
    @Override public boolean isOnFire() {return false;}
    @Override public boolean isInLava() {return false;}
    @Override public boolean isInWater() {return false;}
    @Override public boolean isInWall() {return false;}
    @Override protected void spawnSprintParticle() {}
    @Override protected void spawnSoulSpeedParticle() {}
    @Override public AnimationFactory getFactory() {return this.factory;}
    @Override protected SoundEvent getSwimSound() {return null;}
    @Override protected SoundEvent getDeathSound() {return null;}
    @Override public void playSound(SoundEvent pSound, float pVolume, float pPitch) {}
    @Nullable @Override protected SoundEvent getAmbientSound() {return null;}
    @Override protected void playHurtSound(DamageSource pSource) {}
    @Override protected SoundEvent getHurtSound(DamageSource pDamageSource) {return null;}
    @Override protected void playStepSound(BlockPos pPos, BlockState pState) {}

}
