package net.xylonity.knightquest.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class KnightQuestCommonConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue POISON_ELDKNIGHT;
    public static final ModConfigSpec.IntValue NUM_ELDBOMB_ELDKNIGHT;
    public static final ModConfigSpec.DoubleValue HEAL_ELDKNIGHT;
    public static final ModConfigSpec.DoubleValue DROP_CHANCE_SMALL_ESSENCE;
    public static final ModConfigSpec.DoubleValue DROP_CHANCE_RATMAN_EYE;
    public static final ModConfigSpec.DoubleValue DROP_CHANCE_LIZZY_SCALE;
    public static final ModConfigSpec.DoubleValue INVULNERABILITY_RADIUS_GHOSTY;

    public static final ModConfigSpec.BooleanValue ENABLE_DEEPSLATESET;
    public static final ModConfigSpec.BooleanValue ENABLE_EVOKERSET;
    public static final ModConfigSpec.BooleanValue ENABLE_SQUIRESET;
    public static final ModConfigSpec.BooleanValue ENABLE_BLAZESET;
    public static final ModConfigSpec.BooleanValue ENABLE_DRAGONSET;
    public static final ModConfigSpec.BooleanValue ENABLE_BAMBOOSET_GREEN;
    public static final ModConfigSpec.BooleanValue ENABLE_SHINOBI;
    public static final ModConfigSpec.BooleanValue ENABLE_BAMBOOSET;
    public static final ModConfigSpec.BooleanValue ENABLE_PATHSET;
    public static final ModConfigSpec.BooleanValue ENABLE_BOWSET;
    public static final ModConfigSpec.BooleanValue ENABLE_BATSET;
    public static final ModConfigSpec.BooleanValue ENABLE_SHIELDSET;
    public static final ModConfigSpec.BooleanValue ENABLE_PHANTOMSET;
    public static final ModConfigSpec.BooleanValue ENABLE_HORNSET;
    public static final ModConfigSpec.BooleanValue ENABLE_SEASET;
    public static final ModConfigSpec.BooleanValue ENABLE_PIRATESET;
    public static final ModConfigSpec.BooleanValue ENABLE_SPIDERSET;
    public static final ModConfigSpec.BooleanValue ENABLE_NETHERSET;
    public static final ModConfigSpec.BooleanValue ENABLE_SKULK;
    public static final ModConfigSpec.BooleanValue ENABLE_STRAWHATSET;
    public static final ModConfigSpec.BooleanValue ENABLE_ENDERMANSET;
    public static final ModConfigSpec.BooleanValue ENABLE_VETERANSET;
    public static final ModConfigSpec.BooleanValue ENABLE_FORZESET;
    public static final ModConfigSpec.BooleanValue ENABLE_CREEPERSET;
    public static final ModConfigSpec.BooleanValue ENABLE_POLAR;
    public static final ModConfigSpec.BooleanValue ENABLE_SILVERSET;
    public static final ModConfigSpec.BooleanValue ENABLE_HOLLOWSET;
    public static final ModConfigSpec.BooleanValue ENABLE_WITHERSET;
    public static final ModConfigSpec.BooleanValue ENABLE_APPLE_SET;
    public static final ModConfigSpec.BooleanValue ENABLE_CONQUISTADORSET;
    public static final ModConfigSpec.BooleanValue ENABLE_WITCH;
    public static final ModConfigSpec.BooleanValue ENABLE_TENGU_HELMET;
    public static final ModConfigSpec.BooleanValue ENABLE_HUSKSET;
    public static final ModConfigSpec.BooleanValue ENABLE_BAMBOOSET_BLUE;
    public static final ModConfigSpec.BooleanValue ENABLE_WARLORDSET;
    public static final ModConfigSpec.BooleanValue ENABLE_ZOMBIESET;
    public static final ModConfigSpec.BooleanValue ENABLE_SILVERFISHSET;
    public static final ModConfigSpec.BooleanValue ENABLE_SKELETONSET;

    static {
        BUILDER.push("Config file for Knight Quest");
        BUILDER.comment("");
        POISON_ELDKNIGHT = BUILDER.comment("Eld Knight entity configuration")
                .define("Poison passive attack", true);
        NUM_ELDBOMB_ELDKNIGHT = BUILDER.defineInRange("Number of Eld Bombs generated", 3, 0, 6);
        HEAL_ELDKNIGHT = BUILDER.defineInRange("Quantity of healing each 4 seconds", 3.0, 0.0, 20.0);
        BUILDER.comment("");
        DROP_CHANCE_SMALL_ESSENCE = BUILDER.comment("Drop chance configuration")
                .defineInRange("Drop chance for small essence", 0.15, 0, 1);
        DROP_CHANCE_RATMAN_EYE = BUILDER.defineInRange("Drop chance for ratman eye", 0.20, 0, 1);
        DROP_CHANCE_LIZZY_SCALE = BUILDER.defineInRange("Drop chance for lizzy scale", 0.30, 0, 1);

        BUILDER.comment("");
        INVULNERABILITY_RADIUS_GHOSTY = BUILDER.defineInRange("Ghosty invulnerability radius", 7.0, 0.0, 25.0);

        BUILDER.comment("");
        BUILDER.comment("Armor Passives");
        BUILDER.comment("");

        ENABLE_DEEPSLATESET = BUILDER.comment("Enable Deepslate Set Passive").define("enableDeepslateSet", true);
        ENABLE_EVOKERSET = BUILDER.comment("Enable Evoker Set Passive").define("enableEvokerSet", true);
        ENABLE_SQUIRESET = BUILDER.comment("Enable Squire Set Passive").define("enableSquireSet", true);
        ENABLE_BLAZESET = BUILDER.comment("Enable Blaze Set Passive").define("enableBlazeSet", true);
        ENABLE_DRAGONSET = BUILDER.comment("Enable Dragon Set Passive").define("enableDragonSet", true);
        ENABLE_BAMBOOSET_GREEN = BUILDER.comment("Enable Bamboo Set Green Passive").define("enableBambooSetGreen", true);
        ENABLE_SHINOBI = BUILDER.comment("Enable Shinobi Set Passive").define("enableShinobi", true);
        ENABLE_BAMBOOSET = BUILDER.comment("Enable Bamboo Set Passive").define("enableBambooSet", true);
        ENABLE_PATHSET = BUILDER.comment("Enable Path Set Passive").define("enablePathSet", true);
        ENABLE_BOWSET = BUILDER.comment("Enable Bow Set Passive").define("enableBowSet", true);
        ENABLE_BATSET = BUILDER.comment("Enable Bat Set Passive").define("enableBatSet", true);
        ENABLE_SHIELDSET = BUILDER.comment("Enable Shield Set Passive").define("enableShieldSet", true);
        ENABLE_PHANTOMSET = BUILDER.comment("Enable Phantom Set Passive").define("enablePhantomSet", true);
        ENABLE_HORNSET = BUILDER.comment("Enable Horn Set Passive").define("enableHornSet", true);
        ENABLE_SEASET = BUILDER.comment("Enable Sea Set Passive").define("enableSeaSet", true);
        ENABLE_PIRATESET = BUILDER.comment("Enable Pirate Set Passive").define("enablePirateSet", true);
        ENABLE_SPIDERSET = BUILDER.comment("Enable Spider Set Passive").define("enableSpiderSet", true);
        ENABLE_NETHERSET = BUILDER.comment("Enable Nether Set Passive").define("enableNetherSet", true);
        ENABLE_SKULK = BUILDER.comment("Enable Skulk Passive").define("enableSkulk", true);
        ENABLE_STRAWHATSET = BUILDER.comment("Enable Straw Hat Set Passive").define("enableStrawHatSet", true);
        ENABLE_ENDERMANSET = BUILDER.comment("Enable Enderman Set Passive").define("enableEndermanSet", true);
        ENABLE_VETERANSET = BUILDER.comment("Enable Veteran Set Passive").define("enableVeteranSet", true);
        ENABLE_FORZESET = BUILDER.comment("Enable Forze Set Passive").define("enableForzeSet", true);
        ENABLE_CREEPERSET = BUILDER.comment("Enable Creeper Set Passive").define("enableCreeperSet", true);
        ENABLE_POLAR = BUILDER.comment("Enable Polar Passive").define("enablePolar", true);
        ENABLE_SILVERSET = BUILDER.comment("Enable Silver Set Passive").define("enableSilverSet", true);
        ENABLE_HOLLOWSET = BUILDER.comment("Enable Hollow Set Passive").define("enableHollowSet", true);
        ENABLE_WITHERSET = BUILDER.comment("Enable Wither Set Passive").define("enableWitherSet", true);
        ENABLE_APPLE_SET = BUILDER.comment("Enable Apple Set Passive").define("enableAppleSet", true);
        ENABLE_CONQUISTADORSET = BUILDER.comment("Enable Conquistador Set Passive").define("enableConquistadorSet", true);
        ENABLE_WITCH = BUILDER.comment("Enable Witch Passive").define("enableWitch", true);
        ENABLE_TENGU_HELMET = BUILDER.comment("Enable Tengu Helmet Passive").define("enableTenguHelmet", true);
        ENABLE_HUSKSET = BUILDER.comment("Enable Husk Set Passive").define("enableHuskSet", true);
        ENABLE_BAMBOOSET_BLUE = BUILDER.comment("Enable Bamboo Set Blue Passive").define("enableBambooSetBlue", true);
        ENABLE_WARLORDSET = BUILDER.comment("Enable Warlord Set Passive").define("enableWarlordSet", true);
        ENABLE_ZOMBIESET = BUILDER.comment("Enable Zombie Set Passive").define("enableZombieSet", true);
        ENABLE_SILVERFISHSET = BUILDER.comment("Enable Silverfish Set Passive").define("enableSilverfishSet", true);
        ENABLE_SKELETONSET = BUILDER.comment("Enable Skeleton Set Passive").define("enableSkeletonSet", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
