package net.xylonity.knightquest.common.event;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.SpawnPlacements;
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

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {

        event.register(KnightQuestEntities.BADPATCH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.ELDBOMB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.ELDKINGHT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.GHOSTY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.GREMLIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.LIZZY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.RATMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(KnightQuestEntities.SWAMPMAN.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);

    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(event.includeServer(), new KQGlobalLootModifiersProvider(generator, KnightQuest.MOD_ID));
    }

}

