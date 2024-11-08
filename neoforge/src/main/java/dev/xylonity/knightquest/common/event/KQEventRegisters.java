package dev.xylonity.knightquest.common.event;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import dev.xylonity.knightquest.datagen.KQGlobalLootModifiersProvider;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = KnightQuest.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class KQEventRegisters {

    /**
     * Sets attributes to every entity defined in the scope.
     */

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(KnightQuestEntities.GREMLIN.get(), GremlinEntity.setAttributes().build());
        event.put(KnightQuestEntities.ELDBOMB.get(), EldBombEntity.setAttributes().build());
        event.put(KnightQuestEntities.ELDKNIGHT.get(), EldKnightEntity.setAttributes().build());
        event.put(KnightQuestEntities.SWAMPMAN.get(), SwampmanEntity.setAttributes().build());
        event.put(KnightQuestEntities.RATMAN.get(), RatmanEntity.setAttributes().build());
        event.put(KnightQuestEntities.SAMHAIN.get(), SamhainEntity.setAttributes().build());
        event.put(KnightQuestEntities.LIZZY.get(), LizzyEntity.setAttributes().build());
        event.put(KnightQuestEntities.BADPATCH.get(), BadPatchEntity.setAttributes().build());
        event.put(KnightQuestEntities.SHIELD.get(), GhastlingEntity.setAttributes().build());
        event.put(KnightQuestEntities.MOMMA_LIZZY.get(), MommaLizzyEntity.setAttributes().build());
        event.put(KnightQuestEntities.GHOSTY.get(), GhostyEntity.setAttributes().build());
        event.put(KnightQuestEntities.NETHERMAN.get(), NethermanEntity.setAttributes().build());
        event.put(KnightQuestEntities.NETHERMAN_CLONE.get(), NethermanCloneEntity.setAttributes().build());
    }

    /**
     * Limits spawn placement of entities.
     */

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(KnightQuestEntities.BADPATCH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.ELDBOMB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.ELDKNIGHT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.GHOSTY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.GREMLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.LIZZY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.RATMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(KnightQuestEntities.SWAMPMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    /**
     * Recipe json generator for certain mobs defined on the array MOB_IDS.
     * @see KQGlobalLootModifiersProvider
     */

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new KQGlobalLootModifiersProvider(packOutput, event.getLookupProvider(), KnightQuest.MOD_ID));
    }

}

