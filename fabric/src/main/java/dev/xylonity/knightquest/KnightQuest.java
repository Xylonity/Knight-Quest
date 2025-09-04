package dev.xylonity.knightquest;

import dev.xylonity.knightquest.client.entity.renderer.*;
import dev.xylonity.knightquest.common.event.KQArmorEvents;
import dev.xylonity.knightquest.common.event.KQExtraEvents;
import dev.xylonity.knightquest.common.particle.*;
import dev.xylonity.knightquest.config.InitializeConfig;
import dev.xylonity.knightquest.datagen.KQEntitySpawn;
import dev.xylonity.knightquest.datagen.KQLootTableModifier;
import dev.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import dev.xylonity.knightquest.registry.KnightQuestParticles;
import dev.xylonity.knightquest.registry.KnightQuestWeapons;
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

public class KnightQuest implements ModInitializer, ClientModInitializer {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;

    @Override
    public void onInitialize() {

        KQEntitySpawn.register();
        KQLootTableModifier.register();
        KnightQuestWeapons.init();

        if (FabricLoader.getInstance().isModLoaded("forgeconfigapiport"))
            InitializeConfig.init();

        UseBlockCallback.EVENT.register(new KQExtraEvents());
        ServerTickEvents.END_SERVER_TICK.register(new KQArmorEvents.OnEntityTickEvent());
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new KQArmorEvents.OnHurtPlayerHandler());
        ServerEntityEvents.ENTITY_LOAD.register(new KQArmorEvents.OnEntityJoinWorldEvent());
        ServerLivingEntityEvents.AFTER_DEATH.register(new KQArmorEvents.OnEntityDeathWorldEvent());

        KnightQuestCreativeModeTabs.registerWeaponItem(() -> KnightQuestWeapons.PALADIN_SWORD);
        KnightQuestCreativeModeTabs.registerWeaponItem(() -> KnightQuestWeapons.KHOPESH);
        KnightQuestCreativeModeTabs.registerWeaponItem(() -> KnightQuestWeapons.CLEAVER);
        KnightQuestCreativeModeTabs.registerWeaponItem(() -> KnightQuestWeapons.KUKRI);
        KnightQuestCreativeModeTabs.registerWeaponItem(() -> KnightQuestWeapons.UCHIGATANA);
        KnightQuestCreativeModeTabs.registerWeaponItem(() -> KnightQuestWeapons.NAIL);

        KnightQuestEntities.registerEntityAttributes(FabricDefaultAttributeRegistry::register);

        KnightQuestCommon.init();
    }

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GHOSTY_PARTICLE.get(), GhostyParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.GREMLIN_PARTICLE.get(), GremlinParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.SNOWFLAKE_PARTICLE.get(), SnowflakeParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.POISON_CLOUD_PARTICLE.get(), PoisonCloudParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(KnightQuestParticles.POISON_PARTICLE.get(), PoisonParticle.Provider::new);

        EntityRendererRegistry.register(KnightQuestEntities.GREMLIN.get(), GremlinRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.ELDBOMB.get(), EldBombRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SAMHAIN.get(), SamhainRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.RATMAN.get(), RatmanRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SWAMPMAN.get(), SwampmanRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.FALLEN_KNIGHT.get(), FallenKnightRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.ELDKNIGHT.get(), EldKnightRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.LIZZY.get(), LizzyRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.BADPATCH.get(), BadPatchRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.GHOSTY.get(), GhostyRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SHIELD.get(), ShieldRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.NETHERMAN.get(), NethermanRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.NETHERMAN_CLONE.get(), NethermanCloneRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.NETHERMAN_PROJECTILE_CHARGE.get(), NethermanProjectileChargeRenderer::new);
        EntityRendererRegistry.register(KnightQuestEntities.SWAMPMAN_AXE.get(), SwampmanAxeRenderer::new);

        KQArmorEvents.ClientEventHandlers.registerClientEvents();

    }

}