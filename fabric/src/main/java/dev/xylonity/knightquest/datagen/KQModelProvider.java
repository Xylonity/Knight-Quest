package dev.xylonity.knightquest.datagen;

import com.google.gson.JsonObject;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class KQModelProvider extends FabricModelProvider {

    public KQModelProvider(FabricDataOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) { ;; }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(KnightQuestItems.RATMAN_EYE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.LIZZY_SCALE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(KnightQuestItems.PALADIN_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.CLEAVER.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.STEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.WATER_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.CRIMSON_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.KUKRI.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.KHOPESH.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.NAIL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(KnightQuestItems.UCHIGATANA.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        //itemModelGenerator.generateFlatItem(KnightQuestItems.STEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        //itemModelGenerator.generateFlatItem(KnightQuestItems.WATER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);

        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.GREMLIN_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.RATMAN_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.ELD_KNIGHT_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.ELD_BOMB_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.SAMHAIN_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.SWAMPMAN_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.LIZZY_EGG);
        //generateSpawnEggModel(itemModelGenerator, KnightQuestItems.BADPATCH_EGG);
    }

    private void generateSpawnEggModel(ItemModelGenerators itemModelGenerators, Item spawnEggItem) {
        ResourceLocation parentModel = new ResourceLocation("item/template_spawn_egg");
        ResourceLocation itemModelLocation = ModelLocationUtils.getModelLocation(spawnEggItem);

        itemModelGenerators.output.accept(itemModelLocation, () -> {
            JsonObject json = new JsonObject();
            json.addProperty("parent", parentModel.toString());
            return json;
        });
    }

}