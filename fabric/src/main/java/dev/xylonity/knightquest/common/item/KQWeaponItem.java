package dev.xylonity.knightquest.common.item;

import dev.xylonity.knightlib.compat.block.ChaliceBlock;
import dev.xylonity.knightlib.compat.registry.KnightLibBlocks;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class KQWeaponItem extends SwordItem {

    public KQWeaponItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockState blockState = level.getBlockState(ctx.getClickedPos());
        ItemStack stack = ctx.getItemInHand();

        if (!level.isClientSide && blockState.is(KnightLibBlocks.GREAT_CHALICE)
                && blockState.getValue(ChaliceBlock.fill) == 10 && isEnabled()) {

            CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
            CompoundTag dataTag = customData.copyTag();

            if (!dataTag.getBoolean("Activated")) {
                dataTag.putBoolean("Activated", true);
                stack.set(DataComponents.CUSTOM_DATA, CustomData.of(dataTag));
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
            if (blockState.is(KnightLibBlocks.GREAT_CHALICE) &&
                    (blockState.getValue(ChaliceBlock.fill) == 10 || blockState.getValue(ChaliceBlock.fill) == 1)) {
                return InteractionResultHolder.fail(stack);
            }
        }

        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag dataTag = customData.copyTag();

        if (!dataTag.getBoolean("Activated")) {
            return InteractionResultHolder.fail(stack);
        }

        long currentTime = level.getGameTime();
        long lastUsed = dataTag.getLong("LastUsed");
        int cooldownTicks = getCooldownTicks();

        if (currentTime - lastUsed >= cooldownTicks) {
            interaction(level, player, hand);

            if (!level.isClientSide) {
                dataTag.putLong("LastUsed", currentTime);
                player.getCooldowns().addCooldown(this, cooldownTicks);
            }

            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        if (isEnabled()) {
            pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest." + getName()));

            CustomData customData = pStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
            CompoundTag dataTag = customData.copyTag();
            boolean isActivated = dataTag.getBoolean("Activated");

            if (!isActivated) {
                pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest.disabled_abilities"));
            }

            pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest." + getName() + ".active",
                    KQConfigValues.SPEED_TICKS_KUKRI.get() / 20F,
                    KQConfigValues.INV_TICKS_PALADIN.get() / 20F,
                    (int) Math.floor(KQConfigValues.EXTRA_DAMAGE_UCHIGATANA.get() * 100),
                    KQConfigValues.REFLECTION_TIME_KHOPESH.get() / 20F,
                    KQConfigValues.TICKS_CLEAVER.get() / 20));
            pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest." + getName() + ".passive",
                    (int) Math.floor(KQConfigValues.EXTRA_DAMAGE_PASSIVE_UCHIGATANA.get() * 100),
                    (int) Math.floor(KQConfigValues.ENEMY_HEALTH_PASSIVE_UCHIGATANA.get() * 100),
                    (int) Math.floor(KQConfigValues.CHANCE_BURN_KHOPESH.get() * 100),
                    KQConfigValues.REGEN_HP_PALADIN.get(),
                    KQConfigValues.REGEN_TICKS_PALADIN.get() / 20,
                    (int) Math.floor(KQConfigValues.REGEN_MAX_PALADIN.get().floatValue() * 100),
                    (int) Math.floor(KQConfigValues.EXTRA_DAMAGE_PASSIVE_CLEAVER.get() * 100),
                    (int) Math.floor(KQConfigValues.ENEMY_HEALTH_PASSIVE_CLEAVER.get() * 100)));
            if (!isActivated) {
                pTooltipComponents.add(Component.translatable("tooltip.weapon.knightquest.use_chalice"));
            }
        }


    }

    abstract public void interaction(Level level, Player player, InteractionHand hand);

    abstract public int getCooldownTicks();

    abstract public String getName();

    protected abstract boolean isEnabled();
}
