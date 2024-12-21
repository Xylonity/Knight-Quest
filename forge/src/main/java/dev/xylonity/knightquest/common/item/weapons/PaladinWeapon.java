package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PaladinWeapon extends KQWeaponItem {

    private static final String IS_LOCKED = "isLocked";

    public PaladinWeapon(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag tooltipFlag) {

        CompoundTag tag = stack.getTag();
        if (tag != null && tag.getBoolean("Activated")) {
            components.add(Component.literal("Activated").withStyle(ChatFormatting.GREEN));
        } else {
            components.add(Component.literal("Deactivated").withStyle(ChatFormatting.RED));
        }

        super.appendHoverText(stack, level, components, tooltipFlag);
    }

    @Override
    public void interaction(Level level, Player player, InteractionHand hand) {

    }

    @Override
    public int getCooldownTicks() {
        return 100;
    }

    @Override
    public String getName() {
        return "paladin";
    }
}