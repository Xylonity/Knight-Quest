package net.xylonity.knightquest.common.entity.boss;

import com.eliotlash.mclib.math.functions.limit.Min;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.knightquest.common.entity.boss.ai.MagicProjectileAttackGoal;
import net.xylonity.knightquest.common.entity.entities.GremlinEntity;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class NethermanEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private static final EntityDataAccessor<Byte> PHASE = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> ANIMATION_STRING = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private int explosionPower = 1;
    private boolean hasFronzenPlayers = false;
    private int tickCounterPhaseSwitch = 0;
    private boolean hasChangedPhase = false;
    private boolean specialAttack = false;
    private int specialAttackCounter = 0;
    private boolean specialAttackSound1 = true;

    public NethermanEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 35.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.63f)
                .add(Attributes.FOLLOW_RANGE, 35.0).build();
    }

    public boolean getInvulnerability() {
        return this.entityData.get(INVULNERABLE);
    }
    public void setInvulnerability(boolean invulnerability) {
        this.entityData.set(INVULNERABLE, invulnerability);
    }
    public int getPhase() { return this.entityData.get(PHASE); }
    public void setPhase(int phase) { this.entityData.set(PHASE, (byte) phase); }
    public void setCharging(boolean pCharging) { this.entityData.set(DATA_IS_CHARGING, pCharging); }
    public String getAnimationString() { return this.entityData.get(ANIMATION_STRING); }
    public void setAnimationString(String animationString) { this.entityData.set(ANIMATION_STRING, animationString); }
    public boolean getIsAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }
    public void setIsAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (getInvulnerability())
            return false;
        else
            return super.hurt(pSource, pAmount);
    }

    @Override
    protected void registerGoals() {

        //switch (getPhase()) {
        //    case 1:
        //        break;
        //    case 2:
        //        break;
        //    default:
        //         this.goalSelector.addGoal(1, new MagicProjectileAttackGoal(this));
        //        break;
        //}

        if (getIsAttacking())
            this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.5f, true));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public int getExplosionPower() {
        return explosionPower;
    }

    public void setAnimation(String controllerName, String animationName, int option) {
        long uniqueId = this.getId();
        AnimatableManager<?> manager = this.cache.getManagerForId(uniqueId);
        if (manager != null) {
            Map<String, ? extends AnimationController<?>> controllers = manager.getAnimationControllers();
            AnimationController<?> controller = controllers.get(controllerName);
            if (controller != null) {
                switch (option) {
                    case 1:
                        controller.setAnimation(RawAnimation.begin().then(animationName, Animation.LoopType.PLAY_ONCE));
                    case 2:
                        controller.setAnimation(RawAnimation.begin().then(animationName, Animation.LoopType.LOOP));
                    default:
                        controller.setAnimation(RawAnimation.begin().then("attack_teleport1", Animation.LoopType.PLAY_ONCE).then("attack_teleport2", Animation.LoopType.PLAY_ONCE));
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.bossInfo != null) {
            float progress = this.getHealth() / this.getMaxHealth();
            this.bossInfo.setProgress(progress);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.5F) {
            tickCounterPhaseSwitch++;
            if (!hasChangedPhase) {
                setInvulnerability(true);
                //setAnimation("controller", "phase_switch", 1);
                hasChangedPhase = !hasChangedPhase;
            }
        }

        if (tickCounterPhaseSwitch == 85) {
            ExplosiveConfig.spawnParticles(level(), getX(), getY() + 3.5, getZ(), 4, false, false);

            setPhase(3);

            level().playSound(null, blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
        } else if (tickCounterPhaseSwitch == 145) {
            setInvulnerability(false);
        }

        if (specialAttack) {

            if (specialAttackCounter == 20) {

                level().playLocalSound(blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f, false);

                int particleCount = 80;
                double particleSpeed = 0.3;
                double maxRadius = 2.0;
                for (int i = 0; i < particleCount; i++) {
                    if (random.nextInt(2) == 0) {
                        double baseAngle = 2 * Math.PI * i / particleCount;

                        double angleVariation = (random.nextDouble() - 0.5) * (Math.PI / 8);
                        double angle = baseAngle + angleVariation;

                        double radiusVariation = 1 + (random.nextDouble() - 0.5) * 0.5;
                        double radius = maxRadius * radiusVariation;

                        double offsetX = radius * Math.cos(angle);
                        double offsetZ = radius * Math.sin(angle);

                        double velocityVariation = (random.nextDouble() - 0.5) * 0.1;
                        double velocityX = (particleSpeed + velocityVariation) * Math.cos(angle);
                        double velocityZ = (particleSpeed + velocityVariation) * Math.sin(angle);

                        double heightVariation = (random.nextDouble() - 0.5) * 0.5;

                        double particleX = this.getX() + offsetX;
                        double particleY = this.getY() + 0.2 + heightVariation;
                        double particleZ = this.getZ() + offsetZ;

                        this.level().addParticle(ParticleTypes.CLOUD, particleX, particleY, particleZ, velocityX, 0.0, velocityZ);
                    }
                }

                Vec3 vec3 = this.position().add(0.0D, (double)1.6F, 0.0D);
                Vec3 vec31 = this.getEyePosition().subtract(vec3);
                Vec3 vec32 = vec31.normalize();

                for(int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                    Vec3 vec33 = vec3.add(vec32.scale((double)i));
                    level().addParticle(ParticleTypes.PORTAL, vec33.x, vec33.y - 2, vec33.z, 1, 0.0D, 0.0D);
                }

                //for (int i = 0; i < particleCount; i++) {
                //    if (random.nextInt(2) == 0) {
                //        double angleOffset = (2 * Math.PI / particleCount) * i;
                //        double xParticleOffset = 0.2 * Math.cos(angleOffset);
                //        double zParticleOffset = 0.2 * Math.sin(angleOffset);
                //        level().addParticle(ParticleTypes.SONIC_BOOM, this.getX() + xParticleOffset, this.getY() + 2, this.getZ() + zParticleOffset, 0.0, 2, 0.0);
                //    }
                //}

                //for (int i = 0; i < 60; i++) {

                //    double baseAngle = 2 * Math.PI * i / 60;

                //    double angleVariation = (new Random().nextDouble() - 0.5) * (Math.PI / 8);
                //    double angle = baseAngle + angleVariation;

                //    double offsetX = 4 * Math.cos(angle);
                //    double offsetZ = 4 * Math.sin(angle);

                //    level().addParticle(ParticleTypes.CLOUD, getX() + offsetX, getY(), getZ() + offsetZ, 0, 0, 0);
                //}

            }

            if (specialAttackCounter == 34) {

                level().playLocalSound(blockPosition(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.BLOCKS, 1f, 1f, false);

                int particleCount2 = 20;
                double particleSpeed2 = 0.05;
                double maxRadius2 = 2.0;
                for (int x = 0; x < particleCount2; x++) {
                    if (random.nextInt(2) == 0) {
                        double baseAngle = 2 * Math.PI * x / particleCount2;

                        double angleVariation = (random.nextDouble() - 0.5) * (Math.PI / 8);
                        double angle = baseAngle + angleVariation;

                        double radiusVariation = 1 + (random.nextDouble() - 0.5) * 0.5;
                        double radius = maxRadius2 * radiusVariation;

                        double offsetX = radius * Math.cos(angle);
                        double offsetZ = radius * Math.sin(angle);

                        double velocityVariation = (random.nextDouble() - 0.5) * 0.1;
                        double velocityX = (particleSpeed2 + velocityVariation) * Math.cos(angle);
                        double velocityZ = (particleSpeed2 + velocityVariation) * Math.sin(angle);

                        double heightVariation = (random.nextDouble() - 0.5) * 0.5;

                        double particleX = this.getX() + offsetX;
                        double particleY = this.getY() + 0.2 + heightVariation;
                        double particleZ = this.getZ() + offsetZ;

                        this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, particleX, particleY, particleZ, velocityX, 0.005, velocityZ);
                    }
                }

                specialAttack = !specialAttack;
                specialAttackCounter = 0;

                level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(5)).forEach(player -> {
                    Vec3 direction = player.position().subtract(this.position()).normalize().scale(1.5);
                    player.push(direction.x, direction.y + 0.5, direction.z);
                });

                setInvulnerability(false);

            }

            specialAttackCounter++;
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PHASE, (byte) 2);
        this.entityData.define(DATA_IS_CHARGING, false);
        this.entityData.define(INVULNERABLE, false);
        this.entityData.define(ANIMATION_STRING, "");
        this.entityData.define(IS_ATTACKING, true);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
        if (pCompound.contains("ExplosionPower", 99)) {
            this.explosionPower = pCompound.getByte("ExplosionPower");
        }
    }

    @Override
    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllers.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
        controllers.add(new AnimationController<>(this, "phasecontroller", 0, this::phasePredicate));
    }

    private PlayState phasePredicate(AnimationState<?> event) {

        if (this.getHealth() < this.getMaxHealth() * 0.5F && !hasChangedPhase) {
            event.getController().setAnimation(RawAnimation.begin().then("phase_switch", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState predicate(AnimationState<?> event) {

        //event.getController().setAnimation(RawAnimation.begin().then("rotation", Animation.LoopType.LOOP));
        //event.getController().setAnimation(RawAnimation.begin().then("phase_switch", Animation.LoopType.LOOP));

        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        if (this.getHealth() < this.getMaxHealth() * 0.5F) {
            //event.getController().setAnimation(RawAnimation.begin().then("phase_switch", Animation.LoopType.PLAY_ONCE));
            //event.getController().setAnimation(RawAnimation.begin().then("attack_teleport1", Animation.LoopType.PLAY_ONCE).then("attack_teleport2", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();

            // Lower probability for the special attack

            String attackPattern = switch (new Random().nextInt(13)) {
                case 0, 1, 2, 3 -> "attack";
                case 4, 5, 6, 7 -> "attack2";
                case 8, 9, 10, 11 -> "attack3";
                default -> "specialAttack";
            };

            if (attackPattern.equals("specialAttack")) {
                specialAttack = true;
                event.getController().setAnimation(RawAnimation.begin().then("attack_teleport1", Animation.LoopType.PLAY_ONCE).then("attack_teleport2", Animation.LoopType.PLAY_ONCE));
            } else
                event.getController().setAnimation(RawAnimation.begin().then(attackPattern, Animation.LoopType.PLAY_ONCE));

            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void startSeenByPlayer(@NotNull ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossInfo.addPlayer(pPlayer);
    }

    public void stopSeenByPlayer(@NotNull ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossInfo.removePlayer(pPlayer);
    }

}