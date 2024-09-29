package net.xylonity.knightquest.common.event;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.client.GeoItemArmor;
import net.xylonity.knightquest.common.client.GeoItemArmorRenderer;
import net.xylonity.knightquest.common.client.chest.GeoItemArmorChest;
import net.xylonity.knightquest.common.client.chest.GeoItemArmorChestRenderer;
import net.xylonity.knightquest.common.client.leg.GeoItemArmorLeg;
import net.xylonity.knightquest.common.client.leg.GeoItemArmorLegRenderer;
import net.xylonity.knightquest.common.entity.client.*;
import net.xylonity.knightquest.common.particle.*;
import net.xylonity.knightquest.common.particle.explosiveenhancement.*;
import net.xylonity.knightquest.common.particle.explosiveenhancement.blue.BlueBlastWaveParticle;
import net.xylonity.knightquest.common.particle.explosiveenhancement.blue.BlueFireballParticle;
import net.xylonity.knightquest.common.particle.explosiveenhancement.red.RedBlastWaveParticle;
import net.xylonity.knightquest.common.particle.explosiveenhancement.red.RedFireballParticle;
import net.xylonity.knightquest.registry.KnightQuestEntities;
import net.xylonity.knightquest.registry.KnightQuestParticles;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KQClientEventProviders {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.register(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        event.register(KnightQuestParticles.YELLOW_PARTICLE.get(), YellowParticle.Provider::new);
        event.register(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
        event.register(KnightQuestParticles.SNOWFLAKE_PARTICLE.get(), SnowflakeParticle.Provider::new);
        event.register(KnightQuestParticles.POISON_PARTICLE.get(), PoisonParticle.Provider::new);
        event.register(KnightQuestParticles.POISON_CLOUD_PARTICLE.get(), PoisonCloudParticle.Provider::new);

        /*
         * Explosive Enhancement particle registers
         */

        event.register(KnightQuestParticles.BLASTWAVE.get(), BlastWaveParticle.Provider::new);
        event.register(KnightQuestParticles.FIREBALL.get(), FireballParticle.Provider::new);
        event.register(KnightQuestParticles.BLANK_FIREBALL.get(), FireballParticle.Provider::new);
        event.register(KnightQuestParticles.SMOKE.get(), SmokeParticle.Provider::new);
        event.register(KnightQuestParticles.SPARKS.get(), SparksParticle.Provider::new);
        event.register(KnightQuestParticles.BUBBLE.get(), BubbleParticle.Provider::new);
        event.register(KnightQuestParticles.SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.register(KnightQuestParticles.BLANK_SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        event.register(KnightQuestParticles.UNDERWATERBLASTWAVE.get(), UnderwaterBlastwaveParticle.Provider::new);
        event.register(KnightQuestParticles.UNDERWATERSPARKS.get(), UnderwaterSparksParticle.Provider::new);

        event.register(KnightQuestParticles.BLUEBLASTWAVE.get(), BlueBlastWaveParticle.Provider::new);
        event.register(KnightQuestParticles.BLUEFIREBALL.get(), BlueFireballParticle.Provider::new);

        event.register(KnightQuestParticles.REDBLASTWAVE.get(), RedBlastWaveParticle.Provider::new);
        event.register(KnightQuestParticles.REDFIREBALL.get(), RedFireballParticle.Provider::new);
    }

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
        EntityRenderers.register(KnightQuestEntities.NETHERMAN.get(), NethermanRenderer::new);
        EntityRenderers.register(KnightQuestEntities.NETHERMAN_TELEPORT_CHARGE.get(), NethermanTeleportChargeRenderer::new);
        EntityRenderers.register(KnightQuestEntities.NETHERMAN_CLONE.get(), NethermanCloneRenderer::new);
        EntityRenderers.register(KnightQuestEntities.SWAMPMAN_AXE.get(), SwampmanAxeRenderer::new);
    }

    @SubscribeEvent
    public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmor.class, new GeoItemArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmorChest.class, new GeoItemArmorChestRenderer());
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmorLeg.class, new GeoItemArmorLegRenderer());
    }
}
