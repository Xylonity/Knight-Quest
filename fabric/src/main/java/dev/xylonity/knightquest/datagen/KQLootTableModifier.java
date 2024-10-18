package dev.xylonity.knightquest.datagen;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class KQLootTableModifier {

    private static final ResourceLocation RATMAN_ID = new ResourceLocation(KnightQuest.MOD_ID, "entities/ratman");
    private static final ResourceLocation LIZZY_ID = new ResourceLocation(KnightQuest.MOD_ID, "entities/lizzy");

    public static void init() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (RATMAN_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(KQConfigValues.DROP_CHANCE_RATMAN_EYE))
                        .add(LootItem.lootTableItem(KnightQuestItems.RATMAN_EYE.get()))
                        .add(LootItem.lootTableItem(Items.CROSSBOW))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)));
                tableBuilder.pool(poolBuilder.build());
            }

            if (LIZZY_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(KQConfigValues.DROP_CHANCE_LIZZY_SCALE))
                        .add(LootItem.lootTableItem(KnightQuestItems.LIZZY_SCALE.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
                tableBuilder.pool(poolBuilder.build());
            }

        });

    }

}