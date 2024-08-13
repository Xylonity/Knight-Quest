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

    /**
     * Declaration of certain mobs that will drop the small_essence item
     */

    private static final Identifier[] MOB_IDS;

    static {
        String[] vanilla = {
                "creeper", "spider", "skeleton", "zombie", "cave_spider",
                "blaze", "enderman", "ghast", "magma_cube", "phantom",
                "slime", "stray", "vex", "drowned", "witch", "husk",
                "zombie_villager", "wither_skeleton", "pillager",
                "vindicator", "evoker", "hoglin", "piglin"
        };

        String[] knightquest = {
                "gremlin", "eldknight", "samhain", "ratman", "swampman",
                "eldbomb", "lizzy", "bad_patch"
        };

        MOB_IDS = new Identifier[vanilla.length + knightquest.length];

        for (int i = 0; i < vanilla.length; i++) {
            MOB_IDS[i] = Identifier.of("minecraft", "entities/" + vanilla[i]);
        }

        for (int i = 0; i < knightquest.length; i++) {
            MOB_IDS[vanilla.length + i] = Identifier.of("knightquest", "entities/" + knightquest[i]);
        }
    }

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
