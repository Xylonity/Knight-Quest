package dev.xylonity.knightquest.config;

import dev.xylonity.knightquest.KnightQuest;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.minecraftforge.fml.config.ModConfig;

public class InitializeConfig {

    public static void init() {
        ForgeConfigRegistry.INSTANCE.register(KnightQuest.MOD_ID, ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");
    }

}