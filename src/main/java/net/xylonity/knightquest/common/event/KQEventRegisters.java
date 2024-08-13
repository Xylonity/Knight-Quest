package net.xylonity.knightquest.common.event;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.datagen.KQGlobalLootModifiersProvider;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.common.entity.custom.*;

public class KQEventRegisters {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(KnightQuestEntities.BADPATCH.value(), BadPatchEntity.setAttributes().build());
        event.put(KnightQuestEntities.ELDBOMB.value(), EldBombEntity.setAttributes().build());
        event.put(KnightQuestEntities.ELDKINGHT.value(), EldKnightEntity.setAttributes().build());
        event.put(KnightQuestEntities.GHOSTY.value(), GhostyEntity.setAttributes().build());
        event.put(KnightQuestEntities.GREMLIN.value(), GremlinEntity.setAttributes().build());
        event.put(KnightQuestEntities.LIZZY.value(), LizzyEntity.setAttributes().build());
        event.put(KnightQuestEntities.MOMMA_LIZZY.value(), MommaLizzyEntity.setAttributes().build());
        event.put(KnightQuestEntities.RATMAN.value(), RatmanEntity.setAttributes().build());
        event.put(KnightQuestEntities.SAMHAIN.value(), SamhainEntity.setAttributes().build());
        event.put(KnightQuestEntities.SHIELD.value(), GhastlingEntity.setAttributes().build());
        event.put(KnightQuestEntities.SWAMPMAN.value(), SwampmanEntity.setAttributes().build());
        event.put(KnightQuestEntities.NETHERMAN.value(), NethermanEntity.setAttributes());
        event.put(KnightQuestEntities.NETHERMAN_CLONE.value(), NethermanCloneEntity.setAttributes());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(KnightQuestEntities.RATMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.SAMHAIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TamableAnimal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.GREMLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.SWAMPMAN.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.ELDKINGHT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.ELDBOMB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.LIZZY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(KnightQuestEntities.GHOSTY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new KQGlobalLootModifiersProvider(packOutput, event.getLookupProvider(), KnightQuest.MOD_ID));
    }

}
