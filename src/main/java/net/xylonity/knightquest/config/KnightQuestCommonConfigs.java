package net.xylonity.knightquest.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class KnightQuestCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Eld Knight Configurations
    public static final ForgeConfigSpec.BooleanValue POISON_ELDKNIGHT;
    public static final ForgeConfigSpec.IntValue NUM_ELDBOMB_ELDKNIGHT;
    public static final ForgeConfigSpec.DoubleValue HEAL_ELDKNIGHT;

    // Drop Chance Configurations
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_SMALL_ESSENCE;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_RATMAN_EYE;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_LIZZY_SCALE;

    // Ghosty Configurations
    public static final ForgeConfigSpec.DoubleValue INVULNERABILITY_RADIUS_GHOSTY;

    // Gremlin Configurations
    public static final ForgeConfigSpec.BooleanValue CAN_TAKE_GOLD_GREMLIN;
    public static final ForgeConfigSpec.DoubleValue MULTIPLIER_GREMLIN_MOVEMENT_SPEED;
    public static final ForgeConfigSpec.DoubleValue MULTIPLIER_GREMLIN_ATTACK_SPEED;
    public static final ForgeConfigSpec.DoubleValue MULTIPLIER_GREMLIN_ATTACK_DAMAGE;

    // Armor Set Configurations
    public static final ForgeConfigSpec.BooleanValue ENABLE_DEEPSLATESET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_EVOKERSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SQUIRESET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BLAZESET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_DRAGONSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BAMBOOSET_GREEN;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SHINOBI;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BAMBOOSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_PATHSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BOWSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BATSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SHIELDSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_PHANTOMSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_HORNSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SEASET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_PIRATESET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SPIDERSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_NETHERSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SKULK;
    public static final ForgeConfigSpec.BooleanValue ENABLE_STRAWHATSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_ENDERMANSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_VETERANSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_FORZESET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_CREEPERSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_POLAR;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SILVERSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_HOLLOWSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_WITHERSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_APPLE_SET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_CONQUISTADORSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_WITCH;
    public static final ForgeConfigSpec.BooleanValue ENABLE_TENGU_HELMET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_HUSKSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_BAMBOOSET_BLUE;
    public static final ForgeConfigSpec.BooleanValue ENABLE_WARLORDSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_ZOMBIESET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SILVERFISHSET;
    public static final ForgeConfigSpec.BooleanValue ENABLE_SKELETONSET;

    static {
        // Eld Knight Configuration Section
        BUILDER.push("Eld Knight Configuration");
        POISON_ELDKNIGHT = BUILDER.define("Should do the poison passive attack", true);
        NUM_ELDBOMB_ELDKNIGHT = BUILDER.defineInRange("Number of Eld Bombs generated at half hp", 3, 0, 6);
        HEAL_ELDKNIGHT = BUILDER.defineInRange("Quantity of healing each 4 seconds", 3.0, 0.0, 20.0);
        BUILDER.pop();

        // Ghosty Configuration Section
        BUILDER.push("Ghosty Configuration");
        INVULNERABILITY_RADIUS_GHOSTY = BUILDER.defineInRange("Ghosty invulnerability radius", 7.0, 0.0, 25.0);
        BUILDER.pop();

        // Gremlin Configuration Section
        BUILDER.push("Gremlin Configuration");
        CAN_TAKE_GOLD_GREMLIN = BUILDER.define("Can take gold from a player", true);
        MULTIPLIER_GREMLIN_MOVEMENT_SPEED = BUILDER.defineInRange("Second phase movement speed multipler", 1.1, 1.0, 10.0);
        MULTIPLIER_GREMLIN_ATTACK_SPEED = BUILDER.defineInRange("Second phase attack speed multipler", 1.15, 1.0, 10.0);
        MULTIPLIER_GREMLIN_ATTACK_DAMAGE = BUILDER.defineInRange("Second phase attack damage multipler", 1.2, 1.0, 10.0);
        BUILDER.pop();

        // Drop Chance Configuration Section
        BUILDER.push("Drop Chance Configuration");
        DROP_CHANCE_SMALL_ESSENCE = BUILDER.comment("Drop chance for small essence").defineInRange("Drop chance for small essence", 0.15, 0, 1);
        DROP_CHANCE_RATMAN_EYE = BUILDER.defineInRange("Drop chance for ratman eye", 0.40, 0, 1);
        DROP_CHANCE_LIZZY_SCALE = BUILDER.defineInRange("Drop chance for lizzy scale", 0.30, 0, 1);
        BUILDER.pop();

        // Armor Passives Configuration Section
        BUILDER.push("Armor Passives Configuration");
        ENABLE_DEEPSLATESET = BUILDER.define("Enable Deepslate Set Passive", true);
        ENABLE_EVOKERSET = BUILDER.define("Enable Evoker Set Passive", true);
        ENABLE_SQUIRESET = BUILDER.define("Enable Squire Set Passive", true);
        ENABLE_BLAZESET = BUILDER.define("Enable Blaze Set Passive", true);
        ENABLE_DRAGONSET = BUILDER.define("Enable Dragon Set Passive", true);
        ENABLE_BAMBOOSET_GREEN = BUILDER.define("Enable Bamboo Set Green Passive", true);
        ENABLE_SHINOBI = BUILDER.define("Enable Shinobi Set Passive", true);
        ENABLE_BAMBOOSET = BUILDER.define("Enable Bamboo Set Passive", true);
        ENABLE_PATHSET = BUILDER.define("Enable Path Set Passive", true);
        ENABLE_BOWSET = BUILDER.define("Enable Bow Set Passive", true);
        ENABLE_BATSET = BUILDER.define("Enable Bat Set Passive", true);
        ENABLE_SHIELDSET = BUILDER.define("Enable Shield Set Passive", true);
        ENABLE_PHANTOMSET = BUILDER.define("Enable Phantom Set Passive", true);
        ENABLE_HORNSET = BUILDER.define("Enable Horn Set Passive", true);
        ENABLE_SEASET = BUILDER.define("Enable Sea Set Passive", true);
        ENABLE_PIRATESET = BUILDER.define("Enable Pirate Set Passive", true);
        ENABLE_SPIDERSET = BUILDER.define("Enable Spider Set Passive", true);
        ENABLE_NETHERSET = BUILDER.define("Enable Nether Set Passive", true);
        ENABLE_SKULK = BUILDER.define("Enable Skulk Passive", true);
        ENABLE_STRAWHATSET = BUILDER.define("Enable Straw Hat Set Passive", true);
        ENABLE_ENDERMANSET = BUILDER.define("Enable Enderman Set Passive", true);
        ENABLE_VETERANSET = BUILDER.define("Enable Veteran Set Passive", true);
        ENABLE_FORZESET = BUILDER.define("Enable Forze Set Passive", true);
        ENABLE_CREEPERSET = BUILDER.define("Enable Creeper Set Passive", true);
        ENABLE_POLAR = BUILDER.define("Enable Polar Passive", true);
        ENABLE_SILVERSET = BUILDER.define("Enable Silver Set Passive", true);
        ENABLE_HOLLOWSET = BUILDER.define("Enable Hollow Set Passive", true);
        ENABLE_WITHERSET = BUILDER.define("Enable Wither Set Passive", true);
        ENABLE_APPLE_SET = BUILDER.define("Enable Apple Set Passive", true);
        ENABLE_CONQUISTADORSET = BUILDER.define("Enable Conquistador Set Passive", true);
        ENABLE_WITCH = BUILDER.define("Enable Witch Passive", true);
        ENABLE_TENGU_HELMET = BUILDER.define("Enable Tengu Helmet Passive", true);
        ENABLE_HUSKSET = BUILDER.define("Enable Husk Set Passive", true);
        ENABLE_BAMBOOSET_BLUE = BUILDER.define("Enable Bamboo Set Blue Passive", true);
        ENABLE_WARLORDSET = BUILDER.define("Enable Warlord Set Passive", true);
        ENABLE_ZOMBIESET = BUILDER.define("Enable Zombie Set Passive", true);
        ENABLE_SILVERFISHSET = BUILDER.define("Enable Silverfish Set Passive", true);
        ENABLE_SKELETONSET = BUILDER.define("Enable Skeleton Set Passive", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
