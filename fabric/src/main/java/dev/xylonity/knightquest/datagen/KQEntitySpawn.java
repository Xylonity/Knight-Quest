package dev.xylonity.knightquest.datagen;

import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class KQEntitySpawn {

    public static void addEntitySpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DRIPSTONE_CAVES), MobCategory.MONSTER, KnightQuestEntities.RATMAN, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.LUSH_CAVES), MobCategory.MONSTER, KnightQuestEntities.RATMAN, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.MONSTER, KnightQuestEntities.RATMAN, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.JUNGLE), MobCategory.MONSTER, KnightQuestEntities.RATMAN, 25, 1, 2);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY), MobCategory.MONSTER, KnightQuestEntities.GREMLIN, 15, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.NETHER_WASTES), MobCategory.MONSTER, KnightQuestEntities.GREMLIN, 15, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), MobCategory.MONSTER, KnightQuestEntities.GREMLIN, 15, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.WARPED_FOREST), MobCategory.MONSTER, KnightQuestEntities.GREMLIN, 15, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BEACH), MobCategory.MONSTER, KnightQuestEntities.SWAMPMAN, 15, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.RIVER), MobCategory.MONSTER, KnightQuestEntities.SWAMPMAN, 15, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.JUNGLE), MobCategory.MONSTER, KnightQuestEntities.SWAMPMAN, 15, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SPARSE_JUNGLE), MobCategory.MONSTER, KnightQuestEntities.SWAMPMAN, 15, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.MONSTER, KnightQuestEntities.ELDBOMB, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.TAIGA), MobCategory.MONSTER, KnightQuestEntities.ELDBOMB, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_BEACH), MobCategory.MONSTER, KnightQuestEntities.ELDBOMB, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_PLAINS), MobCategory.MONSTER, KnightQuestEntities.ELDBOMB, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_SLOPES), MobCategory.MONSTER, KnightQuestEntities.ELDBOMB, 25, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_TAIGA), MobCategory.MONSTER, KnightQuestEntities.ELDBOMB, 25, 1, 2);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.MONSTER, KnightQuestEntities.ELDKNIGHT, 13, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.TAIGA), MobCategory.MONSTER, KnightQuestEntities.ELDKNIGHT, 13, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_BEACH), MobCategory.MONSTER, KnightQuestEntities.ELDKNIGHT, 20, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_PLAINS), MobCategory.MONSTER, KnightQuestEntities.ELDKNIGHT, 20, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_SLOPES), MobCategory.MONSTER, KnightQuestEntities.ELDKNIGHT, 20, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SNOWY_TAIGA), MobCategory.MONSTER, KnightQuestEntities.ELDKNIGHT, 20, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.CREATURE, KnightQuestEntities.LIZZY, 6, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST), MobCategory.CREATURE, KnightQuestEntities.LIZZY, 5, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.TAIGA), MobCategory.CREATURE, KnightQuestEntities.LIZZY, 5, 1, 2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.LUSH_CAVES), MobCategory.CREATURE, KnightQuestEntities.LIZZY, 4, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DESERT), MobCategory.CREATURE, KnightQuestEntities.LIZZY, 4, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DARK_FOREST), MobCategory.MONSTER, KnightQuestEntities.GHOSTY, 14, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.TAIGA), MobCategory.MONSTER, KnightQuestEntities.GHOSTY, 18, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.BADLANDS), MobCategory.MONSTER, KnightQuestEntities.GHOSTY, 18, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.LUSH_CAVES), MobCategory.MONSTER, KnightQuestEntities.GHOSTY, 18, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.MONSTER, KnightQuestEntities.GHOSTY, 18, 1, 1);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.NETHER_WASTES), MobCategory.MONSTER, KnightQuestEntities.BADPATCH, 10, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.JUNGLE), MobCategory.MONSTER, KnightQuestEntities.BADPATCH, 10, 1, 2);

        SpawnPlacements.register(KnightQuestEntities.RATMAN, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.GHOSTY, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.GREMLIN, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.SWAMPMAN, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.ELDBOMB, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.ELDKNIGHT, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.LIZZY, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(KnightQuestEntities.BADPATCH, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

}