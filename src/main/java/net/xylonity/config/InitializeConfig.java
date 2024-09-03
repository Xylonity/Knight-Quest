package net.xylonity.config;

import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import net.neoforged.fml.config.ModConfig;
import net.xylonity.KnightQuest;

public class InitializeConfig {

    public static void init() {
        ForgeConfigRegistry.INSTANCE.register(KnightQuest.MOD_ID, ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");
    }

}
