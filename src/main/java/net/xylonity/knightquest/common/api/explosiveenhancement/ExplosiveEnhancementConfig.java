package net.xylonity.knightquest.common.api.explosiveenhancement;

import net.minecraftforge.common.ForgeConfigSpec;

public class ExplosiveEnhancementConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue showBlastWave;
    public static final ForgeConfigSpec.BooleanValue showFireball;
    public static final ForgeConfigSpec.BooleanValue showMushroomCloud;
    public static final ForgeConfigSpec.BooleanValue showSparks;
    public static final ForgeConfigSpec.DoubleValue sparkSize;
    public static final ForgeConfigSpec.DoubleValue sparkOpacity;
    public static final ForgeConfigSpec.BooleanValue showDefaultExplosion;
    public static final ForgeConfigSpec.BooleanValue underwaterExplosions;
    public static final ForgeConfigSpec.BooleanValue showShockwave;
    public static final ForgeConfigSpec.BooleanValue showUnderwaterBlastWave;
    public static final ForgeConfigSpec.IntValue bubbleAmount;
    public static final ForgeConfigSpec.BooleanValue showUnderwaterSparks;
    public static final ForgeConfigSpec.DoubleValue underwaterSparkSize;
    public static final ForgeConfigSpec.DoubleValue underwaterSparkOpacity;
    public static final ForgeConfigSpec.BooleanValue showDefaultExplosionUnderwater;
    public static final ForgeConfigSpec.BooleanValue dynamicSize;
    public static final ForgeConfigSpec.BooleanValue dynamicUnderwater;
    public static final ForgeConfigSpec.BooleanValue attemptBetterSmallExplosions;
    public static final ForgeConfigSpec.DoubleValue smallExplosionYOffset;
    public static final ForgeConfigSpec.BooleanValue modEnabled;
    public static final ForgeConfigSpec.BooleanValue emissiveExplosion;
    public static final ForgeConfigSpec.BooleanValue emissiveWaterExplosion;
    public static final ForgeConfigSpec.BooleanValue alwaysShow;
    public static final ForgeConfigSpec.BooleanValue debugLogs;

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
