package net.xylonity.knightquest.common.api.explosiveenhancement;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

public class ExplosiveValues {
    static Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("explosiveenhancement.toml");
    private static final boolean V = Files.exists(CONFIG_PATH);
    public static boolean showBlastWave = V ? ExplosiveEnhancementConfig.showBlastWave.get() : true;
    public static boolean showFireball = V ? ExplosiveEnhancementConfig.showFireball.get() : true;
    public static boolean showMushroomCloud = V ? ExplosiveEnhancementConfig.showMushroomCloud.get() : true;
    public static boolean showSparks = V ? ExplosiveEnhancementConfig.showSparks.get() : true;
    public static double sparkSize = V ? ExplosiveEnhancementConfig.sparkSize.get() : 5.3;
    public static double sparkOpacity = V ? ExplosiveEnhancementConfig.sparkOpacity.get() : 0.70;
    public static boolean showDefaultExplosion = V ? ExplosiveEnhancementConfig.showDefaultExplosion.get() : false;
    public static boolean underwaterExplosions = V ? ExplosiveEnhancementConfig.underwaterExplosions.get() : true;
    public static boolean showShockwave = V ? ExplosiveEnhancementConfig.showShockwave.get() : true;
    public static boolean showUnderwaterBlastWave = V ? ExplosiveEnhancementConfig.showUnderwaterBlastWave.get() : true;
    public static int bubbleAmount = V ? ExplosiveEnhancementConfig.bubbleAmount.get() : 50;
    public static boolean showUnderwaterSparks = V ? ExplosiveEnhancementConfig.showUnderwaterSparks.get() : false;
    public static double underwaterSparkSize = V ? ExplosiveEnhancementConfig.underwaterSparkSize.get() : 4.0;
    public static double underwaterSparkOpacity = V ? ExplosiveEnhancementConfig.underwaterSparkOpacity.get() : 0.30;
    public static boolean showDefaultExplosionUnderwater = V ? ExplosiveEnhancementConfig.showDefaultExplosionUnderwater.get() : false;
    public static boolean dynamicSize = V ? ExplosiveEnhancementConfig.dynamicSize.get() : true;
    public static boolean dynamicUnderwater = V ? ExplosiveEnhancementConfig.dynamicUnderwater.get() : true;
    public static boolean attemptBetterSmallExplosions = V ? ExplosiveEnhancementConfig.attemptBetterSmallExplosions.get() : true;
    public static double smallExplosionYOffset = V ? ExplosiveEnhancementConfig.smallExplosionYOffset.get() : -0.5;
    public static boolean modEnabled = V ? ExplosiveEnhancementConfig.modEnabled.get() : true;
    public static boolean emissiveExplosion = V ? ExplosiveEnhancementConfig.emissiveExplosion.get() : true;
    public static boolean emissiveWaterExplosion = V ? ExplosiveEnhancementConfig.emissiveWaterExplosion.get() : true;
    public static boolean alwaysShow = V ? ExplosiveEnhancementConfig.alwaysShow.get() : false;
    public static boolean debugLogs = V ? ExplosiveEnhancementConfig.debugLogs.get() : false;

}
