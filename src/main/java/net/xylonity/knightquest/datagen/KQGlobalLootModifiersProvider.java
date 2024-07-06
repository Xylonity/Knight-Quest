package net.xylonity.knightquest.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.registry.KnightQuestItems;

public class KQGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public KQGlobalLootModifiersProvider(PackOutput output) {
        super(output, KnightQuest.MOD_ID);
    }

    /**
     * Declaration of certain mobs that will drop the small_essence item
     */

    private static final ResourceLocation[] MOB_IDS = {
            new ResourceLocation("minecraft", "entities/creeper"),
            new ResourceLocation("minecraft", "entities/spider"),
            new ResourceLocation("minecraft", "entities/skeleton"),
            new ResourceLocation("minecraft", "entities/zombie"),
            new ResourceLocation("minecraft", "entities/cave_spider"),
            new ResourceLocation("minecraft", "entities/blaze"),
            new ResourceLocation("minecraft", "entities/enderman"),
            new ResourceLocation("minecraft", "entities/ghast"),
            new ResourceLocation("minecraft", "entities/magma_cube"),
            new ResourceLocation("minecraft", "entities/phantom"),
            new ResourceLocation("minecraft", "entities/slime"),
            new ResourceLocation("minecraft", "entities/stray"),
            new ResourceLocation("minecraft", "entities/vex"),
            new ResourceLocation("minecraft", "entities/drowned"),
            new ResourceLocation("knightquest", "entities/gremlin"),
            new ResourceLocation("knightquest", "entities/eldknight"),
            new ResourceLocation("knightquest", "entities/samhain"),
            new ResourceLocation("knightquest", "entities/ratman"),
            new ResourceLocation("knightquest", "entities/swampman"),
            new ResourceLocation("knightquest", "entities/eldbomb"),
            new ResourceLocation("knightquest", "entities/lizzy"),
            new ResourceLocation("knightquest", "entities/bad_patch")
    };

    private static final ResourceLocation RATMAN_ID = new ResourceLocation("knightquest", "entities/ratman");
    private static final ResourceLocation LIZZY_ID = new ResourceLocation("knightquest", "entities/lizzy");

    @Override
    protected void start() {

        for (ResourceLocation mobId : MOB_IDS) {
            add(mobId.getPath() + "_small_essence", new KQAddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(mobId).build(),
            }, new ItemStack(KnightQuestItems.SMALL_ESSENCE.get()).getItem(), 0.5F));
        }

        add(RATMAN_ID.getPath() + "_ratman_eye", new KQAddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(RATMAN_ID).build()
        }, new ItemStack(KnightQuestItems.RATMAN_EYE.get()).getItem(), 0.5F));

        add(LIZZY_ID.getPath() + "_lizzy_scale", new KQAddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(LIZZY_ID).build()
        }, new ItemStack(KnightQuestItems.LIZZY_SCALE.get()).getItem(), 0.5F));

    }

}
