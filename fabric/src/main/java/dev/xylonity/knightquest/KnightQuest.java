package dev.xylonity.knightquest;

import dev.xylonity.knightlib.compat.registry.KnightLibBlocks;
import dev.xylonity.knightlib.compat.registry.KnightLibItems;
import dev.xylonity.knightquest.client.entity.renderer.*;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import dev.xylonity.knightquest.common.event.KQExtraEvents;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.common.particle.*;
import dev.xylonity.knightquest.common.particle.explosiveenhancement.*;
import dev.xylonity.knightquest.common.particle.explosiveenhancement.blue.*;
import dev.xylonity.knightquest.common.particle.explosiveenhancement.red.*;
import dev.xylonity.knightquest.config.InitializeConfig;
import dev.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
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
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import software.bernie.geckolib.GeckoLib;

public class KnightQuest implements ModInitializer, ClientModInitializer {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;

    @Override
    public void onInitialize() {

        KnightQuestEntities.init();

        if (FabricLoader.getInstance().isModLoaded("forgeconfigapiport"))
            InitializeConfig.init();

        GeckoLib.initialize();

        UseBlockCallback.EVENT.register(new KQExtraEvents());
        ServerTickEvents.END_SERVER_TICK.register(new KQArmorItem.OnEntityTickEvent());
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new KQArmorItem.OnHurtPlayerHandler());
        ServerEntityEvents.ENTITY_LOAD.register(new KQArmorItem.OnEntityJoinWorldEvent());
        ServerLivingEntityEvents.AFTER_DEATH.register(new KQArmorItem.OnEntityDeathWorldEvent());

        FabricDefaultAttributeRegistry.register(KnightQuestEntities.GREMLIN, GremlinEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.ELDBOMB, EldBombEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.SAMHAIN, SamhainEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.RATMAN, RatmanEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.SWAMPMAN, SwampmanEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.ELDKNIGHT, EldKnightEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.LIZZY, LizzyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.BADPATCH, BadPatchEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.GHOSTY, GhostyEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.SHIELD, GhastlingEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.NETHERMAN, NethermanEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(KnightQuestEntities.NETHERMAN_CLONE, NethermanCloneEntity.setAttributes());

        KnightQuestCreativeModeTabs.registerKnightLibItem(() -> KnightLibBlocks.GREAT_CHALICE);
        KnightQuestCreativeModeTabs.registerKnightLibItem(KnightLibItems.SMALL_ESSENCE);
        KnightQuestCreativeModeTabs.registerKnightLibItem(KnightLibItems.GREAT_ESSENCE);

        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.GREMLIN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.ELD_BOMB_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.ELD_KNIGHT_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.RATMAN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.SAMHAIN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.SWAMPMAN_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.LIZZY_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.BADPATCH_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.GHOSTY_EGG);
        KnightQuestCreativeModeTabs.registerPlatformItem(() -> KnightQuestEntities.NETHERMAN_EGG);

        KnightQuestCommon.init();
    }

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SNOWFLAKE_PARTICLE.get(), SnowflakeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.POISON_CLOUD_PARTICLE.get(), PoisonCloudParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.POISON_PARTICLE.get(), PoisonParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLASTWAVE.get(), BlastWaveParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLUEBLASTWAVE.get(), BlueBlastWaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.REDBLASTWAVE.get(), RedBlastWaveParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.FIREBALL.get(), FireballParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.REDFIREBALL.get(), RedFireballParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLUEFIREBALL.get(), BlueFireballParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLANK_FIREBALL.get(), FireballParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SMOKE.get(), SmokeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SPARKS.get(), SparksParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BUBBLE.get(), BubbleParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.BLANK_SHOCKWAVE.get(), ShockwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.UNDERWATERBLASTWAVE.get(), UnderwaterBlastwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.UNDERWATERSPARKS.get(), UnderwaterSparksParticle.Provider::new);

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

        KQArmorItem.ClientEventHandlers.registerClientEvents();

    }

}
