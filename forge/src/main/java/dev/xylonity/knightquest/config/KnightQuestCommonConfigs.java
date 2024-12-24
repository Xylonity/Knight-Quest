package dev.xylonity.knightquest.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class KnightQuestCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // General Configurations
    public static final ForgeConfigSpec.IntValue REQUIRED_ARMOR_PIECES;

    // Eld Knight Configurations
    public static final ForgeConfigSpec.BooleanValue POISON_ELDKNIGHT;
    public static final ForgeConfigSpec.IntValue NUM_ELDBOMB_ELDKNIGHT;
    public static final ForgeConfigSpec.DoubleValue HEAL_ELDKNIGHT;

    // Drop Chance Configurations
    // public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_SMALL_ESSENCE;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_RATMAN_EYE;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_LIZZY_SCALE;

    // Ghosty Configurations
    public static final ForgeConfigSpec.DoubleValue INVULNERABILITY_RADIUS_GHOSTY;

    // Gremlin Configurations
    public static final ForgeConfigSpec.BooleanValue CAN_TAKE_GOLD_GREMLIN;
    public static final ForgeConfigSpec.DoubleValue MULTIPLIER_GREMLIN_MOVEMENT_SPEED;
    public static final ForgeConfigSpec.DoubleValue MULTIPLIER_GREMLIN_ATTACK_SPEED;
    public static final ForgeConfigSpec.DoubleValue MULTIPLIER_GREMLIN_ATTACK_DAMAGE;

    // Swampman Configurations
    public static final ForgeConfigSpec.DoubleValue PHASE_2_HEALING_SWAMPMAN;
    public static final ForgeConfigSpec.BooleanValue CAN_CHANGE_PHASE_SWAMPMAN;
    public static final ForgeConfigSpec.BooleanValue POISON_PHASE_2_SWAMPMAN;

    // Netherman Configurations
    public static final ForgeConfigSpec.DoubleValue NETHERMAN_HEALTH;
    public static final ForgeConfigSpec.DoubleValue NETHERMAN_DAMAGE;
    public static final ForgeConfigSpec.BooleanValue TELEPORT_ON_HIT;
    public static final ForgeConfigSpec.IntValue FIRE_ATTACK_MIN_TIME;
    public static final ForgeConfigSpec.IntValue FIRE_ATTACK_MAX_TIME;
    public static final ForgeConfigSpec.IntValue MAX_NETHERMAN_CLONES;
    public static final ForgeConfigSpec.IntValue ICE_ATTACK_FREEZE_TICKS;
    public static final ForgeConfigSpec.IntValue DARKNESS_ATTACK_MIN_TIME;
    public static final ForgeConfigSpec.IntValue DARKNESS_ATTACK_MAX_TIME;
    public static final ForgeConfigSpec.IntValue CLONE_EXPLOSION_FREEZE_TICKS;
    public static final ForgeConfigSpec.DoubleValue NETHERMAN_PROJECTILE_EXPLOSION_RADIUS;
    public static final ForgeConfigSpec.BooleanValue RESTORE_BLOCKS_POST_DEATH;
    public static final ForgeConfigSpec.IntValue EXPERIENCE_DROP_AMOUNT;

    // Armor Set Configurations
    public static final ForgeConfigSpec.BooleanValue ENABLE_BAMBOOSET_PUSH_PLAYERS;
    public static final ForgeConfigSpec.IntValue TELEPORT_RADIUS_ENDERMANSET;
    public static final ForgeConfigSpec.DoubleValue CHANCE_ENDERMANSET;
    public static final ForgeConfigSpec.DoubleValue FORZESET_DEFLECT_CHANCE;
    public static final ForgeConfigSpec.DoubleValue FORZESET_DEFLECT_DAMAGE;
    public static final ForgeConfigSpec.DoubleValue SILVERSET_BURN_CHANCE;
    public static final ForgeConfigSpec.DoubleValue HOLLOWSET_HEALING_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue DRAGONSET_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue WITHERSET_WITHER_CHANCE;
    public static final ForgeConfigSpec.BooleanValue SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF;
    public static final ForgeConfigSpec.IntValue WARLORD_SET_EFFECT_RADIUS;
    public static final ForgeConfigSpec.DoubleValue ZOMBIESET_HEALING_AMOUNT;
    public static final ForgeConfigSpec.IntValue ZOMBIESET_HEALING_TICKS;
    public static final ForgeConfigSpec.DoubleValue DEEPSLATE_FALL_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue EVOKER_DARKNESS_CHANCE;
    public static final ForgeConfigSpec.DoubleValue SQUIRE_DAMAGE_RECEIVED_MULTIPLIER;
    public static final ForgeConfigSpec.DoubleValue BLAZE_FIRE_CHANCE;
    public static final ForgeConfigSpec.IntValue BLAZE_FIRE_DURATION_MIN;
    public static final ForgeConfigSpec.IntValue BLAZE_FIRE_DURATION_MAX;
    public static final ForgeConfigSpec.DoubleValue CREEPER_EXPLOSION_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.IntValue SILVERFISH_EFFECT_MAX_HEIGHT;
    public static final ForgeConfigSpec.IntValue SKULK_MAX_LIGHT_LEVEL;

    // Armor Set Passive Enabler Configurations
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

    public static final ForgeConfigSpec.BooleanValue ENABLE_CLEAVER;
    public static final ForgeConfigSpec.BooleanValue ENABLE_KHOPESH;
    public static final ForgeConfigSpec.BooleanValue ENABLE_KUKRI;
    public static final ForgeConfigSpec.BooleanValue ENABLE_NAIL;
    public static final ForgeConfigSpec.BooleanValue ENABLE_PALADIN;
    public static final ForgeConfigSpec.BooleanValue ENABLE_UCHIGATANA;

    public static final ForgeConfigSpec.IntValue COOLDOWN_CLEAVER;
    public static final ForgeConfigSpec.IntValue COOLDOWN_KHOPESH;
    public static final ForgeConfigSpec.IntValue COOLDOWN_KUKRI;
    public static final ForgeConfigSpec.IntValue COOLDOWN_NAIL;
    public static final ForgeConfigSpec.IntValue COOLDOWN_PALADIN;
    public static final ForgeConfigSpec.IntValue COOLDOWN_UCHIGATANA;

    public static final ForgeConfigSpec.IntValue SPEED_TICKS_KUKRI;
    public static final ForgeConfigSpec.IntValue FREEZE_TICKS_KUKRI;
    public static final ForgeConfigSpec.IntValue INV_TICKS_PALADIN;
    public static final ForgeConfigSpec.DoubleValue DASH_POWER_NAIL;
    public static final ForgeConfigSpec.DoubleValue EXTRA_DAMAGE_UCHIGATANA;
    public static final ForgeConfigSpec.DoubleValue EXTRA_DAMAGE_PASSIVE_UCHIGATANA;
    public static final ForgeConfigSpec.DoubleValue ENEMY_HEALTH_PASSIVE_UCHIGATANA;
    public static final ForgeConfigSpec.IntValue REFLECTION_TIME_KHOPESH;
    public static final ForgeConfigSpec.DoubleValue CHANCE_BURN_KHOPESH;
    public static final ForgeConfigSpec.DoubleValue REGEN_MAX_PALADIN;
    public static final ForgeConfigSpec.IntValue REGEN_TICKS_PALADIN;
    public static final ForgeConfigSpec.IntValue REGEN_HP_PALADIN;
    public static final ForgeConfigSpec.IntValue TICKS_CLEAVER;
    public static final ForgeConfigSpec.DoubleValue EXTRA_DAMAGE_PASSIVE_CLEAVER;
    public static final ForgeConfigSpec.DoubleValue ENEMY_HEALTH_PASSIVE_CLEAVER;

    static {
        // General configuration Section
        BUILDER.push("General Configuration");
        BUILDER.comment("The amount of armor pieces required to apply a set effect, which means that if this value is set to 1, you can equip 4 different armors pieces and receive the passive effects of each set.");
        REQUIRED_ARMOR_PIECES = BUILDER.defineInRange("Required armor pieces to apply a full-set bonus effect", 4, 1, 4);
        BUILDER.pop();

        // Weapon configuration Section
        BUILDER.push("Weapon Configuration");
        BUILDER.comment("The following cooldowns and timings, per se, are measured in ticks (1 second = 20 ticks)");
        COOLDOWN_CLEAVER = BUILDER.defineInRange("Cooldown of the Cleaver weapon when using its active ability", 1800, 0, 20000);
        COOLDOWN_KHOPESH = BUILDER.defineInRange("Cooldown of the Khopesh weapon when using its active ability", 500, 0, 20000);
        COOLDOWN_KUKRI = BUILDER.defineInRange("Cooldown of the Kukri weapon when using its active ability", 300, 0, 20000);
        COOLDOWN_NAIL = BUILDER.defineInRange("Cooldown of the Nail weapon when using its active ability", 100, 0, 20000);
        COOLDOWN_PALADIN = BUILDER.defineInRange("Cooldown of the Paladin weapon when using its active ability", 500, 0, 20000);
        COOLDOWN_UCHIGATANA = BUILDER.defineInRange("Cooldown of the Uchigatana weapon when using its active ability", 400, 0, 20000);
        SPEED_TICKS_KUKRI = BUILDER.defineInRange("Duration of the speed boost from the Kukri's active ability", 120, 0, 6000);
        FREEZE_TICKS_KUKRI = BUILDER.defineInRange("Number of freeze ticks applied by the Kukri per hit", 125, 0, 10000);
        INV_TICKS_PALADIN = BUILDER.defineInRange("Duration of invulnerability from the Paladin's active ability", 100, 0, 600);
        DASH_POWER_NAIL = BUILDER.defineInRange("Dash power of the nail (this should stay low unless you wanna go to the moon)", 1.5, 0.0, 50);
        BUILDER.comment("Extra damage modifiers are measured as percentages: 0 means no extra damage, 1 means 100% extra base damage (resulting in 200% total).");
        EXTRA_DAMAGE_UCHIGATANA = BUILDER.defineInRange("Extra damage dealt by the Uchigatana when using its active ability", 0.6, 0.0, 4.0);
        EXTRA_DAMAGE_PASSIVE_UCHIGATANA = BUILDER.defineInRange("Extra damage dealt by the Uchigatana through its passive ability", 0.2, 0.0, 4.0);
        ENEMY_HEALTH_PASSIVE_UCHIGATANA = BUILDER.defineInRange("Maximum health the opponent can have for the Uchigatana's passive ability to take effect", 0.5, 0.0, 1.0);
        REFLECTION_TIME_KHOPESH = BUILDER.defineInRange("Duration of the Khopesh's active ability (reflection)", 160, 0, 1000);
        CHANCE_BURN_KHOPESH = BUILDER.defineInRange("Chance to burn the opponent with the Khopesh's passive ability", 0.15, 0.0, 1.0);
        REGEN_TICKS_PALADIN = BUILDER.defineInRange("Interval (in ticks) at which the Paladin's passive ability regenerates health", 30, 0, 400);
        REGEN_MAX_PALADIN = BUILDER.defineInRange("Maximum percentage of health that the Paladin's passive ability can regenerate", 0.50, 0.0, 1.0);
        REGEN_HP_PALADIN = BUILDER.defineInRange("Amount of health restored by the Paladin's passive ability", 1, 0, 100);
        TICKS_CLEAVER = BUILDER.defineInRange("Duration of the Cleaver's active ability", 600, 0, 4000);
        EXTRA_DAMAGE_PASSIVE_CLEAVER = BUILDER.defineInRange("Extra damage dealt by the Cleaver through its passive ability", 0.2, 0.0, 4.0);
        ENEMY_HEALTH_PASSIVE_CLEAVER = BUILDER.defineInRange("Minimum health the opponent must have for the Cleaver's passive ability to take effect", 0.5, 0.0, 1.0);
        BUILDER.pop();

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

        // Swampman Configuration Section
        BUILDER.push("Swampman Configuration");
        PHASE_2_HEALING_SWAMPMAN = BUILDER.defineInRange("Amount of healing per second on second phase", 0.0, 0.0, 20.0);
        CAN_CHANGE_PHASE_SWAMPMAN = BUILDER.define("Can change phase", true);
        POISON_PHASE_2_SWAMPMAN = BUILDER.define("Should axe throwables apply poison effect", false);
        BUILDER.pop();

        // Netherman Configuration Section
        BUILDER.push("Netherman Configuration");
        NETHERMAN_HEALTH = BUILDER.defineInRange("How much health should the Netherman spawn with?", 400.0, 100.0, 2000.0);
        NETHERMAN_DAMAGE = BUILDER.defineInRange("Amount of damage dealt per normal hit", 16.0, 6.0, 50.0);
        TELEPORT_ON_HIT = BUILDER.define("Should the Netherman teleport when hit?", true);
        BUILDER.comment("Random number between the interval stated below");
        FIRE_ATTACK_MIN_TIME = BUILDER.defineInRange("Minimum time for fire attack (seconds)", 3, 0, 30);
        FIRE_ATTACK_MAX_TIME = BUILDER.defineInRange("Maximum time for fire attack (seconds)", 7, 0, 60);
        MAX_NETHERMAN_CLONES = BUILDER.defineInRange("Maximum number of Netherman clones that can spawn per attack", 4, 0, 10);
        ICE_ATTACK_FREEZE_TICKS = BUILDER.defineInRange("Freeze ticks applied by ice attack", 300, 0, 2000);
        DARKNESS_ATTACK_MIN_TIME = BUILDER.defineInRange("Minimum time for darkness attack (seconds)", 3, 0, 30);
        DARKNESS_ATTACK_MAX_TIME = BUILDER.defineInRange("Maximum time for darkness attack (seconds)", 7, 0, 60);
        CLONE_EXPLOSION_FREEZE_TICKS = BUILDER.defineInRange("Ticks of freeze applied by Netherman clone explosions", 200, 0, 2000);
        NETHERMAN_PROJECTILE_EXPLOSION_RADIUS = BUILDER.defineInRange("Explosion radius of Netherman projectiles", 3.0, 0.0, 10.0);
        RESTORE_BLOCKS_POST_DEATH = BUILDER.define("Should it restore blocks converted to lava back to their original state after dying?", true);
        EXPERIENCE_DROP_AMOUNT = BUILDER.defineInRange("Amount of experience dropped upon death", 500, 0, 3000);
        BUILDER.pop();

        // Drop Chance Configuration Section
        BUILDER.push("Drop Chance Configuration");
        BUILDER.comment("Drop chance for small essence must be changed inside knightlib.toml");
        DROP_CHANCE_RATMAN_EYE = BUILDER.defineInRange("Drop chance for ratman eye", 0.40, 0, 1);
        DROP_CHANCE_LIZZY_SCALE = BUILDER.defineInRange("Drop chance for lizzy scale", 0.30, 0, 1);
        BUILDER.pop();

        // Armor Set Passives Configuration Section
        BUILDER.push("Armor Set Passives Configuration");
        ENABLE_BAMBOOSET_PUSH_PLAYERS = BUILDER.define("Should Bamboo Set push players?", false);
        CHANCE_ENDERMANSET = BUILDER.defineInRange("Teleport chance for Enderman Set", 0.4, 0.1, 1.0);
        TELEPORT_RADIUS_ENDERMANSET = BUILDER.defineInRange("Teleport radius for Enderman Set", 10, 5, 30);
        FORZESET_DEFLECT_CHANCE = BUILDER.defineInRange("Chance for Forze Set to deflect", 0.3, 0.1, 1.0);
        FORZESET_DEFLECT_DAMAGE = BUILDER.defineInRange("Damage multiplier for Forze Set deflection", 0.5, 0.1, 2.0);
        SILVERSET_BURN_CHANCE = BUILDER.defineInRange("Chance for Silver Set to burn", 0.3, 0.1, 1.0);
        HOLLOWSET_HEALING_MULTIPLIER = BUILDER.defineInRange("Healing multiplier per hit for Hollow Set (healing won't be higher than victim's health)", 0.25, 0.1, 2.0);
        DRAGONSET_DAMAGE_MULTIPLIER = BUILDER.defineInRange("Damage multiplier for Dragon Set", 1.15, 1.0, 2.0);
        WITHERSET_WITHER_CHANCE = BUILDER.defineInRange("Chance of applying Wither with Wither Set", 0.3, 0.1, 1.0);
        SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF = BUILDER.define("Should Warlord Set effect apply to itself?", false);
        WARLORD_SET_EFFECT_RADIUS = BUILDER.defineInRange("Effect radius for Warlord Set", 15, 1, 40);
        ZOMBIESET_HEALING_AMOUNT = BUILDER.defineInRange("Healing amount for Zombie Set", 1.0, 1.0, 10.0);
        ZOMBIESET_HEALING_TICKS = BUILDER.defineInRange("Time in ticks for Zombie Set healing interval", 120, 1, 1000);
        DEEPSLATE_FALL_DAMAGE_MULTIPLIER = BUILDER.defineInRange("Fall damage multiplier for Deepslate Set", 0.2, 0.0, 1.0);
        EVOKER_DARKNESS_CHANCE = BUILDER.defineInRange("Chance to apply Darkness for Evoker Set", 0.25, 0.0, 1.0);
        SQUIRE_DAMAGE_RECEIVED_MULTIPLIER = BUILDER.defineInRange("Damage received multiplier for Squire Set", 0.85, 0.0, 1.0);
        BLAZE_FIRE_CHANCE = BUILDER.defineInRange("Chance to apply Fire for Blaze Set", 0.4, 0.0, 1.0);
        BLAZE_FIRE_DURATION_MIN = BUILDER.defineInRange("Minimum seconds on fire for Blaze Set", 2, 1, 100);
        BLAZE_FIRE_DURATION_MAX = BUILDER.defineInRange("Maximum seconds on fire for Blaze Set", 8, 1, 200);
        CREEPER_EXPLOSION_DAMAGE_MULTIPLIER = BUILDER.defineInRange("Explosion damage multiplier for Creeper Set", 0.1, 0.0, 1.0);
        SILVERFISH_EFFECT_MAX_HEIGHT = BUILDER.defineInRange("Maximum height to apply effect for Silverfish Set", 50, 0, 100);
        SKULK_MAX_LIGHT_LEVEL = BUILDER.defineInRange("Maximum light level to grant effect for Skulk Set", 4, 0, 15);
        BUILDER.pop();

        // Armor Set Passives Enabler Configuration Section
        BUILDER.push("Armor Set Passives Enabler Configuration");
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

        // Weapon Enabler Configuration Section
        BUILDER.push("Weapon Enabler Configuration");
        ENABLE_CLEAVER = BUILDER.define("Enable Cleaver weapon abilities", true);
        ENABLE_KHOPESH = BUILDER.define("Enable Khopesh weapon abilities", true);
        ENABLE_KUKRI = BUILDER.define("Enable Kukri weapon abilities", true);
        ENABLE_NAIL = BUILDER.define("Enable Nail weapon abilities", true);
        ENABLE_PALADIN = BUILDER.define("Enable Paladin weapon abilities", true);
        ENABLE_UCHIGATANA = BUILDER.define("Enable Uchigatana weapon abilities", true);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
