package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class KnightQuestCreativeModeTabs {

    public static void init() { ;; }

    private static final List<Supplier<Item>> weaponItems = new ArrayList<>();

    public static void registerWeaponItem(Supplier<Item> itemSupplier) {
        weaponItems.add(itemSupplier);
    }

    public static final Supplier<CreativeModeTab> KNIGHTQUEST_TAB = KnightQuestCommon.COMMON_PLATFORM.registerCreativeModeTab("knightquest",
            () -> KnightQuestCommon.COMMON_PLATFORM.creativeTabBuilder()
                    .title(Component.translatable("itemgroup.knightquest"))
                    .icon(() -> new ItemStack(KnightQuestCommon.COMMON_PLATFORM.getPaladinSword().get()))
                    .displayItems((displayContext, entries) -> {

                        // Knight Lib
                        entries.accept(KnightQuestCommon.COMMON_PLATFORM.getGreatChalice().get());
                        entries.accept(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get());
                        entries.accept(KnightQuestCommon.COMMON_PLATFORM.getSmallEssence().get());

                        // Knight Quest
                        entries.accept(KnightQuestItems.RADIANT_ESSENCE.get());
                        entries.accept(KnightQuestItems.CHAOTIC_ESSENCE.get());
                        entries.accept(KnightQuestItems.EMPTY_GOBLET.get());
                        entries.accept(KnightQuestItems.FILLED_GOBLET.get());
                        entries.accept(KnightQuestItems.RATMAN_EYE.get());
                        entries.accept(KnightQuestItems.LIZZY_SCALE.get());

                        for (Supplier<Item> itemSupplier : weaponItems) {
                            Item item = itemSupplier.get();
                            if (item != null) {
                                entries.accept(item);
                            }
                        }

                        entries.accept(KnightQuestItems.STEEL_AXE.get());
                        entries.accept(KnightQuestItems.STEEL_SWORD.get());

                        entries.accept(KnightQuestItems.GREMLIN_EGG.get());
                        entries.accept(KnightQuestItems.ELD_KNIGHT_EGG.get());
                        entries.accept(KnightQuestItems.ELD_BOMB_EGG.get());
                        entries.accept(KnightQuestItems.SAMHAIN_EGG.get());
                        entries.accept(KnightQuestItems.SWAMPMAN_EGG.get());
                        entries.accept(KnightQuestItems.RATMAN_EGG.get());
                        entries.accept(KnightQuestItems.LIZZY_EGG.get());
                        entries.accept(KnightQuestItems.BADPATCH_EGG.get());
                        entries.accept(KnightQuestItems.GHOSTY_EGG.get());
                        entries.accept(KnightQuestItems.NETHERMAN_EGG.get());

                        entries.accept(KnightQuestItems.SQUIRE_HELMET.get());
                        entries.accept(KnightQuestItems.SQUIRE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SQUIRE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SQUIRE_BOOTS.get());

                        entries.accept(KnightQuestItems.APPLE_HELMET.get());
                        entries.accept(KnightQuestItems.APPLE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.APPLE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.APPLE_BOOTS.get());

                        entries.accept(KnightQuestItems.BAMBOO_BLUE_HELMET.get());
                        entries.accept(KnightQuestItems.BAMBOO_BLUE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.BAMBOO_BLUE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.BAMBOO_BLUE_BOOTS.get());

                        entries.accept(KnightQuestItems.BAMBOO_GREEN_HELMET.get());
                        entries.accept(KnightQuestItems.BAMBOO_GREEN_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.BAMBOO_GREEN_LEGGINGS.get());
                        entries.accept(KnightQuestItems.BAMBOO_GREEN_BOOTS.get());

                        entries.accept(KnightQuestItems.TENGU_HELMET.get());
                        entries.accept(KnightQuestItems.BAMBOO_HELMET.get());
                        entries.accept(KnightQuestItems.BAMBOO_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.BAMBOO_LEGGINGS.get());
                        entries.accept(KnightQuestItems.BAMBOO_BOOTS.get());

                        entries.accept(KnightQuestItems.BAT_HELMET.get());
                        entries.accept(KnightQuestItems.BAT_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.BAT_LEGGINGS.get());
                        entries.accept(KnightQuestItems.BAT_BOOTS.get());

                        entries.accept(KnightQuestItems.BLAZE_HELMET.get());
                        entries.accept(KnightQuestItems.BLAZE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.BLAZE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.BLAZE_BOOTS.get());

                        entries.accept(KnightQuestItems.BOW_HELMET.get());
                        entries.accept(KnightQuestItems.BOW_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.BOW_LEGGINGS.get());
                        entries.accept(KnightQuestItems.BOW_BOOTS.get());

                        entries.accept(KnightQuestItems.HORN_HELMET.get());
                        entries.accept(KnightQuestItems.HORN_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.HORN_LEGGINGS.get());
                        entries.accept(KnightQuestItems.HORN_BOOTS.get());

                        entries.accept(KnightQuestItems.CREEPER_HELMET.get());
                        entries.accept(KnightQuestItems.CREEPER_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.CREEPER_LEGGINGS.get());
                        entries.accept(KnightQuestItems.CREEPER_BOOTS.get());

                        entries.accept(KnightQuestItems.DEEPSLATE_HELMET.get());
                        entries.accept(KnightQuestItems.DEEPSLATE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.DEEPSLATE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.DEEPSLATE_BOOTS.get());

                        entries.accept(KnightQuestItems.DRAGON_HELMET.get());
                        entries.accept(KnightQuestItems.DRAGON_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.DRAGON_LEGGINGS.get());
                        entries.accept(KnightQuestItems.DRAGON_BOOTS.get());

                        entries.accept(KnightQuestItems.ENDERMAN_HELMET.get());
                        entries.accept(KnightQuestItems.ENDERMAN_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.ENDERMAN_LEGGINGS.get());
                        entries.accept(KnightQuestItems.ENDERMAN_BOOTS.get());

                        entries.accept(KnightQuestItems.EVOKER_HELMET.get());
                        entries.accept(KnightQuestItems.EVOKER_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.EVOKER_LEGGINGS.get());
                        entries.accept(KnightQuestItems.EVOKER_BOOTS.get());

                        entries.accept(KnightQuestItems.FORZE_HELMET.get());
                        entries.accept(KnightQuestItems.FORZE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.FORZE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.FORZE_BOOTS.get());

                        entries.accept(KnightQuestItems.HOLLOW_HELMET.get());
                        entries.accept(KnightQuestItems.HOLLOW_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.HOLLOW_LEGGINGS.get());
                        entries.accept(KnightQuestItems.HOLLOW_BOOTS.get());

                        entries.accept(KnightQuestItems.NETHER_HELMET.get());
                        entries.accept(KnightQuestItems.NETHER_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.NETHER_LEGGINGS.get());
                        entries.accept(KnightQuestItems.NETHER_BOOTS.get());

                        entries.accept(KnightQuestItems.VETERAN_HELMET.get());
                        entries.accept(KnightQuestItems.VETERAN_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.VETERAN_LEGGINGS.get());
                        entries.accept(KnightQuestItems.VETERAN_BOOTS.get());

                        entries.accept(KnightQuestItems.PATH_HELMET.get());
                        entries.accept(KnightQuestItems.PATH_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.PATH_LEGGINGS.get());
                        entries.accept(KnightQuestItems.PATH_BOOTS.get());

                        entries.accept(KnightQuestItems.PHANTOM_HELMET.get());
                        entries.accept(KnightQuestItems.PHANTOM_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.PHANTOM_LEGGINGS.get());
                        entries.accept(KnightQuestItems.PHANTOM_BOOTS.get());

                        entries.accept(KnightQuestItems.SEA_HELMET.get());
                        entries.accept(KnightQuestItems.SEA_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SEA_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SEA_BOOTS.get());

                        entries.accept(KnightQuestItems.SHIELD_HELMET.get());
                        entries.accept(KnightQuestItems.SHIELD_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SHIELD_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SHIELD_BOOTS.get());

                        entries.accept(KnightQuestItems.SILVER_HELMET.get());
                        entries.accept(KnightQuestItems.SILVER_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SILVER_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SILVER_BOOTS.get());

                        entries.accept(KnightQuestItems.SILVERFISH_HELMET.get());
                        entries.accept(KnightQuestItems.SILVERFISH_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SILVERFISH_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SILVERFISH_BOOTS.get());

                        entries.accept(KnightQuestItems.SKELETON_HELMET.get());
                        entries.accept(KnightQuestItems.SKELETON_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SKELETON_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SKELETON_BOOTS.get());

                        entries.accept(KnightQuestItems.SPIDER_HELMET.get());
                        entries.accept(KnightQuestItems.SPIDER_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SPIDER_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SPIDER_BOOTS.get());

                        entries.accept(KnightQuestItems.WARLORD_HELMET.get());
                        entries.accept(KnightQuestItems.WARLORD_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.WARLORD_LEGGINGS.get());
                        entries.accept(KnightQuestItems.WARLORD_BOOTS.get());

                        entries.accept(KnightQuestItems.STRAWHAT_HELMET.get());
                        entries.accept(KnightQuestItems.STRAWHAT_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.STRAWHAT_LEGGINGS.get());
                        entries.accept(KnightQuestItems.STRAWHAT_BOOTS.get());

                        entries.accept(KnightQuestItems.PIRATE_HELMET.get());
                        entries.accept(KnightQuestItems.PIRATE2_HELMET.get());
                        entries.accept(KnightQuestItems.PIRATE3_HELMET.get());
                        entries.accept(KnightQuestItems.PIRATE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.PIRATE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.PIRATE_BOOTS.get());

                        entries.accept(KnightQuestItems.CONQUISTADOR_HELMET.get());
                        entries.accept(KnightQuestItems.CONQUISTADOR2_HELMET.get());
                        entries.accept(KnightQuestItems.CONQUISTADOR3_HELMET.get());
                        entries.accept(KnightQuestItems.CONQUISTADOR_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.CONQUISTADOR_LEGGINGS.get());
                        entries.accept(KnightQuestItems.CONQUISTADOR_BOOTS.get());

                        entries.accept(KnightQuestItems.ZOMBIE_HELMET.get());
                        entries.accept(KnightQuestItems.ZOMBIE_HELMET2.get());
                        entries.accept(KnightQuestItems.ZOMBIE_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.ZOMBIE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.ZOMBIE_BOOTS.get());

                        entries.accept(KnightQuestItems.HUSK_HELMET.get());
                        entries.accept(KnightQuestItems.HUSK_HELMET2.get());
                        entries.accept(KnightQuestItems.HUSK_HELMET3.get());
                        entries.accept(KnightQuestItems.HUSK_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.HUSK_LEGGINGS.get());
                        entries.accept(KnightQuestItems.HUSK_BOOTS.get());

                        entries.accept(KnightQuestItems.WITHER_HELMET.get());
                        entries.accept(KnightQuestItems.WITHER_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.WITHER_LEGGINGS.get());
                        entries.accept(KnightQuestItems.WITHER_BOOTS.get());

                        entries.accept(KnightQuestItems.CHAINMAIL_HELMET.get());
                        entries.accept(KnightQuestItems.CHAINMAIL_HELMET2.get());

                        entries.accept(KnightQuestItems.TUNIC_BLUE_LEGGINGS.get());
                        entries.accept(KnightQuestItems.TUNIC_YELLOW_LEGGINGS.get());
                        entries.accept(KnightQuestItems.TUNIC_RED_LEGGINGS.get());
                        entries.accept(KnightQuestItems.TUNIC_GREEN_LEGGINGS.get());
                        entries.accept(KnightQuestItems.TUNIC_SEA_LEGGINGS.get());

                        entries.accept(KnightQuestItems.WITCH_HELMET.get());
                        entries.accept(KnightQuestItems.WITCH_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.WITCH_LEGGINGS.get());
                        entries.accept(KnightQuestItems.WITCH_BOOTS.get());

                        entries.accept(KnightQuestItems.POLAR_HELMET.get());
                        entries.accept(KnightQuestItems.POLAR_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.POLAR_LEGGINGS.get());
                        entries.accept(KnightQuestItems.POLAR_BOOTS.get());

                        entries.accept(KnightQuestItems.SHINOBI_HELMET.get());
                        entries.accept(KnightQuestItems.SHINOBI_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SHINOBI_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SHINOBI_BOOTS.get());

                        entries.accept(KnightQuestItems.SKULK_HELMET.get());
                        entries.accept(KnightQuestItems.SKULK2_HELMET.get());
                        entries.accept(KnightQuestItems.SKULK3_HELMET.get());
                        entries.accept(KnightQuestItems.SKULK4_HELMET.get());
                        entries.accept(KnightQuestItems.SKULK_CHESTPLATE.get());
                        entries.accept(KnightQuestItems.SKULK_LEGGINGS.get());
                        entries.accept(KnightQuestItems.SKULK_BOOTS.get());

                    }).build());

    }