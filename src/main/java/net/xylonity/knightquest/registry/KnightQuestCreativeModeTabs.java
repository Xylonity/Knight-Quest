package net.xylonity.knightquest.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KnightQuest.MOD_ID);

    public static final RegistryObject<CreativeModeTab> KNIGHTQUEST_TAB =
            CREATIVE_MODE_TABS.register("knightquest_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(KnightQuestItems.PALADIN_SWORD.get()))
                            .title(Component.translatable("itemgroup.knightquest"))
                            .displayItems((itemDisplayParameters, output) -> {

                                output.accept(KnightQuestBlocks.GREAT_CHALICE.get());

                                output.accept(KnightQuestItems.SMALL_ESSENCE.get());
                                output.accept(KnightQuestItems.GREAT_ESSENCE.get());
                                output.accept(KnightQuestItems.RADIANT_ESSENCE.get());
                                output.accept(KnightQuestItems.EMPTY_GOBLET.get());
                                output.accept(KnightQuestItems.FILLED_GOBLET.get());
                                output.accept(KnightQuestItems.RATMAN_EYE.get());
                                output.accept(KnightQuestItems.LIZZY_SCALE.get());
                                output.accept(KnightQuestItems.PALADIN_SWORD.get());
                                output.accept(KnightQuestItems.NAIL_SWORD.get());
                                output.accept(KnightQuestItems.UCHIGATANA.get());
                                output.accept(KnightQuestItems.KUKRI.get());
                                output.accept(KnightQuestItems.KHOPESH.get());
                                output.accept(KnightQuestItems.CLEAVER.get());
                                output.accept(KnightQuestItems.CRIMSON_SWORD.get());
                                output.accept(KnightQuestItems.WATER_SWORD.get());
                                output.accept(KnightQuestItems.STEEL_SWORD.get());

                                output.accept(KnightQuestItems.WATER_AXE.get());
                                output.accept(KnightQuestItems.STEEL_AXE.get());

                                output.accept(KnightQuestItems.GREMLIN_EGG.get());
                                output.accept(KnightQuestItems.ELD_KNIGHT_EGG.get());
                                output.accept(KnightQuestItems.ELD_BOMB_EGG.get());
                                output.accept(KnightQuestItems.SAMHAIN_EGG.get());
                                output.accept(KnightQuestItems.SWAMPMAN_EGG.get());
                                output.accept(KnightQuestItems.RATMAN_EGG.get());
                                output.accept(KnightQuestItems.LIZZY_EGG.get());
                                output.accept(KnightQuestItems.BADPATCH_EGG.get());
                                output.accept(KnightQuestItems.GHOSTY_EGG.get());
                                output.accept(KnightQuestItems.GHASTLING_EGG.get());
                                output.accept(KnightQuestItems.MOMMA_LIZZY_EGG.get());

                                output.accept(KnightQuestItems.SQUIRE_HELMET.get());
                                output.accept(KnightQuestItems.SQUIRE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SQUIRE_LEGGINGS.get());
                                output.accept(KnightQuestItems.SQUIRE_BOOTS.get());

                                output.accept(KnightQuestItems.APPLE_HELMET.get());
                                output.accept(KnightQuestItems.APPLE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.APPLE_LEGGINGS.get());
                                output.accept(KnightQuestItems.APPLE_BOOTS.get());

                                output.accept(KnightQuestItems.BAMBOO_BLUE_HELMET.get());
                                output.accept(KnightQuestItems.BAMBOO_BLUE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.BAMBOO_BLUE_LEGGINGS.get());
                                output.accept(KnightQuestItems.BAMBOO_BLUE_BOOTS.get());

                                output.accept(KnightQuestItems.BAMBOO_GREEN_HELMET.get());
                                output.accept(KnightQuestItems.BAMBOO_GREEN_CHESTPLATE.get());
                                output.accept(KnightQuestItems.BAMBOO_GREEN_LEGGINGS.get());
                                output.accept(KnightQuestItems.BAMBOO_GREEN_BOOTS.get());

                                output.accept(KnightQuestItems.TENGU_HELMET.get());
                                output.accept(KnightQuestItems.BAMBOO_HELMET.get());
                                output.accept(KnightQuestItems.BAMBOO_CHESTPLATE.get());
                                output.accept(KnightQuestItems.BAMBOO_LEGGINGS.get());
                                output.accept(KnightQuestItems.BAMBOO_BOOTS.get());

                                output.accept(KnightQuestItems.BAT_HELMET.get());
                                output.accept(KnightQuestItems.BAT_CHESTPLATE.get());
                                output.accept(KnightQuestItems.BAT_LEGGINGS.get());
                                output.accept(KnightQuestItems.BAT_BOOTS.get());

                                output.accept(KnightQuestItems.BLAZE_HELMET.get());
                                output.accept(KnightQuestItems.BLAZE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.BLAZE_LEGGINGS.get());
                                output.accept(KnightQuestItems.BLAZE_BOOTS.get());

                                output.accept(KnightQuestItems.BOW_HELMET.get());
                                output.accept(KnightQuestItems.BOW_CHESTPLATE.get());
                                output.accept(KnightQuestItems.BOW_LEGGINGS.get());
                                output.accept(KnightQuestItems.BOW_BOOTS.get());

                                output.accept(KnightQuestItems.HORN_HELMET.get());
                                output.accept(KnightQuestItems.HORN_CHESTPLATE.get());
                                output.accept(KnightQuestItems.HORN_LEGGINGS.get());
                                output.accept(KnightQuestItems.HORN_BOOTS.get());

                                output.accept(KnightQuestItems.CREEPER_HELMET.get());
                                output.accept(KnightQuestItems.CREEPER_CHESTPLATE.get());
                                output.accept(KnightQuestItems.CREEPER_LEGGINGS.get());
                                output.accept(KnightQuestItems.CREEPER_BOOTS.get());

                                output.accept(KnightQuestItems.DEEPSLATE_HELMET.get());
                                output.accept(KnightQuestItems.DEEPSLATE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.DEEPSLATE_LEGGINGS.get());
                                output.accept(KnightQuestItems.DEEPSLATE_BOOTS.get());

                                output.accept(KnightQuestItems.DRAGON_HELMET.get());
                                output.accept(KnightQuestItems.DRAGON_CHESTPLATE.get());
                                output.accept(KnightQuestItems.DRAGON_LEGGINGS.get());
                                output.accept(KnightQuestItems.DRAGON_BOOTS.get());

                                output.accept(KnightQuestItems.ENDERMAN_HELMET.get());
                                output.accept(KnightQuestItems.ENDERMAN_CHESTPLATE.get());
                                output.accept(KnightQuestItems.ENDERMAN_LEGGINGS.get());
                                output.accept(KnightQuestItems.ENDERMAN_BOOTS.get());

                                output.accept(KnightQuestItems.EVOKER_HELMET.get());
                                output.accept(KnightQuestItems.EVOKER_CHESTPLATE.get());
                                output.accept(KnightQuestItems.EVOKER_LEGGINGS.get());
                                output.accept(KnightQuestItems.EVOKER_BOOTS.get());

                                output.accept(KnightQuestItems.FORZE_HELMET.get());
                                output.accept(KnightQuestItems.FORZE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.FORZE_LEGGINGS.get());
                                output.accept(KnightQuestItems.FORZE_BOOTS.get());

                                output.accept(KnightQuestItems.HOLLOW_HELMET.get());
                                output.accept(KnightQuestItems.HOLLOW_CHESTPLATE.get());
                                output.accept(KnightQuestItems.HOLLOW_LEGGINGS.get());
                                output.accept(KnightQuestItems.HOLLOW_BOOTS.get());

                                output.accept(KnightQuestItems.NETHER_HELMET.get());
                                output.accept(KnightQuestItems.NETHER_CHESTPLATE.get());
                                output.accept(KnightQuestItems.NETHER_LEGGINGS.get());
                                output.accept(KnightQuestItems.NETHER_BOOTS.get());

                                output.accept(KnightQuestItems.VETERAN_HELMET.get());
                                output.accept(KnightQuestItems.VETERAN_CHESTPLATE.get());
                                output.accept(KnightQuestItems.VETERAN_LEGGINGS.get());
                                output.accept(KnightQuestItems.VETERAN_BOOTS.get());

                                output.accept(KnightQuestItems.PATH_HELMET.get());
                                output.accept(KnightQuestItems.PATH_CHESTPLATE.get());
                                output.accept(KnightQuestItems.PATH_LEGGINGS.get());
                                output.accept(KnightQuestItems.PATH_BOOTS.get());

                                output.accept(KnightQuestItems.PHANTOM_HELMET.get());
                                output.accept(KnightQuestItems.PHANTOM_CHESTPLATE.get());
                                output.accept(KnightQuestItems.PHANTOM_LEGGINGS.get());
                                output.accept(KnightQuestItems.PHANTOM_BOOTS.get());

                                output.accept(KnightQuestItems.SEA_HELMET.get());
                                output.accept(KnightQuestItems.SEA_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SEA_LEGGINGS.get());
                                output.accept(KnightQuestItems.SEA_BOOTS.get());

                                output.accept(KnightQuestItems.SHIELD_HELMET.get());
                                output.accept(KnightQuestItems.SHIELD_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SHIELD_LEGGINGS.get());
                                output.accept(KnightQuestItems.SHIELD_BOOTS.get());

                                output.accept(KnightQuestItems.SILVER_HELMET.get());
                                output.accept(KnightQuestItems.SILVER_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SILVER_LEGGINGS.get());
                                output.accept(KnightQuestItems.SILVER_BOOTS.get());

                                output.accept(KnightQuestItems.SILVERFISH_HELMET.get());
                                output.accept(KnightQuestItems.SILVERFISH_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SILVERFISH_LEGGINGS.get());
                                output.accept(KnightQuestItems.SILVERFISH_BOOTS.get());

                                output.accept(KnightQuestItems.SKELETON_HELMET.get());
                                output.accept(KnightQuestItems.SKELETON_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SKELETON_LEGGINGS.get());
                                output.accept(KnightQuestItems.SKELETON_BOOTS.get());

                                output.accept(KnightQuestItems.SPIDER_HELMET.get());
                                output.accept(KnightQuestItems.SPIDER_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SPIDER_LEGGINGS.get());
                                output.accept(KnightQuestItems.SPIDER_BOOTS.get());

                                output.accept(KnightQuestItems.WARLORD_HELMET.get());
                                output.accept(KnightQuestItems.WARLORD_CHESTPLATE.get());
                                output.accept(KnightQuestItems.WARLORD_LEGGINGS.get());
                                output.accept(KnightQuestItems.WARLORD_BOOTS.get());

                                output.accept(KnightQuestItems.STRAWHAT_HELMET.get());
                                output.accept(KnightQuestItems.STRAWHAT_CHESTPLATE.get());
                                output.accept(KnightQuestItems.STRAWHAT_LEGGINGS.get());
                                output.accept(KnightQuestItems.STRAWHAT_BOOTS.get());

                                output.accept(KnightQuestItems.PIRATE_HELMET.get());
                                output.accept(KnightQuestItems.PIRATE2_HELMET.get());
                                output.accept(KnightQuestItems.PIRATE3_HELMET.get());
                                output.accept(KnightQuestItems.PIRATE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.PIRATE_LEGGINGS.get());
                                output.accept(KnightQuestItems.PIRATE_BOOTS.get());

                                output.accept(KnightQuestItems.CONQUISTADOR_HELMET.get());
                                output.accept(KnightQuestItems.CONQUISTADOR2_HELMET.get());
                                output.accept(KnightQuestItems.CONQUISTADOR3_HELMET.get());
                                output.accept(KnightQuestItems.CONQUISTADOR_CHESTPLATE.get());
                                output.accept(KnightQuestItems.CONQUISTADOR_LEGGINGS.get());
                                output.accept(KnightQuestItems.CONQUISTADOR_BOOTS.get());

                                output.accept(KnightQuestItems.ZOMBIE_HELMET.get());
                                output.accept(KnightQuestItems.ZOMBIE_HELMET2.get());
                                output.accept(KnightQuestItems.ZOMBIE_CHESTPLATE.get());
                                output.accept(KnightQuestItems.ZOMBIE_LEGGINGS.get());
                                output.accept(KnightQuestItems.ZOMBIE_BOOTS.get());

                                output.accept(KnightQuestItems.HUSK_HELMET.get());
                                output.accept(KnightQuestItems.HUSK_HELMET2.get());
                                output.accept(KnightQuestItems.HUSK_HELMET3.get());
                                output.accept(KnightQuestItems.HUSK_CHESTPLATE.get());
                                output.accept(KnightQuestItems.HUSK_LEGGINGS.get());
                                output.accept(KnightQuestItems.HUSK_BOOTS.get());

                                output.accept(KnightQuestItems.WITHER_HELMET.get());
                                output.accept(KnightQuestItems.WITHER_CHESTPLATE.get());
                                output.accept(KnightQuestItems.WITHER_LEGGINGS.get());
                                output.accept(KnightQuestItems.WITHER_BOOTS.get());

                                output.accept(KnightQuestItems.CHAINMAIL_HELMET.get());
                                output.accept(KnightQuestItems.CHAINMAIL_HELMET2.get());
                                output.accept(KnightQuestItems.TUNIC_GREEN_LEGGINGS.get());
                                output.accept(KnightQuestItems.TUNIC_RED_LEGGINGS.get());
                                output.accept(KnightQuestItems.TUNIC_BLUE_LEGGINGS.get());
                                output.accept(KnightQuestItems.TUNIC_YELLOW_LEGGINGS.get());
                                output.accept(KnightQuestItems.TUNIC_SEA_LEGGINGS.get());

                                output.accept(KnightQuestItems.WITCH_HELMET.get());
                                output.accept(KnightQuestItems.WITCH_CHESTPLATE.get());
                                output.accept(KnightQuestItems.WITCH_LEGGINGS.get());
                                output.accept(KnightQuestItems.WITCH_BOOTS.get());

                                output.accept(KnightQuestItems.POLAR_HELMET.get());
                                output.accept(KnightQuestItems.POLAR_CHESTPLATE.get());
                                output.accept(KnightQuestItems.POLAR_LEGGINGS.get());
                                output.accept(KnightQuestItems.POLAR_BOOTS.get());

                                output.accept(KnightQuestItems.SHINOBI_HELMET.get());
                                output.accept(KnightQuestItems.SHINOBI_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SHINOBI_LEGGINGS.get());
                                output.accept(KnightQuestItems.SHINOBI_BOOTS.get());

                                output.accept(KnightQuestItems.SKULK_HELMET.get());
                                output.accept(KnightQuestItems.SKULK2_HELMET.get());
                                output.accept(KnightQuestItems.SKULK3_HELMET.get());
                                output.accept(KnightQuestItems.SKULK4_HELMET.get());
                                output.accept(KnightQuestItems.SKULK_CHESTPLATE.get());
                                output.accept(KnightQuestItems.SKULK_LEGGINGS.get());
                                output.accept(KnightQuestItems.SKULK_BOOTS.get());

                            })
                            .backgroundTexture(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/gui/container/creative_inventory/tab_knightquest.png"))
                            .build());

}
