package net.xylonity.common.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
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
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.xylonity.common.api.explosiveenhancement.ExplosiveConfig;
import net.xylonity.common.api.util.ParticleGenerator;
import net.xylonity.common.api.util.TeleportValidator;
import net.xylonity.common.entity.boss.ai.*;
import net.xylonity.registry.KnightQuestParticles;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NethermanEntity extends HostileEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ServerBossBar bossInfo = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.RED, BossBar.Style.PROGRESS)).setDarkenSky(true);
    private static final TrackedData<Byte> PHASE = DataTracker.registerData(NethermanEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> DATA_IS_CHARGING = DataTracker.registerData(NethermanEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> INVULNERABLE = DataTracker.registerData(NethermanEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_ATTACKING = DataTracker.registerData(NethermanEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
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

    public NethermanEntity(EntityType<? extends HostileEntity> pEntityType, World pLevel) {
        super(pEntityType, pLevel);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 350.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.70f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 0.5f, true));

        // Phase 1
        this.goalSelector.add(1, new NethermanLavaTeleportGoal(this));
        this.goalSelector.add(3, new NethermanFlameGoal(this));

        // Phase 2
        this.goalSelector.add(4, new SpawnNethermanClonesGoal(this));

        // Phase 3
        // TODO: For some reason the projectile doesn't react to anything, marked for a potential fix someday
        //this.goalSelector.add(5, new MagicProjectileAttackGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    /**
     * Getters and setters for synched entity data.
     */

    public boolean getInvulnerability() { return this.dataTracker.get(INVULNERABLE); }
    public void setInvulnerability(boolean invulnerability) { this.dataTracker.set(INVULNERABLE, invulnerability); }
    public int getPhase() { return this.dataTracker.get(PHASE); }
    public void setPhase(int phase) { this.dataTracker.set(PHASE, (byte) phase); }
    public void setCharging(boolean pCharging) { this.dataTracker.set(DATA_IS_CHARGING, pCharging); }
    public boolean getIsAttacking() { return this.dataTracker.get(IS_ATTACKING); }
    public void setIsAttacking(boolean attacking) { this.dataTracker.set(IS_ATTACKING, attacking); }

    /**
     * Main logic of the Netherman, handles each phase. The counters may appear to be equal to "random" numbers,
     * but they actually represent the elapsed ticks from the start of each animation.
     */

    @Override
    public void tick() {
        super.tick();

        if (isOnFire() && this.getPhase() == 1) {
            this.extinguish();
        }

        if (this.bossInfo != null) {
            float progress = this.getHealth() / this.getMaxHealth();
            this.bossInfo.setPercent(progress);
        }

        if (getPhase() == 3 && age % 40 == 0) {
            summonLightning();
        }

        if (noMovement) {
            this.getNavigation().stop();
            this.setVelocity(Vec3d.ZERO);
        }

        if (age == 1) {
            ExplosiveConfig.spawnParticles(getWorld(), getX(), getY() + 0.5, getZ(), 4, false, false, 0);
            getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
        }

        if (this.getHealth() < this.getMaxHealth() * 0.66F && getPhase() == 1) {
            tickCounterFirstPhaseSwitch++;
            if (!hasChangedPhase) {
                setNoMovement(true);

                hasChangedPhase = !hasChangedPhase;
            }

            if (tickCounterFirstPhaseSwitch == 0)
                ExplosiveConfig.spawnParticles(getWorld(), getX(), getY(), getZ(), 3, false, false, 1);

            if (tickCounterFirstPhaseSwitch < 195) {
                winterStormAttack();
            } else {
                setNoMovement(false);

                getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
                ExplosiveConfig.spawnParticles(getWorld(), getX(), getY(), getZ(), 4, false, false, 1);

                setPhase(2);
            }
        }

        if (this.getHealth() < this.getMaxHealth() * 0.33F) {
            tickCounterSecondPhaseSwitch++;
            if (!hasChangedSecondPhase) {

                setInvulnerability(true);
                setNoMovement(true);

                hasChangedSecondPhase = !hasChangedSecondPhase;

                if (!weatherChanged && this.getWorld() instanceof ServerWorld serverLevel) {
                    if (!serverLevel.isThundering()) {
                        serverLevel.setWeather(0, 24000, true, true);
                        weatherChanged = !weatherChanged;
                    }
                }

            }
        }

        if (tickCounterSecondPhaseSwitch == 85) {
            ExplosiveConfig.spawnParticles(getWorld(), getX(), getY() + 3.5, getZ(), 4, false, false, 2);

            setPhase(3);

            getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.BLOCKS, 1f, 1f);
        } else if (tickCounterSecondPhaseSwitch == 145) {
            setNoMovement(false);
            setInvulnerability(false);
        }

        if (specialAttack) {

            if (specialAttackCounter == 20) {

                getWorld().playSoundAtBlockCenter(getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1f, 1f, false);

                ParticleGenerator.specialAttackParticles(this, 80, 0.3, 2.0, 0.0, ParticleTypes.CLOUD);

                Vec3d vec3 = this.getPos().add(0.0D, 1.6F, 0.0D);
                Vec3d vec31 = this.getEyePos().subtract(vec3);
                Vec3d vec32 = vec31.normalize();

                for (int i = 1; i < Math.floor(vec31.length()) + 7; ++i) {
                    Vec3d vec33 = vec3.add(vec32.add(Vec3d.unpackRgb(i)));
                    getWorld().addParticle(ParticleTypes.PORTAL, vec33.x, vec33.y - 2, vec33.z, 1, 0.0D, 0.0D);
                }

            }

            if (specialAttackCounter == 34) {

                getWorld().playSoundAtBlockCenter(getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.BLOCKS, 1f, 1f, false);

                ParticleGenerator.specialAttackParticles(this, 20, 0.05, 2.0, 0.005, ParticleTypes.CAMPFIRE_COSY_SMOKE);

                specialAttack = !specialAttack;
                specialAttackCounter = 0;

                getWorld().getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(5), PlayerEntity::isPlayer).forEach(player -> {
                    Vec3d direction = player.getPos().subtract(this.getPos()).normalize().multiply(1.5);
                    player.addVelocity(direction.x, direction.y + 0.5, direction.z);
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
        net.minecraft.util.math.random.Random random = this.getRandom();

        for (int attempt = 0; attempt < 50 && target != null; attempt++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = 5 + random.nextDouble() * 15;
            double x = target.getX() + Math.cos(angle) * distance;
            double z = target.getZ() + Math.sin(angle) * distance;
            double y = target.getY() + (random.nextDouble() - 0.5) * 2;

            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);
            if (TeleportValidator.isValidTeleportPosition(this, targetPos)) {
                for (PlayerEntity player : this.getWorld().getPlayers()) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        ParticleS2CPacket particlePacket = new ParticleS2CPacket(
                                ParticleTypes.PORTAL,
                                true, // longDistance
                                this.getParticleX(0.5D),
                                this.getRandomBodyY() - 0.25D,
                                this.getParticleZ(0.5D),
                                (float) ((this.random.nextDouble() - 0.5D) * 2.0D),
                                (float) -this.random.nextDouble(),
                                (float) ((this.random.nextDouble() - 0.5D) * 2.0D),
                                0.2f,
                                1
                        );
                        serverPlayer.networkHandler.sendPacket(particlePacket);
                    }
                }

                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
                this.updatePosition(x, y, z);
                this.getWorld().playSound(null, getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
                return;

            } else if (bestPos == null || TeleportValidator.isBetterPosition(this, targetPos, bestPos)) {
                bestPos = targetPos;
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (getInvulnerability()
                || ((source.isOf(DamageTypes.ON_FIRE) || source.isOf(DamageTypes.IN_FIRE) || source.isOf(DamageTypes.LAVA)) && this.getPhase() == 1)
                    || (source.isOf(DamageTypes.LIGHTNING_BOLT) && this.getPhase() == 3)
                        || ((source.isOf(DamageTypes.EXPLOSION) || source.isOf(DamageTypes.PLAYER_EXPLOSION)))
                            || (age < 40))
            return false;
        else {

            boolean isDamaged = super.damage(source, amount);

            // Prevents teleporting when the incoming source kills the Netherman

            if (isDamaged && amount < this.getHealth() && new Random().nextInt(0, 2) == 1) {
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
            this.getWorld().setBlockState(entry.getKey(), entry.getValue(), 3);
        }
        changedBlocks.clear();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        restoreBlocks();
    }

    /**
     * Saves every block state per `NethermanLavaTeleportGoal` executed.
     */

    public void saveBlockState(BlockPos pos) {
        if (!changedBlocks.containsKey(pos)) {
            BlockState state = this.getWorld().getBlockState(pos);
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
        Box area = new Box(
                this.getX() - range, this.getY() - range, this.getZ() - range,
                this.getX() + range, this.getY() + range, this.getZ() + range
        );
        List<PlayerEntity> players = getWorld().getEntitiesByClass(PlayerEntity.class, area, entity -> true);

        for (PlayerEntity player : players) {
            if (this.canSee(player))
                player.setFrozenTicks(player.getFrozenTicks() + 4);
        }

        int particleCount = 60;
        double particleSpeed = 1.5;
        double time = this.age / 20.0;

        for (int i = 0; i < particleCount; i++) {
            double offset = i * 0.1;

            double angle = 2 * Math.PI * i / particleCount + time;

            double velocityX = particleSpeed * Math.cos(angle + offset);
            double velocityZ = particleSpeed * Math.sin(angle + offset);
            double velocityY = 0.5 * Math.sin(2 * Math.PI * i / particleCount + time);

            double particleX = this.getX();
            double particleY = this.getY() + 2.2;
            double particleZ = this.getZ();
            this.getWorld().addParticle(KnightQuestParticles.SNOWFLAKE_PARTICLE, particleX, particleY, particleZ, velocityX, velocityY, velocityZ);
        }

        if (age % 4 == 0)
            getWorld().playSoundAtBlockCenter(getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.BLOCKS, 1f, 1f, false);
    }

    /**
     * Prevents the entity from moving per tick.
     */

    public void setNoMovement(boolean noMovement) {
        this.noMovement = noMovement;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WARDEN_DEATH;
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
            lightningPos = this.getBlockPos().add((int) offsetX, 0, (int) offsetZ);
        } while (lightningPos.isWithinDistance(this.getBlockPos(), 2));

        if (this.getWorld() instanceof ServerWorld serverLevel) {
            LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
            assert lightningBolt != null;
            lightningBolt.setPosition(lightningPos.getX(), lightningPos.getY(), lightningPos.getZ());
            serverLevel.spawnEntity(lightningBolt);
        }
    }

    /**
     * Handles and expands the elapsed time after the entity dies.
     */

    @Override
    protected void updatePostDeath() {
        ++this.deathTime;

        if (!weatherReverted && this.getWorld() instanceof ServerWorld serverLevel) {
            serverLevel.setWeather(0, 0, false, false);
            weatherReverted = !weatherReverted;
        }

        if (this.getWorld() instanceof ServerWorld) {
            if (this.deathTime > 0 && this.deathTime % 5 == 0) {
                ExperienceOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), MathHelper.floor((float)500 * 0.08F));
            }
        }

        if (this.deathTime >= 40 && !this.getWorld().isClient() && !this.isRemoved()) {
            this.getWorld().sendEntityStatus(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(PHASE, (byte) 1);
        builder.add(DATA_IS_CHARGING, false);
        builder.add(INVULNERABLE, false);
        builder.add(IS_ATTACKING, false);
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

        if (this.isDead()) {
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

        if (this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
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

            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossInfo.removePlayer(player);
    }

}