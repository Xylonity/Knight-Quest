package net.xylonity.knightquest.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KQItem extends Item {

    private final String tooltipInfoName;

    public KQItem(Properties pProperties, String tooltipInfoName) {
        super(pProperties.tab(KnightQuest.CREATIVE_MODE_TAB));
        this.tooltipInfoName = tooltipInfoName;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TextComponent("tooltip.item.knightquest." + tooltipInfoName));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
