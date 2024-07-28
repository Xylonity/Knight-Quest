package net.xylonity.knightquest.common.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.client.*;
import net.xylonity.knightquest.common.particle.*;
import net.xylonity.knightquest.common.particle.explosiveenhancement.*;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.registry.KnightQuestParticles;

@Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KQClientEventProviders {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(KnightQuestParticles.STARSET_PARTICLE.get(), StarsetParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.YELLOW_PARTICLE.get(), YellowParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.SNOWFLAKE_PARTICLE.get(), SnowflakeParticle.Provider::new);

        /*
         * Explosive Enhancement particle registers
         */

        event.registerSpriteSet(KnightQuestParticles.BLASTWAVE.get(), BlastWaveParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.FIREBALL.get(), FireballParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.BLANK_FIREBALL.get(), FireballParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.SMOKE.get(), SmokeParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.SPARKS.get(), SparksParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.BUBBLE.get(), BubbleParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.BLANK_SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.UNDERWATERBLASTWAVE.get(), UnderwaterBlastwaveParticle.Provider::new);
        event.registerSpriteSet(KnightQuestParticles.UNDERWATERSPARKS.get(), UnderwaterSparksParticle.Provider::new);
    }

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
        EntityRenderers.register(KnightQuestEntities.NETHERMAN.get(), NethermanRenderer::new);
        EntityRenderers.register(KnightQuestEntities.NETHERMAN_TELEPORT_CHARGE.get(), NethermanTeleportChargeRenderer::new);
        EntityRenderers.register(KnightQuestEntities.LASER_BEAM.get(), LaserBeamRenderer::new);
    }

}
