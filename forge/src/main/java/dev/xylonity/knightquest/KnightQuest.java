package dev.xylonity.knightquest;

import dev.xylonity.knightquest.config.KnightQuestCommonConfigs;
import dev.xylonity.knightquest.datagen.KQLootModifiers;
import dev.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import software.bernie.geckolib.GeckoLib;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KnightQuest.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KnightQuest.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, KnightQuest.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, KnightQuest.MOD_ID);

    public KnightQuest() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KnightQuestEntities.ENTITY.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        GeckoLib.initialize();

        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        PARTICLES.register(modEventBus);
        SOUNDS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        KnightQuestCommon.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.GREMLIN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.ELD_BOMB_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.ELD_KNIGHT_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.RATMAN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.SAMHAIN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.SWAMPMAN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.LIZZY_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.BADPATCH_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.GHOSTY_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(KnightQuestEntities.NETHERMAN_EGG);
    }

}