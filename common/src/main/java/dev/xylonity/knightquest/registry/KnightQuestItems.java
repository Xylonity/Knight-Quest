package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class KnightQuestItems {

    public static void init() { ;; }

    public static final Supplier<Item> RADIANT_ESSENCE = registerItem("radiant_essence", () -> new KnightQuestItem(new Item.Properties(), "radiant_essence"));
    public static final Supplier<Item> EMPTY_GOBLET = registerItem("empty_goblet", () -> new KnightQuestItem(new Item.Properties(), "empty_goblet"));
    public static final Supplier<Item> FILLED_GOBLET = registerItem("filled_goblet", () -> new KnightQuestItem(new Item.Properties(), "filled_goblet"));
    public static final Supplier<Item> RATMAN_EYE = registerItem("ratman_eye", () -> new KnightQuestItem(new Item.Properties(), "ratman_eye"));
    public static final Supplier<Item> LIZZY_SCALE = registerItem("lizzy_scale", () -> new KnightQuestItem(new Item.Properties(), "lizzy_scale"));

    public static final Supplier<Item> PALADIN_SWORD = registerSwordItem("paladin_sword", KQItemMaterials.PALADIN, -2.8f, true);
    public static final Supplier<Item> NAIL_SWORD = registerSwordItem("nail_glaive", KQItemMaterials.NAIL, -2.6f, false);
    public static final Supplier<Item> UCHIGATANA = registerSwordItem("uchigatana_katana", KQItemMaterials.UCHIGATANA, -2.2f, false);
    public static final Supplier<Item> KUKRI = registerSwordItem("kukri_dagger", KQItemMaterials.KUKRI, -1f, false);
    public static final Supplier<Item> KHOPESH = registerSwordItem("khopesh_claymore", KQItemMaterials.KHOPESH, -2.2f, false);
    public static final Supplier<Item> CLEAVER = registerSwordItem("cleaver_heavy_axe", KQItemMaterials.CLEAVER, -3f, false);
    public static final Supplier<Item> CRIMSON_SWORD = registerSwordItem("crimson_sword", KQItemMaterials.CRIMSON_SWORD, -2f, false);
    public static final Supplier<Item> WATER_SWORD = registerSwordItem("water_sword", KQItemMaterials.WATER_SWORD, -2f, false);
    public static final Supplier<Item> STEEL_SWORD = registerSwordItem("steel_sword", KQItemMaterials.STEEL_SWORD, -2f, false);

    public static final Supplier<Item> APPLE_HELMET = registerGeoArmorItem("apple_helmet", KQArmorMaterials.APPLE_SET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> APPLE_CHESTPLATE = registerArmorItem("apple_chestplate", KQArmorMaterials.APPLE_SET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> APPLE_LEGGINGS = registerArmorItem("apple_leggings", KQArmorMaterials.APPLE_SET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> APPLE_BOOTS = registerArmorItem("apple_boots", KQArmorMaterials.APPLE_SET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> BAMBOO_BLUE_HELMET = registerGeoArmorItem("bamboo_blue_helmet", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BAMBOO_BLUE_CHESTPLATE = registerArmorItem("bamboo_blue_chestplate", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> BAMBOO_BLUE_LEGGINGS = registerArmorItem("bamboo_blue_leggings", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> BAMBOO_BLUE_BOOTS = registerArmorItem("bamboo_blue_boots", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> BAMBOO_GREEN_HELMET = registerGeoArmorItem("bamboo_green_helmet", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BAMBOO_GREEN_CHESTPLATE = registerArmorItem("bamboo_green_chestplate", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> BAMBOO_GREEN_LEGGINGS = registerArmorItem("bamboo_green_leggings", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> BAMBOO_GREEN_BOOTS = registerArmorItem("bamboo_green_boots", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> TENGU_HELMET = registerGeoArmorItem("tengu_helmet", KQArmorMaterials.TENGU, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BAMBOO_HELMET = registerGeoArmorItem("bamboo_helmet", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BAMBOO_CHESTPLATE = registerArmorItem("bamboo_chestplate", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> BAMBOO_LEGGINGS = registerArmorItem("bamboo_leggings", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> BAMBOO_BOOTS = registerArmorItem("bamboo_boots", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> BAT_HELMET = registerGeoArmorItem("bat_helmet", KQArmorMaterials.BATSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BAT_CHESTPLATE = registerArmorItem("bat_chestplate", KQArmorMaterials.BATSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> BAT_LEGGINGS = registerArmorItem("bat_leggings", KQArmorMaterials.BATSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> BAT_BOOTS = registerArmorItem("bat_boots", KQArmorMaterials.BATSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> BLAZE_HELMET = registerGeoArmorItem("blaze_helmet", KQArmorMaterials.BLAZESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BLAZE_CHESTPLATE = registerArmorItem("blaze_chestplate", KQArmorMaterials.BLAZESET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> BLAZE_LEGGINGS = registerArmorItem("blaze_leggings", KQArmorMaterials.BLAZESET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> BLAZE_BOOTS = registerArmorItem("blaze_boots", KQArmorMaterials.BLAZESET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> BOW_HELMET = registerGeoArmorItem("bow_helmet", KQArmorMaterials.BOWSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> BOW_CHESTPLATE = registerArmorItem("bow_chestplate", KQArmorMaterials.BOWSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> BOW_LEGGINGS = registerArmorItem("bow_leggings", KQArmorMaterials.BOWSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> BOW_BOOTS = registerArmorItem("bow_boots", KQArmorMaterials.BOWSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> HORN_HELMET = registerGeoArmorItem("horn_helmet", KQArmorMaterials.HORNSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> HORN_CHESTPLATE = registerArmorItem("horn_chestplate", KQArmorMaterials.HORNSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> HORN_LEGGINGS = registerArmorItem("horn_leggings", KQArmorMaterials.HORNSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> HORN_BOOTS = registerArmorItem("horn_boots", KQArmorMaterials.HORNSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> CREEPER_HELMET = registerArmorItem("creeper_helmet", KQArmorMaterials.CREEPERSET, ArmorItem.Type.HELMET, true);
    public static final Supplier<Item> CREEPER_CHESTPLATE = registerArmorItem("creeper_chestplate", KQArmorMaterials.CREEPERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> CREEPER_LEGGINGS = registerArmorItem("creeper_leggings", KQArmorMaterials.CREEPERSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> CREEPER_BOOTS = registerArmorItem("creeper_boots", KQArmorMaterials.CREEPERSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> DEEPSLATE_HELMET = registerGeoArmorItem("deepslate_helmet", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> DEEPSLATE_CHESTPLATE = registerArmorItem("deepslate_chestplate", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> DEEPSLATE_LEGGINGS = registerArmorItem("deepslate_leggings", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> DEEPSLATE_BOOTS = registerArmorItem("deepslate_boots", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> DRAGON_HELMET = registerGeoArmorItem("dragon_helmet", KQArmorMaterials.DRAGONSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> DRAGON_CHESTPLATE = registerArmorItem("dragon_chestplate", KQArmorMaterials.DRAGONSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> DRAGON_LEGGINGS = registerArmorItem("dragon_leggings", KQArmorMaterials.DRAGONSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> DRAGON_BOOTS = registerArmorItem("dragon_boots", KQArmorMaterials.DRAGONSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> ENDERMAN_HELMET = registerGeoArmorItem("enderman_helmet", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> ENDERMAN_CHESTPLATE = registerArmorItem("enderman_chestplate", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> ENDERMAN_LEGGINGS = registerArmorItem("enderman_leggings", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> ENDERMAN_BOOTS = registerArmorItem("enderman_boots", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> EVOKER_HELMET = registerGeoArmorItem("evoker_helmet", KQArmorMaterials.EVOKERSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> EVOKER_CHESTPLATE = registerArmorItem("evoker_chestplate", KQArmorMaterials.EVOKERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> EVOKER_LEGGINGS = registerArmorItem("evoker_leggings", KQArmorMaterials.EVOKERSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> EVOKER_BOOTS = registerArmorItem("evoker_boots", KQArmorMaterials.EVOKERSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> FORZE_HELMET = registerGeoArmorItem("forze_helmet", KQArmorMaterials.FORZESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> FORZE_CHESTPLATE = registerGeoArmorItem("forze_chestplate", KQArmorMaterials.FORZESET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final Supplier<Item> FORZE_LEGGINGS = registerArmorItem("forze_leggings", KQArmorMaterials.FORZESET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> FORZE_BOOTS = registerArmorItem("forze_boots", KQArmorMaterials.FORZESET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> HOLLOW_HELMET = registerGeoArmorItem("hollow_helmet", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> HOLLOW_CHESTPLATE = registerArmorItem("hollow_chestplate", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> HOLLOW_LEGGINGS = registerArmorItem("hollow_leggings", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> HOLLOW_BOOTS = registerArmorItem("hollow_boots", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> NETHER_HELMET = registerGeoArmorItem("nether_helmet", KQArmorMaterials.NETHERSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> NETHER_CHESTPLATE = registerArmorItem("nether_chestplate", KQArmorMaterials.NETHERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> NETHER_LEGGINGS = registerArmorItem("nether_leggings", KQArmorMaterials.NETHERSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> NETHER_BOOTS = registerArmorItem("nether_boots", KQArmorMaterials.NETHERSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> VETERAN_HELMET = registerGeoArmorItem("veteran_helmet", KQArmorMaterials.VETERANSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> VETERAN_CHESTPLATE = registerGeoArmorItem("veteran_chestplate", KQArmorMaterials.VETERANSET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final Supplier<Item> VETERAN_LEGGINGS = registerGeoArmorItem("veteran_leggings", KQArmorMaterials.VETERANSET, ArmorItem.Type.LEGGINGS, true, false);
    public static final Supplier<Item> VETERAN_BOOTS = registerArmorItem("veteran_boots", KQArmorMaterials.VETERANSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> PATH_HELMET = registerGeoArmorItem("path_helmet", KQArmorMaterials.PATHSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> PATH_CHESTPLATE = registerArmorItem("path_chestplate", KQArmorMaterials.PATHSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> PATH_LEGGINGS = registerArmorItem("path_leggings", KQArmorMaterials.PATHSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> PATH_BOOTS = registerArmorItem("path_boots", KQArmorMaterials.PATHSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> PHANTOM_HELMET = registerGeoArmorItem("phantom_helmet", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> PHANTOM_CHESTPLATE = registerArmorItem("phantom_chestplate", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> PHANTOM_LEGGINGS = registerArmorItem("phantom_leggings", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> PHANTOM_BOOTS = registerArmorItem("phantom_boots", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SEA_HELMET = registerGeoArmorItem("sea_helmet", KQArmorMaterials.SEASET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SEA_CHESTPLATE = registerArmorItem("sea_chestplate", KQArmorMaterials.SEASET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SEA_LEGGINGS = registerArmorItem("sea_leggings", KQArmorMaterials.SEASET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SEA_BOOTS = registerArmorItem("sea_boots", KQArmorMaterials.SEASET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SHIELD_HELMET = registerGeoArmorItem("shield_helmet", KQArmorMaterials.SHIELDSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SHIELD_CHESTPLATE = registerArmorItem("shield_chestplate", KQArmorMaterials.SHIELDSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SHIELD_LEGGINGS = registerArmorItem("shield_leggings", KQArmorMaterials.SHIELDSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SHIELD_BOOTS = registerArmorItem("shield_boots", KQArmorMaterials.SHIELDSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SILVER_HELMET = registerGeoArmorItem("silver_helmet", KQArmorMaterials.SILVERSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SILVER_CHESTPLATE = registerGeoArmorItem("silver_chestplate", KQArmorMaterials.SILVERSET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final Supplier<Item> SILVER_LEGGINGS = registerArmorItem("silver_leggings", KQArmorMaterials.SILVERSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SILVER_BOOTS = registerArmorItem("silver_boots", KQArmorMaterials.SILVERSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SILVERFISH_HELMET = registerGeoArmorItem("silverfish_helmet", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SILVERFISH_CHESTPLATE = registerArmorItem("silverfish_chestplate", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SILVERFISH_LEGGINGS = registerArmorItem("silverfish_leggings", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SILVERFISH_BOOTS = registerArmorItem("silverfish_boots", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SKELETON_HELMET = registerGeoArmorItem("skeleton_helmet", KQArmorMaterials.SKELETONSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SKELETON_CHESTPLATE = registerArmorItem("skeleton_chestplate", KQArmorMaterials.SKELETONSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SKELETON_LEGGINGS = registerArmorItem("skeleton_leggings", KQArmorMaterials.SKELETONSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SKELETON_BOOTS = registerArmorItem("skeleton_boots", KQArmorMaterials.SKELETONSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SPIDER_HELMET = registerGeoArmorItem("spider_helmet", KQArmorMaterials.SPIDERSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SPIDER_CHESTPLATE = registerGeoArmorItem("spider_chestplate", KQArmorMaterials.SPIDERSET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final Supplier<Item> SPIDER_LEGGINGS = registerArmorItem("spider_leggings", KQArmorMaterials.SPIDERSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SPIDER_BOOTS = registerArmorItem("spider_boots", KQArmorMaterials.SPIDERSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> WARLORD_HELMET = registerGeoArmorItem("warlord_helmet", KQArmorMaterials.WARLORDSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> WARLORD_CHESTPLATE = registerArmorItem("warlord_chestplate", KQArmorMaterials.WARLORDSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> WARLORD_LEGGINGS = registerArmorItem("warlord_leggings", KQArmorMaterials.WARLORDSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> WARLORD_BOOTS = registerArmorItem("warlord_boots", KQArmorMaterials.WARLORDSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> STRAWHAT_HELMET = registerGeoArmorItem("strawhat_helmet", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> STRAWHAT_CHESTPLATE = registerArmorItem("strawhat_chestplate", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> STRAWHAT_LEGGINGS = registerArmorItem("strawhat_leggings", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> STRAWHAT_BOOTS = registerArmorItem("strawhat_boots", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> PIRATE_HELMET = registerGeoArmorItem("pirate_helmet", KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> PIRATE2_HELMET = registerGeoArmorItem("pirate2_helmet", KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> PIRATE3_HELMET = registerGeoArmorItem("pirate3_helmet", KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> PIRATE_CHESTPLATE = registerArmorItem("pirate_chestplate", KQArmorMaterials.PIRATESET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> PIRATE_LEGGINGS = registerArmorItem("pirate_leggings", KQArmorMaterials.PIRATESET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> PIRATE_BOOTS = registerArmorItem("pirate_boots", KQArmorMaterials.PIRATESET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> CONQUISTADOR_HELMET = registerGeoArmorItem("conquistador_helmet", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> CONQUISTADOR2_HELMET = registerGeoArmorItem("conquistador2_helmet", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> CONQUISTADOR3_HELMET = registerGeoArmorItem("conquistador3_helmet", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> CONQUISTADOR_CHESTPLATE = registerArmorItem("conquistador_chestplate", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> CONQUISTADOR_LEGGINGS = registerArmorItem("conquistador_leggings", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> CONQUISTADOR_BOOTS = registerArmorItem("conquistador_boots", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> ZOMBIE_HELMET = registerGeoArmorItem("zombie_helmet", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> ZOMBIE_HELMET2 = registerGeoArmorItem("zombie_helmet2", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> ZOMBIE_CHESTPLATE = registerArmorItem("zombie_chestplate", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> ZOMBIE_LEGGINGS = registerArmorItem("zombie_leggings", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> ZOMBIE_BOOTS = registerArmorItem("zombie_boots", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> HUSK_HELMET = registerGeoArmorItem("husk_helmet", KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> HUSK_HELMET2 = registerGeoArmorItem("husk_helmet2", KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> HUSK_HELMET3 = registerGeoArmorItem("husk_helmet3", KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> HUSK_CHESTPLATE = registerArmorItem("husk_chestplate", KQArmorMaterials.HUSKSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> HUSK_LEGGINGS = registerArmorItem("husk_leggings", KQArmorMaterials.HUSKSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> HUSK_BOOTS = registerArmorItem("husk_boots", KQArmorMaterials.HUSKSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> WITHER_HELMET = registerArmorItem("wither_helmet", KQArmorMaterials.WITHERSET, ArmorItem.Type.HELMET, true);
    public static final Supplier<Item> WITHER_CHESTPLATE = registerArmorItem("wither_chestplate", KQArmorMaterials.WITHERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> WITHER_LEGGINGS = registerArmorItem("wither_leggings", KQArmorMaterials.WITHERSET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> WITHER_BOOTS = registerArmorItem("wither_boots", KQArmorMaterials.WITHERSET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SQUIRE_HELMET = registerGeoArmorItem("squire_helmet", KQArmorMaterials.SQUIRESET, ArmorItem.Type.HELMET, true, true);
    public static final Supplier<Item> SQUIRE_CHESTPLATE = registerArmorItem("squire_chestplate", KQArmorMaterials.SQUIRESET, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SQUIRE_LEGGINGS = registerArmorItem("squire_leggings", KQArmorMaterials.SQUIRESET, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SQUIRE_BOOTS = registerArmorItem("squire_boots", KQArmorMaterials.SQUIRESET, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> CHAINMAIL_HELMET = registerArmorItem("chainmail_helmet", KQArmorMaterials.CHAINMAIL, ArmorItem.Type.HELMET, true);
    public static final Supplier<Item> CHAINMAIL_HELMET2 = registerGeoArmorItem("chainmail_helmet2", KQArmorMaterials.CHAINMAIL, ArmorItem.Type.HELMET, false, false);
    public static final Supplier<Item> TUNIC_BLUE_LEGGINGS = registerArmorItem("tunic_blue_leggings", KQArmorMaterials.TUNIC_BLUE, ArmorItem.Type.LEGGINGS, false);
    public static final Supplier<Item> TUNIC_GREEN_LEGGINGS = registerArmorItem("tunic_green_leggings", KQArmorMaterials.TUNIC_GREEN, ArmorItem.Type.LEGGINGS, false);
    public static final Supplier<Item> TUNIC_YELLOW_LEGGINGS = registerArmorItem("tunic_yellow_leggings", KQArmorMaterials.TUNIC_YELLOW, ArmorItem.Type.LEGGINGS, false);
    public static final Supplier<Item> TUNIC_RED_LEGGINGS = registerArmorItem("tunic_red_leggings", KQArmorMaterials.TUNIC_RED, ArmorItem.Type.LEGGINGS, false);
    public static final Supplier<Item> TUNIC_SEA_LEGGINGS = registerArmorItem("tunic_sea_leggings", KQArmorMaterials.TUNIC_SEA, ArmorItem.Type.LEGGINGS, false);

    public static final Supplier<Item> WITCH_HELMET = registerGeoArmorItem("witch_helmet", KQArmorMaterials.WITCH, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> WITCH_CHESTPLATE = registerArmorItem("witch_chestplate", KQArmorMaterials.WITCH, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> WITCH_LEGGINGS = registerArmorItem("witch_leggings", KQArmorMaterials.WITCH, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> WITCH_BOOTS = registerArmorItem("witch_boots", KQArmorMaterials.WITCH, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> POLAR_HELMET = registerGeoArmorItem("polar_helmet", KQArmorMaterials.POLAR, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> POLAR_CHESTPLATE = registerArmorItem("polar_chestplate", KQArmorMaterials.POLAR, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> POLAR_LEGGINGS = registerArmorItem("polar_leggings", KQArmorMaterials.POLAR, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> POLAR_BOOTS = registerArmorItem("polar_boots", KQArmorMaterials.POLAR, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SHINOBI_HELMET = registerGeoArmorItem("shinobi_helmet", KQArmorMaterials.SHINOBI, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SHINOBI_CHESTPLATE = registerArmorItem("shinobi_chestplate", KQArmorMaterials.SHINOBI, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SHINOBI_LEGGINGS = registerArmorItem("shinobi_leggings", KQArmorMaterials.SHINOBI, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SHINOBI_BOOTS = registerArmorItem("shinobi_boots", KQArmorMaterials.SHINOBI, ArmorItem.Type.BOOTS, true);

    public static final Supplier<Item> SKULK_HELMET = registerGeoArmorItem("skulk_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SKULK2_HELMET = registerGeoArmorItem("skulk2_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SKULK3_HELMET = registerGeoArmorItem("skulk3_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SKULK4_HELMET = registerGeoArmorItem("skulk4_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final Supplier<Item> SKULK_CHESTPLATE = registerArmorItem("skulk_chestplate", KQArmorMaterials.SKULK, ArmorItem.Type.CHESTPLATE, true);
    public static final Supplier<Item> SKULK_LEGGINGS = registerArmorItem("skulk_leggings", KQArmorMaterials.SKULK, ArmorItem.Type.LEGGINGS, true);
    public static final Supplier<Item> SKULK_BOOTS = registerArmorItem("skulk_boots", KQArmorMaterials.SKULK, ArmorItem.Type.BOOTS, true);
    
    private static <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return KnightQuestCommon.COMMON_PLATFORM.registerItem(id, item);
    }

    private static <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, float speedMalus, boolean containsTooltip) {
        return KnightQuestCommon.COMMON_PLATFORM.registerSwordItem(id, itemMaterial, speedMalus, containsTooltip);
    }

    private static <T extends Item> Supplier<T> registerArmorItem(String id, KQArmorMaterials armorMaterial, ArmorItem.Type armorType, boolean containsTooltip) {
        return KnightQuestCommon.COMMON_PLATFORM.registerArmorItem(id, armorMaterial, armorType, containsTooltip);
    }

    private static <T extends Item> Supplier<T> registerGeoArmorItem(String id, KQArmorMaterials armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip) {
        return KnightQuestCommon.COMMON_PLATFORM.registerGeoArmorItem(id, armorMaterial, armorType, containsTooltip, containsExtraTooltip);
    }

    private static class KnightQuestItem extends Item {

        private final String tooltipInfoName;

        public KnightQuestItem(Properties properties, String tooltipInfoName) {
            super(properties);
            this.tooltipInfoName = tooltipInfoName;
        }

        @Override
        public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {

            list.add(Component.translatable("tooltip.item.knightquest." + tooltipInfoName));

            super.appendHoverText(itemStack, level, list, tooltipFlag);
        }
    }

}
