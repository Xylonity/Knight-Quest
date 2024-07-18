package net.xylonity.knightquest.datagen;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.xylonity.knightquest.registry.KnightQuestItems;
import net.xylonity.knightquest.config.init.KQConfigValues;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Supplier;

public class KQAddItemModifier extends LootModifier {

    public static final Supplier<MapCodec<KQAddItemModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.mapCodec(inst ->
                    codecStart(inst).and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(KQAddItemModifier::getItem))
                            .and(Codec.FLOAT.fieldOf("chance").forGetter(KQAddItemModifier::getChance))
                            .apply(inst, KQAddItemModifier::new)));

    private final Item item;
    private float chance;

    public KQAddItemModifier(LootItemCondition[] conditionsIn, Item item, float chance) {
        super(conditionsIn);
        this.item = item;
        this.chance = chance;
    }

    public Item getItem() {
        return item;
    }

    public float getChance() {
        return chance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }

        if (item == KnightQuestItems.SMALL_ESSENCE.get() && new Random().nextFloat() <= KQConfigValues.DROP_CHANCE_SMALL_ESSENCE) {
            generatedLoot.add(new ItemStack(this.item));
        }

        if (item == KnightQuestItems.RATMAN_EYE.get() && new Random().nextFloat() <= KQConfigValues.DROP_CHANCE_RATMAN_EYE) {
            generatedLoot.add(new ItemStack(this.item));
        }

        if (item == KnightQuestItems.LIZZY_SCALE.get() && new Random().nextFloat() <= KQConfigValues.DROP_CHANCE_LIZZY_SCALE) {
            generatedLoot.add(new ItemStack(this.item));
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }

}