package dev.xylonity.knightquest.common.entity.boss;

import dev.xylonity.knightquest.common.ai.navigator.GroundNavigator;
import dev.xylonity.knightquest.common.entity.boss.ai.*;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Map;

public class NethermanEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final ServerBossEvent bossInfo = (ServerBossEvent)(new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private final Map<BlockPos, BlockState> changedBlocks = new HashMap<>();

    private final RawAnimation DEATH = RawAnimation.begin().thenPlay("death");
    private final RawAnimation SUMMONANIM = RawAnimation.begin().thenPlay("summon");
    private final RawAnimation WALKANIM = RawAnimation.begin().thenPlay("walk");
    private final RawAnimation IDLEANIM = RawAnimation.begin().thenPlay("idle");
    private final RawAnimation SPECIALATTACKANIM = RawAnimation.begin().thenPlay("attack_special");
    private final RawAnimation SPECIALATTACK2ANIM = RawAnimation.begin().thenPlay("attack_special2");
    private final RawAnimation SPECIALATTACK3ANIM = RawAnimation.begin().thenPlay("attack_special3");
    private final RawAnimation PHASE_SWITCH_2 = RawAnimation.begin().thenPlay("phase2");
    private final RawAnimation PHASE_SWITCH_3 = RawAnimation.begin().thenPlay("phase3");

    private static final EntityDataAccessor<Boolean> SUMMON = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> NOMOVEMENT = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_DOING_FLAME_ATTACK = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_DOING_SPECIAL_ATTACK2 = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_DOING_SPECIAL_ATTACK3 = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SHOULD_SEARCH_TARGET = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> PHASE = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REVERT_FLAME_ATTACK = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REVERT_SPECIAL_ATTACK2 = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REVERT_SPECIAL_ATTACK3 = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COUNTER_SWITCH_PHASE_2 = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> COUNTER_SWITCH_PHASE_3 = SynchedEntityData.defineId(NethermanEntity.class, EntityDataSerializers.INT);

    private boolean hasBeenSwitchedToPhase2 = false;
    private boolean hasBeenSwitchedToPhase3 = false;

    public NethermanEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level pLevel) {
        return new GroundNavigator(this, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 450D)
                .add(Attributes.ATTACK_DAMAGE, 16.0f)
                .add(Attributes.ATTACK_SPEED, 1.2f)
                .add(Attributes.MOVEMENT_SPEED, 0.8f)
                .add(Attributes.FOLLOW_RANGE, 75.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5.0).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new NethermanAttackGoal(this, 0.5f, true));
        this.goalSelector.addGoal(4, new NethermanTeleport2PlayerGoal(this));

        // Phase 1
        this.goalSelector.addGoal(2, new NethermanLavaTeleportGoal(this));
        this.goalSelector.addGoal(3, new NethermanFlameGoal(this));

        // Phase 2
        this.goalSelector.addGoal(2, new NethermanClonesGoal(this));
        this.goalSelector.addGoal(3, new NethermanIceGoal(this));

        // Phase 3
        this.goalSelector.addGoal(2, new NethermanProjectileChargesGoal(this));
        this.goalSelector.addGoal(3, new NethermanDarknessGoal(this));

        this.targetSelector.addGoal(1, new NethermanNearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        var maxHealth = this.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(KQConfigValues.NETHERMAN_HEALTH);
            this.setHealth((float) KQConfigValues.NETHERMAN_HEALTH);
        }

        var attackDamageAttribute = this.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            attackDamageAttribute.setBaseValue(KQConfigValues.NETHERMAN_DAMAGE);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    /**
     * Getters and setters for synched entity data.
     */

    public boolean getInvulnerability() {
        return this.entityData.get(INVULNERABLE);
    }

    public void setInvulnerability(boolean invulnerability) {
        this.entityData.set(INVULNERABLE, invulnerability);
    }

    public int getPhase() {
        return this.entityData.get(PHASE);
    }

    public void setPhase(int phase) {
        this.entityData.set(PHASE, phase);
    }

    public boolean getIsSummoning() {
        return this.entityData.get(SUMMON);
    }

    public void setIsSummoning(boolean summon) {
        this.entityData.set(SUMMON, summon);
    }

    public boolean getNoMovement() {
        return this.entityData.get(NOMOVEMENT);
    }

    public void setNoMovement(boolean noMovement) {
        this.entityData.set(NOMOVEMENT, noMovement);
    }

    public boolean getShouldSearchTarget() {
        return this.entityData.get(SHOULD_SEARCH_TARGET);
    }

    public void setShouldSearchTarget(boolean shouldSearchTarget) {
        this.entityData.set(SHOULD_SEARCH_TARGET, shouldSearchTarget);
    }

    public boolean getIsDoingFlameAttack() {
        return this.entityData.get(IS_DOING_FLAME_ATTACK);
    }

    public void setIsDoingFlameAttack(boolean isDoingFlameAttack) {
        this.entityData.set(IS_DOING_FLAME_ATTACK, isDoingFlameAttack);
    }

    public int getRevertFlameAttack() {
        return this.entityData.get(REVERT_FLAME_ATTACK);
    }

    public void setRevertFlameAttack(int revertFlameAttack) {
        this.entityData.set(REVERT_FLAME_ATTACK, revertFlameAttack);
    }

    public int getRevertSpecialAttack2() {
        return this.entityData.get(REVERT_SPECIAL_ATTACK2);
    }

    public void setRevertSpecialAttack2(int revertSpecialAttack) {
        this.entityData.set(REVERT_SPECIAL_ATTACK2, revertSpecialAttack);
    }

    public int getCounterSwitchPhase2() {
        return this.entityData.get(COUNTER_SWITCH_PHASE_2);
    }

    public void setCounterSwitchPhase2(int counterSwitchPhase2) {
        this.entityData.set(COUNTER_SWITCH_PHASE_2, counterSwitchPhase2);
    }

    public int getCounterSwitchPhase3() {
        return this.entityData.get(COUNTER_SWITCH_PHASE_3);
    }

    public void setCounterSwitchPhase3(int counterSwitchPhase3) {
        this.entityData.set(COUNTER_SWITCH_PHASE_3, counterSwitchPhase3);
    }

    public boolean getIsDoingSpecialAttack2() {
        return this.entityData.get(IS_DOING_SPECIAL_ATTACK2);
    }

    public void setIsDoingSpecialAttack2(boolean isDoingSpecialAttack) {
        this.entityData.set(IS_DOING_SPECIAL_ATTACK2, isDoingSpecialAttack);
    }

    public boolean getIsDoingSpecialAttack3() {
        return this.entityData.get(IS_DOING_SPECIAL_ATTACK3);
    }

    public void setIsDoingSpecialAttack3(boolean isDoingSpecialAttack) {
        this.entityData.set(IS_DOING_SPECIAL_ATTACK3, isDoingSpecialAttack);
    }

    public int getRevertSpecialAttack3() {
        return this.entityData.get(REVERT_SPECIAL_ATTACK3);
    }

    public void setRevertSpecialAttack3(int revertSpecialAttack) {
        this.entityData.set(REVERT_SPECIAL_ATTACK3, revertSpecialAttack);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PHASE, 1);
        this.entityData.define(INVULNERABLE, true);
        this.entityData.define(SUMMON, true);
        this.entityData.define(NOMOVEMENT, true);
        this.entityData.define(SHOULD_SEARCH_TARGET, false);
        this.entityData.define(IS_DOING_FLAME_ATTACK, false);
        this.entityData.define(IS_DOING_SPECIAL_ATTACK2, false);
        this.entityData.define(IS_DOING_SPECIAL_ATTACK3, false);
        this.entityData.define(REVERT_FLAME_ATTACK, 0);
        this.entityData.define(REVERT_SPECIAL_ATTACK2, 0);
        this.entityData.define(REVERT_SPECIAL_ATTACK3, 0);
        this.entityData.define(COUNTER_SWITCH_PHASE_2, 0);
        this.entityData.define(COUNTER_SWITCH_PHASE_3, 0);
    }

    @Override
    public void tick() {
        super.tick();

        // Summon animation ending
        if (!this.level().isClientSide() && tickCount == 100) {
            setIsSummoning(false);
            setNoMovement(false);
            setInvulnerability(false);
            setShouldSearchTarget(true);
        }

        // Syncs the health bar with the actual health
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());

        if (this.isOnFire()) {
            this.extinguishFire();
        }

        if (getNoMovement()) {
            this.getNavigation().stop();
            this.setDeltaMovement(Vec3.ZERO);
        }

        // Reverts the movement in case the SpecialAttack1 or 2 losses target while ticking
        if (getNoMovement()) {

            if (getIsDoingFlameAttack()) {
                setRevertFlameAttack(getRevertFlameAttack() + 1);
                if (getRevertFlameAttack() == 30) {
                    this.setNoMovement(false);
                    this.setIsDoingFlameAttack(false);
                    this.setRevertFlameAttack(0);
                }
            }

            if (getIsDoingSpecialAttack2()) {
                setRevertSpecialAttack2(getRevertSpecialAttack2() + 1);
                if (getRevertSpecialAttack2() == 30) {
                    this.setNoMovement(false);
                    this.setIsDoingSpecialAttack2(false);
                    this.setRevertSpecialAttack2(0);
                }
            }

            if (getIsDoingSpecialAttack3()) {
                setRevertSpecialAttack3(getRevertSpecialAttack3() + 1);
                if (getRevertSpecialAttack3() == 30) {
                    this.setNoMovement(false);
                    this.setIsDoingSpecialAttack3(false);
                    this.setRevertSpecialAttack3(0);
                }
            }

        }

        // Switch to second phase
        if (getHealth() < getMaxHealth() * 0.66 && getPhase() != 2 && getPhase() != 3) {
            setPhase(2);
            setNoMovement(true);
            setShouldSearchTarget(false);
            setTarget(null);
            setInvulnerability(true);
        }

        // Counter of the second phase animation
        if (getPhase() == 2 && getCounterSwitchPhase2() < 130) {
            setCounterSwitchPhase2(getCounterSwitchPhase2() + 1);
        }

        // Restore atts after switching to second phase
        if (getCounterSwitchPhase2() == 130 && !hasBeenSwitchedToPhase2) {
            setShouldSearchTarget(true);
            setNoMovement(false);
            setInvulnerability(false);
            hasBeenSwitchedToPhase2 = true;
        }

        // Switch to third phase
        if (getHealth() < getMaxHealth() * 0.33 && getPhase() != 3) {
            setPhase(3);
            setNoMovement(true);
            setShouldSearchTarget(false);
            setTarget(null);
            setInvulnerability(true);
        }

        // Counter of the third phase animation
        if (getPhase() == 3 && getCounterSwitchPhase3() < 160) {
            setCounterSwitchPhase3(getCounterSwitchPhase3() + 1);
        }

        // Restore atts after switching to third phase
        if (getCounterSwitchPhase3() == 160 && !hasBeenSwitchedToPhase3) {
            setShouldSearchTarget(true);
            setNoMovement(false);
            setInvulnerability(false);
            hasBeenSwitchedToPhase3 = true;
        }

    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        this.spawnAtLocation(new ItemStack(KnightQuestItems.CHAOTIC_ESSENCE.get()));
        if (this.getRandom().nextFloat() < 0.1) this.spawnAtLocation(new ItemStack(KnightQuestItems.THE_ARCHITECT_OF_CHAOS_DISC.get()));
    }

    /**
     * Restores the blocks converted into lava saved within the `changedBlocks` hashMap.
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
        if (KQConfigValues.RESTORE_BLOCKS_POST_DEATH && this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
            restoreBlocks();
    }

    /**
     * Saves every permuted block state per `NethermanLavaTeleportGoal` executed.
     */

    public void saveBlockState(BlockPos pos) {
        if (!changedBlocks.containsKey(pos)) {
            BlockState state = this.level().getBlockState(pos);
            changedBlocks.put(pos, state);
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

            if (KQConfigValues.TELEPORT_ON_HIT) teleport();

            return isDamaged;

        }
    }

    protected boolean teleport() {
        if (!this.level().isClientSide() && this.isAlive()) {
            double d = this.getX() + (this.random.nextDouble() - 0.5) * 40.0;
            double e = this.getY() + (double)(this.random.nextInt(64) - 32);
            double f = this.getZ() + (this.random.nextDouble() - 0.5) * 40.0;
            return this.teleport(d, e, f);
        } else {
            return false;
        }
    }

    private boolean teleport(double x, double y, double z) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(x, y, z);

        while(mutableBlockPos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(mutableBlockPos).blocksMotion()) {
            mutableBlockPos.move(Direction.DOWN);
        }

        BlockState blockState = this.level().getBlockState(mutableBlockPos);
        boolean bl = blockState.blocksMotion();
        boolean bl2 = blockState.getFluidState().is(FluidTags.WATER);
        if (bl && !bl2) {
            Vec3 vec3 = this.position();
            boolean bl3 = this.randomTeleport(x, y, z, true);
            if (bl3) {
                this.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
                this.level().playSound(null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return bl3;
        } else {
            return false;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WARDEN_DEATH;
    }

    /**
     * Handles and expands the elapsed time after the entity dies.
     */

    @Override
    protected void tickDeath() {
        ++this.deathTime;

        if (this.level() instanceof ServerLevel) {
            if (this.deathTime > 0 && this.deathTime % 10 == 0) {
                int award = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(this, this.lastHurtByPlayer, Mth.floor((float) KQConfigValues.EXPERIENCE_DROP_AMOUNT * 0.08F));
                ExperienceOrb.award((ServerLevel) this.level(), new Vec3(position().x, position().y + 2.5, position().z), award);
            }
        }

        if (this.deathTime >= 80 && !this.level().isClientSide() && !this.isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        this.tickCount = pCompound.getInt("tickCount");

        if (!pCompound.contains("shouldPlaySummonAnimation")) {
            this.setIsSummoning(true);
        } else {
            this.setIsSummoning(pCompound.getBoolean("shouldPlaySummonAnimation"));
        }

        if (!pCompound.contains("shouldSearchTarget")) {
            this.setShouldSearchTarget(false);
        } else {
            this.setShouldSearchTarget(pCompound.getBoolean("shouldSearchTarget"));
        }

        if (!pCompound.contains("isInvulnerable")) {
            this.setInvulnerability(true);
        } else {
            this.setInvulnerability(pCompound.getBoolean("isInvulnerable"));
        }

        if (!pCompound.contains("isNoMovement")) {
            this.setNoMovement(true);
        } else {
            this.setNoMovement(pCompound.getBoolean("isNoMovement"));
        }

        this.setCounterSwitchPhase2(pCompound.getInt("counterSwitchPhase2"));
        this.setCounterSwitchPhase3(pCompound.getInt("counterSwitchPhase3"));

        if (!pCompound.contains("phase")) {
            this.setPhase(1);
        } else {
            this.setPhase(pCompound.getInt("phase"));
        }

        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }

    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        pCompound.putInt("tickCount", this.tickCount);
        pCompound.putBoolean("shouldPlaySummonAnimation", this.getIsSummoning());
        pCompound.putBoolean("shouldSearchTarget", this.getShouldSearchTarget());
        pCompound.putBoolean("isInvulnerable", this.getInvulnerability());
        pCompound.putBoolean("isNoMovement", this.getNoMovement());
        pCompound.putInt("counterSwitchPhase2", this.getCounterSwitchPhase2());
        pCompound.putInt("counterSwitchPhase3", this.getCounterSwitchPhase3());
        pCompound.putInt("phase", this.getPhase());
    }

    @Override
    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        this.bossInfo.setName(this.getDisplayName());
    }

    public void startSeenByPlayer(@NotNull ServerPlayer pPlayer) {
        super.startSeenByPlayer(pPlayer);
        this.bossInfo.addPlayer(pPlayer);
    }

    public void stopSeenByPlayer(@NotNull ServerPlayer pPlayer) {
        super.stopSeenByPlayer(pPlayer);
        this.bossInfo.removePlayer(pPlayer);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movementController", this::movementPredicate));
        controllers.add(new AnimationController<>(this, "attackController", this::attackPredicate));
    }

    private <E extends GeoAnimatable> PlayState movementPredicate(AnimationState<E> event) {

        if (isDeadOrDying()) {
            event.getController().setAnimation(DEATH);
        } else if (getIsSummoning()) {
            event.getController().setAnimation(SUMMONANIM);
        } else if (getCounterSwitchPhase3() < 160 && getPhase() == 3) {
            event.getController().setAnimation(PHASE_SWITCH_3);
        } else if (getCounterSwitchPhase2() < 130 && getPhase() == 2) {
            event.getController().setAnimation(PHASE_SWITCH_2);
        } else if (getIsDoingSpecialAttack3()) {
            event.getController().setAnimation(SPECIALATTACK3ANIM);
        } else if (getIsDoingSpecialAttack2()) {
            event.getController().setAnimation(SPECIALATTACK2ANIM);
        } else if (getIsDoingFlameAttack()) {
            event.getController().setAnimation(SPECIALATTACKANIM);
        } else if (event.isMoving()) {
            event.getController().setAnimation(WALKANIM);
        } else {
            event.getController().setAnimation(IDLEANIM);
        }

        return PlayState.CONTINUE;

    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();

            String attackPattern = switch (random.nextInt(11)) {
                case 3, 4, 5 -> "attack2";
                case 6, 7, 8 -> "attack3";
                default -> "attack";
            };

            event.getController().setAnimation(RawAnimation.begin().thenPlay(attackPattern));

            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}