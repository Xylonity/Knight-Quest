package net.xylonity.knightquest.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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

import java.util.List;
import java.util.function.Supplier;

public class KnightQuestBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KnightQuest.MOD_ID);

    private static <T extends Block> void registerBlockItem(RegistryObject<T> block) {
        KnightQuestItems.ITEMS.register("great_chalice", () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register("great_chalice", block);
        registerBlockItem(toReturn);
        return toReturn;
    }

    public static final RegistryObject<Block> GREAT_CHALICE = registerBlock(
            () -> new ChaliceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER).lightLevel(state -> state.getValue(ChaliceBlock.fill).equals(5) ? 5 : 0))
            {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, Item.@NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.great_chalice"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

}
