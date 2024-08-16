package net.xylonity.knightquest.common.entity.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import net.xylonity.knightquest.registry.KnightQuestItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class SamhainEntity extends TamableAnimal implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private UUID ownerUUID;
    private static final EntityDataAccessor<Boolean> SITTING =
            SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> ARMOR_SLOT = SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> SIT_VARIATION = SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.INT);
    private Level serverWorld;

    public SamhainEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.serverWorld = pLevel;
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots() {
        return Arrays.asList(this.getItemBySlot(EquipmentSlot.HEAD),
                this.getItemBySlot(EquipmentSlot.CHEST),
                this.getItemBySlot(EquipmentSlot.LEGS),
                this.getItemBySlot(EquipmentSlot.FEET));
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.5f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Monster.class, 6.0F, 0.65D, 0.65D));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 0.6D, 6.0F, 2.0F, false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new MoveToPumpkinGoal(this, 0.68F));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    private PlayState predicate(AnimationState<?> event) {
        if (this.isSitting()) {
            String sitVariation = getSitVariation() == 0 ? "sit" : getSitVariation() == 1 ? "sit3" : "sit2";
            event.getController().setAnimation(RawAnimation.begin().then(sitVariation, Animation.LoopType.LOOP));
        } else if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = KnightQuestItems.GREAT_ESSENCE.get();

        if (item == itemForTaming && !isTame()) {
            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.level().isClientSide) {
                        super.tame(player);
                        this.navigation.recomputePath();
                        this.setTarget(null);
                        this.level().broadcastEntityEvent(this, (byte)7);
                        setSitting(true);
                    }
                }

                setSitVariation(getRandom().nextInt(0, 3));

                return InteractionResult.SUCCESS;
            }
        }

        if (isTame() && !this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            if ((itemstack.getItem().equals(KnightQuestItems.GREAT_ESSENCE.get()) || itemstack.getItem().equals(KnightQuestItems.SMALL_ESSENCE.get()))
                    && this.getHealth() < this.getMaxHealth()) {

                if (itemstack.getItem().equals(KnightQuestItems.GREAT_ESSENCE.get())) {
                    this.heal(16.0F);
                } else if (itemstack.getItem().equals(KnightQuestItems.SMALL_ESSENCE.get())) {
                    this.heal(4.0F);
                }

                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

            } else if (item instanceof ArmorItem armorItem) {
                EquipmentSlot slot = armorItem.getEquipmentSlot();
                this.setItemSlot(slot, itemstack);
                return InteractionResult.SUCCESS;
            } else if (itemstack.isEmpty() && hasArmor() && player.isShiftKeyDown()) {
                removeArmor(player);
                return InteractionResult.SUCCESS;
            } else {
                setSitting(!isSitting());
                setSitVariation(getRandom().nextInt(0, 3));
            }
            
            return InteractionResult.SUCCESS;
        }

        if (itemstack.getItem() == itemForTaming) {
            return InteractionResult.PASS;
        }

        return super.mobInteract(player, hand);
    }

    public boolean hasArmor() {
        return !this.entityData.get(ARMOR_SLOT).isEmpty();
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (this.getOwnerUUID() != null) {
            tag.putUUID("ownerUUID", this.getOwnerUUID());
        }

        setSitting(tag.getBoolean("isSitting"));

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                CompoundTag stackNbt = tag.getCompound(slot.getName());
                ItemStack stack = ItemStack.of(stackNbt);
                if (!stack.isEmpty()) {
                    this.setItemSlot(slot, stack);
                }
            }
        }

    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (tag.hasUUID("ownerUUID")) {
            this.setOwnerUUID(tag.getUUID("ownerUUID"));
        }

        tag.putBoolean("isSitting", this.isSitting());

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack stack = this.getItemBySlot(slot);
                if (!stack.isEmpty()) {
                    CompoundTag stackNbt = new CompoundTag();
                    stack.save(stackNbt);
                    tag.put(slot.getName(), stackNbt);
                }
            }
        }

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SITTING, false);
        this.entityData.define(ARMOR_SLOT, ItemStack.EMPTY);
        this.entityData.define(SIT_VARIATION, 0);
    }

    private void setSitVariation(int sitVariation) {
        this.entityData.set(SIT_VARIATION, sitVariation);
    }

    private int getSitVariation() {
        return this.entityData.get(SIT_VARIATION);
    }

    public void equipArmor(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ArmorItem armorItem) {
            float armorValue = armorItem.getDefense();
            Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).setBaseValue(armorValue);
            this.entityData.set(ARMOR_SLOT, itemStack.copy());
            itemStack.shrink(1);
        }
    }

    public ItemStack getArmor() {
        return this.entityData.get(ARMOR_SLOT);
    }

    public void removeArmor(Player pPlayer) {
        ItemStack armorStack = this.entityData.get(ARMOR_SLOT);

        if (!armorStack.isEmpty()) {
            if (!pPlayer.getInventory().add(armorStack)) {
                this.spawnAtLocation(armorStack);
            }

            this.entityData.set(ARMOR_SLOT, ItemStack.EMPTY);
        }

        Objects.requireNonNull(this.getAttribute(Attributes.ARMOR)).setBaseValue(0.0);
        this.entityData.set(ARMOR_SLOT, ItemStack.EMPTY);
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        ItemStack armorStack = this.entityData.get(ARMOR_SLOT);
        if (!armorStack.isEmpty()) {
            this.spawnAtLocation(armorStack);
        }
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
        this.setOrderedToSit(sitting);
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    public boolean canBeLeashed(@NotNull Player player) {
        return false;
    }

    @Override
    public void setTame(boolean tamed) {
        super.setTame(tamed);
        if (tamed) {
            Objects.requireNonNull(getAttribute(Attributes.MAX_HEALTH)).setBaseValue(35.0D);
            Objects.requireNonNull(getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(4D);
            Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.5f);
        } else {
            Objects.requireNonNull(getAttribute(Attributes.MAX_HEALTH)).setBaseValue(30.0D);
            Objects.requireNonNull(getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(4D);
            Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.5f);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return null;
    }

    @Override
    protected @NotNull SoundEvent getSwimSound() {
        return SoundEvents.AXOLOTL_SWIM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ALLAY_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return SoundEvents.ALLAY_HURT;
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    public static class MoveToPumpkinGoal extends Goal {

        private final SamhainEntity entity;
        private final double speed;
        private BlockPos targetPumpkinPos;
        private final int searchRadius = 10;
        private final double circleRadius = 3.0;
        private double angle = 0;
        private final double angleIncrement = Math.PI / 8;
        private final Random random = new Random();
        private int ticksCircling = 0;
        private final int maxTicksCircling = 80;

        public MoveToPumpkinGoal(SamhainEntity entity, double speed) {
            this.entity = entity;
            this.speed = speed;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            BlockPos entityPos = entity.blockPosition();
            Level level = entity.level();

            targetPumpkinPos = findNearestPumpkin(level, entityPos, searchRadius);
            return targetPumpkinPos != null;
        }

        @Override
        public boolean canContinueToUse() {
            return targetPumpkinPos != null && entity.level().getBlockState(targetPumpkinPos).is(Blocks.PUMPKIN);
        }

        @Override
        public void start() {
            if (targetPumpkinPos != null) {
                moveToNextPosition();
                ticksCircling = 0;
            }
        }

        @Override
        public void stop() {
            targetPumpkinPos = null;
        }

        @Override
        public void tick() {
            if (targetPumpkinPos != null) {
                if (ticksCircling < maxTicksCircling) {
                    if (entity.getNavigation().isDone()) {
                        angle += angleIncrement;
                        moveToNextPosition();
                    }
                    ticksCircling++;
                    if (random.nextInt(20) == 0) {
                        entity.getJumpControl().jump();
                    }
                } else {
                    entity.getNavigation().moveTo(targetPumpkinPos.getX() + 0.5, targetPumpkinPos.getY(), targetPumpkinPos.getZ() + 0.5, speed);
                }
            }
        }

        private void moveToNextPosition() {
            double xOffset = targetPumpkinPos.getX() + circleRadius * Math.cos(angle);
            double zOffset = targetPumpkinPos.getZ() + circleRadius * Math.sin(angle);
            entity.getLookControl().setLookAt(targetPumpkinPos.getX(), targetPumpkinPos.getY(), targetPumpkinPos.getZ());
            entity.getNavigation().moveTo(xOffset, targetPumpkinPos.getY(), zOffset, speed);
        }

        private BlockPos findNearestPumpkin(Level level, BlockPos entityPos, int radius) {
            for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-radius, -2, -radius), entityPos.offset(radius, 2, radius))) {
                if (level.getBlockState(pos).is(Blocks.PUMPKIN)) {
                    return pos;
                }
            }
            return null;
        }

    }

}

