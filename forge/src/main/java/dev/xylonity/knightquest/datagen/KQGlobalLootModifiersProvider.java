package dev.xylonity.knightquest.datagen;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class KQGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public KQGlobalLootModifiersProvider(PackOutput output) {
        super(output, KnightQuest.MOD_ID);
    }

    private static final ResourceLocation RATMAN_ID = new ResourceLocation("knightquest", "entities/ratman");
    private static final ResourceLocation LIZZY_ID = new ResourceLocation("knightquest", "entities/lizzy");

    @Override
    protected void start() {

        add(RATMAN_ID.getPath() + "_ratman_eye", new KQAddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(RATMAN_ID).build()
        }, new ItemStack(KnightQuestItems.RATMAN_EYE.get()).getItem(), 0.5F));

        add(LIZZY_ID.getPath() + "_lizzy_scale", new KQAddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(LIZZY_ID).build()
        }, new ItemStack(KnightQuestItems.LIZZY_SCALE.get()).getItem(), 0.5F));

    }

}
