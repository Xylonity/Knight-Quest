package net.xylonity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.xylonity.client.entity.renderer.*;
import net.xylonity.common.particle.*;
import net.xylonity.common.particle.explosiveenhancement.*;
import net.xylonity.common.particle.explosiveenhancement.blue.BlueBlastWaveParticle;
import net.xylonity.common.particle.explosiveenhancement.blue.BlueFireballParticle;
import net.xylonity.common.particle.explosiveenhancement.red.RedBlastWaveParticle;
import net.xylonity.common.particle.explosiveenhancement.red.RedFireballParticle;
import net.xylonity.registry.KnightQuestParticles;
import net.xylonity.registry.KnightQuestEntities;

public class KnightQuestClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.CHALICE_PARTICLE, ChaliceParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GHOSTY_PARTICLE, GhostyParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GREMLIN_PARTICLE, GremlinParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SNOWFLAKE_PARTICLE, SnowflakeParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLASTWAVE, BlastWaveParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLUEBLASTWAVE, BlueBlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.REDBLASTWAVE, RedBlastWaveParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.FIREBALL, FireballParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.REDFIREBALL, RedFireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLUEFIREBALL, BlueFireballParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLANK_FIREBALL, FireballParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SMOKE, SmokeParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SPARKS, SparkParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BUBBLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLANK_SHOCKWAVE, ShockwaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.UNDERWATERBLASTWAVE, UnderwaterBlastWaveParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.UNDERWATERSPARKS, UnderwaterSparkParticle.Factory::new);

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
        EntityRendererRegistry.register(KnightQuestEntities.NETHERMAN, NethermanRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.NETHERMAN_CLONE, NethermanCloneRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.NETHERMAN_TELEPORT_CHARGE, NethermanTeleportChargeRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SWAMPMAN_AXE, SwampmanAxeRenderer::new);
    }
}

