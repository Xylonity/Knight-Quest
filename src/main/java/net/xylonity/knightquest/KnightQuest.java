package net.xylonity.knightquest;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.xylonity.knightquest.registry.KnightQuestBlocks;
import net.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import net.xylonity.knightquest.registry.KnightQuestItems;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.common.entity.client.*;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import net.xylonity.knightquest.config.KnightQuestCommonConfigs;
import net.xylonity.knightquest.datagen.KQLootModifiers;
import org.slf4j.Logger;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest
{
    public static final String MOD_ID = "knightquest";
    private static final Logger LOGGER = LogUtils.getLogger();

    public KnightQuest()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KnightQuestItems.ITEMS.register(modEventBus);
        KnightQuestCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        KnightQuestBlocks.BLOCKS.register(modEventBus);
        KnightQuestEntities.ENTITY.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        KnightQuestParticles.PARTICLES.register(modEventBus);

        // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, KnightQuestClientConfigs.SPEC, "knightquest-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent public void onServerStarting(ServerStartingEvent event) {  }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(KnightQuestEntities.GREMLIN.get(), GremlinRenderer::new);
            EntityRenderers.register(KnightQuestEntities.ELDBOMB.get(), EldBombRenderer::new);
            EntityRenderers.register(KnightQuestEntities.ELDKINGHT.get(), EldKnightRenderer::new);
            EntityRenderers.register(KnightQuestEntities.SWAMPMAN.get(), SwampmanRenderer::new);
            EntityRenderers.register(KnightQuestEntities.RATMAN.get(), RatmanRenderer::new);
            EntityRenderers.register(KnightQuestEntities.SAMHAIN.get(), SamhainRenderer::new);
            EntityRenderers.register(KnightQuestEntities.LIZZY.get(), LizzyRenderer::new);
            EntityRenderers.register(KnightQuestEntities.BADPATCH.get(), BadPatchRenderer::new);
            EntityRenderers.register(KnightQuestEntities.SHIELD.get(), ShieldRenderer::new);
            EntityRenderers.register(KnightQuestEntities.MOMMA_LIZZY.get(), MommaLizzyRenderer::new);
            EntityRenderers.register(KnightQuestEntities.GHOSTY.get(), GhostyRenderer::new);
        }
    }
}
