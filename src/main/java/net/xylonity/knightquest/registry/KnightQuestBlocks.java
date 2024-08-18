package net.xylonity.knightquest.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
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

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return KnightQuestItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static final RegistryObject<Block> GREAT_CHALICE = registerBlock("great_chalice",
            () -> new ChaliceBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).requiresCorrectToolForDrops().strength(3.0F, 6.0F).sound(SoundType.COPPER).lightLevel(state -> state.getValue(ChaliceBlock.fill).equals(5) ? 5 : 0))
            {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable BlockGetter pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
                    pTooltip.add(new TextComponent("tooltip.item.knightquest.great_chalice"));
                    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
                }
            });

}
