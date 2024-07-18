package net.xylonity.knightquest.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.xylonity.knightquest.registry.KnightQuestItems;

public class KQItemModelProvider extends ItemModelProvider {
    public KQItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(KnightQuestItems.GREMLIN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
