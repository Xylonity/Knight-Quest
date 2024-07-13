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
import net.xylonity.registry.KnightQuestItems;

public class KQLootTableModifiers {

    private static final Identifier[] MOB_IDS = {
            Identifier.of("minecraft", "entities/creeper"),
            Identifier.of("minecraft", "entities/skeleton"),
            Identifier.of("minecraft", "entities/zombie"),
            Identifier.of("minecraft", "entities/cave_spider"),
            Identifier.of("minecraft", "entities/blaze"),
            Identifier.of("minecraft", "entities/enderman"),
            Identifier.of("minecraft", "entities/ghast"),
            Identifier.of("minecraft", "entities/magma_cube"),
            Identifier.of("minecraft", "entities/phantom"),
            Identifier.of("minecraft", "entities/slime"),
            Identifier.of("minecraft", "entities/stray"),
            Identifier.of("minecraft", "entities/vex"),
            Identifier.of("minecraft", "entities/drowned"),
            Identifier.of("knightquest", "entities/gremlin"),
            Identifier.of("knightquest", "entities/eldknight"),
            Identifier.of("knightquest", "entities/samhain"),
            Identifier.of("knightquest", "entities/ratman"),
            Identifier.of("knightquest", "entities/swampman"),
            Identifier.of("knightquest", "entities/eldbomb"),
            Identifier.of("knightquest", "entities/lizzy"),
            Identifier.of("knightquest", "entities/bad_patch")
    };

    private static final Identifier RATMAN_ID = Identifier.of("knightquest", "entities/ratman");
    private static final Identifier LIZZY_ID = Identifier.of("knightquest", "entities/lizzy");

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder) -> {
            for (Identifier mobId : MOB_IDS) {
                if (mobId.equals(resourceManager.getValue())) {
                    LootPool.Builder poolBuilder = LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(0.15f))
                            .with(ItemEntry.builder(KnightQuestItems.SMALL_ESSENCE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                    lootManager.pool(poolBuilder.build());
                    break;
                }
            }

            if (RATMAN_ID.equals(resourceManager.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.4f))
                        .with(ItemEntry.builder(KnightQuestItems.RATMAN_EYE))
                        .with(ItemEntry.builder(Items.CROSSBOW))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                lootManager.pool(poolBuilder.build());
            }

            if (LIZZY_ID.equals(resourceManager.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.3f))
                        .with(ItemEntry.builder(KnightQuestItems.LIZZY_SCALE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                lootManager.pool(poolBuilder.build());
            }

        });

    }

}
