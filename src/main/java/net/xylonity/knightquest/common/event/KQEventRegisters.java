package net.xylonity.knightquest.common.event;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.common.entity.entities.*;
import net.xylonity.knightquest.datagen.KQGlobalLootModifiersProvider;

@Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KQEventRegisters {

    /**
     * Sets attributes to every entity defined in the scope.
     */

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(KnightQuestEntities.GREMLIN.get(), GremlinEntity.setAttributes());
        event.put(KnightQuestEntities.ELDBOMB.get(), EldBombEntity.setAttributes());
        event.put(KnightQuestEntities.ELDKINGHT.get(), EldKnightEntity.setAttributes());
        event.put(KnightQuestEntities.SWAMPMAN.get(), SwampmanEntity.setAttributes());
        event.put(KnightQuestEntities.RATMAN.get(), RatmanEntity.setAttributes());
        event.put(KnightQuestEntities.SAMHAIN.get(), SamhainEntity.setAttributes());
        event.put(KnightQuestEntities.LIZZY.get(), LizzyEntity.setAttributes());
        event.put(KnightQuestEntities.BADPATCH.get(), BadPatchEntity.setAttributes());
        event.put(KnightQuestEntities.SHIELD.get(), GhastlingEntity.setAttributes());
        event.put(KnightQuestEntities.MOMMA_LIZZY.get(), MommaLizzyEntity.setAttributes());
        event.put(KnightQuestEntities.GHOSTY.get(), GhostyEntity.setAttributes());
        event.put(KnightQuestEntities.NETHERMAN.get(), NethermanEntity.setAttributes());
        event.put(KnightQuestEntities.NETHERMAN_CLONE.get(), NethermanCloneEntity.setAttributes());
    }

    /**
     * Limits spawn placement of entities.
     */

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(KnightQuestEntities.BADPATCH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.ELDBOMB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.ELDKINGHT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.GHOSTY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.GREMLIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.LIZZY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.RATMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.SAMHAIN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TamableAnimal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.SWAMPMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }

    /**
     * Recipe json generator for certain mobs defined on the array MOB_IDS.
     * @see KQGlobalLootModifiersProvider
     */

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new KQGlobalLootModifiersProvider(packOutput, KnightQuest.MOD_ID, event.getLookupProvider()));
    }

}

