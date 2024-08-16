package net.xylonity.knightquest.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.client.GeoItemArmor;
import net.xylonity.knightquest.common.client.chest.GeoItemArmorChest;
import net.xylonity.knightquest.common.client.leg.GeoItemArmorLeg;
import net.xylonity.knightquest.common.item.KQArmorItem;
import net.xylonity.knightquest.common.item.KQItem;
import net.xylonity.knightquest.common.material.KQArmorMaterials;
import net.xylonity.knightquest.common.material.KQItemMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightQuestItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KnightQuest.MOD_ID);

    public static final RegistryObject<Item> GREAT_ESSENCE = ITEMS.register("great_essence", () -> new KQItem(new Item.Properties(), "great_essence"));
    public static final RegistryObject<Item> SMALL_ESSENCE = ITEMS.register("small_essence", () -> new KQItem(new Item.Properties(), "small_essence"));
    public static final RegistryObject<Item> RADIANT_ESSENCE = ITEMS.register("radiant_essence", () -> new KQItem(new Item.Properties(), "small_essence"));
    public static final RegistryObject<Item> EMPTY_GOBLET = ITEMS.register("empty_goblet", () -> new KQItem(new Item.Properties(), "empty_goblet"));
    public static final RegistryObject<Item> FILLED_GOBLET = ITEMS.register("filled_goblet", () -> new KQItem(new Item.Properties(), "filled_goblet"));
    public static final RegistryObject<Item> RATMAN_EYE = ITEMS.register("ratman_eye", () -> new KQItem(new Item.Properties(), "ratman_eye"));
    public static final RegistryObject<Item> LIZZY_SCALE = ITEMS.register("lizzy_scale", () -> new KQItem(new Item.Properties(), "lizzy_scale"));

    public static final RegistryObject<Item> PALADIN_SWORD = ITEMS.register("paladin_sword",
            () -> new SwordItem(KQItemMaterials.PALADIN, 4, -2.8f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB))
            {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.paladin_sword"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });

    public static final RegistryObject<Item> NAIL_SWORD = ITEMS.register("nail_glaive",
            () -> new SwordItem(KQItemMaterials.NAIL, 4, -2.6f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> UCHIGATANA = ITEMS.register("uchigatana_katana",
            () -> new SwordItem(KQItemMaterials.UCHIGATANA, 4, -2.2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> KUKRI = ITEMS.register("kukri_dagger",
            () -> new SwordItem(KQItemMaterials.KUKRI, 4, -1f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> KHOPESH = ITEMS.register("khopesh_claymore",
            () -> new SwordItem(KQItemMaterials.KHOPESH, 4, -2.2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CLEAVER = ITEMS.register("cleaver_heavy_axe",
            () -> new SwordItem(KQItemMaterials.CLEAVER, 4, -3f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CRIMSON_SWORD = ITEMS.register("crimson_sword",
            () -> new SwordItem(KQItemMaterials.CRIMSON_SWORD, 4, -2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> WATER_SWORD = ITEMS.register("water_sword",
            () -> new SwordItem(KQItemMaterials.WATER_SWORD, 4, -2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(KQItemMaterials.STEEL_SWORD, 4, -2f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> WATER_AXE = ITEMS.register("water_axe",
            () -> new AxeItem(KQItemMaterials.WATER_AXE, 4, -2.8f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(KQItemMaterials.STEEL_AXE, 4, -2.8f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> APPLE_HELMET = ITEMS.register("apple_helmet",
            () ->  new GeoItemArmor(KQArmorMaterials.APPLE_SET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/apple_helmet.png", "geo/apple_helmet.geo.json"));
    public static final RegistryObject<Item> APPLE_CHESTPLATE = ITEMS.register("apple_chestplate",
            () ->  new KQArmorItem(KQArmorMaterials.APPLE_SET, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> APPLE_LEGGINGS = ITEMS.register("apple_leggings",
            () ->  new KQArmorItem(KQArmorMaterials.APPLE_SET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> APPLE_BOOTS = ITEMS.register("apple_boots",
            () ->  new KQArmorItem(KQArmorMaterials.APPLE_SET, EquipmentSlot.FEET, new Item.Properties()));

    public static final RegistryObject<Item> BAMBOO_BLUE_HELMET = ITEMS.register("bamboo_blue_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.BAMBOOSET_BLUE, EquipmentSlot.HEAD, new Item.Properties()
                    , "textures/armor/bamboo_blue_helmet.png", "geo/bambooblue_helmet.geo.json"));
    public static final RegistryObject<Item> BAMBOO_BLUE_CHESTPLATE = ITEMS.register("bamboo_blue_chestplate",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET_BLUE, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_BLUE_LEGGINGS = ITEMS.register("bamboo_blue_leggings",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET_BLUE, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_BLUE_BOOTS = ITEMS.register("bamboo_blue_boots",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET_BLUE, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> BAMBOO_GREEN_HELMET = ITEMS.register("bamboo_green_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.BAMBOOSET_GREEN, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/bamboo_green_helmet.png", "geo/bamboogreen_helmet.geo.json"));
    public static final RegistryObject<Item> BAMBOO_GREEN_CHESTPLATE = ITEMS.register("bamboo_green_chestplate",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET_GREEN, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_GREEN_LEGGINGS = ITEMS.register("bamboo_green_leggings",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET_GREEN, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_GREEN_BOOTS = ITEMS.register("bamboo_green_boots",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET_GREEN, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> TENGU_HELMET = ITEMS.register("tengu_helmet",
            () ->  new GeoItemArmor(KQArmorMaterials.TENGU, EquipmentSlot.HEAD, new Item.Properties()
                    , "textures/armor/tengu_helmet.png", "geo/tengu_helmet.geo.json"));
    public static final RegistryObject<Item> BAMBOO_HELMET = ITEMS.register("bamboo_helmet",
            () ->  new GeoItemArmor(KQArmorMaterials.BAMBOOSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/bamboo_helmet.png", "geo/bamboo_helmet.geo.json"));
    public static final RegistryObject<Item> BAMBOO_CHESTPLATE = ITEMS.register("bamboo_chestplate",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_LEGGINGS = ITEMS.register("bamboo_leggings",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> BAMBOO_BOOTS = ITEMS.register("bamboo_boots",
            () ->  new KQArmorItem(KQArmorMaterials.BAMBOOSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> BAT_HELMET = ITEMS.register("bat_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.BATSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/bat_helmet.png", "geo/bat_helmet.geo.json"));
    public static final RegistryObject<Item> BAT_CHESTPLATE = ITEMS.register("bat_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.BATSET, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> BAT_LEGGINGS = ITEMS.register("bat_leggings",
            () -> new KQArmorItem(KQArmorMaterials.BATSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> BAT_BOOTS = ITEMS.register("bat_boots",
            () -> new KQArmorItem(KQArmorMaterials.BATSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> BLAZE_HELMET = ITEMS.register("blaze_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.BLAZESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/blaze_helmet.png", "geo/blaze_helmet.geo.json"));
    public static final RegistryObject<Item> BLAZE_CHESTPLATE = ITEMS.register("blaze_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.BLAZESET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> BLAZE_LEGGINGS = ITEMS.register("blaze_leggings",
            () -> new KQArmorItem(KQArmorMaterials.BLAZESET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> BLAZE_BOOTS = ITEMS.register("blaze_boots",
            () -> new KQArmorItem(KQArmorMaterials.BLAZESET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> BOW_HELMET = ITEMS.register("bow_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.BOWSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/bow_helmet.png", "geo/bow_helmet.geo.json"));
    public static final RegistryObject<Item> BOW_CHESTPLATE = ITEMS.register("bow_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.BOWSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> BOW_LEGGINGS = ITEMS.register("bow_leggings",
            () -> new KQArmorItem(KQArmorMaterials.BOWSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> BOW_BOOTS = ITEMS.register("bow_boots",
            () -> new KQArmorItem(KQArmorMaterials.BOWSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> HORN_HELMET = ITEMS.register("horn_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.HORNSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/horn_helmet.png", "geo/horn_helmet.geo.json"));
    public static final RegistryObject<Item> HORN_CHESTPLATE = ITEMS.register("horn_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.HORNSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> HORN_LEGGINGS = ITEMS.register("horn_leggings",
            () -> new KQArmorItem(KQArmorMaterials.HORNSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> HORN_BOOTS = ITEMS.register("horn_boots",
            () -> new KQArmorItem(KQArmorMaterials.HORNSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> CREEPER_HELMET = ITEMS.register("creeper_helmet",
            () -> new KQArmorItem(KQArmorMaterials.CREEPERSET, EquipmentSlot.HEAD,new Item.Properties()));
    public static final RegistryObject<Item> CREEPER_CHESTPLATE = ITEMS.register("creeper_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.CREEPERSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> CREEPER_LEGGINGS = ITEMS.register("creeper_leggings",
            () -> new KQArmorItem(KQArmorMaterials.CREEPERSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> CREEPER_BOOTS = ITEMS.register("creeper_boots",
            () -> new KQArmorItem(KQArmorMaterials.CREEPERSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> DEEPSLATE_HELMET = ITEMS.register("deepslate_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.DEEPSLATESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/deepslate_helmet.png", "geo/deepslate_helmet.geo.json"));
    public static final RegistryObject<Item> DEEPSLATE_CHESTPLATE = ITEMS.register("deepslate_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.DEEPSLATESET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_LEGGINGS = ITEMS.register("deepslate_leggings",
            () -> new KQArmorItem(KQArmorMaterials.DEEPSLATESET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> DEEPSLATE_BOOTS = ITEMS.register("deepslate_boots",
            () -> new KQArmorItem(KQArmorMaterials.DEEPSLATESET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> DRAGON_HELMET = ITEMS.register("dragon_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.DRAGONSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/dragon_helmet.png", "geo/dragon_helmet.geo.json"));
    public static final RegistryObject<Item> DRAGON_CHESTPLATE = ITEMS.register("dragon_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.DRAGONSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_LEGGINGS = ITEMS.register("dragon_leggings",
            () -> new KQArmorItem(KQArmorMaterials.DRAGONSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> DRAGON_BOOTS = ITEMS.register("dragon_boots",
            () -> new KQArmorItem(KQArmorMaterials.DRAGONSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> ENDERMAN_HELMET = ITEMS.register("enderman_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.ENDERMANSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/enderman_helmet.png", "geo/enderman_helmet.geo.json"));
    public static final RegistryObject<Item> ENDERMAN_CHESTPLATE = ITEMS.register("enderman_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.ENDERMANSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> ENDERMAN_LEGGINGS = ITEMS.register("enderman_leggings",
            () -> new KQArmorItem(KQArmorMaterials.ENDERMANSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> ENDERMAN_BOOTS = ITEMS.register("enderman_boots",
            () -> new KQArmorItem(KQArmorMaterials.ENDERMANSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> EVOKER_HELMET = ITEMS.register("evoker_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.EVOKERSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/evoker_helmet.png", "geo/evoker_helmet.geo.json"));
    public static final RegistryObject<Item> EVOKER_CHESTPLATE = ITEMS.register("evoker_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.EVOKERSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> EVOKER_LEGGINGS = ITEMS.register("evoker_leggings",
            () -> new KQArmorItem(KQArmorMaterials.EVOKERSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> EVOKER_BOOTS = ITEMS.register("evoker_boots",
            () -> new KQArmorItem(KQArmorMaterials.EVOKERSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> FORZE_HELMET = ITEMS.register("forze_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.FORZESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/forze_helmet.png", "geo/forze_helmet.geo.json"));
    public static final RegistryObject<Item> FORZE_CHESTPLATE = ITEMS.register("forze_chestplate",
            () -> new GeoItemArmorChest(KQArmorMaterials.FORZESET, EquipmentSlot.CHEST,new Item.Properties()
                    , "textures/armor/forze_main.png", "geo/forze_main.geo.json"));
    public static final RegistryObject<Item> FORZE_LEGGINGS = ITEMS.register("forze_leggings",
            () -> new KQArmorItem(KQArmorMaterials.FORZESET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> FORZE_BOOTS = ITEMS.register("forze_boots",
            () -> new KQArmorItem(KQArmorMaterials.FORZESET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> HOLLOW_HELMET = ITEMS.register("hollow_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.HOLLOWSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/hollow_helmet.png", "geo/hollow_helmet.geo.json"));
    public static final RegistryObject<Item> HOLLOW_CHESTPLATE = ITEMS.register("hollow_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.HOLLOWSET, EquipmentSlot.CHEST, new Item.Properties()));
    public static final RegistryObject<Item> HOLLOW_LEGGINGS = ITEMS.register("hollow_leggings",
            () -> new KQArmorItem(KQArmorMaterials.HOLLOWSET, EquipmentSlot.LEGS, new Item.Properties()));
    public static final RegistryObject<Item> HOLLOW_BOOTS = ITEMS.register("hollow_boots",
            () -> new KQArmorItem(KQArmorMaterials.HOLLOWSET, EquipmentSlot.FEET, new Item.Properties()));

    public static final RegistryObject<Item> NETHER_HELMET = ITEMS.register("nether_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.NETHERSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/nether_helmet.png", "geo/nether_helmet.geo.json"));
    public static final RegistryObject<Item> NETHER_CHESTPLATE = ITEMS.register("nether_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.NETHERSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> NETHER_LEGGINGS = ITEMS.register("nether_leggings",
            () -> new KQArmorItem(KQArmorMaterials.NETHERSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> NETHER_BOOTS = ITEMS.register("nether_boots",
            () -> new KQArmorItem(KQArmorMaterials.NETHERSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> VETERAN_HELMET = ITEMS.register("veteran_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.VETERANSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/veteran_helmet.png", "geo/veteran_helmet.geo.json"));
    public static final RegistryObject<Item> VETERAN_CHESTPLATE = ITEMS.register("veteran_chestplate",
            () -> new GeoItemArmorChest(KQArmorMaterials.VETERANSET, EquipmentSlot.CHEST,new Item.Properties()
                    , "textures/armor/veteran_main.png", "geo/veteran_main.geo.json"));
    public static final RegistryObject<Item> VETERAN_LEGGINGS = ITEMS.register("veteran_leggings",
            () -> new GeoItemArmorLeg(KQArmorMaterials.VETERANSET, EquipmentSlot.LEGS,new Item.Properties()
                    , "textures/armor/veteran_leggings.png", "geo/veteran_leggings.geo.json"));
    public static final RegistryObject<Item> VETERAN_BOOTS = ITEMS.register("veteran_boots",
            () -> new KQArmorItem(KQArmorMaterials.VETERANSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> PATH_HELMET = ITEMS.register("path_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.PATHSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/path_helmet.png", "geo/path_helmet.geo.json"));
    public static final RegistryObject<Item> PATH_CHESTPLATE = ITEMS.register("path_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.PATHSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> PATH_LEGGINGS = ITEMS.register("path_leggings",
            () -> new KQArmorItem(KQArmorMaterials.PATHSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> PATH_BOOTS = ITEMS.register("path_boots",
            () -> new KQArmorItem(KQArmorMaterials.PATHSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> PHANTOM_HELMET = ITEMS.register("phantom_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.PHANTOMSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/phantom_helmet.png", "geo/phantom_helmet.geo.json"));
    public static final RegistryObject<Item> PHANTOM_CHESTPLATE = ITEMS.register("phantom_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.PHANTOMSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> PHANTOM_LEGGINGS = ITEMS.register("phantom_leggings",
            () -> new KQArmorItem(KQArmorMaterials.PHANTOMSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> PHANTOM_BOOTS = ITEMS.register("phantom_boots",
            () -> new KQArmorItem(KQArmorMaterials.PHANTOMSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SEA_HELMET = ITEMS.register("sea_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SEASET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/sea_helmet.png", "geo/sea_helmet.geo.json"));
    public static final RegistryObject<Item> SEA_CHESTPLATE = ITEMS.register("sea_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SEASET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SEA_LEGGINGS = ITEMS.register("sea_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SEASET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SEA_BOOTS = ITEMS.register("sea_boots",
            () -> new KQArmorItem(KQArmorMaterials.SEASET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SHIELD_HELMET = ITEMS.register("shield_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SHIELDSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/shield_helmet.png", "geo/shield_helmet.geo.json"));
    public static final RegistryObject<Item> SHIELD_CHESTPLATE = ITEMS.register("shield_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SHIELDSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SHIELD_LEGGINGS = ITEMS.register("shield_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SHIELDSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SHIELD_BOOTS = ITEMS.register("shield_boots",
            () -> new KQArmorItem(KQArmorMaterials.SHIELDSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SILVERSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/silver_helmet.png", "geo/silver_helmet.geo.json"));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new GeoItemArmorChest(KQArmorMaterials.SILVERSET, EquipmentSlot.CHEST,new Item.Properties()
                    , "textures/armor/silver_main.png", "geo/silver_main.geo.json"));
    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SILVERSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new KQArmorItem(KQArmorMaterials.SILVERSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SILVERFISH_HELMET = ITEMS.register("silverfish_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SILVERFISHSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/silverfish_helmet.png", "geo/silverfish_helmet.geo.json"));
    public static final RegistryObject<Item> SILVERFISH_CHESTPLATE = ITEMS.register("silverfish_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SILVERFISHSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SILVERFISH_LEGGINGS = ITEMS.register("silverfish_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SILVERFISHSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SILVERFISH_BOOTS = ITEMS.register("silverfish_boots",
            () -> new KQArmorItem(KQArmorMaterials.SILVERFISHSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SKELETON_HELMET = ITEMS.register("skeleton_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SKELETONSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/skeleton_helmet.png", "geo/skeleton_helmet.geo.json"));
    public static final RegistryObject<Item> SKELETON_CHESTPLATE = ITEMS.register("skeleton_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SKELETONSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SKELETON_LEGGINGS = ITEMS.register("skeleton_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SKELETONSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SKELETON_BOOTS = ITEMS.register("skeleton_boots",
            () -> new KQArmorItem(KQArmorMaterials.SKELETONSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SPIDER_HELMET = ITEMS.register("spider_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SPIDERSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/spider_helmet.png", "geo/spider_helmet.geo.json"));
    public static final RegistryObject<Item> SPIDER_CHESTPLATE = ITEMS.register("spider_chestplate",
            () -> new GeoItemArmorChest(KQArmorMaterials.SPIDERSET, EquipmentSlot.CHEST,new Item.Properties()
                    , "textures/armor/spider_main.png", "geo/spider_main.geo.json"));
    public static final RegistryObject<Item> SPIDER_LEGGINGS = ITEMS.register("spider_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SPIDERSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SPIDER_BOOTS = ITEMS.register("spider_boots",
            () -> new KQArmorItem(KQArmorMaterials.SPIDERSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> WARLORD_HELMET = ITEMS.register("warlord_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.WARLORDSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/warlord_helmet.png", "geo/warlord_helmet.geo.json"));
    public static final RegistryObject<Item> WARLORD_CHESTPLATE = ITEMS.register("warlord_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.WARLORDSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> WARLORD_LEGGINGS = ITEMS.register("warlord_leggings",
            () -> new KQArmorItem(KQArmorMaterials.WARLORDSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> WARLORD_BOOTS = ITEMS.register("warlord_boots",
            () -> new KQArmorItem(KQArmorMaterials.WARLORDSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> STRAWHAT_HELMET = ITEMS.register("strawhat_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.STRAWHATSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/strawhat_helmet.png", "geo/strawhat_helmet.geo.json"));
    public static final RegistryObject<Item> STRAWHAT_CHESTPLATE = ITEMS.register("strawhat_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.STRAWHATSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> STRAWHAT_LEGGINGS = ITEMS.register("strawhat_leggings",
            () -> new KQArmorItem(KQArmorMaterials.STRAWHATSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> STRAWHAT_BOOTS = ITEMS.register("strawhat_boots",
            () -> new KQArmorItem(KQArmorMaterials.STRAWHATSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> PIRATE_HELMET = ITEMS.register("pirate_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.PIRATESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/pirate_helmet.png", "geo/pirate_helmet.geo.json"));
    public static final RegistryObject<Item> PIRATE2_HELMET = ITEMS.register("pirate2_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.PIRATESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/pirate2_helmet.png", "geo/pirate2_helmet.geo.json"));
    public static final RegistryObject<Item> PIRATE3_HELMET = ITEMS.register("pirate3_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.PIRATESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/pirate3_helmet.png", "geo/pirate3_helmet.geo.json"));
    public static final RegistryObject<Item> PIRATE_CHESTPLATE = ITEMS.register("pirate_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.PIRATESET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> PIRATE_LEGGINGS = ITEMS.register("pirate_leggings",
            () -> new KQArmorItem(KQArmorMaterials.PIRATESET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> PIRATE_BOOTS = ITEMS.register("pirate_boots",
            () -> new KQArmorItem(KQArmorMaterials.PIRATESET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> CONQUISTADOR_HELMET = ITEMS.register("conquistador_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.CONQUISTADORSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/conquistador_helmet.png", "geo/conquistador_helmet.geo.json"));
    public static final RegistryObject<Item> CONQUISTADOR2_HELMET = ITEMS.register("conquistador2_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.CONQUISTADORSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/conquistador2_helmet.png", "geo/conquistador2_helmet.geo.json"));
    public static final RegistryObject<Item> CONQUISTADOR3_HELMET = ITEMS.register("conquistador3_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.CONQUISTADORSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/conquistador3_helmet.png", "geo/conquistador3_helmet.geo.json"));
    public static final RegistryObject<Item> CONQUISTADOR_CHESTPLATE = ITEMS.register("conquistador_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.CONQUISTADORSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> CONQUISTADOR_LEGGINGS = ITEMS.register("conquistador_leggings",
            () -> new KQArmorItem(KQArmorMaterials.CONQUISTADORSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> CONQUISTADOR_BOOTS = ITEMS.register("conquistador_boots",
            () -> new KQArmorItem(KQArmorMaterials.CONQUISTADORSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> ZOMBIE_HELMET = ITEMS.register("zombie_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.ZOMBIESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/zombie_helmet.png", "geo/zombie_helmet.geo.json"));
    public static final RegistryObject<Item> ZOMBIE_HELMET2 = ITEMS.register("zombie_helmet2",
            () -> new GeoItemArmor(KQArmorMaterials.ZOMBIESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/zombie_helmet2.png", "geo/zombie_helmet2.geo.json"));
    public static final RegistryObject<Item> ZOMBIE_CHESTPLATE = ITEMS.register("zombie_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.ZOMBIESET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> ZOMBIE_LEGGINGS = ITEMS.register("zombie_leggings",
            () -> new KQArmorItem(KQArmorMaterials.ZOMBIESET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> ZOMBIE_BOOTS = ITEMS.register("zombie_boots",
            () -> new KQArmorItem(KQArmorMaterials.ZOMBIESET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> HUSK_HELMET = ITEMS.register("husk_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.HUSKSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/husk_helmet.png", "geo/husk_helmet.geo.json"));
    public static final RegistryObject<Item> HUSK_HELMET2 = ITEMS.register("husk_helmet2",
            () -> new GeoItemArmor(KQArmorMaterials.HUSKSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/husk_helmet2.png", "geo/husk_helmet2.geo.json"));
    public static final RegistryObject<Item> HUSK_HELMET3 = ITEMS.register("husk_helmet3",
            () -> new GeoItemArmor(KQArmorMaterials.HUSKSET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/husk_helmet3.png", "geo/husk_helmet3.geo.json"));
    public static final RegistryObject<Item> HUSK_CHESTPLATE = ITEMS.register("husk_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.HUSKSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> HUSK_LEGGINGS = ITEMS.register("husk_leggings",
            () -> new KQArmorItem(KQArmorMaterials.HUSKSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> HUSK_BOOTS = ITEMS.register("husk_boots",
            () -> new KQArmorItem(KQArmorMaterials.HUSKSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> WITHER_HELMET = ITEMS.register("wither_helmet",
            () -> new KQArmorItem(KQArmorMaterials.WITHERSET, EquipmentSlot.HEAD,new Item.Properties()));
    public static final RegistryObject<Item> WITHER_CHESTPLATE = ITEMS.register("wither_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.WITHERSET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> WITHER_LEGGINGS = ITEMS.register("wither_leggings",
            () -> new KQArmorItem(KQArmorMaterials.WITHERSET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> WITHER_BOOTS = ITEMS.register("wither_boots",
            () -> new KQArmorItem(KQArmorMaterials.WITHERSET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SQUIRE_HELMET = ITEMS.register("squire_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SQUIRESET, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/squire_helmet.png", "geo/squire_helmet.geo.json"));
    public static final RegistryObject<Item> SQUIRE_CHESTPLATE = ITEMS.register("squire_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SQUIRESET, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SQUIRE_LEGGINGS = ITEMS.register("squire_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SQUIRESET, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SQUIRE_BOOTS = ITEMS.register("squire_boots",
            () -> new KQArmorItem(KQArmorMaterials.SQUIRESET, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> CHAINMAIL_HELMET = ITEMS.register("chainmail_helmet",
            () -> new ArmorItem(KQArmorMaterials.CHAINMAIL, EquipmentSlot.HEAD,new Item.Properties()));
    public static final RegistryObject<Item> CHAINMAIL_HELMET2 = ITEMS.register("chainmail_helmet2",
            () -> new GeoItemArmor(KQArmorMaterials.CHAINMAIL, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/chainmail_helmet2.png", "geo/chainmail_helmet2.geo.json"));
    public static final RegistryObject<Item> TUNIC_BLUE_LEGGINGS = ITEMS.register("tunic_blue_leggings",
            () -> new ArmorItem(KQArmorMaterials.TUNIC_BLUE, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> TUNIC_GREEN_LEGGINGS = ITEMS.register("tunic_green_leggings",
            () -> new ArmorItem(KQArmorMaterials.TUNIC_GREEN, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> TUNIC_YELLOW_LEGGINGS = ITEMS.register("tunic_yellow_leggings",
            () -> new ArmorItem(KQArmorMaterials.TUNIC_YELLOW, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> TUNIC_RED_LEGGINGS = ITEMS.register("tunic_red_leggings",
            () -> new ArmorItem(KQArmorMaterials.TUNIC_RED, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> TUNIC_SEA_LEGGINGS = ITEMS.register("tunic_sea_leggings",
            () -> new ArmorItem(KQArmorMaterials.TUNIC_SEA, EquipmentSlot.LEGS,new Item.Properties()));

    public static final RegistryObject<Item> WITCH_HELMET = ITEMS.register("witch_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.WITCH, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/witch_helmet.png", "geo/witch_helmet.geo.json"));
    public static final RegistryObject<Item> WITCH_CHESTPLATE = ITEMS.register("witch_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.WITCH, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> WITCH_LEGGINGS = ITEMS.register("witch_leggings",
            () -> new KQArmorItem(KQArmorMaterials.WITCH, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> WITCH_BOOTS = ITEMS.register("witch_boots",
            () -> new KQArmorItem(KQArmorMaterials.WITCH, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> POLAR_HELMET = ITEMS.register("polar_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.POLAR, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/polar_helmet.png", "geo/polar_helmet.geo.json"));
    public static final RegistryObject<Item> POLAR_CHESTPLATE = ITEMS.register("polar_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.POLAR, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> POLAR_LEGGINGS = ITEMS.register("polar_leggings",
            () -> new KQArmorItem(KQArmorMaterials.POLAR, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> POLAR_BOOTS = ITEMS.register("polar_boots",
            () -> new KQArmorItem(KQArmorMaterials.POLAR, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SHINOBI_HELMET = ITEMS.register("shinobi_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SHINOBI, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/shinobi_helmet.png", "geo/shinobi_helmet.geo.json"));
    public static final RegistryObject<Item> SHINOBI_CHESTPLATE = ITEMS.register("shinobi_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SHINOBI, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SHINOBI_LEGGINGS = ITEMS.register("shinobi_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SHINOBI, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SHINOBI_BOOTS = ITEMS.register("shinobi_boots",
            () -> new KQArmorItem(KQArmorMaterials.SHINOBI, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> SKULK_HELMET = ITEMS.register("skulk_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SKULK, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/skulk_helmet.png", "geo/skulk_helmet.geo.json"));
    public static final RegistryObject<Item> SKULK2_HELMET = ITEMS.register("skulk2_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SKULK, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/skulk2_helmet.png", "geo/skulk2_helmet.geo.json"));
    public static final RegistryObject<Item> SKULK3_HELMET = ITEMS.register("skulk3_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SKULK, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/skulk3_helmet.png", "geo/skulk3_helmet.geo.json"));
    public static final RegistryObject<Item> SKULK4_HELMET = ITEMS.register("skulk4_helmet",
            () -> new GeoItemArmor(KQArmorMaterials.SKULK, EquipmentSlot.HEAD,new Item.Properties()
                    , "textures/armor/skulk4_helmet.png", "geo/skulk4_helmet.geo.json"));
    public static final RegistryObject<Item> SKULK_CHESTPLATE = ITEMS.register("skulk_chestplate",
            () -> new KQArmorItem(KQArmorMaterials.SKULK, EquipmentSlot.CHEST,new Item.Properties()));
    public static final RegistryObject<Item> SKULK_LEGGINGS = ITEMS.register("skulk_leggings",
            () -> new KQArmorItem(KQArmorMaterials.SKULK, EquipmentSlot.LEGS,new Item.Properties()));
    public static final RegistryObject<Item> SKULK_BOOTS = ITEMS.register("skulk_boots",
            () -> new KQArmorItem(KQArmorMaterials.SKULK, EquipmentSlot.FEET,new Item.Properties()));

    public static final RegistryObject<Item> GREMLIN_EGG = ITEMS.register("gremlin_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.GREMLIN, 0xc22f26, 0x45545d, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> ELD_BOMB_EGG = ITEMS.register("eldbomb_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.ELDBOMB, 0x43404e, 0x81da25, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> ELD_KNIGHT_EGG = ITEMS.register("eldknight_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.ELDKINGHT, 0x7f8ab2, 0x8a392e, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> RATMAN_EGG = ITEMS.register("ratman_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.RATMAN, 0x3a303d, 0xb75383, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SAMHAIN_EGG = ITEMS.register("samhain_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.SAMHAIN, 0xfdde03, 0x982938, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> SWAMPMAN_EGG = ITEMS.register("swampman_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.SWAMPMAN, 0x108773, 0x9e304f, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LIZZY_EGG = ITEMS.register("lizzy_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.LIZZY, 0x0babf2, 0xd1802b, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> BADPATCH_EGG = ITEMS.register("bad_patch_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.BADPATCH, 0xec160b, 0xeff1f8, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> GHOSTY_EGG = ITEMS.register("ghosty_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.GHOSTY, 0x2cb87e, 0xfbe105, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> MOMMA_LIZZY_EGG = ITEMS.register("momma_lizzy_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.MOMMA_LIZZY, 0x0babf2, 0x9f5b14, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> GHASTLING_EGG = ITEMS.register("ghastling_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.SHIELD, 0x930c13, 0xfb9600, new Item.Properties().tab(KnightQuest.CREATIVE_MODE_TAB)){
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.ghastling_spawn_egg"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
    
}
