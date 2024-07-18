package net.xylonity.knightquest.common.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestTags {
    public static class Blocks {
        public static final TagKey<Block> KNIGHTQUEST_TOOLS = tag("knightquest_tools");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, name));
        }
    }
}