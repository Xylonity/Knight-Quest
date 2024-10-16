package dev.xylonity.knightquest;

import dev.xylonity.knightquest.config.KnightQuestCommonConfigs;
import dev.xylonity.knightquest.datagen.KQLootModifiers;
import dev.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, KnightQuest.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, KnightQuest.MOD_ID);

    public KnightQuest() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KnightQuestEntities.ENTITY.register(modEventBus);
        KnightQuestItems.ITEMS.register(modEventBus);
        KnightQuestCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        PARTICLES.register(modEventBus);
        SOUNDS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        KnightQuestCommon.init();
    }

}