package net.xylonity.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.xylonity.KnightQuest;
import net.xylonity.common.client.GeoItemArmor;
import net.xylonity.common.item.KQArmorItem;
import net.xylonity.common.item.KQItem;
import net.xylonity.common.material.KQArmorMaterials;
import net.xylonity.common.material.KQItemMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightQuestItems {

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(GREAT_ESSENCE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(KnightQuest.MOD_ID, name), item);
    }

    public static void register() {

        KnightQuest.LOGGER.debug("Registering items for " + KnightQuest.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(KnightQuestItems::addItemsToIngredientTabItemGroup);
    }

    public static final Item GREAT_ESSENCE = registerItem("great_essence", new KQItem(new Item.Settings(), "great_essence"));
    public static final Item SMALL_ESSENCE = registerItem("small_essence", new KQItem(new Item.Settings(), "small_essence"));
    public static final Item EMPTY_GOBLET = registerItem("empty_goblet", new KQItem(new Item.Settings(), "empty_goblet"));
    public static final Item FILLED_GOBLET = registerItem("filled_goblet", new KQItem(new Item.Settings(), "filled_goblet"));
    public static final Item RATMAN_EYE = registerItem("ratman_eye", new KQItem(new Item.Settings(), "ratman_eye"));
    public static final Item LIZZY_SCALE = registerItem("lizzy_scale", new KQItem(new Item.Settings(), "lizzy_scale"));

    public static final Item PALADIN_SWORD = registerItem("paladin_sword",
            new SwordItem(KQItemMaterials.PALADIN, (new Item.Settings()).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.PALADIN, 4, -2.8F)))
            {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.item.knightquest.paladin_sword"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });

    public static final Item NAIL_SWORD = registerItem("nail_glaive",
            new SwordItem(KQItemMaterials.NAIL, (new Item.Settings()).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.NAIL, 4, -2.6F))));
    public static final Item UCHIGATANA = registerItem("uchigatana_katana",
            new SwordItem(KQItemMaterials.UCHIGATANA,(new Item.Settings()).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.UCHIGATANA, 4, -2.2F))));
    public static final Item KUKRI = registerItem("kukri_dagger",
            new SwordItem(KQItemMaterials.KUKRI,(new Item.Settings()).attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.KUKRI, 4, -1F))));
    public static final Item KHOPESH = registerItem("khopesh_claymore",
            new SwordItem(KQItemMaterials.KHOPESH,(new Item.Settings()).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.KHOPESH, 4, -2.8F))));
    public static final Item CLEAVER = registerItem("cleaver_heavy_axe",
            new SwordItem(KQItemMaterials.CLEAVER, (new Item.Settings()).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.CLEAVER, 4, -3F))));
    public static final Item CRIMSON_SWORD = registerItem("crimson_sword",
            new SwordItem(KQItemMaterials.CRIMSON_SWORD, (new Item.Settings()).fireproof().attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.CRIMSON_SWORD, 4, -2F))));
    public static final Item WATER_SWORD = registerItem("water_sword",
            new SwordItem(KQItemMaterials.WATER_SWORD,(new Item.Settings()).attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.WATER_SWORD, 4, -2F))));
    public static final Item STEEL_SWORD = registerItem("steel_sword",
            new SwordItem(KQItemMaterials.STEEL_SWORD, (new Item.Settings()).attributeModifiers(SwordItem.createAttributeModifiers(KQItemMaterials.STEEL_SWORD, 4, -2F))));

    public static final Item WATER_AXE = registerItem("water_axe",
            new AxeItem(KQItemMaterials.WATER_AXE, (new Item.Settings()).attributeModifiers(AxeItem.createAttributeModifiers(KQItemMaterials.WATER_AXE, 4, -2.8F))));
    public static final Item STEEL_AXE = registerItem("steel_axe",
            new AxeItem(KQItemMaterials.STEEL_AXE, (new Item.Settings()).attributeModifiers(AxeItem.createAttributeModifiers(KQItemMaterials.STEEL_AXE, 4, -2.8F))));

    public static final Item APPLE_HELMET = registerItem("apple_helmet",
            new GeoItemArmor(KQArmorMaterials.APPLE_SET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/apple_helmet.png", "geo/apple_helmet.geo.json"));
    public static final Item APPLE_CHESTPLATE = registerItem("apple_chestplate",
            new KQArmorItem(KQArmorMaterials.APPLE_SET, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item APPLE_LEGGINGS = registerItem("apple_leggings",
            new KQArmorItem(KQArmorMaterials.APPLE_SET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item APPLE_BOOTS = registerItem("apple_boots",
            new KQArmorItem(KQArmorMaterials.APPLE_SET, ArmorItem.Type.BOOTS, new Item.Settings()));

    public static final Item BAMBOO_BLUE_HELMET = registerItem("bamboo_blue_helmet",
            new GeoItemArmor(KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.HELMET, new Item.Settings()
                    , "textures/armor/bamboo_blue_helmet.png", "geo/bambooblue_helmet.geo.json"));
    public static final Item BAMBOO_BLUE_CHESTPLATE = registerItem("bamboo_blue_chestplate",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item BAMBOO_BLUE_LEGGINGS = registerItem("bamboo_blue_leggings",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item BAMBOO_BLUE_BOOTS = registerItem("bamboo_blue_boots",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item BAMBOO_GREEN_HELMET = registerItem("bamboo_green_helmet",
            new GeoItemArmor(KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/bamboo_green_helmet.png", "geo/bamboogreen_helmet.geo.json"));
    public static final Item BAMBOO_GREEN_CHESTPLATE = registerItem("bamboo_green_chestplate",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item BAMBOO_GREEN_LEGGINGS = registerItem("bamboo_green_leggings",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item BAMBOO_GREEN_BOOTS = registerItem("bamboo_green_boots",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item TENGU_HELMET = registerItem("tengu_helmet",
            new GeoItemArmor(KQArmorMaterials.TENGU, ArmorItem.Type.HELMET, new Item.Settings()
                    , "textures/armor/tengu_helmet.png", "geo/tengu_helmet.geo.json"));
    public static final Item BAMBOO_HELMET = registerItem("bamboo_helmet",
            new GeoItemArmor(KQArmorMaterials.BAMBOOSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/bamboo_helmet.png", "geo/bamboo_helmet.geo.json"));
    public static final Item BAMBOO_CHESTPLATE = registerItem("bamboo_chestplate",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item BAMBOO_LEGGINGS = registerItem("bamboo_leggings",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item BAMBOO_BOOTS = registerItem("bamboo_boots",
            new KQArmorItem(KQArmorMaterials.BAMBOOSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item BAT_HELMET = registerItem("bat_helmet",
            new GeoItemArmor(KQArmorMaterials.BATSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/bat_helmet.png", "geo/bat_helmet.geo.json"));
    public static final Item BAT_CHESTPLATE = registerItem("bat_chestplate",
            new KQArmorItem(KQArmorMaterials.BATSET, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item BAT_LEGGINGS = registerItem("bat_leggings",
            new KQArmorItem(KQArmorMaterials.BATSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item BAT_BOOTS = registerItem("bat_boots",
            new KQArmorItem(KQArmorMaterials.BATSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item BLAZE_HELMET = registerItem("blaze_helmet",
            new GeoItemArmor(KQArmorMaterials.BLAZESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/blaze_helmet.png", "geo/blaze_helmet.geo.json"));
    public static final Item BLAZE_CHESTPLATE = registerItem("blaze_chestplate",
            new KQArmorItem(KQArmorMaterials.BLAZESET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item BLAZE_LEGGINGS = registerItem("blaze_leggings",
            new KQArmorItem(KQArmorMaterials.BLAZESET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item BLAZE_BOOTS = registerItem("blaze_boots",
            new KQArmorItem(KQArmorMaterials.BLAZESET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item BOW_HELMET = registerItem("bow_helmet",
            new GeoItemArmor(KQArmorMaterials.BOWSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/bow_helmet.png", "geo/bow_helmet.geo.json"));
    public static final Item BOW_CHESTPLATE = registerItem("bow_chestplate",
            new KQArmorItem(KQArmorMaterials.BOWSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item BOW_LEGGINGS = registerItem("bow_leggings",
            new KQArmorItem(KQArmorMaterials.BOWSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item BOW_BOOTS = registerItem("bow_boots",
            new KQArmorItem(KQArmorMaterials.BOWSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item HORN_HELMET = registerItem("horn_helmet",
            new GeoItemArmor(KQArmorMaterials.HORNSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/horn_helmet.png", "geo/horn_helmet.geo.json"));
    public static final Item HORN_CHESTPLATE = registerItem("horn_chestplate",
            new KQArmorItem(KQArmorMaterials.HORNSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item HORN_LEGGINGS = registerItem("horn_leggings",
            new KQArmorItem(KQArmorMaterials.HORNSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item HORN_BOOTS = registerItem("horn_boots",
            new KQArmorItem(KQArmorMaterials.HORNSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item CREEPER_HELMET = registerItem("creeper_helmet",
            new KQArmorItem(KQArmorMaterials.CREEPERSET, ArmorItem.Type.HELMET,new Item.Settings()));
    public static final Item CREEPER_CHESTPLATE = registerItem("creeper_chestplate",
            new KQArmorItem(KQArmorMaterials.CREEPERSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item CREEPER_LEGGINGS = registerItem("creeper_leggings",
            new KQArmorItem(KQArmorMaterials.CREEPERSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item CREEPER_BOOTS = registerItem("creeper_boots",
            new KQArmorItem(KQArmorMaterials.CREEPERSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item DEEPSLATE_HELMET = registerItem("deepslate_helmet",
            new GeoItemArmor(KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/deepslate_helmet.png", "geo/deepslate_helmet.geo.json"));
    public static final Item DEEPSLATE_CHESTPLATE = registerItem("deepslate_chestplate",
            new KQArmorItem(KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item DEEPSLATE_LEGGINGS = registerItem("deepslate_leggings",
            new KQArmorItem(KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item DEEPSLATE_BOOTS = registerItem("deepslate_boots",
            new KQArmorItem(KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item DRAGON_HELMET = registerItem("dragon_helmet",
            new GeoItemArmor(KQArmorMaterials.DRAGONSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/dragon_helmet.png", "geo/dragon_helmet.geo.json"));
    public static final Item DRAGON_CHESTPLATE = registerItem("dragon_chestplate",
            new KQArmorItem(KQArmorMaterials.DRAGONSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item DRAGON_LEGGINGS = registerItem("dragon_leggings",
            new KQArmorItem(KQArmorMaterials.DRAGONSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item DRAGON_BOOTS = registerItem("dragon_boots",
            new KQArmorItem(KQArmorMaterials.DRAGONSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item ENDERMAN_HELMET = registerItem("enderman_helmet",
            new GeoItemArmor(KQArmorMaterials.ENDERMANSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/enderman_helmet.png", "geo/enderman_helmet.geo.json"));
    public static final Item ENDERMAN_CHESTPLATE = registerItem("enderman_chestplate",
            new KQArmorItem(KQArmorMaterials.ENDERMANSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item ENDERMAN_LEGGINGS = registerItem("enderman_leggings",
            new KQArmorItem(KQArmorMaterials.ENDERMANSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item ENDERMAN_BOOTS = registerItem("enderman_boots",
            new KQArmorItem(KQArmorMaterials.ENDERMANSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item EVOKER_HELMET = registerItem("evoker_helmet",
            new GeoItemArmor(KQArmorMaterials.EVOKERSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/evoker_helmet.png", "geo/evoker_helmet.geo.json"));
    public static final Item EVOKER_CHESTPLATE = registerItem("evoker_chestplate",
            new KQArmorItem(KQArmorMaterials.EVOKERSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item EVOKER_LEGGINGS = registerItem("evoker_leggings",
            new KQArmorItem(KQArmorMaterials.EVOKERSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item EVOKER_BOOTS = registerItem("evoker_boots",
            new KQArmorItem(KQArmorMaterials.EVOKERSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item FORZE_HELMET = registerItem("forze_helmet",
            new GeoItemArmor(KQArmorMaterials.FORZESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/forze_helmet.png", "geo/forze_helmet.geo.json"));
    public static final Item FORZE_CHESTPLATE = registerItem("forze_chestplate",
            new GeoItemArmor(KQArmorMaterials.FORZESET, ArmorItem.Type.CHESTPLATE,new Item.Settings()
                    , "textures/armor/forze_main.png", "geo/forze_main.geo.json"));
    public static final Item FORZE_LEGGINGS = registerItem("forze_leggings",
            new KQArmorItem(KQArmorMaterials.FORZESET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item FORZE_BOOTS = registerItem("forze_boots",
            new KQArmorItem(KQArmorMaterials.FORZESET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item HOLLOW_HELMET = registerItem("hollow_helmet",
            new GeoItemArmor(KQArmorMaterials.HOLLOWSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/hollow_helmet.png", "geo/hollow_helmet.geo.json"));
    public static final Item HOLLOW_CHESTPLATE = registerItem("hollow_chestplate",
            new KQArmorItem(KQArmorMaterials.HOLLOWSET, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item HOLLOW_LEGGINGS = registerItem("hollow_leggings",
            new KQArmorItem(KQArmorMaterials.HOLLOWSET, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item HOLLOW_BOOTS = registerItem("hollow_boots",
            new KQArmorItem(KQArmorMaterials.HOLLOWSET, ArmorItem.Type.BOOTS, new Item.Settings()));

    public static final Item NETHER_HELMET = registerItem("nether_helmet",
            new GeoItemArmor(KQArmorMaterials.NETHERSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/nether_helmet.png", "geo/nether_helmet.geo.json"));
    public static final Item NETHER_CHESTPLATE = registerItem("nether_chestplate",
            new KQArmorItem(KQArmorMaterials.NETHERSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item NETHER_LEGGINGS = registerItem("nether_leggings",
            new KQArmorItem(KQArmorMaterials.NETHERSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item NETHER_BOOTS = registerItem("nether_boots",
            new KQArmorItem(KQArmorMaterials.NETHERSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item VETERAN_HELMET = registerItem("veteran_helmet",
            new GeoItemArmor(KQArmorMaterials.VETERANSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/veteran_helmet.png", "geo/veteran_helmet.geo.json"));
    public static final Item VETERAN_CHESTPLATE = registerItem("veteran_chestplate",
            new GeoItemArmor(KQArmorMaterials.VETERANSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()
                    , "textures/armor/veteran_main.png", "geo/veteran_main.geo.json"));
    public static final Item VETERAN_LEGGINGS = registerItem("veteran_leggings",
            new GeoItemArmor(KQArmorMaterials.VETERANSET, ArmorItem.Type.LEGGINGS,new Item.Settings()
                    , "textures/armor/veteran_leggings.png", "geo/veteran_leggings.geo.json"));
    public static final Item VETERAN_BOOTS = registerItem("veteran_boots",
            new KQArmorItem(KQArmorMaterials.VETERANSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item PATH_HELMET = registerItem("path_helmet",
            new GeoItemArmor(KQArmorMaterials.PATHSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/path_helmet.png", "geo/path_helmet.geo.json"));
    public static final Item PATH_CHESTPLATE = registerItem("path_chestplate",
            new KQArmorItem(KQArmorMaterials.PATHSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item PATH_LEGGINGS = registerItem("path_leggings",
            new KQArmorItem(KQArmorMaterials.PATHSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item PATH_BOOTS = registerItem("path_boots",
            new KQArmorItem(KQArmorMaterials.PATHSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item PHANTOM_HELMET = registerItem("phantom_helmet",
            new GeoItemArmor(KQArmorMaterials.PHANTOMSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/phantom_helmet.png", "geo/phantom_helmet.geo.json"));
    public static final Item PHANTOM_CHESTPLATE = registerItem("phantom_chestplate",
            new KQArmorItem(KQArmorMaterials.PHANTOMSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item PHANTOM_LEGGINGS = registerItem("phantom_leggings",
            new KQArmorItem(KQArmorMaterials.PHANTOMSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item PHANTOM_BOOTS = registerItem("phantom_boots",
            new KQArmorItem(KQArmorMaterials.PHANTOMSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SEA_HELMET = registerItem("sea_helmet",
            new GeoItemArmor(KQArmorMaterials.SEASET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/sea_helmet.png", "geo/sea_helmet.geo.json"));
    public static final Item SEA_CHESTPLATE = registerItem("sea_chestplate",
            new KQArmorItem(KQArmorMaterials.SEASET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SEA_LEGGINGS = registerItem("sea_leggings",
            new KQArmorItem(KQArmorMaterials.SEASET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SEA_BOOTS = registerItem("sea_boots",
            new KQArmorItem(KQArmorMaterials.SEASET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SHIELD_HELMET = registerItem("shield_helmet",
            new GeoItemArmor(KQArmorMaterials.SHIELDSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/shield_helmet.png", "geo/shield_helmet.geo.json"));
    public static final Item SHIELD_CHESTPLATE = registerItem("shield_chestplate",
            new KQArmorItem(KQArmorMaterials.SHIELDSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SHIELD_LEGGINGS = registerItem("shield_leggings",
            new KQArmorItem(KQArmorMaterials.SHIELDSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SHIELD_BOOTS = registerItem("shield_boots",
            new KQArmorItem(KQArmorMaterials.SHIELDSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SILVER_HELMET = registerItem("silver_helmet",
            new GeoItemArmor(KQArmorMaterials.SILVERSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/silver_helmet.png", "geo/silver_helmet.geo.json"));
    public static final Item SILVER_CHESTPLATE = registerItem("silver_chestplate",
            new GeoItemArmor(KQArmorMaterials.SILVERSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()
                    , "textures/armor/silver_main.png", "geo/silver_main.geo.json"));
    public static final Item SILVER_LEGGINGS = registerItem("silver_leggings",
            new KQArmorItem(KQArmorMaterials.SILVERSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SILVER_BOOTS = registerItem("silver_boots",
            new KQArmorItem(KQArmorMaterials.SILVERSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SILVERFISH_HELMET = registerItem("silverfish_helmet",
            new GeoItemArmor(KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/silverfish_helmet.png", "geo/silverfish_helmet.geo.json"));
    public static final Item SILVERFISH_CHESTPLATE = registerItem("silverfish_chestplate",
            new KQArmorItem(KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SILVERFISH_LEGGINGS = registerItem("silverfish_leggings",
            new KQArmorItem(KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SILVERFISH_BOOTS = registerItem("silverfish_boots",
            new KQArmorItem(KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SKELETON_HELMET = registerItem("skeleton_helmet",
            new GeoItemArmor(KQArmorMaterials.SKELETONSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/skeleton_helmet.png", "geo/skeleton_helmet.geo.json"));
    public static final Item SKELETON_CHESTPLATE = registerItem("skeleton_chestplate",
            new KQArmorItem(KQArmorMaterials.SKELETONSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SKELETON_LEGGINGS = registerItem("skeleton_leggings",
            new KQArmorItem(KQArmorMaterials.SKELETONSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SKELETON_BOOTS = registerItem("skeleton_boots",
            new KQArmorItem(KQArmorMaterials.SKELETONSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SPIDER_HELMET = registerItem("spider_helmet",
            new GeoItemArmor(KQArmorMaterials.SPIDERSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/spider_helmet.png", "geo/spider_helmet.geo.json"));
    public static final Item SPIDER_CHESTPLATE = registerItem("spider_chestplate",
            new GeoItemArmor(KQArmorMaterials.SPIDERSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()
                    , "textures/armor/spider_main.png", "geo/spider_main.geo.json"));
    public static final Item SPIDER_LEGGINGS = registerItem("spider_leggings",
            new KQArmorItem(KQArmorMaterials.SPIDERSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SPIDER_BOOTS = registerItem("spider_boots",
            new KQArmorItem(KQArmorMaterials.SPIDERSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item WARLORD_HELMET = registerItem("warlord_helmet",
            new GeoItemArmor(KQArmorMaterials.WARLORDSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/warlord_helmet.png", "geo/warlord_helmet.geo.json"));
    public static final Item WARLORD_CHESTPLATE = registerItem("warlord_chestplate",
            new KQArmorItem(KQArmorMaterials.WARLORDSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item WARLORD_LEGGINGS = registerItem("warlord_leggings",
            new KQArmorItem(KQArmorMaterials.WARLORDSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item WARLORD_BOOTS = registerItem("warlord_boots",
            new KQArmorItem(KQArmorMaterials.WARLORDSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item STRAWHAT_HELMET = registerItem("strawhat_helmet",
            new GeoItemArmor(KQArmorMaterials.STRAWHATSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/strawhat_helmet.png", "geo/strawhat_helmet.geo.json"));
    public static final Item STRAWHAT_CHESTPLATE = registerItem("strawhat_chestplate",
            new KQArmorItem(KQArmorMaterials.STRAWHATSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item STRAWHAT_LEGGINGS = registerItem("strawhat_leggings",
            new KQArmorItem(KQArmorMaterials.STRAWHATSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item STRAWHAT_BOOTS = registerItem("strawhat_boots",
            new KQArmorItem(KQArmorMaterials.STRAWHATSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item PIRATE_HELMET = registerItem("pirate_helmet",
            new GeoItemArmor(KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/pirate_helmet.png", "geo/pirate_helmet.geo.json"));
    public static final Item PIRATE2_HELMET = registerItem("pirate2_helmet",
            new GeoItemArmor(KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/pirate2_helmet.png", "geo/pirate2_helmet.geo.json"));
    public static final Item PIRATE3_HELMET = registerItem("pirate3_helmet",
            new GeoItemArmor(KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/pirate3_helmet.png", "geo/pirate3_helmet.geo.json"));
    public static final Item PIRATE_CHESTPLATE = registerItem("pirate_chestplate",
            new KQArmorItem(KQArmorMaterials.PIRATESET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item PIRATE_LEGGINGS = registerItem("pirate_leggings",
            new KQArmorItem(KQArmorMaterials.PIRATESET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item PIRATE_BOOTS = registerItem("pirate_boots",
            new KQArmorItem(KQArmorMaterials.PIRATESET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item CONQUISTADOR_HELMET = registerItem("conquistador_helmet",
            new GeoItemArmor(KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/conquistador_helmet.png", "geo/conquistador_helmet.geo.json"));
    public static final Item CONQUISTADOR2_HELMET = registerItem("conquistador2_helmet",
            new GeoItemArmor(KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/conquistador2_helmet.png", "geo/conquistador2_helmet.geo.json"));
    public static final Item CONQUISTADOR3_HELMET = registerItem("conquistador3_helmet",
            new GeoItemArmor(KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/conquistador3_helmet.png", "geo/conquistador3_helmet.geo.json"));
    public static final Item CONQUISTADOR_CHESTPLATE = registerItem("conquistador_chestplate",
            new KQArmorItem(KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item CONQUISTADOR_LEGGINGS = registerItem("conquistador_leggings",
            new KQArmorItem(KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item CONQUISTADOR_BOOTS = registerItem("conquistador_boots",
            new KQArmorItem(KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item ZOMBIE_HELMET = registerItem("zombie_helmet",
            new GeoItemArmor(KQArmorMaterials.ZOMBIESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/zombie_helmet.png", "geo/zombie_helmet.geo.json"));
    public static final Item ZOMBIE_HELMET2 = registerItem("zombie_helmet2",
            new GeoItemArmor(KQArmorMaterials.ZOMBIESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/zombie_helmet2.png", "geo/zombie_helmet2.geo.json"));
    public static final Item ZOMBIE_CHESTPLATE = registerItem("zombie_chestplate",
            new KQArmorItem(KQArmorMaterials.ZOMBIESET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item ZOMBIE_LEGGINGS = registerItem("zombie_leggings",
            new KQArmorItem(KQArmorMaterials.ZOMBIESET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item ZOMBIE_BOOTS = registerItem("zombie_boots",
            new KQArmorItem(KQArmorMaterials.ZOMBIESET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item HUSK_HELMET = registerItem("husk_helmet",
            new GeoItemArmor(KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/husk_helmet.png", "geo/husk_helmet.geo.json"));
    public static final Item HUSK_HELMET2 = registerItem("husk_helmet2",
            new GeoItemArmor(KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/husk_helmet2.png", "geo/husk_helmet2.geo.json"));
    public static final Item HUSK_HELMET3 = registerItem("husk_helmet3",
            new GeoItemArmor(KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/husk_helmet3.png", "geo/husk_helmet3.geo.json"));
    public static final Item HUSK_CHESTPLATE = registerItem("husk_chestplate",
            new KQArmorItem(KQArmorMaterials.HUSKSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item HUSK_LEGGINGS = registerItem("husk_leggings",
            new KQArmorItem(KQArmorMaterials.HUSKSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item HUSK_BOOTS = registerItem("husk_boots",
            new KQArmorItem(KQArmorMaterials.HUSKSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item WITHER_HELMET = registerItem("wither_helmet",
            new KQArmorItem(KQArmorMaterials.WITHERSET, ArmorItem.Type.HELMET,new Item.Settings()));
    public static final Item WITHER_CHESTPLATE = registerItem("wither_chestplate",
            new KQArmorItem(KQArmorMaterials.WITHERSET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item WITHER_LEGGINGS = registerItem("wither_leggings",
            new KQArmorItem(KQArmorMaterials.WITHERSET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item WITHER_BOOTS = registerItem("wither_boots",
            new KQArmorItem(KQArmorMaterials.WITHERSET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SQUIRE_HELMET = registerItem("squire_helmet",
            new GeoItemArmor(KQArmorMaterials.SQUIRESET, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/squire_helmet.png", "geo/squire_helmet.geo.json") {
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.item.knightquest.squire_helmet"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });
    public static final Item SQUIRE_CHESTPLATE = registerItem("squire_chestplate",
            new KQArmorItem(KQArmorMaterials.SQUIRESET, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SQUIRE_LEGGINGS = registerItem("squire_leggings",
            new KQArmorItem(KQArmorMaterials.SQUIRESET, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SQUIRE_BOOTS = registerItem("squire_boots",
            new KQArmorItem(KQArmorMaterials.SQUIRESET, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item CHAINMAIL_HELMET = registerItem("chainmail_helmet",
            new ArmorItem(KQArmorMaterials.CHAINMAIL, ArmorItem.Type.HELMET,new Item.Settings()));
    public static final Item CHAINMAIL_HELMET2 = registerItem("chainmail_helmet2",
            new GeoItemArmor(KQArmorMaterials.CHAINMAIL, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/chainmail_helmet2.png", "geo/chainmail_helmet2.geo.json"));
    public static final Item TUNIC_BLUE_LEGGINGS = registerItem("tunic_blue_leggings",
            new ArmorItem(KQArmorMaterials.TUNIC_BLUE, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item TUNIC_GREEN_LEGGINGS = registerItem("tunic_green_leggings",
            new ArmorItem(KQArmorMaterials.TUNIC_GREEN, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item TUNIC_YELLOW_LEGGINGS = registerItem("tunic_yellow_leggings",
            new ArmorItem(KQArmorMaterials.TUNIC_YELLOW, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item TUNIC_RED_LEGGINGS = registerItem("tunic_red_leggings",
            new ArmorItem(KQArmorMaterials.TUNIC_RED, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item TUNIC_SEA_LEGGINGS = registerItem("tunic_sea_leggings",
            new ArmorItem(KQArmorMaterials.TUNIC_SEA, ArmorItem.Type.LEGGINGS,new Item.Settings()));

    public static final Item WITCH_HELMET = registerItem("witch_helmet",
            new GeoItemArmor(KQArmorMaterials.WITCH, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/witch_helmet.png", "geo/witch_helmet.geo.json"));
    public static final Item WITCH_CHESTPLATE = registerItem("witch_chestplate",
            new KQArmorItem(KQArmorMaterials.WITCH, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item WITCH_LEGGINGS = registerItem("witch_leggings",
            new KQArmorItem(KQArmorMaterials.WITCH, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item WITCH_BOOTS = registerItem("witch_boots",
            new KQArmorItem(KQArmorMaterials.WITCH, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item POLAR_HELMET = registerItem("polar_helmet",
            new GeoItemArmor(KQArmorMaterials.POLAR, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/polar_helmet.png", "geo/polar_helmet.geo.json"));
    public static final Item POLAR_CHESTPLATE = registerItem("polar_chestplate",
            new KQArmorItem(KQArmorMaterials.POLAR, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item POLAR_LEGGINGS = registerItem("polar_leggings",
            new KQArmorItem(KQArmorMaterials.POLAR, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item POLAR_BOOTS = registerItem("polar_boots",
            new KQArmorItem(KQArmorMaterials.POLAR, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SHINOBI_HELMET = registerItem("shinobi_helmet",
            new GeoItemArmor(KQArmorMaterials.SHINOBI, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/shinobi_helmet.png", "geo/shinobi_helmet.geo.json"));
    public static final Item SHINOBI_CHESTPLATE = registerItem("shinobi_chestplate",
            new KQArmorItem(KQArmorMaterials.SHINOBI, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SHINOBI_LEGGINGS = registerItem("shinobi_leggings",
            new KQArmorItem(KQArmorMaterials.SHINOBI, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SHINOBI_BOOTS = registerItem("shinobi_boots",
            new KQArmorItem(KQArmorMaterials.SHINOBI, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item SKULK_HELMET = registerItem("skulk_helmet",
            new GeoItemArmor(KQArmorMaterials.SKULK, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/skulk_helmet.png", "geo/skulk_helmet.geo.json"));
    public static final Item SKULK2_HELMET = registerItem("skulk2_helmet",
            new GeoItemArmor(KQArmorMaterials.SKULK, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/skulk2_helmet.png", "geo/skulk2_helmet.geo.json"));
    public static final Item SKULK3_HELMET = registerItem("skulk3_helmet",
            new GeoItemArmor(KQArmorMaterials.SKULK, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/skulk3_helmet.png", "geo/skulk3_helmet.geo.json"));
    public static final Item SKULK4_HELMET = registerItem("skulk4_helmet",
            new GeoItemArmor(KQArmorMaterials.SKULK, ArmorItem.Type.HELMET,new Item.Settings()
                    , "textures/armor/skulk4_helmet.png", "geo/skulk4_helmet.geo.json"));
    public static final Item SKULK_CHESTPLATE = registerItem("skulk_chestplate",
            new KQArmorItem(KQArmorMaterials.SKULK, ArmorItem.Type.CHESTPLATE,new Item.Settings()));
    public static final Item SKULK_LEGGINGS = registerItem("skulk_leggings",
            new KQArmorItem(KQArmorMaterials.SKULK, ArmorItem.Type.LEGGINGS,new Item.Settings()));
    public static final Item SKULK_BOOTS = registerItem("skulk_boots",
            new KQArmorItem(KQArmorMaterials.SKULK, ArmorItem.Type.BOOTS,new Item.Settings()));

    public static final Item GREMLIN_EGG = registerItem("gremlin_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.GREMLIN, 0xc22f26, 0x45545d, new Item.Settings()));
    public static final Item ELD_BOMB_EGG = registerItem("eldbomb_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.ELDBOMB, 0x43404e, 0x81da25, new Item.Settings()));
    public static final Item ELD_KNIGHT_EGG = registerItem("eldknight_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.ELDKNIGHT, 0x7f8ab2, 0x8a392e, new Item.Settings()));
    public static final Item RATMAN_EGG = registerItem("ratman_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.RATMAN, 0x3a303d, 0xb75383, new Item.Settings()));
    public static final Item SAMHAIN_EGG = registerItem("samhain_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.SAMHAIN, 0xfdde03, 0x982938, new Item.Settings()));
    public static final Item SWAMPMAN_EGG = registerItem("swampman_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.SWAMPMAN, 0x108773, 0x9e304f, new Item.Settings()));
    public static final Item LIZZY_EGG = registerItem("lizzy_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.LIZZY, 0x0babf2, 0xd1802b, new Item.Settings()));
    public static final Item BADPATCH_EGG = registerItem("bad_patch_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.BADPATCH, 0xec160b, 0xeff1f8, new Item.Settings()));
    public static final Item GHOSTY_EGG = registerItem("ghosty_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.GHOSTY, 0x2cb87e, 0xfbe105, new Item.Settings()));
    public static final Item GHASTLING_EGG = registerItem("ghastling_spawn_egg",
            new SpawnEggItem(KnightQuestEntities.SHIELD, 0x930c13, 0xfb9600, new Item.Settings()){
                @Override
                public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                    tooltip.add(Text.translatable("tooltip.item.knightquest.ghastling_spawn_egg"));
                    super.appendTooltip(stack, context, tooltip, type);
                }
            });

}
