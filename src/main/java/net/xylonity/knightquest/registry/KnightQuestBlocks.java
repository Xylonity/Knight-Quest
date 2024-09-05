package net.xylonity.knightquest.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.block.ChaliceBlock;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KnightQuestBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, KnightQuest.MOD_ID);

    public static final DeferredHolder<Block, ChaliceBlock> GREAT_CHALICE = BLOCKS.register("great_chalice", () -> new ChaliceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER)
                    .lightLevel(state ->
            switch (state.getValue(ChaliceBlock.fill)) {
                case 1 -> 1;
                case 2, 9 -> 2;
                case 3, 8 -> 4;
                case 4, 7 -> 6;
                case 5, 6 -> 8;
                default -> 0;
            }))
            {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, Item.@NotNull TooltipContext pContext, @NotNull List<Component> pTootipComponents, @NotNull TooltipFlag pTooltipFlag) {
                    pTootipComponents.add(Component.translatable("tooltip.item.knightquest.great_chalice"));
                    super.appendHoverText(pStack, pContext, pTootipComponents, pTooltipFlag);
                }
            });

}
