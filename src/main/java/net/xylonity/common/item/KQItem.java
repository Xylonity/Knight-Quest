package net.xylonity.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class KQItem extends Item {

    private final String tooltipInfoName;

    public KQItem(Settings settings, String tooltipInfoName) {
        super(settings);
        this.tooltipInfoName = tooltipInfoName;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.item.knightquest." + tooltipInfoName));
        super.appendTooltip(stack, context, tooltip, type);
    }

}