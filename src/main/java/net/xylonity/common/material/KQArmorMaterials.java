package net.xylonity.common.material;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.xylonity.KnightQuest;
import net.xylonity.registry.KnightQuestItems;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class KQArmorMaterials {

    public static final RegistryEntry<ArmorMaterial> APPLE_SET;
    public static final RegistryEntry<ArmorMaterial> BAMBOOSET_BLUE;
    public static final RegistryEntry<ArmorMaterial> BAMBOOSET_GREEN;
    public static final RegistryEntry<ArmorMaterial> BAMBOOSET;
    public static final RegistryEntry<ArmorMaterial> BATSET;
    public static final RegistryEntry<ArmorMaterial> BLAZESET;
    public static final RegistryEntry<ArmorMaterial> BOWSET;
    public static final RegistryEntry<ArmorMaterial> HORNSET;
    public static final RegistryEntry<ArmorMaterial> CREEPERSET;
    public static final RegistryEntry<ArmorMaterial> DEEPSLATESET;
    public static final RegistryEntry<ArmorMaterial> DRAGONSET;
    public static final RegistryEntry<ArmorMaterial> ENDERMANSET;
    public static final RegistryEntry<ArmorMaterial> EVOKERSET;
    public static final RegistryEntry<ArmorMaterial> FORZESET;
    public static final RegistryEntry<ArmorMaterial> HOLLOWSET;
    public static final RegistryEntry<ArmorMaterial> NETHERSET;
    public static final RegistryEntry<ArmorMaterial> VETERANSET;
    public static final RegistryEntry<ArmorMaterial> PATHSET;
    public static final RegistryEntry<ArmorMaterial> PHANTOMSET;
    public static final RegistryEntry<ArmorMaterial> SEASET;
    public static final RegistryEntry<ArmorMaterial> SHIELDSET;
    public static final RegistryEntry<ArmorMaterial> SILVERSET;
    public static final RegistryEntry<ArmorMaterial> SILVERFISHSET;
    public static final RegistryEntry<ArmorMaterial> SKELETONSET;
    public static final RegistryEntry<ArmorMaterial> SPIDERSET;
    public static final RegistryEntry<ArmorMaterial> WARLORDSET;
    public static final RegistryEntry<ArmorMaterial> STRAWHATSET;
    public static final RegistryEntry<ArmorMaterial> PIRATESET;
    public static final RegistryEntry<ArmorMaterial> CONQUISTADORSET;
    public static final RegistryEntry<ArmorMaterial> ZOMBIESET;
    public static final RegistryEntry<ArmorMaterial> HUSKSET;
    public static final RegistryEntry<ArmorMaterial> WITHERSET;
    public static final RegistryEntry<ArmorMaterial> SQUIRESET;
    public static final RegistryEntry<ArmorMaterial> TUNIC_BLUE;
    public static final RegistryEntry<ArmorMaterial> TUNIC_GREEN;
    public static final RegistryEntry<ArmorMaterial> TUNIC_YELLOW;
    public static final RegistryEntry<ArmorMaterial> TUNIC_RED;
    public static final RegistryEntry<ArmorMaterial> TUNIC_SEA;
    public static final RegistryEntry<ArmorMaterial> CHAINMAIL;
    public static final RegistryEntry<ArmorMaterial> WITCH;
    public static final RegistryEntry<ArmorMaterial> POLAR;
    public static final RegistryEntry<ArmorMaterial> SHINOBI;
    public static final RegistryEntry<ArmorMaterial> SKULK;
    public static final RegistryEntry<ArmorMaterial> TENGU;


    private static EnumMap<ArmorItem.Type, Integer> createArmorValuesMap(int boots, int leggings, int chestplate, int helmet) {
        EnumMap<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.BOOTS, boots);
        map.put(ArmorItem.Type.LEGGINGS, leggings);
        map.put(ArmorItem.Type.CHESTPLATE, chestplate);
        map.put(ArmorItem.Type.HELMET, helmet);
        return map;
    }

    static {

        APPLE_SET = register("appleset", createArmorValuesMap(3, 6, 8, 3), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4F, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "appleset"), "", true)));

        BAMBOOSET_BLUE = register("bamboo_blue", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "bamboo_blue"), "", true)));

        BAMBOOSET_GREEN = register("bamboo_green", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "bamboo_green"), "", true)));

        BAMBOOSET = register("bamboo", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "bamboo"), "", true)));

        BATSET = register("bat", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "bat"), "", true)));

        BLAZESET = register("blaze", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "blaze"), "", true)));

        BOWSET = register("bow", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "bow"), "", true)));

        HORNSET = register("horn", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "horn"), "", true)));

        CREEPERSET = register("creeper", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "creeper"), "", true)));

        DEEPSLATESET = register("deepslate", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "deepslate"), "", true)));

        DRAGONSET = register("dragon", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "dragon"), "", true)));

        ENDERMANSET = register("enderman", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "enderman"), "", true)));

        EVOKERSET = register("evoker", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "evoker"), "", true)));

        FORZESET = register("forze", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "forze"), "", true)));

        HOLLOWSET = register("hollow", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "hollow"), "", true)));

        NETHERSET = register("nether", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "nether"), "", true)));

        VETERANSET = register("veteran", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "veteran"), "", true)));

        PATHSET = register("path", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "path"), "", true)));

        PHANTOMSET = register("phantom", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "phantom"), "", true)));

        SEASET = register("sea", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "sea"), "", true)));

        SHIELDSET = register("shield", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "shield"), "", true)));

        SILVERSET = register("silver", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "silver"), "", true)));

        SILVERFISHSET = register("silverfish", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "silverfish"), "", true)));

        SKELETONSET = register("skeleton", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "skeleton"), "", true)));

        SPIDERSET = register("spider", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "spider"), "", true)));

        WARLORDSET = register("warlord", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "warlord"), "", true)));

        STRAWHATSET = register("strawhat", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "strawhat"), "", true)));

        PIRATESET = register("pirate", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "pirate"), "", true)));

        CONQUISTADORSET = register("conquistador", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "conquistador"), "", true)));

        ZOMBIESET = register("zombie", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "zombie"), "", true)));

        HUSKSET = register("husk", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "husk"), "", true)));

        WITHERSET = register("wither", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "wither"), "", true)));

        SQUIRESET = register("squire", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "squire"), "", true)));

        TUNIC_BLUE = register("tunic_blue", createArmorValuesMap(2, 5, 5, 2), 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "tunic_blue"), "", true)));

        TUNIC_GREEN = register("tunic_green", createArmorValuesMap(2, 5, 5, 2), 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "tunic_green"), "", true)));

        TUNIC_YELLOW = register("tunic_yellow", createArmorValuesMap(2, 5, 5, 2), 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "tunic_yellow"), "", true)));

        TUNIC_RED = register("tunic_red", createArmorValuesMap(2, 5, 5, 2), 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "tunic_red"), "", true)));

        TUNIC_SEA = register("tunic_sea", createArmorValuesMap(2, 5, 5, 2), 20, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "tunic_sea"), "", true)));

        CHAINMAIL = register("chainmail", createArmorValuesMap(2, 5, 5, 2), 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.4f, 0F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "chainmail"), "", true)));

        WITCH = register("witch", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "witch"), "", true)));

        POLAR = register("polar", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "polar"), "", true)));

        SHINOBI = register("shinobi", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "shinobi"), "", true)));

        SKULK = register("skulk", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "skulk"), "", true)));

        TENGU = register("tengu", createArmorValuesMap(3, 6, 8, 3), 37, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F,
                () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE),
                List.of(new ArmorMaterial.Layer(Identifier.of(KnightQuest.MOD_ID, "tengu"), "", true)));

    }

    public static String getKeyNameFromMaterial(RegistryEntry<ArmorMaterial> holder) {
        String keyName = holder.getKey().orElseThrow().getValue().toString();
        return keyName.replace(":", ".");
    }

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enumMap.put(armoritem$type, defense.get(armoritem$type));
        }

        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(KnightQuest.MOD_ID, id), new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));

    }

}