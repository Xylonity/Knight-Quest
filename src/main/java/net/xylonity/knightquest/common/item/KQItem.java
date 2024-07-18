package net.xylonity.knightquest.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class KQItem extends Item {

    private final String tooltipInfoName;

    public KQItem(Properties pProperties, String tooltipInfoName) {
        super(pProperties);
        this.tooltipInfoName = tooltipInfoName;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.item.knightquest." + tooltipInfoName));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

}
