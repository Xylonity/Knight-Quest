package net.xylonity.datagen;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.xylonity.config.values.KQConfigValues;
import net.xylonity.registry.KnightQuestItems;

public class KQLootTableModifiers {

    private static final Identifier RATMAN_ID = Identifier.of("knightquest", "entities/ratman");
    private static final Identifier LIZZY_ID = Identifier.of("knightquest", "entities/lizzy");

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder) -> {

            if (RATMAN_ID.equals(resourceManager.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(KQConfigValues.DROP_CHANCE_RATMAN_EYE))
                        .with(ItemEntry.builder(KnightQuestItems.RATMAN_EYE))
                        .with(ItemEntry.builder(Items.CROSSBOW))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                lootManager.pool(poolBuilder.build());
            }

            if (LIZZY_ID.equals(resourceManager.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(KQConfigValues.DROP_CHANCE_LIZZY_SCALE))
                        .with(ItemEntry.builder(KnightQuestItems.LIZZY_SCALE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                lootManager.pool(poolBuilder.build());
            }

        });

    }

}
