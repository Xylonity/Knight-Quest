package dev.xylonity.knightquest;

import dev.xylonity.knightquest.client.entity.renderer.*;
import dev.xylonity.knightquest.common.event.KQExtraEvents;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.common.particle.*;
import dev.xylonity.knightquest.common.particle.explosiveenhancement.*;
import dev.xylonity.knightquest.common.particle.explosiveenhancement.blue.*;
import dev.xylonity.knightquest.common.particle.explosiveenhancement.red.*;
import dev.xylonity.knightquest.config.InitializeConfig;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import dev.xylonity.knightquest.registry.KnightQuestParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.loader.api.FabricLoader;
import software.bernie.geckolib.GeckoLib;

public class KnightQuest implements ModInitializer, ClientModInitializer {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;

    @Override
    public void onInitialize() {

        KnightQuestParticles.init();
        KnightQuestEntities.init();

        if (FabricLoader.getInstance().isModLoaded("forgeconfigapiport"))
            InitializeConfig.init();

        GeckoLib.initialize();

        UseBlockCallback.EVENT.register(new KQExtraEvents());
        ServerTickEvents.END_SERVER_TICK.register(new KQArmorItem.OnEntityTickEvent());
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new KQArmorItem.OnHurtPlayerHandler());
        ServerEntityEvents.ENTITY_LOAD.register(new KQArmorItem.OnEntityJoinWorldEvent());
        ServerLivingEntityEvents.AFTER_DEATH.register(new KQArmorItem.OnEntityDeathWorldEvent());

        KnightQuestCommon.init();
    }

    @Override
    public void onInitializeClient() {

        KQArmorItem.ClientEventHandlers.registerClientEvents();

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GHOSTY_PARTICLE, GhostyParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GREMLIN_PARTICLE, GremlinParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SNOWFLAKE_PARTICLE, SnowflakeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.POISON_CLOUD_PARTICLE, PoisonCloudParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.POISON_PARTICLE, PoisonParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLASTWAVE, BlastWaveParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLUEBLASTWAVE, BlueBlastWaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.REDBLASTWAVE, RedBlastWaveParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.FIREBALL, FireballParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.REDFIREBALL, RedFireballParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLUEFIREBALL, BlueFireballParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLANK_FIREBALL, FireballParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SMOKE, SmokeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SPARKS, SparksParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BUBBLE, BubbleParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SHOCKWAVE, ShockwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLANK_SHOCKWAVE, ShockwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.UNDERWATERBLASTWAVE, UnderwaterBlastwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.UNDERWATERSPARKS, UnderwaterSparksParticle.Provider::new);

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
