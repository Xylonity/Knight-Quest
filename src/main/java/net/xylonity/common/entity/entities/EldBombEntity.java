package net.xylonity.common.entity.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.xylonity.registry.KnightQuestParticles;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EldBombEntity extends CreeperEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int currentFuseTime;
    private int fuseTime = 30;
    private int explosionRadius = 3;

    public EldBombEntity(EntityType<? extends CreeperEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return CreeperEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 7.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new CreeperIgniteGoal(this));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, PolarBearEntity.class, 35.0F, 1.0, 0.5));
        this.goalSelector.add(4, new MeleeAttackGoal(this, 0.5, false));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.5));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new RevengeGoal(this));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

        if (this.currentFuseTime > 10) {
            event.getController().setAnimation(RawAnimation.begin().then("sneak", Animation.LoopType.LOOP));
        } else if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    public int getSwell() {
        return this.currentFuseTime;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public void tick() {
        if (this.isAlive()) {
            if (this.isIgnited()) {
                this.setFuseSpeed(1);
            }

            int i = this.getFuseSpeed();
            if (i > 0 && this.currentFuseTime == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
                this.emitGameEvent(GameEvent.PRIME_FUSE);
            }

            this.currentFuseTime += i;
            if (this.currentFuseTime < 0) {
                this.currentFuseTime = 0;
            }

            if (this.currentFuseTime >= this.fuseTime) {
                this.currentFuseTime = this.fuseTime;

                this.poisonNearbyPlayers();

                this.explode();
            }
        }

        super.tick();
    }

    private void explode() {
        if (!this.getWorld().isClient) {
            float power = this.shouldRenderOverlay() ? 2.5F : 1.0F;
            this.dead = true;
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius * power, World.ExplosionSourceType.MOB);

            if (this.getWorld() instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 6; i++) {
                    double particleX = this.getX();
                    double particleY = this.getY() + 1;
                    double particleZ = this.getZ();

                    ParticleS2CPacket packet = new ParticleS2CPacket(
                            KnightQuestParticles.POISON_PARTICLE,
                            true,
                            particleX, particleY, particleZ,
                            1.2F, 1.2F, 1.2F,
                            0.05F, 1
                    );

                    serverWorld.getPlayers().forEach(player -> player.networkHandler.sendPacket(packet));
                }

                float[] arrayX = {0.5F, -1, 1};
                float[] arrayZ = {1, 0, -0.5F};

                for (int i = 0; i < 3; i++) {
                    double particleX = this.getX() + arrayX[i];
                    double particleY = this.getY() + 0.2;
                    double particleZ = this.getZ() + arrayZ[i];

                    ParticleS2CPacket packet = new ParticleS2CPacket(
                            KnightQuestParticles.POISON_CLOUD_PARTICLE,
                            true,
                            particleX, particleY, particleZ,
                            0, 0.15F, 0,
                            1F, 1
                    );

                    serverWorld.getPlayers().forEach(player -> player.networkHandler.sendPacket(packet));
                }
            }

            this.discard();
        }
    }

    private void poisonNearbyPlayers() {
        this.getWorld().getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(3.5), player -> true)
                .forEach(player -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 160, 0)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }

}
