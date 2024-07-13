package net.xylonity.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;

public class KnightQuestCreativeModeTabs {
    public static final ItemGroup Knight_Quest = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(KnightQuest.MOD_ID, "knightquest"),
            FabricItemGroup.builder().texture(Identifier.of(KnightQuest.MOD_ID, "textures/gui/container/creative_inventory/tab_knightquest.png")).displayName(Text.translatable("itemgroup.knightquest"))
                    .icon(() -> new ItemStack(KnightQuestItems.PALADIN_SWORD)).entries((displayContext, entries) -> {
                        entries.add(KnightQuestBlocks.GREAT_CHALICE);

                        entries.add(KnightQuestItems.SMALL_ESSENCE);
                        entries.add(KnightQuestItems.GREAT_ESSENCE);
                        entries.add(KnightQuestItems.EMPTY_GOBLET);
                        entries.add(KnightQuestItems.FILLED_GOBLET);

                        entries.add(KnightQuestItems.RATMAN_EYE);
                        entries.add(KnightQuestItems.LIZZY_SCALE);

                        entries.add(KnightQuestItems.STEEL_AXE);
                        entries.add(KnightQuestItems.WATER_AXE);

                        entries.add(KnightQuestItems.PALADIN_SWORD);
                        entries.add(KnightQuestItems.NAIL_SWORD);
                        entries.add(KnightQuestItems.UCHIGATANA);
                        entries.add(KnightQuestItems.KUKRI);
                        entries.add(KnightQuestItems.KHOPESH);
                        entries.add(KnightQuestItems.CLEAVER);
                        entries.add(KnightQuestItems.CRIMSON_SWORD);
                        entries.add(KnightQuestItems.WATER_SWORD);
                        entries.add(KnightQuestItems.STEEL_SWORD);

                        entries.add(KnightQuestItems.GREMLIN_EGG);
                        entries.add(KnightQuestItems.ELD_KNIGHT_EGG);
                        entries.add(KnightQuestItems.ELD_BOMB_EGG);
                        entries.add(KnightQuestItems.SAMHAIN_EGG);
                        entries.add(KnightQuestItems.SWAMPMAN_EGG);
                        entries.add(KnightQuestItems.RATMAN_EGG);
                        entries.add(KnightQuestItems.LIZZY_EGG);
                        entries.add(KnightQuestItems.BADPATCH_EGG);
                        entries.add(KnightQuestItems.GHOSTY_EGG);
                        entries.add(KnightQuestItems.GHASTLING_EGG);

                        entries.add(KnightQuestItems.SQUIRE_HELMET);
                        entries.add(KnightQuestItems.SQUIRE_CHESTPLATE);
                        entries.add(KnightQuestItems.SQUIRE_LEGGINGS);
                        entries.add(KnightQuestItems.SQUIRE_BOOTS);

                        entries.add(KnightQuestItems.APPLE_HELMET);
                        entries.add(KnightQuestItems.APPLE_CHESTPLATE);
                        entries.add(KnightQuestItems.APPLE_LEGGINGS);
                        entries.add(KnightQuestItems.APPLE_BOOTS);

                        entries.add(KnightQuestItems.BAMBOO_BLUE_HELMET);
                        entries.add(KnightQuestItems.BAMBOO_BLUE_CHESTPLATE);
                        entries.add(KnightQuestItems.BAMBOO_BLUE_LEGGINGS);
                        entries.add(KnightQuestItems.BAMBOO_BLUE_BOOTS);

                        entries.add(KnightQuestItems.BAMBOO_GREEN_HELMET);
                        entries.add(KnightQuestItems.BAMBOO_GREEN_CHESTPLATE);
                        entries.add(KnightQuestItems.BAMBOO_GREEN_LEGGINGS);
                        entries.add(KnightQuestItems.BAMBOO_GREEN_BOOTS);

                        entries.add(KnightQuestItems.TENGU_HELMET);
                        entries.add(KnightQuestItems.BAMBOO_HELMET);
                        entries.add(KnightQuestItems.BAMBOO_CHESTPLATE);
                        entries.add(KnightQuestItems.BAMBOO_LEGGINGS);
                        entries.add(KnightQuestItems.BAMBOO_BOOTS);

                        entries.add(KnightQuestItems.BAT_HELMET);
                        entries.add(KnightQuestItems.BAT_CHESTPLATE);
                        entries.add(KnightQuestItems.BAT_LEGGINGS);
                        entries.add(KnightQuestItems.BAT_BOOTS);

                        entries.add(KnightQuestItems.BLAZE_HELMET);
                        entries.add(KnightQuestItems.BLAZE_CHESTPLATE);
                        entries.add(KnightQuestItems.BLAZE_LEGGINGS);
                        entries.add(KnightQuestItems.BLAZE_BOOTS);

                        entries.add(KnightQuestItems.BOW_HELMET);
                        entries.add(KnightQuestItems.BOW_CHESTPLATE);
                        entries.add(KnightQuestItems.BOW_LEGGINGS);
                        entries.add(KnightQuestItems.BOW_BOOTS);

                        entries.add(KnightQuestItems.HORN_HELMET);
                        entries.add(KnightQuestItems.HORN_CHESTPLATE);
                        entries.add(KnightQuestItems.HORN_LEGGINGS);
                        entries.add(KnightQuestItems.HORN_BOOTS);

                        entries.add(KnightQuestItems.CREEPER_HELMET);
                        entries.add(KnightQuestItems.CREEPER_CHESTPLATE);
                        entries.add(KnightQuestItems.CREEPER_LEGGINGS);
                        entries.add(KnightQuestItems.CREEPER_BOOTS);

                        entries.add(KnightQuestItems.DEEPSLATE_HELMET);
                        entries.add(KnightQuestItems.DEEPSLATE_CHESTPLATE);
                        entries.add(KnightQuestItems.DEEPSLATE_LEGGINGS);
                        entries.add(KnightQuestItems.DEEPSLATE_BOOTS);

                        entries.add(KnightQuestItems.DRAGON_HELMET);
                        entries.add(KnightQuestItems.DRAGON_CHESTPLATE);
                        entries.add(KnightQuestItems.DRAGON_LEGGINGS);
                        entries.add(KnightQuestItems.DRAGON_BOOTS);

                        entries.add(KnightQuestItems.ENDERMAN_HELMET);
                        entries.add(KnightQuestItems.ENDERMAN_CHESTPLATE);
                        entries.add(KnightQuestItems.ENDERMAN_LEGGINGS);
                        entries.add(KnightQuestItems.ENDERMAN_BOOTS);

                        entries.add(KnightQuestItems.EVOKER_HELMET);
                        entries.add(KnightQuestItems.EVOKER_CHESTPLATE);
                        entries.add(KnightQuestItems.EVOKER_LEGGINGS);
                        entries.add(KnightQuestItems.EVOKER_BOOTS);

                        entries.add(KnightQuestItems.FORZE_HELMET);
                        entries.add(KnightQuestItems.FORZE_CHESTPLATE);
                        entries.add(KnightQuestItems.FORZE_LEGGINGS);
                        entries.add(KnightQuestItems.FORZE_BOOTS);

                        entries.add(KnightQuestItems.HOLLOW_HELMET);
                        entries.add(KnightQuestItems.HOLLOW_CHESTPLATE);
                        entries.add(KnightQuestItems.HOLLOW_LEGGINGS);
                        entries.add(KnightQuestItems.HOLLOW_BOOTS);

                        entries.add(KnightQuestItems.NETHER_HELMET);
                        entries.add(KnightQuestItems.NETHER_CHESTPLATE);
                        entries.add(KnightQuestItems.NETHER_LEGGINGS);
                        entries.add(KnightQuestItems.NETHER_BOOTS);

                        entries.add(KnightQuestItems.VETERAN_HELMET);
                        entries.add(KnightQuestItems.VETERAN_CHESTPLATE);
                        entries.add(KnightQuestItems.VETERAN_LEGGINGS);
                        entries.add(KnightQuestItems.VETERAN_BOOTS);

                        entries.add(KnightQuestItems.PATH_HELMET);
                        entries.add(KnightQuestItems.PATH_CHESTPLATE);
                        entries.add(KnightQuestItems.PATH_LEGGINGS);
                        entries.add(KnightQuestItems.PATH_BOOTS);

                        entries.add(KnightQuestItems.PHANTOM_HELMET);
                        entries.add(KnightQuestItems.PHANTOM_CHESTPLATE);
                        entries.add(KnightQuestItems.PHANTOM_LEGGINGS);
                        entries.add(KnightQuestItems.PHANTOM_BOOTS);

                        entries.add(KnightQuestItems.SEA_HELMET);
                        entries.add(KnightQuestItems.SEA_CHESTPLATE);
                        entries.add(KnightQuestItems.SEA_LEGGINGS);
                        entries.add(KnightQuestItems.SEA_BOOTS);

                        entries.add(KnightQuestItems.SHIELD_HELMET);
                        entries.add(KnightQuestItems.SHIELD_CHESTPLATE);
                        entries.add(KnightQuestItems.SHIELD_LEGGINGS);
                        entries.add(KnightQuestItems.SHIELD_BOOTS);

                        entries.add(KnightQuestItems.SILVER_HELMET);
                        entries.add(KnightQuestItems.SILVER_CHESTPLATE);
                        entries.add(KnightQuestItems.SILVER_LEGGINGS);
                        entries.add(KnightQuestItems.SILVER_BOOTS);

                        entries.add(KnightQuestItems.SILVERFISH_HELMET);
                        entries.add(KnightQuestItems.SILVERFISH_CHESTPLATE);
                        entries.add(KnightQuestItems.SILVERFISH_LEGGINGS);
                        entries.add(KnightQuestItems.SILVERFISH_BOOTS);

                        entries.add(KnightQuestItems.SKELETON_HELMET);
                        entries.add(KnightQuestItems.SKELETON_CHESTPLATE);
                        entries.add(KnightQuestItems.SKELETON_LEGGINGS);
                        entries.add(KnightQuestItems.SKELETON_BOOTS);

                        entries.add(KnightQuestItems.SPIDER_HELMET);
                        entries.add(KnightQuestItems.SPIDER_CHESTPLATE);
                        entries.add(KnightQuestItems.SPIDER_LEGGINGS);
                        entries.add(KnightQuestItems.SPIDER_BOOTS);

                        entries.add(KnightQuestItems.WARLORD_HELMET);
                        entries.add(KnightQuestItems.WARLORD_CHESTPLATE);
                        entries.add(KnightQuestItems.WARLORD_LEGGINGS);
                        entries.add(KnightQuestItems.WARLORD_BOOTS);

                        entries.add(KnightQuestItems.STRAWHAT_HELMET);
                        entries.add(KnightQuestItems.STRAWHAT_CHESTPLATE);
                        entries.add(KnightQuestItems.STRAWHAT_LEGGINGS);
                        entries.add(KnightQuestItems.STRAWHAT_BOOTS);

                        entries.add(KnightQuestItems.PIRATE_HELMET);
                        entries.add(KnightQuestItems.PIRATE2_HELMET);
                        entries.add(KnightQuestItems.PIRATE3_HELMET);
                        entries.add(KnightQuestItems.PIRATE_CHESTPLATE);
                        entries.add(KnightQuestItems.PIRATE_LEGGINGS);
                        entries.add(KnightQuestItems.PIRATE_BOOTS);

                        entries.add(KnightQuestItems.CONQUISTADOR_HELMET);
                        entries.add(KnightQuestItems.CONQUISTADOR2_HELMET);
                        entries.add(KnightQuestItems.CONQUISTADOR3_HELMET);
                        entries.add(KnightQuestItems.CONQUISTADOR_CHESTPLATE);
                        entries.add(KnightQuestItems.CONQUISTADOR_LEGGINGS);
                        entries.add(KnightQuestItems.CONQUISTADOR_BOOTS);

                        entries.add(KnightQuestItems.ZOMBIE_HELMET);
                        entries.add(KnightQuestItems.ZOMBIE_HELMET2);
                        entries.add(KnightQuestItems.ZOMBIE_CHESTPLATE);
                        entries.add(KnightQuestItems.ZOMBIE_LEGGINGS);
                        entries.add(KnightQuestItems.ZOMBIE_BOOTS);

                        entries.add(KnightQuestItems.HUSK_HELMET);
                        entries.add(KnightQuestItems.HUSK_HELMET2);
                        entries.add(KnightQuestItems.HUSK_HELMET3);
                        entries.add(KnightQuestItems.HUSK_CHESTPLATE);
                        entries.add(KnightQuestItems.HUSK_LEGGINGS);
                        entries.add(KnightQuestItems.HUSK_BOOTS);

                        entries.add(KnightQuestItems.WITHER_HELMET);
                        entries.add(KnightQuestItems.WITHER_CHESTPLATE);
                        entries.add(KnightQuestItems.WITHER_LEGGINGS);
                        entries.add(KnightQuestItems.WITHER_BOOTS);

                        entries.add(KnightQuestItems.CHAINMAIL_HELMET);
                        entries.add(KnightQuestItems.CHAINMAIL_HELMET2);

                        entries.add(KnightQuestItems.TUNIC_BLUE_LEGGINGS);
                        entries.add(KnightQuestItems.TUNIC_YELLOW_LEGGINGS);
                        entries.add(KnightQuestItems.TUNIC_RED_LEGGINGS);
                        entries.add(KnightQuestItems.TUNIC_GREEN_LEGGINGS);
                        entries.add(KnightQuestItems.TUNIC_SEA_LEGGINGS);

                        entries.add(KnightQuestItems.WITCH_HELMET);
                        entries.add(KnightQuestItems.WITCH_CHESTPLATE);
                        entries.add(KnightQuestItems.WITCH_LEGGINGS);
                        entries.add(KnightQuestItems.WITCH_BOOTS);

                        entries.add(KnightQuestItems.POLAR_HELMET);
                        entries.add(KnightQuestItems.POLAR_CHESTPLATE);
                        entries.add(KnightQuestItems.POLAR_LEGGINGS);
                        entries.add(KnightQuestItems.POLAR_BOOTS);

                        entries.add(KnightQuestItems.SHINOBI_HELMET);
                        entries.add(KnightQuestItems.SHINOBI_CHESTPLATE);
                        entries.add(KnightQuestItems.SHINOBI_LEGGINGS);
                        entries.add(KnightQuestItems.SHINOBI_BOOTS);

                        entries.add(KnightQuestItems.SKULK_HELMET);
                        entries.add(KnightQuestItems.SKULK2_HELMET);
                        entries.add(KnightQuestItems.SKULK3_HELMET);
                        entries.add(KnightQuestItems.SKULK4_HELMET);
                        entries.add(KnightQuestItems.SKULK_CHESTPLATE);
                        entries.add(KnightQuestItems.SKULK_LEGGINGS);
                        entries.add(KnightQuestItems.SKULK_BOOTS);

                    }).build());

    public static void register() {
        KnightQuest.LOGGER.info("Registering Item Group: " + KnightQuest.MOD_ID);
    }

}
