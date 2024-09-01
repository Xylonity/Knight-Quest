package net.xylonity.knightquest.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.block.ChaliceBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class KnightQuestBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KnightQuest.MOD_ID);

    private static <X extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<X> block) {
        return KnightQuestItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <X extends Block> RegistryObject<X> registerBlock(String name, Supplier<X> block) {
        RegistryObject<X> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static final RegistryObject<Block> GREAT_CHALICE = registerBlock("great_chalice",
            () -> new ChaliceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER)
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
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable BlockGetter pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
                    pTooltip.add(Component.translatable("tooltip.item.knightquest.great_chalice"));
                    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
                }
            });

}
