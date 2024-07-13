package net.xylonity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import net.xylonity.registry.KnightQuestBlocks;
import net.xylonity.registry.KnightQuestItems;

import java.util.Optional;

public class KQModelProvider extends FabricModelProvider {

    public KQModelProvider(FabricDataOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(KnightQuestBlocks.GREAT_CHALICE);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

        itemModelGenerator.register(KnightQuestItems.GREAT_ESSENCE, Models.GENERATED);
       /* itemModelGenerator.register(KnightQuestItems.SMALL_ESSENCE, Models.GENERATED);
        itemModelGenerator.register(KnightQuestItems.RATMAN_EYE, Models.GENERATED);
        itemModelGenerator.register(KnightQuestItems.LIZZY_SCALE, Models.GENERATED);
        itemModelGenerator.register(KnightQuestItems.PALADIN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.CLEAVER, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.WATER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.CRIMSON_SWORD, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.KUKRI, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.KHOPESH, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.NAIL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.UCHIGATANA, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(KnightQuestItems.WATER_AXE, Models.HANDHELD);

        itemModelGenerator.register(KnightQuestItems.GREMLIN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.RATMAN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.ELD_KNIGHT_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.ELD_BOMB_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.SAMHAIN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.SWAMPMAN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.LIZZY_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        itemModelGenerator.register(KnightQuestItems.BADPATCH_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));*/
    }

}
