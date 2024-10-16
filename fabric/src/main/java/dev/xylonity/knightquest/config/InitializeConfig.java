package dev.xylonity.knightquest.config;

import dev.xylonity.knightquest.KnightQuest;
import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import net.neoforged.fml.config.ModConfig;

public class InitializeConfig {

    public static void init() {
        ForgeConfigRegistry.INSTANCE.register(KnightQuest.MOD_ID, ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");
    }

}