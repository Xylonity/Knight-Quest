package dev.xylonity.knightquest.common.material;

import com.google.common.base.Suppliers;
import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

public enum KQItemMaterials implements Tier {

    PALADIN(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2350, 0.5f, 10f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    NAIL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2120, 0.5f, 9f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    UCHIGATANA(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 0.5f, 8f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    KUKRI(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 400, 0.5f, 4f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    KHOPESH(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2120, 0.5f, 9f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    CLEAVER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 0.5f, 12f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),

    WATER_SWORD(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 850, 0.5f, 1.8f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    STEEL_SWORD(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 300, 0.5f, 1.5f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    WATER_AXE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 850, 6.0f, 1.3f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get())),
    STEEL_AXE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 300, 6.5f, 4f, 15, () -> Ingredient.of(KnightQuestCommon.COMMON_PLATFORM.getGreatEssence().get()));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    KQItemMaterials(final TagKey tagKey, final int uses, final float speed, final float damage, final int enchantment, final Supplier<Ingredient> ingredient) {
        this.incorrectBlocksForDrops = tagKey;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantment;
        Objects.requireNonNull(ingredient);
        this.repairIngredient = Suppliers.memoize(ingredient::get);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public Tool createToolProperties(TagKey<Block> pBlock) {
        return Tier.super.createToolProperties(pBlock);
    }

}