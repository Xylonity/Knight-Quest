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

import java.util.*;

public class SamhainEntity extends TamableAnimal implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private UUID ownerUUID;
    private static final EntityDataAccessor<Boolean> SITTING =
            SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> ARMOR_SLOT = SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.ITEM_STACK);

    public SamhainEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 0.5f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.6f).build();
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
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private <E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event) {

        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation((new AnimationBuilder()).addAnimation("attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        if (this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("sit", ILoopType.EDefaultLoopTypes.LOOP));
        }

        if (this.dead) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("death", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = KnightQuestItems.GREAT_ESSENCE.get();

        if (item == itemForTaming && !isTame()) {
            if (this.getLevel().isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.getLevel().isClientSide) {
                        super.tame(player);
                        this.navigation.recomputePath();
                        this.setTarget(null);
                        this.getLevel().broadcastEntityEvent(this, (byte)7);
                        setSitting(true);
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }

        if (isTame() && !this.getLevel().isClientSide && hand == InteractionHand.MAIN_HAND) {
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

            } else if (itemstack.getItem() == KnightQuestItems.SQUIRE_HELMET.get()) {
                equipArmor(itemstack);
                return InteractionResult.SUCCESS;
            } else if (itemstack.isEmpty() && hasArmor() && player.isShiftKeyDown()) {
                removeArmor(player);
                return InteractionResult.SUCCESS;
            } else {
                setSitting(!isSitting());
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
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setSitting(tag.getBoolean("isSitting"));
        this.entityData.set(ARMOR_SLOT, ItemStack.of(tag.getCompound("ArmorItem")));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("isSitting", this.isSitting());
        CompoundTag armorTag = new CompoundTag();
        this.getArmor().save(armorTag);
        tag.put("ArmorItem", armorTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SITTING, false);
        this.entityData.define(ARMOR_SLOT, ItemStack.EMPTY);
    }

    public void equipArmor(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) itemStack.getItem();
            float armorValue = armorItem.getDefense();
            this.getAttribute(Attributes.ARMOR).setBaseValue(armorValue);
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

        this.getAttribute(Attributes.ARMOR).setBaseValue(0.0);
        this.entityData.set(ARMOR_SLOT, ItemStack.EMPTY);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
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

    public boolean canBeLeashed(Player player) {
        return false;
    }

    @Override
    public void setTame(boolean tamed) {
        super.setTame(tamed);
        if (tamed) {
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(25.0D);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4D);
            getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.5f);
        } else {
            getAttribute(Attributes.MAX_HEALTH).setBaseValue(30.0D);
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4D);
            getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.5f);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.AXOLOTL_SWIM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ALLAY_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ALLAY_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1.0F);
    }

    public class MoveToPumpkinGoal extends Goal {

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
            Level level = entity.getLevel();

            targetPumpkinPos = findNearestPumpkin(level, entityPos, searchRadius);
            return targetPumpkinPos != null;
        }

        @Override
        public boolean canContinueToUse() {
            return targetPumpkinPos != null && entity.getLevel().getBlockState(targetPumpkinPos).is(Blocks.PUMPKIN);
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

