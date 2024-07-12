package net.xylonity.common.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.xylonity.registry.KnightQuestItems;

import java.util.function.Supplier;

public enum KQItemMaterials implements ToolMaterial {

    PALADIN(4, 2131, 0.5f, 3.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    NAIL(4, 1821, 0.5f, 2.2f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    UCHIGATANA(4, 1080, 0.5f, 2.8f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    KUKRI(4, 200, 0.5f, 1f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    KHOPESH(4, 1831, 0.5f, 3.1f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CLEAVER(4, 1831, 0.5f, 3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CRIMSON_SWORD(4, 1450, 0.5f, 2.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WATER_SWORD(4, 850, 0.5f, 1.8f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    STEEL_SWORD(4, 300, 0.5f, 2.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WATER_AXE(4, 850, 6.0f, 1.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    STEEL_AXE(4, 300, 6.5f, 1.5f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    KQItemMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
