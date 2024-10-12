package dev.xylonity.knightquest.common.material;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum KQArmorMaterials implements ArmorMaterial {

    APPLE_SET("apple", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    BAMBOOSET_BLUE("bamboo_blue", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    BAMBOOSET_GREEN("bamboo_green", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    BAMBOOSET("bamboo", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    BATSET("bat", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    BLAZESET("blaze", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    BOWSET("bow", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    HORNSET("horn", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    CREEPERSET("creeper", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    DEEPSLATESET("deepslate", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    DRAGONSET("dragon", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    ENDERMANSET("enderman", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    EVOKERSET("evoker", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    FORZESET("forze", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    HOLLOWSET("hollow", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    NETHERSET("nether", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    VETERANSET("veteran", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    PATHSET("path", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    PHANTOMSET("phantom", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    SEASET("sea", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    SHIELDSET("shield", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    SILVERSET("silver", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    SILVERFISHSET("silverfish", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    SKELETONSET("skeleton", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    SPIDERSET("spider", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    WARLORDSET("warlord", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    STRAWHATSET("strawhat", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    PIRATESET("pirate", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    CONQUISTADORSET("conquistador", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    ZOMBIESET("zombie", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    HUSKSET("husk", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    WITHERSET("wither", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    SQUIRESET("squire", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    TUNIC_BLUE("tunic_blue", 20, convertProtectionArrayToEnumMap(new int[]{ 2, 5, 5, 2 }), 12,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(Items.APPLE)),
    TUNIC_GREEN("tunic_green", 20, convertProtectionArrayToEnumMap(new int[]{ 2, 5, 5, 2 }), 12,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(Items.APPLE)),
    TUNIC_YELLOW("tunic_yellow", 20, convertProtectionArrayToEnumMap(new int[]{ 2, 5, 5, 2 }), 12,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(Items.APPLE)),
    TUNIC_RED("tunic_red", 20, convertProtectionArrayToEnumMap(new int[]{ 2, 5, 5, 2 }), 12,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(Items.APPLE)),
    TUNIC_SEA("tunic_sea", 20, convertProtectionArrayToEnumMap(new int[]{ 2, 5, 5, 2 }), 12,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.5f, 0F, () -> Ingredient.of(Items.APPLE)),
    CHAINMAIL("chainmail", 20, convertProtectionArrayToEnumMap(new int[]{ 2, 5, 5, 2 }), 12,
            SoundEvents.ARMOR_EQUIP_IRON, 0.5f, 0F, () -> Ingredient.of(Items.APPLE)),
    WITCH("witch", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    POLAR("polar", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    SHINOBI("shinobi", 35, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 20,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.05F, () -> Ingredient.of(Items.APPLE)),
    SKULK("skulk", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE)),
    TENGU("tengu", 40, convertProtectionArrayToEnumMap(new int[]{ 3, 8, 6, 3 }), 25,
            SoundEvents.ARMOR_EQUIP_NETHERITE,4f, 0.1F, () -> Ingredient.of(Items.APPLE));

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static EnumMap<ArmorItem.Type, Integer> convertProtectionArrayToEnumMap(int[] protectionAmounts) {
        EnumMap<ArmorItem.Type, Integer> protectionMap = new EnumMap<>(ArmorItem.Type.class);
        protectionMap.put(ArmorItem.Type.HELMET, protectionAmounts[0]);
        protectionMap.put(ArmorItem.Type.CHESTPLATE, protectionAmounts[1]);
        protectionMap.put(ArmorItem.Type.LEGGINGS, protectionAmounts[2]);
        protectionMap.put(ArmorItem.Type.BOOTS, protectionAmounts[3]);
        return protectionMap;
    }

    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });

    KQArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionAmounts, int enchantmentValue, SoundEvent equipSound,
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
    public int getDurabilityForType(ArmorItem.@NotNull Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.@NotNull Type pType) {
        return this.protectionAmounts.get(pType);
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
        return KnightQuestCommon.MOD_ID + ":" + this.name;
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