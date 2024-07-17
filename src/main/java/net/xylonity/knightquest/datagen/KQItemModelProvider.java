package net.xylonity.knightquest.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class KQItemModelProvider extends ItemModelProvider {

    public KQItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //withExistingParent(KnightQuestItems.GREMLIN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }
}
