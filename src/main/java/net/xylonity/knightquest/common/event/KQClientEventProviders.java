package net.xylonity.knightquest.common.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.client.*;
import net.xylonity.knightquest.common.particle.GhostyParticle;
import net.xylonity.knightquest.common.particle.GremlinParticle;
import net.xylonity.knightquest.common.particle.StarsetParticle;
import net.xylonity.knightquest.common.particle.YellowParticle;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.registry.KnightQuestParticles;

@EventBusSubscriber(modid = KnightQuest.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KQClientEventProviders
{
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
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

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(KnightQuestParticles.STARSET_PARTICLE.get(), StarsetParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.YELLOW_PARTICLE.get(), YellowParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
    }

}
