package dev.xylonity.knightquest;

import dev.xylonity.knightquest.config.KnightQuestCommonConfigs;
import dev.xylonity.knightquest.datagen.KQLootModifiers;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import dev.xylonity.knightquest.registry.KnightQuestParticles;
import dev.xylonity.knightquest.registry.KnightQuestSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import software.bernie.geckolib.GeckoLib;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KnightQuest.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KnightQuest.MOD_ID);

    public KnightQuest() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KnightQuestEntities.ENTITY.register(modEventBus);
        KnightQuestSounds.SOUNDS.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        KnightQuestParticles.PARTICLES.register(modEventBus);

        GeckoLib.initialize();

        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        KnightQuestCommon.init();
    }

}