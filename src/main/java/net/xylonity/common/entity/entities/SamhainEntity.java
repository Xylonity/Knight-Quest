package net.xylonity.common.entity.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xylonity.registry.KnightQuestItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

public class SamhainEntity extends TameableEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private UUID ownerUUID;

    public SamhainEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, 0.6D, 6.0F, 2.0F));
        this.goalSelector.add(4, new WanderAroundGoal(this, 0.5f, 1));
        this.goalSelector.add(3, new FleeEntityGoal<>(this, HostileEntity.class, 80, 0.5f, 0.5f));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        if (this.isSitting()) {
            event.getController().setAnimation(RawAnimation.begin().then("sit", Animation.LoopType.LOOP));
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

    private static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(SamhainEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = KnightQuestItems.GREAT_ESSENCE;

        if (item == itemForTaming && !isTamed()) {
            if (this.getWorld().isClient()) {
                return ActionResult.CONSUME;
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                if (!this.getWorld().isClient()) {
                    super.setOwner(player);
                    ownerUUID = player.getUuid();
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.getWorld().sendEntityStatus(this, (byte)7);
                    setSit(true);
                }

                return ActionResult.SUCCESS;
            }
        }

        if (isTamed() && player.getUuid().equals(ownerUUID) &&  this.getHealth() < this.getMaxHealth()) {
            if (!this.getWorld().isClient() && item == itemForTaming) {
                this.heal(16.0F);
                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }
                return ActionResult.SUCCESS;
            } else if (!this.getWorld().isClient() && item == KnightQuestItems.SMALL_ESSENCE) {
                this.heal(4.0F);
                this.getWorld().playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }

        if(isTamed() && !this.getWorld().isClient() && hand == Hand.MAIN_HAND && player.getUuid().equals(ownerUUID)) {
            setSit(!isSitting());
            return ActionResult.SUCCESS;
        }

        if (itemstack.getItem() == itemForTaming) {
            return ActionResult.PASS;
        }

        return super.interactMob(player, hand);
    }

    public void setSit(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
        super.setSitting(sitting);
    }

    public boolean isSitting() {
        return this.dataTracker.get(SITTING);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("isSitting", this.dataTracker.get(SITTING));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(SITTING, nbt.getBoolean("isSitting"));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public Team getScoreboardTeam() {
        return super.getScoreboardTeam();
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(SITTING, false);
    }

}

