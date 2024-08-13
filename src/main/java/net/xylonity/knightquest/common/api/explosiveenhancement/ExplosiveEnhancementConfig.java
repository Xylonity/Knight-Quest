package net.xylonity.knightquest.common.api.explosiveenhancement;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ExplosiveEnhancementConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue showBlastWave;
    public static final ModConfigSpec.BooleanValue showFireball;
    public static final ModConfigSpec.BooleanValue showMushroomCloud;
    public static final ModConfigSpec.BooleanValue showSparks;
    public static final ModConfigSpec.DoubleValue sparkSize;
    public static final ModConfigSpec.DoubleValue sparkOpacity;
    public static final ModConfigSpec.BooleanValue showDefaultExplosion;
    public static final ModConfigSpec.BooleanValue underwaterExplosions;
    public static final ModConfigSpec.BooleanValue showShockwave;
    public static final ModConfigSpec.BooleanValue showUnderwaterBlastWave;
    public static final ModConfigSpec.IntValue bubbleAmount;
    public static final ModConfigSpec.BooleanValue showUnderwaterSparks;
    public static final ModConfigSpec.DoubleValue underwaterSparkSize;
    public static final ModConfigSpec.DoubleValue underwaterSparkOpacity;
    public static final ModConfigSpec.BooleanValue showDefaultExplosionUnderwater;
    public static final ModConfigSpec.BooleanValue dynamicSize;
    public static final ModConfigSpec.BooleanValue dynamicUnderwater;
    public static final ModConfigSpec.BooleanValue attemptBetterSmallExplosions;
    public static final ModConfigSpec.DoubleValue smallExplosionYOffset;
    public static final ModConfigSpec.BooleanValue modEnabled;
    public static final ModConfigSpec.BooleanValue emissiveExplosion;
    public static final ModConfigSpec.BooleanValue emissiveWaterExplosion;
    public static final ModConfigSpec.BooleanValue alwaysShow;
    public static final ModConfigSpec.BooleanValue debugLogs;

    static {
        BUILDER.push("Config file for Explosive Enhancement");

        showBlastWave = BUILDER.comment("Show blast wave effects").define("showBlastWave", true);
        showFireball = BUILDER.comment("Show fireball effects").define("showFireball", true);
        showMushroomCloud = BUILDER.comment("Show mushroom cloud effects").define("showMushroomCloud", true);
        showSparks = BUILDER.comment("Show sparks effects").define("showSparks", true);
        sparkSize = BUILDER.comment("Size of sparks").defineInRange("sparkSize", 5.3, 0.0, Double.MAX_VALUE);
        sparkOpacity = BUILDER.comment("Opacity of sparks").defineInRange("sparkOpacity", 0.70, 0.0, 1.0);
        showDefaultExplosion = BUILDER.comment("Show default explosion effects").define("showDefaultExplosion", false);
        underwaterExplosions = BUILDER.comment("Enable underwater explosions").define("underwaterExplosions", true);
        showShockwave = BUILDER.comment("Show shockwave effects").define("showShockwave", true);
        showUnderwaterBlastWave = BUILDER.comment("Show underwater blast wave effects").define("showUnderwaterBlastWave", true);
        bubbleAmount = BUILDER.comment("Number of bubbles for underwater explosions").defineInRange("bubbleAmount", 50, 0, Integer.MAX_VALUE);
        showUnderwaterSparks = BUILDER.comment("Show underwater sparks effects").define("showUnderwaterSparks", false);
        underwaterSparkSize = BUILDER.comment("Size of underwater sparks").defineInRange("underwaterSparkSize", 4.0, 0.0, Double.MAX_VALUE);
        underwaterSparkOpacity = BUILDER.comment("Opacity of underwater sparks").defineInRange("underwaterSparkOpacity", 0.30, 0.0, 1.0);
        showDefaultExplosionUnderwater = BUILDER.comment("Show default explosion effects underwater").define("showDefaultExplosionUnderwater", false);
        dynamicSize = BUILDER.comment("Enable dynamic explosion size").define("dynamicSize", true);
        dynamicUnderwater = BUILDER.comment("Enable dynamic underwater explosion size").define("dynamicUnderwater", true);
        attemptBetterSmallExplosions = BUILDER.comment("Attempt better small explosion effects").define("attemptBetterSmallExplosions", true);
        smallExplosionYOffset = BUILDER.comment("Y offset for small explosions").defineInRange("smallExplosionYOffset", -0.5, -Double.MAX_VALUE, Double.MAX_VALUE);
        modEnabled = BUILDER.comment("Enable the mod").define("modEnabled", true);
        emissiveExplosion = BUILDER.comment("Enable emissive explosion effects").define("emissiveExplosion", true);
        emissiveWaterExplosion = BUILDER.comment("Enable emissive water explosion effects").define("emissiveWaterExplosion", true);
        alwaysShow = BUILDER.comment("Always show explosion effects").define("alwaysShow", false);
        debugLogs = BUILDER.comment("Enable debug logs").define("debugLogs", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
