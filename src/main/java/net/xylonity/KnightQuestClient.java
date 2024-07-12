package net.xylonity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.xylonity.registry.KnightQuestBlocks;
import net.xylonity.registry.KnightQuestParticles;
import net.xylonity.registry.KnightQuestEntities;
import net.xylonity.common.entity.client.*;
import net.xylonity.common.particle.ChaliceParticle;
import net.xylonity.common.particle.GhostyParticle;
import net.xylonity.common.particle.GremlinParticle;
import net.xylonity.common.particle.StarsetParticle;

public class KnightQuestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(KnightQuestBlocks.GREAT_CHALICE, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.STARSET_PARTICLE, StarsetParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.CHALICE_PARTICLE, ChaliceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GHOSTY_PARTICLE, GhostyParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GREMLIN_PARTICLE, GremlinParticle.Factory::new);

        EntityRendererRegistry.register(KnightQuestEntities.GREMLIN, GremlinRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.ELDBOMB, EldBombRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SAMHAIN, SamhainRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.RATMAN, RatmanRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SWAMPMAN, SwampmanRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.ELDKNIGHT, EldKnightRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.LIZZY, LizzyRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.BADPATCH, BadPatchRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.GHOSTY, GhostyRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SHIELD, ShieldRenderer::new);
    }
}

