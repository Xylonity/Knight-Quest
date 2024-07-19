package net.xylonity.knightquest.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.client.GeoItemArmor;
import net.xylonity.knightquest.common.item.KQArmorItem;
import net.xylonity.knightquest.common.item.KQItem;
import net.xylonity.knightquest.common.material.KQArmorMaterials;
import net.xylonity.knightquest.common.material.KQItemMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KnightQuestItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, KnightQuest.MOD_ID);
    private static final String TOOLTIP_ITEM_PATH = "tooltip.item.knightquest.";

    private static RegistryObject<Item> registerGeoArmorItem(String name, KQArmorMaterials material, ArmorItem.Type type, boolean hasTooltip ,boolean hasExtraTooltip) {
        if (hasExtraTooltip)
            return ITEMS.register(name, () -> new GeoItemArmor(material, type, new Item.Properties(), name, hasTooltip) {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + name));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
        else
            return ITEMS.register(name, () -> new GeoItemArmor(material, type, new Item.Properties(), name, hasTooltip));
    }

    private static RegistryObject<Item> registerSwordItem(String name, Tier material, float speedModifier, boolean hasTooltip) {
        if (hasTooltip)
            return ITEMS.register(name, () -> new SwordItem(material, 4, speedModifier, new Item.Properties()) {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + name));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
        else
            return ITEMS.register(name, () -> new SwordItem(material, 4, speedModifier, new Item.Properties()));
    }

    private static RegistryObject<Item> registerArmorItem(String name, KQArmorMaterials material, ArmorItem.Type type, boolean hasTooltip) {
        return ITEMS.register(name, () -> new KQArmorItem(material, type, new Item.Properties(), hasTooltip));
    }

    private static <X extends Mob> RegistryObject<Item> registerSpawnEggItem(String name, RegistryObject<EntityType<X>> entity, int backgroundColor, int highlightColor) {
        return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, backgroundColor, highlightColor, new Item.Properties()));
    }

    private static RegistryObject<Item> registerItem(String name) {
        return ITEMS.register(name, () -> new KQItem(new Item.Properties(), name));
    }

    public static final RegistryObject<Item> GREAT_ESSENCE = registerItem("great_essence");
    public static final RegistryObject<Item> SMALL_ESSENCE = registerItem("small_essence");
    public static final RegistryObject<Item> EMPTY_GOBLET = registerItem("empty_goblet");
    public static final RegistryObject<Item> FILLED_GOBLET = registerItem("filled_goblet");
    public static final RegistryObject<Item> RATMAN_EYE = registerItem("ratman_eye");
    public static final RegistryObject<Item> LIZZY_SCALE = registerItem("lizzy_scale");

    public static final RegistryObject<Item> PALADIN_SWORD = registerSwordItem("paladin_sword", KQItemMaterials.PALADIN, -2.8f, true);
    public static final RegistryObject<Item> NAIL_SWORD = registerSwordItem("nail_glaive", KQItemMaterials.NAIL, -2.6f, false);
    public static final RegistryObject<Item> UCHIGATANA = registerSwordItem("uchigatana_katana", KQItemMaterials.UCHIGATANA, -2.2f, false);
    public static final RegistryObject<Item> KUKRI = registerSwordItem("kukri_dagger", KQItemMaterials.KUKRI, -1f, false);
    public static final RegistryObject<Item> KHOPESH = registerSwordItem("khopesh_claymore", KQItemMaterials.KHOPESH, -2.2f, false);
    public static final RegistryObject<Item> CLEAVER = registerSwordItem("cleaver_heavy_axe", KQItemMaterials.CLEAVER, -3f, false);
    public static final RegistryObject<Item> CRIMSON_SWORD = registerSwordItem("crimson_sword", KQItemMaterials.CRIMSON_SWORD, -2f, false);
    public static final RegistryObject<Item> WATER_SWORD = registerSwordItem("water_sword", KQItemMaterials.WATER_SWORD, -2f, false);
    public static final RegistryObject<Item> STEEL_SWORD = registerSwordItem("steel_sword", KQItemMaterials.STEEL_SWORD, -2f, false);

    public static final RegistryObject<Item> WATER_AXE = ITEMS.register("water_axe", () -> new AxeItem(KQItemMaterials.WATER_AXE, 4, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(KQItemMaterials.STEEL_AXE, 4, -2.8f, new Item.Properties()));

    public static final RegistryObject<Item> APPLE_HELMET = registerGeoArmorItem("apple_helmet", KQArmorMaterials.APPLE_SET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> APPLE_CHESTPLATE = registerArmorItem("apple_chestplate", KQArmorMaterials.APPLE_SET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> APPLE_LEGGINGS = registerArmorItem("apple_leggings", KQArmorMaterials.APPLE_SET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> APPLE_BOOTS = registerArmorItem("apple_boots", KQArmorMaterials.APPLE_SET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> BAMBOO_BLUE_HELMET = registerGeoArmorItem("bamboo_blue_helmet", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BAMBOO_BLUE_CHESTPLATE = registerArmorItem("bamboo_blue_chestplate", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> BAMBOO_BLUE_LEGGINGS = registerArmorItem("bamboo_blue_leggings", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> BAMBOO_BLUE_BOOTS = registerArmorItem("bamboo_blue_boots", KQArmorMaterials.BAMBOOSET_BLUE, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> BAMBOO_GREEN_HELMET = registerGeoArmorItem("bamboo_green_helmet", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BAMBOO_GREEN_CHESTPLATE = registerArmorItem("bamboo_green_chestplate", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> BAMBOO_GREEN_LEGGINGS = registerArmorItem("bamboo_green_leggings", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> BAMBOO_GREEN_BOOTS = registerArmorItem("bamboo_green_boots", KQArmorMaterials.BAMBOOSET_GREEN, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> TENGU_HELMET = registerGeoArmorItem("tengu_helmet", KQArmorMaterials.TENGU, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BAMBOO_HELMET = registerGeoArmorItem("bamboo_helmet", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BAMBOO_CHESTPLATE = registerArmorItem("bamboo_chestplate", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> BAMBOO_LEGGINGS = registerArmorItem("bamboo_leggings", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> BAMBOO_BOOTS = registerArmorItem("bamboo_boots", KQArmorMaterials.BAMBOOSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> BAT_HELMET = registerGeoArmorItem("bat_helmet", KQArmorMaterials.BATSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BAT_CHESTPLATE = registerArmorItem("bat_chestplate", KQArmorMaterials.BATSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> BAT_LEGGINGS = registerArmorItem("bat_leggings", KQArmorMaterials.BATSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> BAT_BOOTS = registerArmorItem("bat_boots", KQArmorMaterials.BATSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> BLAZE_HELMET = registerGeoArmorItem("blaze_helmet", KQArmorMaterials.BLAZESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BLAZE_CHESTPLATE = registerArmorItem("blaze_chestplate", KQArmorMaterials.BLAZESET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> BLAZE_LEGGINGS = registerArmorItem("blaze_leggings", KQArmorMaterials.BLAZESET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> BLAZE_BOOTS = registerArmorItem("blaze_boots", KQArmorMaterials.BLAZESET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> BOW_HELMET = registerGeoArmorItem("bow_helmet", KQArmorMaterials.BOWSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> BOW_CHESTPLATE = registerArmorItem("bow_chestplate", KQArmorMaterials.BOWSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> BOW_LEGGINGS = registerArmorItem("bow_leggings", KQArmorMaterials.BOWSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> BOW_BOOTS = registerArmorItem("bow_boots", KQArmorMaterials.BOWSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> HORN_HELMET = registerGeoArmorItem("horn_helmet", KQArmorMaterials.HORNSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> HORN_CHESTPLATE = registerArmorItem("horn_chestplate", KQArmorMaterials.HORNSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> HORN_LEGGINGS = registerArmorItem("horn_leggings", KQArmorMaterials.HORNSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> HORN_BOOTS = registerArmorItem("horn_boots", KQArmorMaterials.HORNSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> CREEPER_HELMET = registerArmorItem("creeper_helmet", KQArmorMaterials.CREEPERSET, ArmorItem.Type.HELMET, true);
    public static final RegistryObject<Item> CREEPER_CHESTPLATE = registerArmorItem("creeper_chestplate", KQArmorMaterials.CREEPERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> CREEPER_LEGGINGS = registerArmorItem("creeper_leggings", KQArmorMaterials.CREEPERSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> CREEPER_BOOTS = registerArmorItem("creeper_boots", KQArmorMaterials.CREEPERSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> DEEPSLATE_HELMET = registerGeoArmorItem("deepslate_helmet", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> DEEPSLATE_CHESTPLATE = registerArmorItem("deepslate_chestplate", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> DEEPSLATE_LEGGINGS = registerArmorItem("deepslate_leggings", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> DEEPSLATE_BOOTS = registerArmorItem("deepslate_boots", KQArmorMaterials.DEEPSLATESET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> DRAGON_HELMET = registerGeoArmorItem("dragon_helmet", KQArmorMaterials.DRAGONSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> DRAGON_CHESTPLATE = registerArmorItem("dragon_chestplate", KQArmorMaterials.DRAGONSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> DRAGON_LEGGINGS = registerArmorItem("dragon_leggings", KQArmorMaterials.DRAGONSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> DRAGON_BOOTS = registerArmorItem("dragon_boots", KQArmorMaterials.DRAGONSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> ENDERMAN_HELMET = registerGeoArmorItem("enderman_helmet", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> ENDERMAN_CHESTPLATE = registerArmorItem("enderman_chestplate", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> ENDERMAN_LEGGINGS = registerArmorItem("enderman_leggings", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> ENDERMAN_BOOTS = registerArmorItem("enderman_boots", KQArmorMaterials.ENDERMANSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> EVOKER_HELMET = registerGeoArmorItem("evoker_helmet", KQArmorMaterials.EVOKERSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> EVOKER_CHESTPLATE = registerArmorItem("evoker_chestplate", KQArmorMaterials.EVOKERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> EVOKER_LEGGINGS = registerArmorItem("evoker_leggings", KQArmorMaterials.EVOKERSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> EVOKER_BOOTS = registerArmorItem("evoker_boots", KQArmorMaterials.EVOKERSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> FORZE_HELMET = registerGeoArmorItem("forze_helmet", KQArmorMaterials.FORZESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> FORZE_CHESTPLATE = registerGeoArmorItem("forze_chestplate", KQArmorMaterials.FORZESET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final RegistryObject<Item> FORZE_LEGGINGS = registerArmorItem("forze_leggings", KQArmorMaterials.FORZESET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> FORZE_BOOTS = registerArmorItem("forze_boots", KQArmorMaterials.FORZESET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> HOLLOW_HELMET = registerGeoArmorItem("hollow_helmet", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> HOLLOW_CHESTPLATE = registerArmorItem("hollow_chestplate", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> HOLLOW_LEGGINGS = registerArmorItem("hollow_leggings", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> HOLLOW_BOOTS = registerArmorItem("hollow_boots", KQArmorMaterials.HOLLOWSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> NETHER_HELMET = registerGeoArmorItem("nether_helmet", KQArmorMaterials.NETHERSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> NETHER_CHESTPLATE = registerArmorItem("nether_chestplate", KQArmorMaterials.NETHERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> NETHER_LEGGINGS = registerArmorItem("nether_leggings", KQArmorMaterials.NETHERSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> NETHER_BOOTS = registerArmorItem("nether_boots", KQArmorMaterials.NETHERSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> VETERAN_HELMET = registerGeoArmorItem("veteran_helmet", KQArmorMaterials.VETERANSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> VETERAN_CHESTPLATE = registerGeoArmorItem("veteran_chestplate", KQArmorMaterials.VETERANSET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final RegistryObject<Item> VETERAN_LEGGINGS = registerGeoArmorItem("veteran_leggings", KQArmorMaterials.VETERANSET, ArmorItem.Type.LEGGINGS, true, false);
    public static final RegistryObject<Item> VETERAN_BOOTS = registerArmorItem("veteran_boots", KQArmorMaterials.VETERANSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> PATH_HELMET = registerGeoArmorItem("path_helmet", KQArmorMaterials.PATHSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> PATH_CHESTPLATE = registerArmorItem("path_chestplate", KQArmorMaterials.PATHSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> PATH_LEGGINGS = registerArmorItem("path_leggings", KQArmorMaterials.PATHSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> PATH_BOOTS = registerArmorItem("path_boots", KQArmorMaterials.PATHSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> PHANTOM_HELMET = registerGeoArmorItem("phantom_helmet", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> PHANTOM_CHESTPLATE = registerArmorItem("phantom_chestplate", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> PHANTOM_LEGGINGS = registerArmorItem("phantom_leggings", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> PHANTOM_BOOTS = registerArmorItem("phantom_boots", KQArmorMaterials.PHANTOMSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SEA_HELMET = registerGeoArmorItem("sea_helmet", KQArmorMaterials.SEASET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SEA_CHESTPLATE = registerArmorItem("sea_chestplate", KQArmorMaterials.SEASET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SEA_LEGGINGS = registerArmorItem("sea_leggings", KQArmorMaterials.SEASET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SEA_BOOTS = registerArmorItem("sea_boots", KQArmorMaterials.SEASET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SHIELD_HELMET = registerGeoArmorItem("shield_helmet", KQArmorMaterials.SHIELDSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SHIELD_CHESTPLATE = registerArmorItem("shield_chestplate", KQArmorMaterials.SHIELDSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SHIELD_LEGGINGS = registerArmorItem("shield_leggings", KQArmorMaterials.SHIELDSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SHIELD_BOOTS = registerArmorItem("shield_boots", KQArmorMaterials.SHIELDSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SILVER_HELMET = registerGeoArmorItem("silver_helmet", KQArmorMaterials.SILVERSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SILVER_CHESTPLATE = registerGeoArmorItem("silver_chestplate", KQArmorMaterials.SILVERSET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final RegistryObject<Item> SILVER_LEGGINGS = registerArmorItem("silver_leggings", KQArmorMaterials.SILVERSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SILVER_BOOTS = registerArmorItem("silver_boots", KQArmorMaterials.SILVERSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SILVERFISH_HELMET = registerGeoArmorItem("silverfish_helmet", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SILVERFISH_CHESTPLATE = registerArmorItem("silverfish_chestplate", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SILVERFISH_LEGGINGS = registerArmorItem("silverfish_leggings", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SILVERFISH_BOOTS = registerArmorItem("silverfish_boots", KQArmorMaterials.SILVERFISHSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SKELETON_HELMET = registerGeoArmorItem("skeleton_helmet", KQArmorMaterials.SKELETONSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SKELETON_CHESTPLATE = registerArmorItem("skeleton_chestplate", KQArmorMaterials.SKELETONSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SKELETON_LEGGINGS = registerArmorItem("skeleton_leggings", KQArmorMaterials.SKELETONSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SKELETON_BOOTS = registerArmorItem("skeleton_boots", KQArmorMaterials.SKELETONSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SPIDER_HELMET = registerGeoArmorItem("spider_helmet", KQArmorMaterials.SPIDERSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SPIDER_CHESTPLATE = registerGeoArmorItem("spider_chestplate", KQArmorMaterials.SPIDERSET, ArmorItem.Type.CHESTPLATE, true, false);
    public static final RegistryObject<Item> SPIDER_LEGGINGS = registerArmorItem("spider_leggings", KQArmorMaterials.SPIDERSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SPIDER_BOOTS = registerArmorItem("spider_boots", KQArmorMaterials.SPIDERSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> WARLORD_HELMET = registerGeoArmorItem("warlord_helmet", KQArmorMaterials.WARLORDSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> WARLORD_CHESTPLATE = registerArmorItem("warlord_chestplate", KQArmorMaterials.WARLORDSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> WARLORD_LEGGINGS = registerArmorItem("warlord_leggings", KQArmorMaterials.WARLORDSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> WARLORD_BOOTS = registerArmorItem("warlord_boots", KQArmorMaterials.WARLORDSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> STRAWHAT_HELMET = registerGeoArmorItem("strawhat_helmet", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> STRAWHAT_CHESTPLATE = registerArmorItem("strawhat_chestplate", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> STRAWHAT_LEGGINGS = registerArmorItem("strawhat_leggings", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> STRAWHAT_BOOTS = registerArmorItem("strawhat_boots", KQArmorMaterials.STRAWHATSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> PIRATE_HELMET = registerGeoArmorItem("pirate_helmet", KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> PIRATE2_HELMET = registerGeoArmorItem("pirate2_helmet", KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> PIRATE3_HELMET = registerGeoArmorItem("pirate3_helmet", KQArmorMaterials.PIRATESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> PIRATE_CHESTPLATE = registerArmorItem("pirate_chestplate", KQArmorMaterials.PIRATESET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> PIRATE_LEGGINGS = registerArmorItem("pirate_leggings", KQArmorMaterials.PIRATESET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> PIRATE_BOOTS = registerArmorItem("pirate_boots", KQArmorMaterials.PIRATESET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> CONQUISTADOR_HELMET = registerGeoArmorItem("conquistador_helmet", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> CONQUISTADOR2_HELMET = registerGeoArmorItem("conquistador2_helmet", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> CONQUISTADOR3_HELMET = registerGeoArmorItem("conquistador3_helmet", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> CONQUISTADOR_CHESTPLATE = registerArmorItem("conquistador_chestplate", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> CONQUISTADOR_LEGGINGS = registerArmorItem("conquistador_leggings", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> CONQUISTADOR_BOOTS = registerArmorItem("conquistador_boots", KQArmorMaterials.CONQUISTADORSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> ZOMBIE_HELMET = registerGeoArmorItem("zombie_helmet", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> ZOMBIE_HELMET2 = registerGeoArmorItem("zombie_helmet2", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> ZOMBIE_CHESTPLATE = registerArmorItem("zombie_chestplate", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> ZOMBIE_LEGGINGS = registerArmorItem("zombie_leggings", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> ZOMBIE_BOOTS = registerArmorItem("zombie_boots", KQArmorMaterials.ZOMBIESET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> HUSK_HELMET = registerGeoArmorItem("husk_helmet", KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> HUSK_HELMET2 = registerGeoArmorItem("husk_helmet2", KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> HUSK_HELMET3 = registerGeoArmorItem("husk_helmet3", KQArmorMaterials.HUSKSET, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> HUSK_CHESTPLATE = registerArmorItem("husk_chestplate", KQArmorMaterials.HUSKSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> HUSK_LEGGINGS = registerArmorItem("husk_leggings", KQArmorMaterials.HUSKSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> HUSK_BOOTS = registerArmorItem("husk_boots", KQArmorMaterials.HUSKSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> WITHER_HELMET = registerArmorItem("wither_helmet", KQArmorMaterials.WITHERSET, ArmorItem.Type.HELMET, true);
    public static final RegistryObject<Item> WITHER_CHESTPLATE = registerArmorItem("wither_chestplate", KQArmorMaterials.WITHERSET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> WITHER_LEGGINGS = registerArmorItem("wither_leggings", KQArmorMaterials.WITHERSET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> WITHER_BOOTS = registerArmorItem("wither_boots", KQArmorMaterials.WITHERSET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SQUIRE_HELMET = registerGeoArmorItem("squire_helmet", KQArmorMaterials.SQUIRESET, ArmorItem.Type.HELMET, true, true);
    public static final RegistryObject<Item> SQUIRE_CHESTPLATE = registerArmorItem("squire_chestplate", KQArmorMaterials.SQUIRESET, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SQUIRE_LEGGINGS = registerArmorItem("squire_leggings", KQArmorMaterials.SQUIRESET, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SQUIRE_BOOTS = registerArmorItem("squire_boots", KQArmorMaterials.SQUIRESET, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> CHAINMAIL_HELMET = registerArmorItem("chainmail_helmet", KQArmorMaterials.CHAINMAIL, ArmorItem.Type.HELMET, true);
    public static final RegistryObject<Item> CHAINMAIL_HELMET2 = registerGeoArmorItem("chainmail_helmet2", KQArmorMaterials.CHAINMAIL, ArmorItem.Type.HELMET, false, false);
    public static final RegistryObject<Item> TUNIC_BLUE_LEGGINGS = registerArmorItem("tunic_blue_leggings", KQArmorMaterials.TUNIC_BLUE, ArmorItem.Type.LEGGINGS, false);
    public static final RegistryObject<Item> TUNIC_GREEN_LEGGINGS = registerArmorItem("tunic_green_leggings", KQArmorMaterials.TUNIC_GREEN, ArmorItem.Type.LEGGINGS, false);
    public static final RegistryObject<Item> TUNIC_YELLOW_LEGGINGS = registerArmorItem("tunic_yellow_leggings", KQArmorMaterials.TUNIC_YELLOW, ArmorItem.Type.LEGGINGS, false);
    public static final RegistryObject<Item> TUNIC_RED_LEGGINGS = registerArmorItem("tunic_red_leggings", KQArmorMaterials.TUNIC_RED, ArmorItem.Type.LEGGINGS, false);
    public static final RegistryObject<Item> TUNIC_SEA_LEGGINGS = registerArmorItem("tunic_sea_leggings", KQArmorMaterials.TUNIC_SEA, ArmorItem.Type.LEGGINGS, false);

    public static final RegistryObject<Item> WITCH_HELMET = registerGeoArmorItem("witch_helmet", KQArmorMaterials.WITCH, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> WITCH_CHESTPLATE = registerArmorItem("witch_chestplate", KQArmorMaterials.WITCH, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> WITCH_LEGGINGS = registerArmorItem("witch_leggings", KQArmorMaterials.WITCH, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> WITCH_BOOTS = registerArmorItem("witch_boots", KQArmorMaterials.WITCH, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> POLAR_HELMET = registerGeoArmorItem("polar_helmet", KQArmorMaterials.POLAR, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> POLAR_CHESTPLATE = registerArmorItem("polar_chestplate", KQArmorMaterials.POLAR, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> POLAR_LEGGINGS = registerArmorItem("polar_leggings", KQArmorMaterials.POLAR, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> POLAR_BOOTS = registerArmorItem("polar_boots", KQArmorMaterials.POLAR, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SHINOBI_HELMET = registerGeoArmorItem("shinobi_helmet", KQArmorMaterials.SHINOBI, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SHINOBI_CHESTPLATE = registerArmorItem("shinobi_chestplate", KQArmorMaterials.SHINOBI, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SHINOBI_LEGGINGS = registerArmorItem("shinobi_leggings", KQArmorMaterials.SHINOBI, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SHINOBI_BOOTS = registerArmorItem("shinobi_boots", KQArmorMaterials.SHINOBI, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> SKULK_HELMET = registerGeoArmorItem("skulk_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SKULK2_HELMET = registerGeoArmorItem("skulk2_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SKULK3_HELMET = registerGeoArmorItem("skulk3_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SKULK4_HELMET = registerGeoArmorItem("skulk4_helmet", KQArmorMaterials.SKULK, ArmorItem.Type.HELMET, true, false);
    public static final RegistryObject<Item> SKULK_CHESTPLATE = registerArmorItem("skulk_chestplate", KQArmorMaterials.SKULK, ArmorItem.Type.CHESTPLATE, true);
    public static final RegistryObject<Item> SKULK_LEGGINGS = registerArmorItem("skulk_leggings", KQArmorMaterials.SKULK, ArmorItem.Type.LEGGINGS, true);
    public static final RegistryObject<Item> SKULK_BOOTS = registerArmorItem("skulk_boots", KQArmorMaterials.SKULK, ArmorItem.Type.BOOTS, true);

    public static final RegistryObject<Item> GREMLIN_EGG = registerSpawnEggItem("gremlin_spawn_egg", KnightQuestEntities.GREMLIN, 0xc22f26, 0x45545d);
    public static final RegistryObject<Item> ELD_BOMB_EGG = registerSpawnEggItem("eldbomb_spawn_egg", KnightQuestEntities.ELDBOMB, 0x43404e, 0x81da25);
    public static final RegistryObject<Item> ELD_KNIGHT_EGG = registerSpawnEggItem("eldknight_spawn_egg", KnightQuestEntities.ELDKINGHT, 0x7f8ab2, 0x8a392e);
    public static final RegistryObject<Item> RATMAN_EGG = registerSpawnEggItem("ratman_spawn_egg", KnightQuestEntities.RATMAN, 0x3a303d, 0xb75383);
    public static final RegistryObject<Item> SAMHAIN_EGG = registerSpawnEggItem("samhain_spawn_egg", KnightQuestEntities.SAMHAIN, 0xfdde03, 0x982938);
    public static final RegistryObject<Item> SWAMPMAN_EGG = registerSpawnEggItem("swampman_spawn_egg", KnightQuestEntities.SWAMPMAN, 0x108773, 0x9e304f);
    public static final RegistryObject<Item> LIZZY_EGG = registerSpawnEggItem("lizzy_spawn_egg", KnightQuestEntities.LIZZY, 0x0babf2, 0xd1802b);
    public static final RegistryObject<Item> BADPATCH_EGG = registerSpawnEggItem("bad_patch_spawn_egg", KnightQuestEntities.BADPATCH, 0xec160b, 0xeff1f8);
    public static final RegistryObject<Item> GHOSTY_EGG = registerSpawnEggItem("ghosty_spawn_egg", KnightQuestEntities.GHOSTY, 0x2cb87e, 0xfbe105);
    public static final RegistryObject<Item> MOMMA_LIZZY_EGG = registerSpawnEggItem("momma_lizzy_spawn_egg", KnightQuestEntities.MOMMA_LIZZY, 0x0babf2, 0x9f5b14);
    public static final RegistryObject<Item> GHASTLING_EGG = ITEMS.register("ghastling_spawn_egg",
            () -> new ForgeSpawnEggItem(KnightQuestEntities.SHIELD, 0x930c13, 0xfb9600, new Item.Properties()){
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.ghastling_spawn_egg"));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
    
}
