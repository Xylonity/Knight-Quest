package net.xylonity.knightquest.common.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.datagen.KQAddItemModifier;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.common.entity.entities.*;

import javax.annotation.Nonnull;

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
    public static void registerSpawnPlacements(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(KnightQuestEntities.BADPATCH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.ELDBOMB.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.ELDKINGHT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.GHOSTY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.GREMLIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.LIZZY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.RATMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.SAMHAIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TamableAnimal::checkAnimalSpawnRules);
            SpawnPlacements.register(KnightQuestEntities.SWAMPMAN.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        });
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {

        /**
         * Declaration of certain mobs that will drop the small_essence item
         */

        final String[] MOB_IDS = {
                "creeper",
                "spider",
                "skeleton",
                "zombie",
                "cave_spider",
                "blaze",
                "enderman",
                "ghast",
                "magma_cube",
                "phantom",
                "slime",
                "stray",
                "vex",
                "drowned",
                "gremlin",
                "eldknight",
                "samhain",
                "ratman",
                "swampman",
                "eldbomb",
                "lizzy",
                "bad_patch"
        };

        final String RATMAN_ID = "ratman";
        final String LIZZY_ID = "lizzy";

        for (String mobId : MOB_IDS) {
            event.getRegistry().register(new KQAddItemModifier.Serializer().setRegistryName(
                    new ResourceLocation(KnightQuest.MOD_ID, mobId + "_small_essence")
            ));
        }

        event.getRegistry().register(new KQAddItemModifier.Serializer().setRegistryName(
                new ResourceLocation(KnightQuest.MOD_ID, RATMAN_ID + "_ratman_eye")
        ));

        event.getRegistry().register(new KQAddItemModifier.Serializer().setRegistryName(
                new ResourceLocation(KnightQuest.MOD_ID, LIZZY_ID + "_lizzy_scale")
        ));

    }

}

