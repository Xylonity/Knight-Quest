package net.xylonity.registry;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.block.ChaliceBlock;

public class KnightQuestBlocks {

    public static final Block GREAT_CHALICE = registerBlock("great_chalice",
            new ChaliceBlock(AbstractBlock.Settings.create().nonOpaque().mapColor(MapColor.STONE_GRAY).requiresTool().strength(2.0F).sounds(BlockSoundGroup.COPPER)
                    .luminance(state -> state.get(ChaliceBlock.fill).equals(5) ? 5 : 0)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(KnightQuest.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(KnightQuest.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void register() {

        KnightQuest.LOGGER.debug("Registering blocks for " + KnightQuest.MOD_ID);

    }
}
