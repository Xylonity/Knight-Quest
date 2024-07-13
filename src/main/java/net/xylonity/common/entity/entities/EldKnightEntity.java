package net.xylonity.common.entity.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.xylonity.registry.KnightQuestParticles;
import net.xylonity.registry.KnightQuestEntities;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EldKnightEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private World serverWorld;
    private boolean summoned = false;
    private int counter;

    public EldKnightEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.serverWorld = world;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 90.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.4f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.45D, false));
        this.goalSelector.add(2, new RevengeGoal(this, new Class[0]));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(5, new SwimGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new WanderNearTargetGoal(this, 0.7D, 15));

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        if (this.dead) {
            event.getController().setAnimation(RawAnimation.begin().then("death", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15F, 1.0F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }

    @Override
    public void tick() {
        super.tick();
        counter++;
        if (!summoned && this.getHealth() < this.getMaxHealth() * 0.5) {
            summoned = true;
            summonMinions();

        }

        if (summoned && counter > 80) {

            if (true) {
                summonParticle();
                poisonNearbyPlayers();
            }

            if (this.getHealth() < this.getMaxHealth() * 0.75) {
                this.heal(3);
            }

            counter = 0;
        }
    }

    private void poisonNearbyPlayers() {
        this.serverWorld.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(3.5), PlayerEntity::isPlayer).forEach(player -> {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1));
        });
    }

    private void summonParticle() {
        serverWorld.addParticle(KnightQuestParticles.STARSET_PARTICLE, this.getX(), getY() - 0.48, getZ(), 4d, 0d, 0d);
    }

    @Override
    public boolean damage(DamageSource pSource, float pAmount) {
        if (pSource.getType().equals(DamageTypes.PLAYER_EXPLOSION) || pSource.getType().equals(DamageTypes.EXPLOSION)) {
            return super.damage(pSource, pAmount * 0.15f);
        }

        if (pSource.getType().equals(DamageTypes.ARROW)) {
            return super.damage(pSource, pAmount * 0.10f);
        }

        return super.damage(pSource, pAmount);
    }

    private void summonMinions() {
        double distance = 3.0;
        double angle = (1 != 0) ? Math.toRadians((double) 360 / 3) : Math.toRadians(120);
        boolean punch = false;

        for (int i = 0; i < 3; i++) {
            double xOffset = distance * Math.cos(angle * i);
            double zOffset = distance * Math.sin(angle * i);
            EldBombEntity entity = KnightQuestEntities.ELDBOMB.create(serverWorld);
            if (entity != null) {
                BlockPos spawnPos = this.getBlockPos().add((int) xOffset, 0, (int) zOffset);

                if (!serverWorld.getBlockState(spawnPos).isAir()) {
                    for (int d = 1; d <= distance; d++) {
                        BlockPos adjustedPos = this.getBlockPos().add((int) (xOffset / d), 0, (int) (zOffset / d));
                        if (serverWorld.getBlockState(adjustedPos).isAir()) {
                            spawnPos = adjustedPos;
                            break;
                        }
                    }
                }

                entity.refreshPositionAndAngles(spawnPos, 0.0F, 0.0F);
                serverWorld.spawnEntity(entity);

                for (int j = 0; j < 20; j++) {
                    serverWorld.addParticle(ParticleTypes.EFFECT, spawnPos.getX() + 0.5 + serverWorld.random.nextDouble() - 0.5,
                            spawnPos.getY() + 0.5 + serverWorld.random.nextDouble() - 0.5,
                            spawnPos.getZ() + 0.5 + serverWorld.random.nextDouble() - 0.5, 0, 0, 0);
                }

                if (!punch) {
                    serverWorld.getEntitiesByClass(PlayerEntity.class, entity.getBoundingBox().expand(5), PlayerEntity::isPlayer).forEach(player -> {
                        Vec3d direction = player.getPos().subtract(this.getPos()).normalize().multiply(1.5);
                        player.addVelocity(direction.x, direction.y + 0.5, direction.z);
                    });
                    punch = true;
                }

            }

        }

        int particleCount = 120;
        double particleRadius = 3.5;

        for (int i = 0; i < particleCount; i++) {
            double angleOffset = (2 * Math.PI / particleCount) * i;
            double xParticleOffset = particleRadius * Math.cos(angleOffset);
            double zParticleOffset = particleRadius * Math.sin(angleOffset);
            serverWorld.addParticle(ParticleTypes.CLOUD, this.getX() + xParticleOffset, this.getY() - 0.1, this.getZ() + zParticleOffset, 0.0, 0.05, 0.0);
        }

        serverWorld.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, SoundCategory.BLOCKS, 1f, 1f);
    }

}
