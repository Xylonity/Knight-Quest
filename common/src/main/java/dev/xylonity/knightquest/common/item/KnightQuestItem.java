package dev.xylonity.knightquest.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightQuestItem extends Item {

    private final String tooltipInfoName;

    public KnightQuestItem(Properties properties, String tooltipInfoName) {
        super(properties);
        this.tooltipInfoName = tooltipInfoName;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {

        list.add(Component.translatable("tooltip.item.knightquest." + tooltipInfoName));

        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }
}