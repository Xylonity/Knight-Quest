package net.xylonity.knightquest.common.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.knightquest.common.api.util.ParticleGenerator;
import net.xylonity.knightquest.common.api.util.TeleportValidator;
import net.xylonity.knightquest.common.entity.boss.ai.*;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class NethermanEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(Objects.requireNonNull(this.getDisplayName()), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private static final EntityDataAccessor<Byte> PHASE = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private final Map<BlockPos, BlockState> changedBlocks = new HashMap<>();    // Restores the previous blocks after they are switched with lava
    private int explosionPower = 1;
    private int tickCounterFirstPhaseSwitch = 0;
    private int tickCounterSecondPhaseSwitch = 0;
    private boolean hasChangedPhase = false;
    private boolean hasChangedSecondPhase = false;
    private boolean specialAttack = false;
    private int specialAttackCounter = 0;
    private boolean noMovement = false;
    private boolean weatherChanged = false;
    private boolean weatherReverted = false;

    public NethermanEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 350.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.70f)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5.0).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new NethermanAttackGoal(this, 0.5f, true));

        // Phase 1
        this.goalSelector.addGoal(2, new NethermanLavaTeleportGoal(this));
        this.goalSelector.addGoal(3, new NethermanFlameGoal(this));

        // Phase 2
        this.goalSelector.addGoal(2, new SpawnNethermanClonesGoal(this));

        // Phase 3
        this.goalSelector.addGoal(2, new MagicProjectileAttackGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    /**
     * Getters and setters for synched entity data.
     */

    public boolean getInvulnerability() { return this.entityData.get(INVULNERABLE); }
    public void setInvulnerability(boolean invulnerability) { this.entityData.set(INVULNERABLE, invulnerability); }
    public int getPhase() { return this.entityData.get(PHASE); }
    public void setPhase(int phase) { this.entityData.set(PHASE, (byte) phase); }
    public void setCharging(boolean pCharging) { this.entityData.set(DATA_IS_CHARGING, pCharging); }
    public boolean getIsAttacking() { return this.entityData.get(IS_ATTACKING); }
    public void setIsAttacking(boolean attacking) { this.entityData.set(IS_ATTACKING, attacking); }

    /**
     * Main logic of the Netherman, handles each phase. The counters may appear to be equal to "random" numbers,
     * but they actually represent the elapsed ticks from the start of each animation.
     */

    @Override
    public void tick() {
        super.tick();

        if (isOnFire() && this.getPhase() == 1) {
            this.extinguishFire();
        }

        float progress = this.getHealth() / this.getMaxHealth();
        this.bossInfo.setProgress(progress);

        if (getPhase() == 3 && tickCount % 40 == 0) {
            summonLightning();
        }

        if (noMovement) {
            this.getNavigation().stop();
            this.setDeltaMovement(Vec3.ZERO);
        }

        if (tickCount == 1) {
            ExplosiveConfig.spawnParticles(level(), getX(), getY() + 0.5, getZ(), 4, false, false, 0);
            level().playSound(null, blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.66F && getPhase() == 1) {
            tickCounterFirstPhaseSwitch++;
            if (!hasChangedPhase) {
                setNoMovement(true);

                hasChangedPhase = !hasChangedPhase;
            }

            if (tickCounterFirstPhaseSwitch == 0)
                ExplosiveConfig.spawnParticles(level(), getX(), getY(), getZ(), 3, false, false, 1);

            if (tickCounterFirstPhaseSwitch < 195) {
                winterStormAttack();
            } else {
                setNoMovement(false);

                level().playSound(null, blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
                ExplosiveConfig.spawnParticles(level(), getX(), getY(), getZ(), 4, false, false, 1);

                setPhase(2);
            }
        }

        if (this.getHealth() < this.getMaxHealth() * 0.33F) {
            tickCounterSecondPhaseSwitch++;
            if (!hasChangedSecondPhase) {

                setInvulnerability(true);
                setNoMovement(true);

                hasChangedSecondPhase = !hasChangedSecondPhase;

                if (!weatherChanged && this.level() instanceof ServerLevel serverLevel) {
                    if (!serverLevel.isThundering()) {
                        serverLevel.setWeatherParameters(0, 24000, true, true);
                        weatherChanged = !weatherChanged;
                    }
                }

            }
        }

        if (tickCounterSecondPhaseSwitch == 85) {
            ExplosiveConfig.spawnParticles(level(), getX(), getY() + 3.5, getZ(), 4, false, false, 2);

            setPhase(3);

            level().playSound(null, blockPosition(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
        } else if (tickCounterSecondPhaseSwitch == 145) {
            setNoMovement(false);
            setInvulnerability(false);
        }

        if (specialAttack) {

            if (specialAttackCounter == 20) {

                level().playLocalSound(blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f, false);

                ParticleGenerator.specialAttackParticles(this, 80, 0.3, 2.0, 0.0, ParticleTypes.CLOUD);

                Vec3 vec3 = this.position().add(0.0D, 1.6F, 0.0D);
                Vec3 vec31 = this.getEyePosition().subtract(vec3);
                Vec3 vec32 = vec31.normalize();

                for (int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                    Vec3 vec33 = vec3.add(vec32.scale(i));
                    level().addParticle(ParticleTypes.PORTAL, vec33.x, vec33.y - 2, vec33.z, 1, 0.0D, 0.0D);
                }

            }

            if (specialAttackCounter == 34) {

                level().playLocalSound(blockPosition(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.BLOCKS, 1f, 1f, false);

                ParticleGenerator.specialAttackParticles(this, 20, 0.05, 2.0, 0.005, ParticleTypes.CAMPFIRE_COSY_SMOKE);

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

    /**
     * Attempts 50 times to teleport the Netherman, checking if a position is favourable or not.
     */

    private void teleportAroundTarget() {
        BlockPos bestPos = null;
        Entity target = this.getTarget();
        RandomSource random = this.getRandom();

        for (int attempt = 0; attempt < 50 && target != null; attempt++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = 5 + random.nextDouble() * 15;
            double x = target.getX() + Math.cos(angle) * distance;
            double z = target.getZ() + Math.sin(angle) * distance;
            double y = target.getY() + (random.nextDouble() - 0.5) * 2;

            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);
            if (TeleportValidator.isValidTeleportPosition(this, targetPos)) {
                for (Player player : this.level().players()) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        for (int u = 0; u < 20; ++u) {
                            serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                    ParticleTypes.PORTAL,
                                    true,
                                    this.getRandomX(0.5D),
                                    this.getRandomY() - 0.25D,
                                    this.getRandomZ(0.5D),
                                    (float) ((this.random.nextDouble() - 0.5D) * 2.0D),
                                    (float) -this.random.nextDouble(),
                                    0.2f,
                                    0.0f,
                                    1
                            ));
                        }
                    }
                }

                this.level().playSound(null, this.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f);
                this.teleportTo(x, y, z);
                this.level().playSound(null, blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f);
                return;

            } else if (bestPos == null || TeleportValidator.isBetterPosition(this, targetPos, bestPos)) {
                bestPos = targetPos;
            }
        }
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        if (getInvulnerability()
                || ((pSource.is(DamageTypes.ON_FIRE) || pSource.is(DamageTypes.IN_FIRE) || pSource.is(DamageTypes.LAVA)) && this.getPhase() == 1)
                    || (pSource.is(DamageTypes.LIGHTNING_BOLT) && this.getPhase() == 3)
                        || ((pSource.is(DamageTypes.EXPLOSION) || pSource.is(DamageTypes.PLAYER_EXPLOSION)))
                            || (tickCount < 40))
            return false;
        else {

            boolean isDamaged = super.hurt(pSource, pAmount);

            // Prevents teleporting when the incoming source kills the Netherman

            if (isDamaged && pAmount < this.getHealth() && new Random().nextInt(0, 2) == 1) {
                teleportAroundTarget();
            }

            return isDamaged;

        }
    }

    /**
     * Restores the blocks converted into lava saved within then `changedBlocks` hashMap.
     */

    private void restoreBlocks() {
        for (Map.Entry<BlockPos, BlockState> entry : changedBlocks.entrySet()) {
            this.level().setBlock(entry.getKey(), entry.getValue(), 3);
        }
        changedBlocks.clear();
    }

    @Override
    public void die(@NotNull DamageSource pDamageSource) {
        super.die(pDamageSource);
        restoreBlocks();
    }

    /**
     * Saves every block state per `NethermanLavaTeleportGoal` executed.
     */

    public void saveBlockState(BlockPos pos) {
        if (!changedBlocks.containsKey(pos)) {
            BlockState state = this.level().getBlockState(pos);
            changedBlocks.put(pos, state);
        }
    }

    public int getExplosionPower() {
        return explosionPower;
    }

    public AnimationController<?> getPhaseController() {
        return this.cache.getManagerForId(this.getId()).getAnimationControllers().get("phasecontroller");
    }

    /**
     * Performs a "snowy" attack generating a bunch of particles. Also, freezes players in the line of sight of the Netherman.
     */

    private void winterStormAttack() {

        double range = 26.0;
        AABB area = new AABB(this.getX() - range, this.getY() - range, this.getZ() - range, this.getX() + range, this.getY() + range, this.getZ() + range);
        List<Player> players = this.level().getEntitiesOfClass(Player.class, area);

        for (Player player : players) {
            if (this.hasLineOfSight(player))
                player.setTicksFrozen(player.getTicksFrozen() + 4);
        }

        int particleCount = 60;
        double particleSpeed = 1.5;
        double time = this.tickCount / 20.0;

        for (int i = 0; i < particleCount; i++) {
            double offset = i * 0.1;

            double angle = 2 * Math.PI * i / particleCount + time;

            double velocityX = particleSpeed * Math.cos(angle + offset);
            double velocityZ = particleSpeed * Math.sin(angle + offset);
            double velocityY = 0.5 * Math.sin(2 * Math.PI * i / particleCount + time);

            double particleX = this.getX();
            double particleY = this.getY() + 2.2;
            double particleZ = this.getZ();
            this.level().addParticle(KnightQuestParticles.SNOWFLAKE_PARTICLE.get(), particleX, particleY, particleZ, velocityX, velocityY, velocityZ);
        }

        if (tickCount % 4 == 0)
            level().playLocalSound(blockPosition(), SoundEvents.WARDEN_ATTACK_IMPACT, SoundSource.BLOCKS, 1f, 1f, false);
    }

    /**
     * Prevents the entity from moving per tick.
     */

    public void setNoMovement(boolean noMovement) {
        this.noMovement = noMovement;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;
    }

    /**
     * Generates a lighting that won't land on the Netherman itself but in a random position.
     */

    private void summonLightning() {
        Random random = new Random();
        double offsetX, offsetZ;
        BlockPos lightningPos;

        do {
            offsetX = (random.nextDouble() - 0.5) * 2 * 20;
            offsetZ = (random.nextDouble() - 0.5) * 2 * 20;
            lightningPos = this.blockPosition().offset((int) offsetX, 0, (int) offsetZ);
        } while (lightningPos.closerThan(this.blockPosition(), 2));

        if (this.level() instanceof ServerLevel serverLevel) {
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
            assert lightningBolt != null;
            lightningBolt.moveTo(lightningPos.getX(), lightningPos.getY(), lightningPos.getZ());
            serverLevel.addFreshEntity(lightningBolt);
        }
    }

    /**
     * Handles and expands the elapsed time after the entity dies.
     */

    @Override
    protected void tickDeath() {
        ++this.deathTime;

        if (!weatherReverted && this.level() instanceof ServerLevel serverLevel) {
            serverLevel.setWeatherParameters(0, 0, false, false);
            weatherReverted = !weatherReverted;
        }

        if (this.level() instanceof ServerLevel) {
            if (this.deathTime > 0 && this.deathTime % 5 == 0) {
                assert this.lastHurtByPlayer != null;
                int award = net.neoforged.neoforge.event.EventHooks.getExperienceDrop(this, this.lastHurtByPlayer, Mth.floor((float) 500 * 0.08F));
                ExperienceOrb.award((ServerLevel) this.level(), this.position(), award);
            }
        }

        if (this.deathTime >= 40 && !this.level().isClientSide() && !this.isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(PHASE, (byte) 1);
        pBuilder.define(DATA_IS_CHARGING, false);
        pBuilder.define(INVULNERABLE, false);
        pBuilder.define(IS_ATTACKING, false);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (this.hasCustomName()) {
            this.bossInfo.setName(Objects.requireNonNull(this.getDisplayName()));
        }

        if (pCompound.contains("ExplosionPower", 99)) {
            this.explosionPower = pCompound.getByte("ExplosionPower");
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllers.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
        controllers.add(new AnimationController<>(this, "phasecontroller", 0, this::secondPhasePredicate));
        controllers.add(new AnimationController<>(this, "rotationcontroller", 0, this::rotationPredicate));
        controllers.add(new AnimationController<>(this, "deadcontroller", 0, this::deadPredicate));
    }

    private PlayState deadPredicate(AnimationState<?> event) {

        if (this.isDeadOrDying()) {
            event.getController().setAnimation(RawAnimation.begin().then("dead", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState rotationPredicate(AnimationState<?> event) {

        if (this.getHealth() < this.getMaxHealth() * 0.66F && !hasChangedPhase) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("rotation", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState secondPhasePredicate(AnimationState<?> event) {

        if (this.getHealth() < this.getMaxHealth() * 0.33F && !hasChangedSecondPhase) {
            event.getController().setAnimation(RawAnimation.begin().then("phase_switch", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;

    }

    private PlayState predicate(AnimationState<?> event) {

        if (getIsAttacking() && (getHealth() > getMaxHealth() * 0.70 || (getHealth() < getMaxHealth() * 0.60 && getHealth() > getMaxHealth() * 0.45) || getHealth() < getMaxHealth() * 0.30)) {
            event.getController().setAnimation(RawAnimation.begin().then("teleport_charge", Animation.LoopType.PLAY_ONCE));
        }
        else if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();

            // Lower probability for the special attack

            String attackPattern = switch (random.nextInt(11)) {
                case 0, 1, 2 -> "attack";
                case 3, 4, 5 -> "attack2";
                case 6, 7, 8 -> "attack3";
                default -> "specialAttack";
            };

            if (attackPattern.equals("specialAttack") && getPhase() != 3 && (getHealth() > getMaxHealth() * 0.70 || (getHealth() < getMaxHealth() * 0.60 && getHealth() > getMaxHealth() * 0.4))) {
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

    @Override
    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossInfo.setName(Objects.requireNonNull(this.getDisplayName()));
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