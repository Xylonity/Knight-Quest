package net.xylonity.knightquest.common.material;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.registry.KnightQuestItems;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum KQArmorMaterials implements ArmorMaterial {

    APPLE_SET("appleset", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    BAMBOOSET_BLUE("bamboo_blue", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    BAMBOOSET_GREEN("bamboo_green", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    BAMBOOSET("bamboo", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    BATSET("bat", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    BLAZESET("blaze", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    BOWSET("bow", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    HORNSET("horn", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    CREEPERSET("creeper", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    DEEPSLATESET("deepslate", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    DRAGONSET("dragon", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    ENDERMANSET("enderman", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    EVOKERSET("evoker", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    FORZESET("forze", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    HOLLOWSET("hollow", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    NETHERSET("nether", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    VETERANSET("veteran", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    PATHSET("path", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    PHANTOMSET("phantom", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SEASET("sea", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SHIELDSET("shield", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SILVERSET("silver", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SILVERFISHSET("silverfish", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SKELETONSET("skeleton", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SPIDERSET("spider", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    WARLORDSET("warlord", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    STRAWHATSET("strawhat", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    PIRATESET("pirate", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    CONQUISTADORSET("conquistador", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    ZOMBIESET("zombie", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    HUSKSET("husk", 37, new int[]{ 3, 8, 6, 3 }, 25,
        SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    WITHERSET("wither", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SQUIRESET("squire", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    TUNIC_BLUE("tunic_blue", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    TUNIC_GREEN("tunic_green", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    TUNIC_YELLOW("tunic_yellow", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    TUNIC_RED("tunic_red", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    TUNIC_SEA("tunic_sea", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.4f, 0F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    CHAINMAIL("chainmail", 20, new int[]{ 2, 5, 5, 2 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 0.4f, 0F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    WITCH("witch", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    POLAR("polar", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SHINOBI("shinobi", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    SKULK("skulk", 37, new int[]{ 3, 8, 6, 3 }, 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get())),
    TENGU("tengu", 37, new int[]{ 3, 8, 6, 3 }, 25,
    SoundEvents.ARMOR_EQUIP_NETHERITE, 4f, 0.1F, () -> Ingredient.of(KnightQuestItems.GREAT_ESSENCE.get()));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    KQArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                     float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return protectionAmounts[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public @NotNull String getName() {
        return KnightQuest.MOD_ID + ":" + this.name;
    }

    public String getKeyName() { return this.name; }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
