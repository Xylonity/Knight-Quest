package dev.xylonity.knightquest.common.material;

import dev.xylonity.knightlib.compat.registry.KnightLibItems;
import dev.xylonity.knightquest.KnightQuest;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class KQArmorMaterials {

    public static final Holder<ArmorMaterial> APPLE_SET;
    public static final Holder<ArmorMaterial> BAMBOOSET_BLUE;
    public static final Holder<ArmorMaterial> BAMBOOSET_GREEN;
    public static final Holder<ArmorMaterial> BAMBOOSET;
    public static final Holder<ArmorMaterial> BATSET;
    public static final Holder<ArmorMaterial> BLAZESET;
    public static final Holder<ArmorMaterial> BOWSET;
    public static final Holder<ArmorMaterial> HORNSET;
    public static final Holder<ArmorMaterial> CREEPERSET;
    public static final Holder<ArmorMaterial> DEEPSLATESET;
    public static final Holder<ArmorMaterial> DRAGONSET;
    public static final Holder<ArmorMaterial> ENDERMANSET;
    public static final Holder<ArmorMaterial> EVOKERSET;
    public static final Holder<ArmorMaterial> FORZESET;
    public static final Holder<ArmorMaterial> HOLLOWSET;
    public static final Holder<ArmorMaterial> NETHERSET;
    public static final Holder<ArmorMaterial> VETERANSET;
    public static final Holder<ArmorMaterial> PATHSET;
    public static final Holder<ArmorMaterial> PHANTOMSET;
    public static final Holder<ArmorMaterial> SEASET;
    public static final Holder<ArmorMaterial> SHIELDSET;
    public static final Holder<ArmorMaterial> SILVERSET;
    public static final Holder<ArmorMaterial> SILVERFISHSET;
    public static final Holder<ArmorMaterial> SKELETONSET;
    public static final Holder<ArmorMaterial> SPIDERSET;
    public static final Holder<ArmorMaterial> WARLORDSET;
    public static final Holder<ArmorMaterial> STRAWHATSET;
    public static final Holder<ArmorMaterial> PIRATESET;
    public static final Holder<ArmorMaterial> CONQUISTADORSET;
    public static final Holder<ArmorMaterial> ZOMBIESET;
    public static final Holder<ArmorMaterial> HUSKSET;
    public static final Holder<ArmorMaterial> WITHERSET;
    public static final Holder<ArmorMaterial> SQUIRESET;
    public static final Holder<ArmorMaterial> TUNIC_BLUE;
    public static final Holder<ArmorMaterial> TUNIC_GREEN;
    public static final Holder<ArmorMaterial> TUNIC_YELLOW;
    public static final Holder<ArmorMaterial> TUNIC_RED;
    public static final Holder<ArmorMaterial> TUNIC_SEA;
    public static final Holder<ArmorMaterial> CHAINMAIL;
    public static final Holder<ArmorMaterial> WITCH;
    public static final Holder<ArmorMaterial> POLAR;
    public static final Holder<ArmorMaterial> SHINOBI;
    public static final Holder<ArmorMaterial> SKULK;
    public static final Holder<ArmorMaterial> TENGU;


    private static EnumMap<ArmorItem.Type, Integer> createArmorValuesMap(int boots, int leggings, int chestplate, int helmet) {
        EnumMap<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.BOOTS, boots);
        map.put(ArmorItem.Type.LEGGINGS, leggings);
        map.put(ArmorItem.Type.CHESTPLATE, chestplate);
        map.put(ArmorItem.Type.HELMET, helmet);
        return map;
    }

    static {

        APPLE_SET = register("apple", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "apple"), "", true)));
        BAMBOOSET_BLUE = register("bamboo_blue", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "bamboo_blue"), "", true)));
        BAMBOOSET_GREEN = register("bamboo_green", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "bamboo_green"), "", true)));
        BAMBOOSET = register("bamboo", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "bamboo"), "", true)));
        BATSET = register("bat", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "bat"), "", true)));
        BLAZESET = register("blaze", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "blaze"), "", true)));
        BOWSET = register("bow", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "bow"), "", true)));
        HORNSET = register("horn", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "horn"), "", true)));
        CREEPERSET = register("creeper", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "creeper"), "", true)));
        DEEPSLATESET = register("deepslate", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "deepslate"), "", true)));
        DRAGONSET = register("dragon", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "dragon"), "", true)));
        ENDERMANSET = register("enderman", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "enderman"), "", true)));
        EVOKERSET = register("evoker", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "evoker"), "", true)));
        FORZESET = register("forze", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "forze"), "", true)));
        HOLLOWSET = register("hollow", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "hollow"), "", true)));
        NETHERSET = register("nether", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "nether"), "", true)));
        VETERANSET = register("veteran", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "veteran"), "", true)));
        PATHSET = register("path", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "path"), "", true)));
        PHANTOMSET = register("phantom", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "phantom"), "", true)));
        SEASET = register("sea", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "sea"), "", true)));
        SHIELDSET = register("shield", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "shield"), "", true)));
        SILVERSET = register("silver", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "silver"), "", true)));
        SILVERFISHSET = register("silverfish", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "silverfish"), "", true)));
        SKELETONSET = register("skeleton", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "skeleton"), "", true)));
        SPIDERSET = register("spider", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "spider"), "", true)));
        WARLORDSET = register("warlord", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "warlord"), "", true)));
        STRAWHATSET = register("strawhat", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "strawhat"), "", true)));
        PIRATESET = register("pirate", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "pirate"), "", true)));
        CONQUISTADORSET = register("conquistador", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "conquistador"), "", true)));
        ZOMBIESET = register("zombie", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "zombie"), "", true)));
        HUSKSET = register("husk", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "husk"), "", true)));
        WITHERSET = register("wither", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "wither"), "", true)));
        SQUIRESET = register("squire", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "squire"), "", true)));
        TUNIC_BLUE = register("tunic_blue", createArmorValuesMap(2, 5, 5, 2), 25,
                SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "tunic_blue"), "", true)));
        TUNIC_GREEN = register("tunic_green", createArmorValuesMap(2, 5, 5, 2), 25,
                SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "tunic_green"), "", true)));
        TUNIC_YELLOW = register("tunic_yellow", createArmorValuesMap(2, 5, 5, 2), 25,
                SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "tunic_yellow"), "", true)));
        TUNIC_RED = register("tunic_red", createArmorValuesMap(2, 5, 5, 2), 25,
                SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "tunic_red"), "", true)));
        TUNIC_SEA = register("tunic_sea", createArmorValuesMap(2, 5, 5, 2), 25,
                SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "tunic_sea"), "", true)));
        CHAINMAIL = register("chainmail", createArmorValuesMap(2, 5, 5, 2), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "chainmail"), "", true)));
        WITCH = register("witch", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "witch"), "", true)));
        POLAR = register("polar", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "polar"), "", true)));
        SHINOBI = register("shinobi", createArmorValuesMap(3, 6, 8, 3), 20,
                SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.05F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "shinobi"), "", true)));
        SKULK = register("skulk", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "skulk"), "", true)));
        TENGU = register("tengu", createArmorValuesMap(3, 6, 8, 3), 25,
                SoundEvents.ARMOR_EQUIP_NETHERITE, 4F, 0.1F, () -> Ingredient.of(KnightLibItems.GREAT_ESSENCE.get()),
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "tengu"), "", true)));

    }

    public static String getKeyNameFromMaterial(Holder<ArmorMaterial> holder) {
        String keyName = holder.unwrapKey().orElseThrow().location().toString();
        keyName = keyName.replace(":", ".");
        keyName = keyName.replace("knightquest.", "");
        return keyName;
    }

    private static Holder<ArmorMaterial> register(String pName, EnumMap<ArmorItem.Type, Integer> pDefense, int pEnchantmentValue, Holder<SoundEvent> pEquipSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngridient, List<ArmorMaterial.Layer> pLayers) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armor : ArmorItem.Type.values()) {
            enummap.put(armor, pDefense.get(armor));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, pName),
                new ArmorMaterial(enummap, pEnchantmentValue, pEquipSound, pRepairIngridient, pLayers, pToughness, pKnockbackResistance)
        );
    }

}