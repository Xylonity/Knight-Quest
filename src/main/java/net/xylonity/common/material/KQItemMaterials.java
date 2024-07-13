package net.xylonity.common.material;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.xylonity.registry.KnightQuestItems;

import java.util.Objects;
import java.util.function.Supplier;

public enum KQItemMaterials implements ToolMaterial {

    PALADIN(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4, 2131, 0.5f, 3.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    NAIL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4, 1821, 0.5f, 2.2f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    UCHIGATANA(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4, 1080, 0.5f, 2.8f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    KUKRI(BlockTags.INCORRECT_FOR_IRON_TOOL, 4, 200, 0.5f, 1f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    KHOPESH(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4, 1831, 0.5f, 3.1f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CLEAVER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4, 1831, 0.5f, 3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    CRIMSON_SWORD(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 4, 1450, 0.5f, 2.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WATER_SWORD(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 4, 850, 0.5f, 1.8f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    STEEL_SWORD(BlockTags.INCORRECT_FOR_IRON_TOOL, 4, 300, 0.5f, 2.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    WATER_AXE(BlockTags.INCORRECT_FOR_IRON_TOOL, 4, 850, 6.0f, 1.3f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE)),
    STEEL_AXE(BlockTags.INCORRECT_FOR_IRON_TOOL, 4, 300, 6.5f, 1.5f, 15,
            () -> Ingredient.ofItems(KnightQuestItems.GREAT_ESSENCE));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    KQItemMaterials(final TagKey inverseTag, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }

}
