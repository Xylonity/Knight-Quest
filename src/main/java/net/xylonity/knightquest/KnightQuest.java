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
import net.xylonity.knightquest.registry.*;
import net.xylonity.knightquest.common.entity.client.*;
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
        KnightQuestSounds.SOUNDS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        MinecraftForge.EVENT_BUS.register(this);

    }

    @SubscribeEvent public void onServerStarting(ServerStartingEvent event) {  }

}
