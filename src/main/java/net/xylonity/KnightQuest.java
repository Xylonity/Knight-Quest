package net.xylonity;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.xylonity.common.api.FCAPChecker;
import net.xylonity.common.entity.boss.NethermanCloneEntity;
import net.xylonity.common.entity.boss.NethermanEntity;
import net.xylonity.common.item.KQArmorItem;
import net.xylonity.config.InitializeConfig;
import net.xylonity.datagen.KQEntitySpawn;
import net.xylonity.registry.*;
import net.xylonity.common.entity.entities.*;
import net.xylonity.common.event.KQExtraEvents;
import net.xylonity.datagen.KQLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KnightQuest implements ModInitializer {
	public static final String MOD_ID = "knightquest";
    public static final Logger LOGGER = LoggerFactory.getLogger("knightquest");

	@Override
	public void onInitialize() {

		KnightQuestItems.register();
		KnightQuestCreativeModeTabs.register();

		KnightQuestParticles.register();
		KnightQuestSounds.registerSounds();

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

		KnightQuestEntities.registerModEntities();
		KQLootTableModifiers.modifyLootTables();
		KQEntitySpawn.addEntitySpawns();

		if (FCAPChecker.isLoaded()) {
			KnightQuest.LOGGER.info("[Knight Quest] The mod 'forgeconfigapiport' was detected, reading config file `knightquest.toml` for Knight Quest.");
			InitializeConfig.init();
		} else {
			KnightQuest.LOGGER.warn("[Knight Quest] The mod 'forgeconfigapiport' is not loaded or is using a version lower than 21.0.2. Skipping configuration generation and reading for Knight Quest...");
		}

		UseBlockCallback.EVENT.register(new KQExtraEvents());
		ServerTickEvents.END_SERVER_TICK.register(new KQArmorItem.OnEntityTickEvent());
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new KQArmorItem.OnHurtPlayerHandler());
		ServerEntityEvents.ENTITY_LOAD.register(new KQArmorItem.OnEntityJoinWorldEvent());
		ServerLivingEntityEvents.AFTER_DEATH.register(new KQArmorItem.OnEntityDeathWorldEvent());
	}
}