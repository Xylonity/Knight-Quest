package net.xylonity.knightquest;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.xylonity.knightquest.common.event.KQClientEventProviders;
import net.xylonity.knightquest.common.event.KQEventRegisters;
import net.xylonity.knightquest.common.event.KQExtraEvents;
import net.xylonity.knightquest.registry.KnightQuestBlocks;
import net.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import net.xylonity.knightquest.registry.KnightQuestItems;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.common.item.KQArmorItem;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import net.xylonity.knightquest.config.KnightQuestCommonConfigs;
import net.xylonity.knightquest.datagen.KQLootModifiers;
import org.slf4j.Logger;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest
{
    public static final String MOD_ID = "knightquest";
    public static final Logger LOGGER = LogUtils.getLogger();

    public KnightQuest(IEventBus modEventBus, ModContainer modContainer) throws NoSuchFieldException {

        KnightQuestItems.ITEMS.register(modEventBus);
        KnightQuestCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        KnightQuestBlocks.BLOCKS.register(modEventBus);
        KnightQuestEntities.ENTITIES.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        KnightQuestParticles.PARTICLES.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        modEventBus.addListener(KQEventRegisters::registerEntityAttributes);
        modEventBus.addListener(KQEventRegisters::registerSpawnPlacements);
        modEventBus.addListener(KQEventRegisters::gatherData);
        modEventBus.addListener(KQClientEventProviders::registerEntityRenderers);
        modEventBus.addListener(KQClientEventProviders::registerParticleFactories);

        NeoForge.EVENT_BUS.addListener(KQArmorItem.ArmorStatusManagerEvents::onLivingTick);
        NeoForge.EVENT_BUS.addListener(KQArmorItem.ArmorStatusManagerEvents::onArrowHit);
        NeoForge.EVENT_BUS.addListener(KQArmorItem.ArmorStatusManagerEvents::onLivingDead);
        NeoForge.EVENT_BUS.addListener(KQArmorItem.ArmorStatusManagerEvents::onLivingUpdate);
        NeoForge.EVENT_BUS.addListener(KQArmorItem.ArmorStatusManagerEvents::onLivingHurt);
        NeoForge.EVENT_BUS.addListener(KQExtraEvents::entitySamhainSpawnHandler);

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

}
