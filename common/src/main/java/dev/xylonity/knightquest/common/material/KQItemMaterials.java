package dev.xylonity.knightquest.common.material;

import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

public enum KQItemMaterials implements Tier {

    PALADIN(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2131, 9.0F, 4F, 15, () -> Ingredient.of(Items.ICE)),
    NAIL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1821, 9.0F, 2.5F, 15, () -> Ingredient.of(Items.ICE)),
    UCHIGATANA(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1080, 9.0F, 3F, 15, () -> Ingredient.of(Items.ICE)),
    KUKRI(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 200, 9.0F, 0F, 14, () -> Ingredient.of(Items.ICE)),
    KHOPESH(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1831, 9.0F, 3.5F, 15, () -> Ingredient.of(Items.ICE)),
    CLEAVER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1931, 9.0F, 3.8F, 15, () -> Ingredient.of(Items.ICE)),
    CRIMSON_SWORD(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 1450, 9.0F, 2.5F, 14, () -> Ingredient.of(Items.ICE)),
    WATER_SWORD(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 850, 9.0F, 2F, 14, () -> Ingredient.of(Items.ICE)),
    STEEL_SWORD(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 300, 9.0F, 2.5F, 14, () -> Ingredient.of(Items.ICE)),
    WATER_AXE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 850, 9.0F, 1.5F, 14, () -> Ingredient.of(Items.ICE)),
    STEEL_AXE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 300, 9.0F, 1.5F, 14, () -> Ingredient.of(Items.ICE));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    KQItemMaterials(final TagKey p_334032_, final int p_43332_, final float p_43334_, final float p_43335_, final int p_43333_, final Supplier<Ingredient> p_43337_) {
        this.incorrectBlocksForDrops = p_334032_;
        this.uses = p_43332_;
        this.speed = p_43334_;
        this.damage = p_43335_;
        this.enchantmentValue = p_43333_;
        Objects.requireNonNull(p_43337_);
        this.repairIngredient = Suppliers.memoize(p_43337_::get);
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
        return (Ingredient)this.repairIngredient.get();
    }

    @Override
    public Tool createToolProperties(TagKey<Block> pBlock) {
        return Tier.super.createToolProperties(pBlock);
    }

}