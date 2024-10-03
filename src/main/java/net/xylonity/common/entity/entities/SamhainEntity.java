package net.xylonity.common.entity.entities;

import dev.xylonity.knightlib.compat.registry.KnightLibItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.xylonity.common.entity.entities.ai.MoveToPumpkinGoal;
import net.xylonity.common.entity.entities.ai.RangedAttackGoal;
import net.xylonity.registry.KnightQuestItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class SamhainEntity extends TameableEntity implements GeoEntity, RangedAttackMob, Tameable {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Boolean> SITTING = DataTracker.registerData(SamhainEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> SIT_VARIATION = DataTracker.registerData(SamhainEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private PlayerEntity theOwner;
    private UUID ownerUUID;

    public SamhainEntity(EntityType<? extends SamhainEntity> entityType, World world) {
        super(entityType, world);
        setCanPickUpLoot(true);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return Arrays.asList(
                this.getEquippedStack(EquipmentSlot.HEAD),
                this.getEquippedStack(EquipmentSlot.CHEST),
                this.getEquippedStack(EquipmentSlot.LEGS),
                this.getEquippedStack(EquipmentSlot.FEET)
        );
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new RangedAttackGoal<>(this, 0.7D, 10, 15.0F));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 0.6D, true));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 0.6D, 6.0F, 2.0F));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(7, new MoveToPumpkinGoal(this, 0.68D));

        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
    }

    private int getSitVariation() {
        return this.dataTracker.get(SIT_VARIATION);
    }

    private void setSitVariation(int sitVariation) {
        this.dataTracker.set(SIT_VARIATION, sitVariation);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "attackcontroller", 0, this::attackPredicate));
    }

    private PlayState attackPredicate(AnimationState<?> event) {

        if (this.isUsingItem() && this.getMainHandStack().getItem() instanceof RangedWeaponItem) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("bow_attack", Animation.LoopType.PLAY_ONCE));
        } else if (this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

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
    public void setOwner(PlayerEntity player) {
        super.setOwner(player);
        this.theOwner = player;
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return this.theOwner;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_AXOLOTL_SWIM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ALLAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ALLAY_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return super.createNavigation(world);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();
        Item itemForTaming = KnightLibItems.GREAT_ESSENCE.get();

        /*
         * Consumes a single Great Essence item and tames the Samhain.
         */

        if (item == itemForTaming && !isTamed()) {
            this.theOwner = player;
            if (this.getWorld().isClient()) {
                return ActionResult.CONSUME;
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                if (!this.getWorld().isClient()) {
                    super.setOwner(player);
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.setAttacking(null);
                    this.setAttacking(false);
                    this.setAttacker(null);
                    this.getWorld().sendEntityStatus(this, (byte)7);
                    setSit(true);
                }

                setSitVariation(getRandom().nextBetween(0, 3));

                return ActionResult.SUCCESS;
            }
        }

        /*
         * Handles the actions when the Samhain is tamed.
         */

        if (isTamed() && !this.getWorld().isClient() && hand == Hand.MAIN_HAND && (getOwner() == player || player.getUuid().equals(ownerUUID))) {
            if ((itemstack.getItem().equals(KnightLibItems.GREAT_ESSENCE.get()) || itemstack.getItem().equals(KnightLibItems.SMALL_ESSENCE.get())) && this.getHealth() < this.getMaxHealth()) {

                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                if (itemstack.getItem().equals(KnightLibItems.GREAT_ESSENCE.get())) {
                    this.heal(16.0F);
                } else if (itemstack.getItem().equals(KnightLibItems.SMALL_ESSENCE.get())) {
                    this.heal(4.0F);
                }

            } else if (player.isSneaking()) {
                removeArmor(player);
                removeItem(player);
            } else {
                setSit(!isSitting());
                setSitVariation(getRandom().nextBetween(0, 3));
            }

            return ActionResult.SUCCESS;
        }

        if (itemstack.getItem() == itemForTaming) {
            return ActionResult.PASS;
        }

        return super.interactMob(player, hand);
    }

    private void removeArmor(PlayerEntity pPlayer) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack armorStack = this.getEquippedStack(slot);

                if (!armorStack.isEmpty()) {
                    boolean addedToInventory = pPlayer.getInventory().insertStack(armorStack);

                    if (!addedToInventory) {
                        pPlayer.dropItem(armorStack, false);
                    }

                    this.equipStack(slot, ItemStack.EMPTY);
                }
            }
        }
    }

    private void removeItem(PlayerEntity pPlayer) {
        ItemStack itemStack = this.getStackInHand(Hand.MAIN_HAND);

        if (!itemStack.isEmpty()) {
            boolean addedToInventory = pPlayer.getInventory().insertStack(itemStack);

            if (!addedToInventory) {
                pPlayer.dropItem(itemStack, false);
            }

            this.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    @Override
    protected void loot(ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getStack();
        Item item = stack.getItem();

        if ((item instanceof SwordItem || item instanceof AxeItem || item instanceof RangedWeaponItem) && isTamed()) {
            EquipmentSlot slot = this.getPreferredEquipmentSlot(stack);
            ItemStack currentItem = this.getStackInHand(Hand.MAIN_HAND);

            if (currentItem.isEmpty()) {
                this.equipStack(slot, stack.split(1));

                if (stack.isEmpty()) {
                    itemEntity.discard();
                }
            }
        } else if (item instanceof ArmorItem armorItem && isTamed()) {
            EquipmentSlot slot = armorItem.getSlotType();
            ItemStack currentArmor = this.getEquippedStack(slot);

            if (currentArmor.isEmpty()) {
                this.equipStack(slot, stack.split(1));

                if (stack.isEmpty()) {
                    itemEntity.discard();
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getOwner() == null && this.getOwnerUuid() != null) {
            this.theOwner = this.getWorld().getPlayerByUuid(this.getOwnerUuid());
        }
    }

    public void setSit(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
        super.setSitting(sitting);
    }

    public boolean isSitting() {
        return this.dataTracker.get(SITTING);
    }

    @Override
    public void setTamed(boolean tamed, boolean updateAttributes) {
        super.setTamed(tamed, updateAttributes);
        if (tamed) {
            Objects.requireNonNull(getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(20.0D);
            Objects.requireNonNull(getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue(0.5D);
            Objects.requireNonNull(getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).setBaseValue((double)0.5f);
        } else {
            Objects.requireNonNull(getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).setBaseValue(8.0D);
            Objects.requireNonNull(getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)).setBaseValue(0.5D);
            Objects.requireNonNull(getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)).setBaseValue((double)0.5f);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        if (this.getOwnerUuid() != null) {
            nbt.putUuid("OwnerUUID", this.getOwnerUuid());
        }

        nbt.putBoolean("isSitting", this.dataTracker.get(SITTING));

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                ItemStack stack = this.getEquippedStack(slot);
                if (!stack.isEmpty()) {
                    NbtCompound stackNbt = new NbtCompound();
                    stack.encode(this.getWorld().getRegistryManager(), stackNbt);
                    nbt.put(slot.getName(), stackNbt);
                }
            }
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        if (nbt.containsUuid("OwnerUUID")) {
            UUID ownerUuid = nbt.getUuid("OwnerUUID");
            this.setOwnerUuid(ownerUuid);
        }

        this.dataTracker.set(SITTING, nbt.getBoolean("isSitting"));

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
                NbtCompound stackNbt = nbt.getCompound(slot.getName());
                ItemStack stack = ItemStack.fromNbtOrEmpty(this.getWorld().getRegistryManager(), stackNbt);
                if (!stack.isEmpty()) {
                    this.equipStack(slot, stack);
                }
            }
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public Team getScoreboardTeam() {
        return super.getScoreboardTeam();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SITTING, false);
        builder.add(SIT_VARIATION, 0);
    }

    protected PersistentProjectileEntity createArrowProjectile(ItemStack arrowStack, float pullProgress) {
        return ProjectileUtil.createArrowProjectile(this, arrowStack, pullProgress, this.getMainHandStack());
    }

    public Hand getActiveHand() {
        return this.getMainHandStack().getItem() instanceof BowItem ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getProjectileType(this.getStackInHand(getActiveHand()));
        PersistentProjectileEntity arrowEntity = this.createArrowProjectile(itemStack, pullProgress);

        if (arrowEntity != null) {
            double d0 = target.getX() - this.getX();
            double d1 = target.getBodyY(0.34D) - arrowEntity.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = MathHelper.sqrt((float) (d0 * d0 + d2 * d2));
            arrowEntity.setVelocity(d0, d1 + d3 * 0.2F, d2, 1.6F, (float) (14 - this.getWorld().getDifficulty().getId() * 4));
            this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
            this.getWorld().spawnEntity(arrowEntity);
        }
    }
}

