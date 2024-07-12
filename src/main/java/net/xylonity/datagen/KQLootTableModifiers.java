package net.xylonity.datagen;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.xylonity.registry.KnightQuestItems;

public class KQLootTableModifiers {

    private static final Identifier[] MOB_IDS = {
            new Identifier("minecraft", "entities/creeper"),
            new Identifier("minecraft", "entities/skeleton"),
            new Identifier("minecraft", "entities/zombie"),
            new Identifier("minecraft", "entities/cave_spider"),
            new Identifier("minecraft", "entities/blaze"),
            new Identifier("minecraft", "entities/enderman"),
            new Identifier("minecraft", "entities/ghast"),
            new Identifier("minecraft", "entities/magma_cube"),
            new Identifier("minecraft", "entities/phantom"),
            new Identifier("minecraft", "entities/slime"),
            new Identifier("minecraft", "entities/stray"),
            new Identifier("minecraft", "entities/vex"),
            new Identifier("minecraft", "entities/drowned"),
            new Identifier("knightquest", "entities/gremlin"),
            new Identifier("knightquest", "entities/eldknight"),
            new Identifier("knightquest", "entities/samhain"),
            new Identifier("knightquest", "entities/ratman"),
            new Identifier("knightquest", "entities/swampman"),
            new Identifier("knightquest", "entities/eldbomb"),
            new Identifier("knightquest", "entities/lizzy"),
            new Identifier("knightquest", "entities/bad_patch")
    };

    private static final Identifier RATMAN_ID = new Identifier("knightquest", "entities/ratman");
    private static final Identifier LIZZY_ID = new Identifier("knightquest", "entities/lizzy");

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            for (Identifier mobId : MOB_IDS) {
                if (mobId.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(0.15f))
                            .with(ItemEntry.builder(KnightQuestItems.SMALL_ESSENCE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
                    tableBuilder.pool(poolBuilder.build());
                    break;
                }
            }

            if (RATMAN_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.4f))
                        .with(ItemEntry.builder(KnightQuestItems.RATMAN_EYE))
                        .with(ItemEntry.builder(Items.CROSSBOW))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LIZZY_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(KnightQuestItems.LIZZY_SCALE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)));
                tableBuilder.pool(poolBuilder.build());
            }

        });

    }

}
