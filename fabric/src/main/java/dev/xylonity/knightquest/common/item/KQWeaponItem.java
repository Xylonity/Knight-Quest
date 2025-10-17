package dev.xylonity.knightquest.common.item;

import dev.xylonity.knightlib.api.interop.GreatChaliceState;
import dev.xylonity.knightlib.common.blockentity.GreatChaliceBlockEntity;
import dev.xylonity.knightlib.registry.KnightLibBlocks;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class KQWeaponItem extends SwordItem {

    public KQWeaponItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos pos = ctx.getClickedPos();
        ItemStack stack = ctx.getItemInHand();
        CompoundTag tag = stack.getOrCreateTag();
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof GreatChaliceBlockEntity be && !level.isClientSide && be.isFull() && be.getState() == GreatChaliceState.CHAOTIC) {
            be.setCharges(0);
            level.playSound(null, pos, SoundEvents.EVOKER_PREPARE_SUMMON, SoundSource.BLOCKS, 1, 1);
            if (!tag.getBoolean("Activated")) {
                tag.putBoolean("Activated", true);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }

        }

        return InteractionResult.PASS;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        BlockHitResult blockHit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

        if (blockHit.getType() == HitResult.Type.BLOCK) {
            BlockState blockState = level.getBlockState(blockHit.getBlockPos());
            if (blockState.is(KnightLibBlocks.GREAT_CHALICE.get())) {
                return InteractionResultHolder.fail(stack);
            }
        }

        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.getBoolean("Activated")) {
            return InteractionResultHolder.fail(stack);
        }

        long currentTime = level.getGameTime();
        long lastUsed = tag.getLong("LastUsed");
        int cooldownTicks = getCooldownTicks();

        if (currentTime - lastUsed >= cooldownTicks) {

            interaction(level, player, hand);

            if (!level.isClientSide) {
                tag.putLong("LastUsed", currentTime);
                player.getCooldowns().addCooldown(this, cooldownTicks);
            }

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return InteractionResultHolder.fail(stack);

    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (isEnabled()) {
            pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest." + getName()));

            boolean isActivated = pStack.getOrCreateTag().getBoolean("Activated");

            if (!isActivated) pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest.disabled_abilities"));
            pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest." + getName() + ".active",
                    KQConfigValues.SPEED_TICKS_KUKRI / 20F,
                    KQConfigValues.INV_TICKS_PALADIN / 20F,
                    (int) Math.floor(KQConfigValues.EXTRA_DAMAGE_UCHIGATANA * 100),
                    KQConfigValues.REFLECTION_TIME_KHOPESH / 20F,
                    KQConfigValues.TICKS_CLEAVER / 20));
            pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest." + getName() + ".passive",
                    (int) Math.floor(KQConfigValues.EXTRA_DAMAGE_PASSIVE_UCHIGATANA * 100),
                    (int) Math.floor(KQConfigValues.ENEMY_HEALTH_PASSIVE_UCHIGATANA * 100),
                    (int) Math.floor(KQConfigValues.CHANCE_BURN_KHOPESH * 100),
                    KQConfigValues.REGEN_HP_PALADIN,
                    KQConfigValues.REGEN_TICKS_PALADIN / 20,
                    (int) Math.floor(KQConfigValues.REGEN_MAX_PALADIN * 100),
                    (int) Math.floor(KQConfigValues.EXTRA_DAMAGE_PASSIVE_CLEAVER * 100),
                    (int) Math.floor(KQConfigValues.ENEMY_HEALTH_PASSIVE_CLEAVER * 100)));
            if (!isActivated) pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest.use_chalice"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    abstract public void interaction(Level level, Player player, InteractionHand hand);
    abstract public int getCooldownTicks();
    abstract public String getName();
    protected abstract boolean isEnabled();

}
