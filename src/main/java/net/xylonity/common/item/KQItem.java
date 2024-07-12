package net.xylonity.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KQItem extends Item {

    private final String tooltipInfoName;

    public KQItem(Settings settings, String tooltipInfoName) {
        super(settings);
        this.tooltipInfoName = tooltipInfoName;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.item.knightquest." + tooltipInfoName));
        super.appendTooltip(stack, world, tooltip, context);
    }
}