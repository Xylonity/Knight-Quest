package dev.xylonity.knightquest.common.entity.entities;

import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Arrays;

public class FallenKnightEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public FallenKnightEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return Arrays.asList(this.getItemBySlot(EquipmentSlot.HEAD),
                this.getItemBySlot(EquipmentSlot.CHEST),
                this.getItemBySlot(EquipmentSlot.LEGS),
                this.getItemBySlot(EquipmentSlot.FEET));
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 35.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)
                .add(Attributes.ATTACK_SPEED, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.5f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 5, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 2, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("animation.model.attack", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        Item[] weapon = {KnightQuestCommon.COMMON_PLATFORM.getPaladinSword().get()};
        int indexWeapons = this.random.nextInt(weapon.length);

        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(weapon[indexWeapons]));

        Item[] helmets = {KnightQuestItems.VETERAN_HELMET.get(), KnightQuestItems.APPLE_HELMET.get(), KnightQuestItems.HORN_HELMET.get(), KnightQuestItems.ZOMBIE_HELMET2.get()};
        Item[] chestplates = {KnightQuestItems.VETERAN_CHESTPLATE.get(), KnightQuestItems.APPLE_CHESTPLATE.get(), KnightQuestItems.HORN_CHESTPLATE.get(), KnightQuestItems.ZOMBIE_CHESTPLATE.get()};
        Item[] leggings = {KnightQuestItems.VETERAN_LEGGINGS.get(), KnightQuestItems.APPLE_LEGGINGS.get(), KnightQuestItems.HORN_LEGGINGS.get(), KnightQuestItems.ZOMBIE_LEGGINGS.get()};
        Item[] boots = {KnightQuestItems.VETERAN_BOOTS.get(), KnightQuestItems.APPLE_BOOTS.get(), KnightQuestItems.HORN_BOOTS.get(), KnightQuestItems.ZOMBIE_BOOTS.get()};

        int index = this.random.nextInt(helmets.length);

        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(helmets[index]));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(chestplates[index]));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(leggings[index]));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(boots[index]));

        AttributeInstance maxHealth = this.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(KQConfigValues.FALLENKNIGHT_MAX_HEALTH.get());
            this.setHealth(KQConfigValues.FALLENKNIGHT_MAX_HEALTH.get().floatValue());
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
    }

    private PlayState predicate(AnimationState<?> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.model.walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("animation.model.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                CompoundTag stackNbt = tag.getCompound(slot.getName());
                ItemStack stack = ItemStack.parseOptional(this.level().registryAccess(), stackNbt);
                if (!stack.isEmpty()) {
                    this.setItemSlot(slot, stack);
                }
            }
        }

    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack stack = this.getItemBySlot(slot);
                if (!stack.isEmpty()) {
                    CompoundTag stackNbt = new CompoundTag();
                    stack.save(this.level().registryAccess(), stackNbt);
                    tag.put(slot.getName(), stackNbt);
                }
            }
        }

    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
    }

}