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
            MOB_IDS[i] = new ResourceLocation("minecraft", "entities/" + vanilla[i]);
        }

        for (int i = 0; i < knightquest.length; i++) {
            MOB_IDS[vanilla.length + i] = new ResourceLocation("knightquest", "entities/" + knightquest[i]);
        }
    }

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
