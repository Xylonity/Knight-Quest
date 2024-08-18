package net.xylonity.knightquest.common.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
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
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.STARSET_PARTICLE.get(), StarsetParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.YELLOW_PARTICLE.get(), YellowParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.SNOWFLAKE_PARTICLE.get(), SnowflakeParticle.Provider::new);

        /*
         * Explosive Enhancement particle registers
         */

        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.BLASTWAVE.get(), BlastWaveParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.FIREBALL.get(), FireballParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.BLANK_FIREBALL.get(), FireballParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.SMOKE.get(), SmokeParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.SPARKS.get(), SparksParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.BUBBLE.get(), BubbleParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.BLANK_SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.UNDERWATERBLASTWAVE.get(), UnderwaterBlastwaveParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.UNDERWATERSPARKS.get(), UnderwaterSparksParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.BLUEBLASTWAVE.get(), BlueBlastWaveParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.BLUEFIREBALL.get(), BlueFireballParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.REDBLASTWAVE.get(), RedBlastWaveParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(KnightQuestParticles.REDFIREBALL.get(), RedFireballParticle.Provider::new);
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
    }

    @SubscribeEvent
    public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmor.class, new GeoItemArmorRenderer());
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmorChest.class, new GeoItemArmorChestRenderer());
        GeoArmorRenderer.registerArmorRenderer(GeoItemArmorLeg.class, new GeoItemArmorLegRenderer());
    }
}
