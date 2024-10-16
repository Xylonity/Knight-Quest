package dev.xylonity.knightquest.registry;

import dev.xylonity.knightlib.compat.registry.KnightLibBlocks;
import dev.xylonity.knightlib.compat.registry.KnightLibItems;
import dev.xylonity.knightquest.KnightQuest;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class KnightQuestCreativeModeTabs {

        public static final CreativeModeTab Knight_Quest = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "knightquest"),
                FabricItemGroup.builder().backgroundTexture(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/gui/container/creative_inventory/tab_knightquest.png")).title(Component.translatable("itemgroup.knightquest"))
                        .icon(() -> new ItemStack(KnightQuestItems.PALADIN_SWORD)).displayItems((displayContext, entries) -> {
                            entries.accept(KnightLibBlocks.GREAT_CHALICE);

                            entries.accept(KnightLibItems.SMALL_ESSENCE.get());
                            entries.accept(KnightLibItems.GREAT_ESSENCE.get());
                            entries.accept(KnightQuestItems.RADIANT_ESSENCE);
                            entries.accept(KnightQuestItems.EMPTY_GOBLET);
                            entries.accept(KnightQuestItems.FILLED_GOBLET);

                            entries.accept(KnightQuestItems.RATMAN_EYE);
                            entries.accept(KnightQuestItems.LIZZY_SCALE);

                            entries.accept(KnightQuestItems.STEEL_AXE);
                            entries.accept(KnightQuestItems.WATER_AXE);

                            entries.accept(KnightQuestItems.PALADIN_SWORD);
                            entries.accept(KnightQuestItems.NAIL_SWORD);
                            entries.accept(KnightQuestItems.UCHIGATANA);
                            entries.accept(KnightQuestItems.KUKRI);
                            entries.accept(KnightQuestItems.KHOPESH);
                            entries.accept(KnightQuestItems.CLEAVER);
                            entries.accept(KnightQuestItems.CRIMSON_SWORD);
                            entries.accept(KnightQuestItems.WATER_SWORD);
                            entries.accept(KnightQuestItems.STEEL_SWORD);

                            entries.accept(KnightQuestEntities.GREMLIN_EGG);
                            entries.accept(KnightQuestEntities.ELD_KNIGHT_EGG);
                            entries.accept(KnightQuestEntities.ELD_BOMB_EGG);
                            entries.accept(KnightQuestEntities.SAMHAIN_EGG);
                            entries.accept(KnightQuestEntities.SWAMPMAN_EGG);
                            entries.accept(KnightQuestEntities.RATMAN_EGG);
                            entries.accept(KnightQuestEntities.LIZZY_EGG);
                            entries.accept(KnightQuestEntities.BADPATCH_EGG);
                            entries.accept(KnightQuestEntities.GHOSTY_EGG);
                            entries.accept(KnightQuestEntities.NETHERMAN_EGG);
                            //entries.accept(KnightQuestItems.GHASTLING_EGG);

                            entries.accept(KnightQuestItems.SQUIRE_HELMET);
                            entries.accept(KnightQuestItems.SQUIRE_CHESTPLATE);
                            entries.accept(KnightQuestItems.SQUIRE_LEGGINGS);
                            entries.accept(KnightQuestItems.SQUIRE_BOOTS);

                            entries.accept(KnightQuestItems.APPLE_HELMET);
                            entries.accept(KnightQuestItems.APPLE_CHESTPLATE);
                            entries.accept(KnightQuestItems.APPLE_LEGGINGS);
                            entries.accept(KnightQuestItems.APPLE_BOOTS);

                            entries.accept(KnightQuestItems.BAMBOO_BLUE_HELMET);
                            entries.accept(KnightQuestItems.BAMBOO_BLUE_CHESTPLATE);
                            entries.accept(KnightQuestItems.BAMBOO_BLUE_LEGGINGS);
                            entries.accept(KnightQuestItems.BAMBOO_BLUE_BOOTS);

                            entries.accept(KnightQuestItems.BAMBOO_GREEN_HELMET);
                            entries.accept(KnightQuestItems.BAMBOO_GREEN_CHESTPLATE);
                            entries.accept(KnightQuestItems.BAMBOO_GREEN_LEGGINGS);
                            entries.accept(KnightQuestItems.BAMBOO_GREEN_BOOTS);

                            entries.accept(KnightQuestItems.TENGU_HELMET);
                            entries.accept(KnightQuestItems.BAMBOO_HELMET);
                            entries.accept(KnightQuestItems.BAMBOO_CHESTPLATE);
                            entries.accept(KnightQuestItems.BAMBOO_LEGGINGS);
                            entries.accept(KnightQuestItems.BAMBOO_BOOTS);

                            entries.accept(KnightQuestItems.BAT_HELMET);
                            entries.accept(KnightQuestItems.BAT_CHESTPLATE);
                            entries.accept(KnightQuestItems.BAT_LEGGINGS);
                            entries.accept(KnightQuestItems.BAT_BOOTS);

                            entries.accept(KnightQuestItems.BLAZE_HELMET);
                            entries.accept(KnightQuestItems.BLAZE_CHESTPLATE);
                            entries.accept(KnightQuestItems.BLAZE_LEGGINGS);
                            entries.accept(KnightQuestItems.BLAZE_BOOTS);

                            entries.accept(KnightQuestItems.BOW_HELMET);
                            entries.accept(KnightQuestItems.BOW_CHESTPLATE);
                            entries.accept(KnightQuestItems.BOW_LEGGINGS);
                            entries.accept(KnightQuestItems.BOW_BOOTS);

                            entries.accept(KnightQuestItems.HORN_HELMET);
                            entries.accept(KnightQuestItems.HORN_CHESTPLATE);
                            entries.accept(KnightQuestItems.HORN_LEGGINGS);
                            entries.accept(KnightQuestItems.HORN_BOOTS);

                            entries.accept(KnightQuestItems.CREEPER_HELMET);
                            entries.accept(KnightQuestItems.CREEPER_CHESTPLATE);
                            entries.accept(KnightQuestItems.CREEPER_LEGGINGS);
                            entries.accept(KnightQuestItems.CREEPER_BOOTS);

                            entries.accept(KnightQuestItems.DEEPSLATE_HELMET);
                            entries.accept(KnightQuestItems.DEEPSLATE_CHESTPLATE);
                            entries.accept(KnightQuestItems.DEEPSLATE_LEGGINGS);
                            entries.accept(KnightQuestItems.DEEPSLATE_BOOTS);

                            entries.accept(KnightQuestItems.DRAGON_HELMET);
                            entries.accept(KnightQuestItems.DRAGON_CHESTPLATE);
                            entries.accept(KnightQuestItems.DRAGON_LEGGINGS);
                            entries.accept(KnightQuestItems.DRAGON_BOOTS);

                            entries.accept(KnightQuestItems.ENDERMAN_HELMET);
                            entries.accept(KnightQuestItems.ENDERMAN_CHESTPLATE);
                            entries.accept(KnightQuestItems.ENDERMAN_LEGGINGS);
                            entries.accept(KnightQuestItems.ENDERMAN_BOOTS);

                            entries.accept(KnightQuestItems.EVOKER_HELMET);
                            entries.accept(KnightQuestItems.EVOKER_CHESTPLATE);
                            entries.accept(KnightQuestItems.EVOKER_LEGGINGS);
                            entries.accept(KnightQuestItems.EVOKER_BOOTS);

                            entries.accept(KnightQuestItems.FORZE_HELMET);
                            entries.accept(KnightQuestItems.FORZE_CHESTPLATE);
                            entries.accept(KnightQuestItems.FORZE_LEGGINGS);
                            entries.accept(KnightQuestItems.FORZE_BOOTS);

                            entries.accept(KnightQuestItems.HOLLOW_HELMET);
                            entries.accept(KnightQuestItems.HOLLOW_CHESTPLATE);
                            entries.accept(KnightQuestItems.HOLLOW_LEGGINGS);
                            entries.accept(KnightQuestItems.HOLLOW_BOOTS);

                            entries.accept(KnightQuestItems.NETHER_HELMET);
                            entries.accept(KnightQuestItems.NETHER_CHESTPLATE);
                            entries.accept(KnightQuestItems.NETHER_LEGGINGS);
                            entries.accept(KnightQuestItems.NETHER_BOOTS);

                            entries.accept(KnightQuestItems.VETERAN_HELMET);
                            entries.accept(KnightQuestItems.VETERAN_CHESTPLATE);
                            entries.accept(KnightQuestItems.VETERAN_LEGGINGS);
                            entries.accept(KnightQuestItems.VETERAN_BOOTS);

                            entries.accept(KnightQuestItems.PATH_HELMET);
                            entries.accept(KnightQuestItems.PATH_CHESTPLATE);
                            entries.accept(KnightQuestItems.PATH_LEGGINGS);
                            entries.accept(KnightQuestItems.PATH_BOOTS);

                            entries.accept(KnightQuestItems.PHANTOM_HELMET);
                            entries.accept(KnightQuestItems.PHANTOM_CHESTPLATE);
                            entries.accept(KnightQuestItems.PHANTOM_LEGGINGS);
                            entries.accept(KnightQuestItems.PHANTOM_BOOTS);

                            entries.accept(KnightQuestItems.SEA_HELMET);
                            entries.accept(KnightQuestItems.SEA_CHESTPLATE);
                            entries.accept(KnightQuestItems.SEA_LEGGINGS);
                            entries.accept(KnightQuestItems.SEA_BOOTS);

                            entries.accept(KnightQuestItems.SHIELD_HELMET);
                            entries.accept(KnightQuestItems.SHIELD_CHESTPLATE);
                            entries.accept(KnightQuestItems.SHIELD_LEGGINGS);
                            entries.accept(KnightQuestItems.SHIELD_BOOTS);

                            entries.accept(KnightQuestItems.SILVER_HELMET);
                            entries.accept(KnightQuestItems.SILVER_CHESTPLATE);
                            entries.accept(KnightQuestItems.SILVER_LEGGINGS);
                            entries.accept(KnightQuestItems.SILVER_BOOTS);

                            entries.accept(KnightQuestItems.SILVERFISH_HELMET);
                            entries.accept(KnightQuestItems.SILVERFISH_CHESTPLATE);
                            entries.accept(KnightQuestItems.SILVERFISH_LEGGINGS);
                            entries.accept(KnightQuestItems.SILVERFISH_BOOTS);

                            entries.accept(KnightQuestItems.SKELETON_HELMET);
                            entries.accept(KnightQuestItems.SKELETON_CHESTPLATE);
                            entries.accept(KnightQuestItems.SKELETON_LEGGINGS);
                            entries.accept(KnightQuestItems.SKELETON_BOOTS);

                            entries.accept(KnightQuestItems.SPIDER_HELMET);
                            entries.accept(KnightQuestItems.SPIDER_CHESTPLATE);
                            entries.accept(KnightQuestItems.SPIDER_LEGGINGS);
                            entries.accept(KnightQuestItems.SPIDER_BOOTS);

                            entries.accept(KnightQuestItems.WARLORD_HELMET);
                            entries.accept(KnightQuestItems.WARLORD_CHESTPLATE);
                            entries.accept(KnightQuestItems.WARLORD_LEGGINGS);
                            entries.accept(KnightQuestItems.WARLORD_BOOTS);

                            entries.accept(KnightQuestItems.STRAWHAT_HELMET);
                            entries.accept(KnightQuestItems.STRAWHAT_CHESTPLATE);
                            entries.accept(KnightQuestItems.STRAWHAT_LEGGINGS);
                            entries.accept(KnightQuestItems.STRAWHAT_BOOTS);

                            entries.accept(KnightQuestItems.PIRATE_HELMET);
                            entries.accept(KnightQuestItems.PIRATE2_HELMET);
                            entries.accept(KnightQuestItems.PIRATE3_HELMET);
                            entries.accept(KnightQuestItems.PIRATE_CHESTPLATE);
                            entries.accept(KnightQuestItems.PIRATE_LEGGINGS);
                            entries.accept(KnightQuestItems.PIRATE_BOOTS);

                            entries.accept(KnightQuestItems.CONQUISTADOR_HELMET);
                            entries.accept(KnightQuestItems.CONQUISTADOR2_HELMET);
                            entries.accept(KnightQuestItems.CONQUISTADOR3_HELMET);
                            entries.accept(KnightQuestItems.CONQUISTADOR_CHESTPLATE);
                            entries.accept(KnightQuestItems.CONQUISTADOR_LEGGINGS);
                            entries.accept(KnightQuestItems.CONQUISTADOR_BOOTS);

                            entries.accept(KnightQuestItems.ZOMBIE_HELMET);
                            entries.accept(KnightQuestItems.ZOMBIE_HELMET2);
                            entries.accept(KnightQuestItems.ZOMBIE_CHESTPLATE);
                            entries.accept(KnightQuestItems.ZOMBIE_LEGGINGS);
                            entries.accept(KnightQuestItems.ZOMBIE_BOOTS);

                            entries.accept(KnightQuestItems.HUSK_HELMET);
                            entries.accept(KnightQuestItems.HUSK_HELMET2);
                            entries.accept(KnightQuestItems.HUSK_HELMET3);
                            entries.accept(KnightQuestItems.HUSK_CHESTPLATE);
                            entries.accept(KnightQuestItems.HUSK_LEGGINGS);
                            entries.accept(KnightQuestItems.HUSK_BOOTS);

                            entries.accept(KnightQuestItems.WITHER_HELMET);
                            entries.accept(KnightQuestItems.WITHER_CHESTPLATE);
                            entries.accept(KnightQuestItems.WITHER_LEGGINGS);
                            entries.accept(KnightQuestItems.WITHER_BOOTS);

                            entries.accept(KnightQuestItems.CHAINMAIL_HELMET);
                            entries.accept(KnightQuestItems.CHAINMAIL_HELMET2);

                            entries.accept(KnightQuestItems.TUNIC_BLUE_LEGGINGS);
                            entries.accept(KnightQuestItems.TUNIC_YELLOW_LEGGINGS);
                            entries.accept(KnightQuestItems.TUNIC_RED_LEGGINGS);
                            entries.accept(KnightQuestItems.TUNIC_GREEN_LEGGINGS);
                            entries.accept(KnightQuestItems.TUNIC_SEA_LEGGINGS);

                            entries.accept(KnightQuestItems.WITCH_HELMET);
                            entries.accept(KnightQuestItems.WITCH_CHESTPLATE);
                            entries.accept(KnightQuestItems.WITCH_LEGGINGS);
                            entries.accept(KnightQuestItems.WITCH_BOOTS);

                            entries.accept(KnightQuestItems.POLAR_HELMET);
                            entries.accept(KnightQuestItems.POLAR_CHESTPLATE);
                            entries.accept(KnightQuestItems.POLAR_LEGGINGS);
                            entries.accept(KnightQuestItems.POLAR_BOOTS);

                            entries.accept(KnightQuestItems.SHINOBI_HELMET);
                            entries.accept(KnightQuestItems.SHINOBI_CHESTPLATE);
                            entries.accept(KnightQuestItems.SHINOBI_LEGGINGS);
                            entries.accept(KnightQuestItems.SHINOBI_BOOTS);

                            entries.accept(KnightQuestItems.SKULK_HELMET);
                            entries.accept(KnightQuestItems.SKULK2_HELMET);
                            entries.accept(KnightQuestItems.SKULK3_HELMET);
                            entries.accept(KnightQuestItems.SKULK4_HELMET);
                            entries.accept(KnightQuestItems.SKULK_CHESTPLATE);
                            entries.accept(KnightQuestItems.SKULK_LEGGINGS);
                            entries.accept(KnightQuestItems.SKULK_BOOTS);

                        }).build());

        public static void init() {
        }

    }