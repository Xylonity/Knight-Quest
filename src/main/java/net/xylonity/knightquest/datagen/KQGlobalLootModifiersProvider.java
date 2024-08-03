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

    private static final ResourceLocation[] MOB_IDS;

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

        MOB_IDS = new ResourceLocation[vanilla.length + knightquest.length];

        for (int i = 0; i < vanilla.length; i++) {
            MOB_IDS[i] = ResourceLocation.fromNamespaceAndPath("minecraft", "entities/" + vanilla[i]);
        }

        for (int i = 0; i < knightquest.length; i++) {
            MOB_IDS[vanilla.length + i] = ResourceLocation.fromNamespaceAndPath("knightquest", "entities/" + knightquest[i]);
        }
    }

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
