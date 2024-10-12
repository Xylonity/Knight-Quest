package dev.xylonity.knightquest.common.material;

import com.google.common.base.Suppliers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import javax.tools.Tool;
import java.util.Objects;
import java.util.function.Supplier;

public enum KQItemMaterials implements Tier {

    PALADIN(4, 2131, 0.5f, 3.3f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    NAIL(4, 1821, 0.5f, 2.2f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    UCHIGATANA(4, 1080, 0.5f, 2.8f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    KUKRI(4, 200, 0.5f, 1f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    KHOPESH(4, 1831, 0.5f, 3.1f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    CLEAVER(4, 1831, 0.5f, 3f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    CRIMSON_SWORD(4, 1450, 0.5f, 2.3f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    WATER_SWORD(4, 850, 0.5f, 1.8f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    STEEL_SWORD(4, 300, 0.5f, 2.3f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    WATER_AXE(4, 850, 6.0f, 1.3f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT)),
    STEEL_AXE(4, 300, 6.5f, 1.5f, 15,
            () -> Ingredient.of(Items.ACACIA_BOAT));

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
    public int getUses() {
        return this.itemDurability;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}