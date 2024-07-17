package net.xylonity.knightquest.common.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.client.GeoItemArmor;
import net.xylonity.knightquest.common.client.GeoItemArmorRenderer;
import net.xylonity.knightquest.common.client.chest.GeoItemArmorChest;
import net.xylonity.knightquest.common.client.chest.GeoItemArmorChestRenderer;
import net.xylonity.knightquest.common.client.leg.GeoItemArmorLeg;
import net.xylonity.knightquest.common.client.leg.GeoItemArmorLegRenderer;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import net.xylonity.knightquest.common.particle.GhostyParticle;
import net.xylonity.knightquest.common.particle.GremlinParticle;
import net.xylonity.knightquest.common.particle.StarsetParticle;
import net.xylonity.knightquest.common.particle.YellowParticle;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KQClientEventProviders {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.register(KnightQuestParticles.STARSET_PARTICLE.get(), StarsetParticle.Provider::new);
        event.register(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        event.register(KnightQuestParticles.YELLOW_PARTICLE.get(), YellowParticle.Provider::new);
        event.register(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
    }
    @SubscribeEvent
    public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmor.class, new GeoItemArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmorChest.class, new GeoItemArmorChestRenderer());
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmorLeg.class, new GeoItemArmorLegRenderer());
    }
}
