package dev.xylonity.knightquest.datagen;

import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class KQItemModelProvider extends ItemModelProvider {
    public KQItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(KnightQuestEntities.GREMLIN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}