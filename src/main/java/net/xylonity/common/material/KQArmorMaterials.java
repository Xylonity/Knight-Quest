package net.xylonity.common.material;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.xylonity.KnightQuest;
import net.xylonity.registry.KnightQuestItems;

import java.util.EnumMap;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public enum KQArmorMaterials implements ArmorMaterial {

    APPLE_SET("appleset", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    BAMBOOSET_BLUE("bamboo_blue", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    BAMBOOSET_GREEN("bamboo_green", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    BAMBOOSET("bamboo", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    BATSET("bat", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    BLAZESET("blaze", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    BOWSET("bow", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    HORNSET("horn", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CREEPERSET("creeper", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    DEEPSLATESET("deepslate", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    DRAGONSET("dragon", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    ENDERMANSET("enderman", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    EVOKERSET("evoker", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    FORZESET("forze", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    HOLLOWSET("hollow", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    NETHERSET("nether", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    VETERANSET("veteran", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    PATHSET("path", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    PHANTOMSET("phantom", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SEASET("sea", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SHIELDSET("shield", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SILVERSET("silver", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SILVERFISHSET("silverfish", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SKELETONSET("skeleton", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SPIDERSET("spider", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WARLORDSET("warlord", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    STRAWHATSET("strawhat", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    PIRATESET("pirate", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CONQUISTADORSET("conquistador", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    ZOMBIESET("zombie", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    HUSKSET("husk", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WITHERSET("wither", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SQUIRESET("squire", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    TUNIC_BLUE("tunic_blue", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    TUNIC_GREEN("tunic_green", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    TUNIC_YELLOW("tunic_yellow", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    TUNIC_RED("tunic_red", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    TUNIC_SEA("tunic_sea", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CHAINMAIL("chainmail", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0.4f, 0F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WITCH("witch", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    POLAR("polar", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SHINOBI("shinobi", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    SKULK("skulk", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    TENGU("tengu", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredientSupplier;

    KQArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
    }

    public int getDurability(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()] * this.durabilityMultiplier;
    }

    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredientSupplier.get();
    }

    public String getName() {
        return KnightQuest.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getKeyName() { return this.name; }

}
