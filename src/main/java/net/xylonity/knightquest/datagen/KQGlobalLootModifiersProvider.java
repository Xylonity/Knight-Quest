package net.xylonity.knightquest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.xylonity.knightquest.registry.KnightQuestItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class KQGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public KQGlobalLootModifiersProvider(PackOutput output, String modid, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, modid, registries);
    }

    /**
     * Declaration of certain mobs that will drop the small_essence item
     */

    private static final ResourceLocation[] MOB_IDS = {
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/creeper"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/spider"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/skeleton"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/zombie"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/cave_spider"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/blaze"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/enderman"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/ghast"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/magma_cube"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/phantom"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/slime"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/stray"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/vex"),
            ResourceLocation.fromNamespaceAndPath("minecraft", "entities/drowned"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/gremlin"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/eldknight"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/samhain"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/ratman"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/swampman"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/eldbomb"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/lizzy"),
            ResourceLocation.fromNamespaceAndPath("knightquest", "entities/bad_patch")
    };

    private static final ResourceLocation RATMAN_ID = ResourceLocation.fromNamespaceAndPath("knightquest", "entities/ratman");
    private static final ResourceLocation LIZZY_ID = ResourceLocation.fromNamespaceAndPath("knightquest", "entities/lizzy");

    @Override
    protected void start(HolderLookup.@NotNull Provider registries) {

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
