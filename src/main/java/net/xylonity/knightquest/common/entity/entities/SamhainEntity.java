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
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import net.xylonity.knightquest.common.entity.entities.ai.MoveToPumpkinGoal;
import net.xylonity.knightquest.common.entity.entities.ai.RangedAttackGoal;
import net.xylonity.knightquest.registry.KnightQuestItems;
import org.jetbrains.annotations.NotNull;
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

public class SamhainEntity extends TamableAnimal implements IAnimatable, RangedAttackMob {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> SIT_VARIATION = SynchedEntityData.defineId(SamhainEntity.class, EntityDataSerializers.INT);

    public SamhainEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setCanPickUpLoot(true);
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
        this.goalSelector.addGoal(2, new RangedAttackGoal<>(this, 0.7D, 10, 15.0f));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 0.6D, 6.0F, 2.0F, false));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new MoveToPumpkinGoal(this, 0.68F));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
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

    @Override
    public @NotNull HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (this.isSitting()) {
            String sitVariation = getSitVariation() == 0 ? "sit" : getSitVariation() == 1 ? "sit3" : "sit2";
            event.getController().setAnimation(new AnimationBuilder().addAnimation(sitVariation, ILoopType.EDefaultLoopTypes.LOOP));
        } else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item itemForTaming = KnightQuestItems.GREAT_ESSENCE.get();
        Item item = itemstack.getItem();

        /*
         * Consumes a single Great Essence item and tames the Samhain.
         */

        if (item == itemForTaming && !isTame()) {
            if (this.level.isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, player)) {
                    if (!this.level.isClientSide) {
                        super.tame(player);
                        this.navigation.recomputePath();
                        this.setTarget(null);
                        this.level.broadcastEntityEvent(this, (byte)7);
                        setSitting(true);
                    }
                }

                setSitVariation(getRandom().nextInt(0, 3));

                return InteractionResult.SUCCESS;
            }
        }

        /*
         * Handles the actions when the Samhain is tamed.
         */

        if (isTame() && !this.level.isClientSide && hand == InteractionHand.MAIN_HAND && getOwner() == player) {
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

            } else if (player.isShiftKeyDown()) {
                removeArmor(player);
                removeItem(player);
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

    @Override
    protected void pickUpItem(ItemEntity itemEntity) {
        ItemStack itemStack = itemEntity.getItem();
        Item item = itemStack.getItem();

        final Item[] COMPATIBLE_WEAPONS = new Item[] {
                Items.IRON_SWORD,
                Items.STONE_SWORD,
                Items.GOLDEN_SWORD,
                Items.WOODEN_SWORD,
                Items.NETHERITE_SWORD,
                Items.DIAMOND_SWORD,
                Items.BOW,
                KnightQuestItems.PALADIN_SWORD.get(),
                KnightQuestItems.NAIL_SWORD.get(),
                KnightQuestItems.UCHIGATANA.get(),
                KnightQuestItems.KUKRI.get(),
                KnightQuestItems.KHOPESH.get(),
                KnightQuestItems.CLEAVER.get(),
                KnightQuestItems.CRIMSON_SWORD.get(),
                KnightQuestItems.WATER_SWORD.get(),
                KnightQuestItems.STEEL_SWORD.get(),
                KnightQuestItems.WATER_AXE.get(),
                KnightQuestItems.STEEL_AXE.get()
        };

        if ((Arrays.stream(COMPATIBLE_WEAPONS).toList().contains(item)) && isTame()) {
            EquipmentSlot slot = LivingEntity.getEquipmentSlotForItem(itemStack);
            ItemStack currentItem = this.getItemInHand(InteractionHand.MAIN_HAND);

            if (currentItem.isEmpty()) {
                this.setItemSlot(slot, itemStack.split(1));

                if (itemStack.isEmpty()) {
                    itemEntity.discard();
                }
            }
        }

    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (this.getOwnerUUID() != null) {
            tag.putUUID("ownerUUID", this.getOwnerUUID());
        }

        setSitting(tag.getBoolean("isSitting"));

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HAND) {
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
            if (slot.getType() == EquipmentSlot.Type.HAND) {
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
        this.entityData.define(SIT_VARIATION, 0);
    }

    private void setSitVariation(int sitVariation) {
        this.entityData.set(SIT_VARIATION, sitVariation);
    }

    private int getSitVariation() {
        return this.entityData.get(SIT_VARIATION);
    }

    private void removeArmor(Player pPlayer) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.ARMOR) {
                ItemStack armorStack = this.getItemBySlot(slot);

                if (!armorStack.isEmpty()) {
                    boolean addedToInventory = pPlayer.getInventory().add(armorStack);

                    if (!addedToInventory) {
                        pPlayer.spawnAtLocation(armorStack);
                    }

                    this.setItemSlot(slot, ItemStack.EMPTY);
                }
            }
        }
    }

    private void removeItem(Player pPlayer) {
        ItemStack itemStack = this.getItemInHand(InteractionHand.MAIN_HAND);

        if (!itemStack.isEmpty()) {
            boolean addedToInventory = pPlayer.getInventory().add(itemStack);

            if (!addedToInventory) {
                pPlayer.spawnAtLocation(itemStack);
            }

            this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
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
            Objects.requireNonNull(getAttribute(Attributes.MAX_HEALTH)).setBaseValue(20.0D);
            Objects.requireNonNull(getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(0.5D);
            Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.5f);
            this.setHealth(20.0F);
        } else {
            Objects.requireNonNull(getAttribute(Attributes.MAX_HEALTH)).setBaseValue(8.0D);
            Objects.requireNonNull(getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(0.5D);
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

    @Override
    public void performRangedAttack(@NotNull LivingEntity pTarget, float pVelocity) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
        AbstractArrow abstractarrow = this.getArrow(itemstack, pVelocity);
        if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem)
            abstractarrow = ((net.minecraft.world.item.BowItem)this.getMainHandItem().getItem()).customArrow(abstractarrow);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.34D) - abstractarrow.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(abstractarrow);
    }

    protected AbstractArrow getArrow(ItemStack pArrowStack, float pVelocity) {
        return ProjectileUtil.getMobArrow(this, pArrowStack, pVelocity);
    }
}

