package dev.xylonity.knightquest.config;

import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.neoforged.neoforge.common.ModConfigSpec;

public class KnightQuestCommonConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    // General Configurations
    public static final ModConfigSpec.IntValue REQUIRED_ARMOR_PIECES;

    // Eld Knight Configurations
    public static final ModConfigSpec.BooleanValue POISON_ELDKNIGHT;
    public static final ModConfigSpec.IntValue NUM_ELDBOMB_ELDKNIGHT;
    public static final ModConfigSpec.DoubleValue HEAL_ELDKNIGHT;

    // Drop Chance Configurations
    // public static final ModConfigSpec.DoubleValue DROP_CHANCE_SMALL_ESSENCE;
    public static final ModConfigSpec.DoubleValue DROP_CHANCE_RATMAN_EYE;
    public static final ModConfigSpec.DoubleValue DROP_CHANCE_LIZZY_SCALE;

    // Ghosty Configurations
    public static final ModConfigSpec.DoubleValue INVULNERABILITY_RADIUS_GHOSTY;

    // Gremlin Configurations
    public static final ModConfigSpec.BooleanValue CAN_TAKE_GOLD_GREMLIN;
    public static final ModConfigSpec.DoubleValue MULTIPLIER_GREMLIN_MOVEMENT_SPEED;
    public static final ModConfigSpec.DoubleValue MULTIPLIER_GREMLIN_ATTACK_SPEED;
    public static final ModConfigSpec.DoubleValue MULTIPLIER_GREMLIN_ATTACK_DAMAGE;

    // Swampman Configurations
    public static final ModConfigSpec.DoubleValue PHASE_2_HEALING_SWAMPMAN;
    public static final ModConfigSpec.BooleanValue CAN_CHANGE_PHASE_SWAMPMAN;
    public static final ModConfigSpec.BooleanValue POISON_PHASE_2_SWAMPMAN;

    // Netherman Configurations
    public static final ModConfigSpec.DoubleValue NETHERMAN_HEALTH;
    public static final ModConfigSpec.DoubleValue NETHERMAN_DAMAGE;
    public static final ModConfigSpec.BooleanValue TELEPORT_ON_HIT;
    public static final ModConfigSpec.IntValue FIRE_ATTACK_MIN_TIME;
    public static final ModConfigSpec.IntValue FIRE_ATTACK_MAX_TIME;
    public static final ModConfigSpec.IntValue MAX_NETHERMAN_CLONES;
    public static final ModConfigSpec.IntValue ICE_ATTACK_FREEZE_TICKS;
    public static final ModConfigSpec.IntValue DARKNESS_ATTACK_MIN_TIME;
    public static final ModConfigSpec.IntValue DARKNESS_ATTACK_MAX_TIME;
    public static final ModConfigSpec.IntValue CLONE_EXPLOSION_FREEZE_TICKS;
    public static final ModConfigSpec.DoubleValue NETHERMAN_PROJECTILE_EXPLOSION_RADIUS;
    public static final ModConfigSpec.BooleanValue RESTORE_BLOCKS_POST_DEATH;
    public static final ModConfigSpec.IntValue EXPERIENCE_DROP_AMOUNT;

    // Armor Set Configurations
    public static final ModConfigSpec.BooleanValue ENABLE_BAMBOOSET_PUSH_PLAYERS;
    public static final ModConfigSpec.IntValue TELEPORT_RADIUS_ENDERMANSET;
    public static final ModConfigSpec.DoubleValue CHANCE_ENDERMANSET;
    public static final ModConfigSpec.DoubleValue FORZESET_DEFLECT_CHANCE;
    public static final ModConfigSpec.DoubleValue FORZESET_DEFLECT_DAMAGE;
    public static final ModConfigSpec.DoubleValue SILVERSET_BURN_CHANCE;
    public static final ModConfigSpec.DoubleValue HOLLOWSET_HEALING_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue DRAGONSET_DAMAGE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue WITHERSET_WITHER_CHANCE;
    public static final ModConfigSpec.BooleanValue SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF;
    public static final ModConfigSpec.IntValue WARLORD_SET_EFFECT_RADIUS;
    public static final ModConfigSpec.DoubleValue ZOMBIESET_HEALING_AMOUNT;
    public static final ModConfigSpec.IntValue ZOMBIESET_HEALING_TICKS;
    public static final ModConfigSpec.DoubleValue DEEPSLATE_FALL_DAMAGE_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue EVOKER_DARKNESS_CHANCE;
    public static final ModConfigSpec.DoubleValue SQUIRE_DAMAGE_RECEIVED_MULTIPLIER;
    public static final ModConfigSpec.DoubleValue BLAZE_FIRE_CHANCE;
    public static final ModConfigSpec.IntValue BLAZE_FIRE_DURATION_MIN;
    public static final ModConfigSpec.IntValue BLAZE_FIRE_DURATION_MAX;
    public static final ModConfigSpec.DoubleValue CREEPER_EXPLOSION_DAMAGE_MULTIPLIER;
    public static final ModConfigSpec.IntValue SILVERFISH_EFFECT_MAX_HEIGHT;
    public static final ModConfigSpec.IntValue SKULK_MAX_LIGHT_LEVEL;

    // Armor Set Passive Enabler Configurations
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

    public static final ModConfigSpec.BooleanValue ENABLE_CLEAVER;
    public static final ModConfigSpec.BooleanValue ENABLE_KHOPESH;
    public static final ModConfigSpec.BooleanValue ENABLE_KUKRI;
    public static final ModConfigSpec.BooleanValue ENABLE_NAIL;
    public static final ModConfigSpec.BooleanValue ENABLE_PALADIN;
    public static final ModConfigSpec.BooleanValue ENABLE_UCHIGATANA;

    public static final ModConfigSpec.IntValue COOLDOWN_CLEAVER;
    public static final ModConfigSpec.IntValue COOLDOWN_KHOPESH;
    public static final ModConfigSpec.IntValue COOLDOWN_KUKRI;
    public static final ModConfigSpec.IntValue COOLDOWN_NAIL;
    public static final ModConfigSpec.IntValue COOLDOWN_PALADIN;
    public static final ModConfigSpec.IntValue COOLDOWN_UCHIGATANA;

    public static final ModConfigSpec.IntValue SPEED_TICKS_KUKRI;
    public static final ModConfigSpec.IntValue FREEZE_TICKS_KUKRI;
    public static final ModConfigSpec.IntValue INV_TICKS_PALADIN;
    public static final ModConfigSpec.DoubleValue DASH_POWER_NAIL;
    public static final ModConfigSpec.DoubleValue EXTRA_DAMAGE_UCHIGATANA;
    public static final ModConfigSpec.DoubleValue EXTRA_DAMAGE_PASSIVE_UCHIGATANA;
    public static final ModConfigSpec.DoubleValue ENEMY_HEALTH_PASSIVE_UCHIGATANA;
    public static final ModConfigSpec.IntValue REFLECTION_TIME_KHOPESH;
    public static final ModConfigSpec.DoubleValue CHANCE_BURN_KHOPESH;
    public static final ModConfigSpec.DoubleValue REGEN_MAX_PALADIN;
    public static final ModConfigSpec.IntValue REGEN_TICKS_PALADIN;
    public static final ModConfigSpec.IntValue REGEN_HP_PALADIN;
    public static final ModConfigSpec.IntValue TICKS_CLEAVER;
    public static final ModConfigSpec.DoubleValue EXTRA_DAMAGE_PASSIVE_CLEAVER;
    public static final ModConfigSpec.DoubleValue ENEMY_HEALTH_PASSIVE_CLEAVER;

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
        NETHERMAN_HEALTH = BUILDER.defineInRange("How much health should the Netherman spawn with?", 450.0, 100.0, 2000.0);
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

    public static void assignValues() {
        // General Configuration Section
        KQConfigValues.REQUIRED_ARMOR_PIECES.set(REQUIRED_ARMOR_PIECES.get());

        // Weapon Configuration Section
        KQConfigValues.COOLDOWN_CLEAVER.set(COOLDOWN_CLEAVER.get());
        KQConfigValues.COOLDOWN_KHOPESH.set(COOLDOWN_KHOPESH.get());
        KQConfigValues.COOLDOWN_KUKRI.set(COOLDOWN_KUKRI.get());
        KQConfigValues.COOLDOWN_NAIL.set(COOLDOWN_NAIL.get());
        KQConfigValues.COOLDOWN_PALADIN.set(COOLDOWN_PALADIN.get());
        KQConfigValues.COOLDOWN_UCHIGATANA.set(COOLDOWN_UCHIGATANA.get());
        KQConfigValues.SPEED_TICKS_KUKRI.set(SPEED_TICKS_KUKRI.get());
        KQConfigValues.FREEZE_TICKS_KUKRI.set(FREEZE_TICKS_KUKRI.get());
        KQConfigValues.INV_TICKS_PALADIN.set(INV_TICKS_PALADIN.get());
        KQConfigValues.DASH_POWER_NAIL.set(DASH_POWER_NAIL.get());
        KQConfigValues.EXTRA_DAMAGE_UCHIGATANA.set(EXTRA_DAMAGE_UCHIGATANA.get());
        KQConfigValues.EXTRA_DAMAGE_PASSIVE_UCHIGATANA.set(EXTRA_DAMAGE_PASSIVE_UCHIGATANA.get());
        KQConfigValues.ENEMY_HEALTH_PASSIVE_UCHIGATANA.set(ENEMY_HEALTH_PASSIVE_UCHIGATANA.get());
        KQConfigValues.REFLECTION_TIME_KHOPESH.set(REFLECTION_TIME_KHOPESH.get());
        KQConfigValues.CHANCE_BURN_KHOPESH.set(CHANCE_BURN_KHOPESH.get());
        KQConfigValues.REGEN_TICKS_PALADIN.set(REGEN_TICKS_PALADIN.get());
        KQConfigValues.REGEN_MAX_PALADIN.set(REGEN_MAX_PALADIN.get());
        KQConfigValues.REGEN_HP_PALADIN.set(REGEN_HP_PALADIN.get());
        KQConfigValues.TICKS_CLEAVER.set(TICKS_CLEAVER.get());
        KQConfigValues.EXTRA_DAMAGE_PASSIVE_CLEAVER.set(EXTRA_DAMAGE_PASSIVE_CLEAVER.get());
        KQConfigValues.ENEMY_HEALTH_PASSIVE_CLEAVER.set(ENEMY_HEALTH_PASSIVE_CLEAVER.get());

        // Eld Knight Configuration Section
        KQConfigValues.POISON_ELDKNIGHT.set(POISON_ELDKNIGHT.get());
        KQConfigValues.NUM_ELDBOMB_ELDKNIGHT.set(NUM_ELDBOMB_ELDKNIGHT.get());
        KQConfigValues.HEAL_ELDKNIGHT.set(HEAL_ELDKNIGHT.get());

        // Ghosty Configuration Section
        KQConfigValues.INVULNERABILITY_RADIUS_GHOSTY.set(INVULNERABILITY_RADIUS_GHOSTY.get());

        // Gremlin Configuration Section
        KQConfigValues.CAN_TAKE_GOLD_GREMLIN.set(CAN_TAKE_GOLD_GREMLIN.get());
        KQConfigValues.MULTIPLIER_GREMLIN_MOVEMENT_SPEED.set(MULTIPLIER_GREMLIN_MOVEMENT_SPEED.get());
        KQConfigValues.MULTIPLIER_GREMLIN_ATTACK_SPEED.set(MULTIPLIER_GREMLIN_ATTACK_SPEED.get());
        KQConfigValues.MULTIPLIER_GREMLIN_ATTACK_DAMAGE.set(MULTIPLIER_GREMLIN_ATTACK_DAMAGE.get());

        // Swampman Configuration Section
        KQConfigValues.PHASE_2_HEALING_SWAMPMAN.set(PHASE_2_HEALING_SWAMPMAN.get());
        KQConfigValues.CAN_CHANGE_PHASE_SWAMPMAN.set(CAN_CHANGE_PHASE_SWAMPMAN.get());
        KQConfigValues.POISON_PHASE_2_SWAMPMAN.set(POISON_PHASE_2_SWAMPMAN.get());

        // Netherman Configuration Section
        KQConfigValues.NETHERMAN_HEALTH.set(NETHERMAN_HEALTH.get());
        KQConfigValues.NETHERMAN_DAMAGE.set(NETHERMAN_DAMAGE.get());
        KQConfigValues.TELEPORT_ON_HIT.set(TELEPORT_ON_HIT.get());
        KQConfigValues.FIRE_ATTACK_MIN_TIME.set(FIRE_ATTACK_MIN_TIME.get());
        KQConfigValues.FIRE_ATTACK_MAX_TIME.set(FIRE_ATTACK_MAX_TIME.get());
        KQConfigValues.MAX_NETHERMAN_CLONES.set(MAX_NETHERMAN_CLONES.get());
        KQConfigValues.ICE_ATTACK_FREEZE_TICKS.set(ICE_ATTACK_FREEZE_TICKS.get());
        KQConfigValues.DARKNESS_ATTACK_MIN_TIME.set(DARKNESS_ATTACK_MIN_TIME.get());
        KQConfigValues.DARKNESS_ATTACK_MAX_TIME.set(DARKNESS_ATTACK_MAX_TIME.get());
        KQConfigValues.CLONE_EXPLOSION_FREEZE_TICKS.set(CLONE_EXPLOSION_FREEZE_TICKS.get());
        KQConfigValues.NETHERMAN_PROJECTILE_EXPLOSION_RADIUS.set(NETHERMAN_PROJECTILE_EXPLOSION_RADIUS.get());
        KQConfigValues.RESTORE_BLOCKS_POST_DEATH.set(RESTORE_BLOCKS_POST_DEATH.get());
        KQConfigValues.EXPERIENCE_DROP_AMOUNT.set(EXPERIENCE_DROP_AMOUNT.get());

        // Drop Chance Configuration Section
        KQConfigValues.DROP_CHANCE_RATMAN_EYE.set(DROP_CHANCE_RATMAN_EYE.get());
        KQConfigValues.DROP_CHANCE_LIZZY_SCALE.set(DROP_CHANCE_LIZZY_SCALE.get());

        // Armor Set Passives Configuration Section
        KQConfigValues.ENABLE_BAMBOOSET_PUSH_PLAYERS.set(ENABLE_BAMBOOSET_PUSH_PLAYERS.get());
        KQConfigValues.CHANCE_ENDERMANSET.set(CHANCE_ENDERMANSET.get());
        KQConfigValues.TELEPORT_RADIUS_ENDERMANSET.set(TELEPORT_RADIUS_ENDERMANSET.get());
        KQConfigValues.FORZESET_DEFLECT_CHANCE.set(FORZESET_DEFLECT_CHANCE.get());
        KQConfigValues.FORZESET_DEFLECT_DAMAGE.set(FORZESET_DEFLECT_DAMAGE.get());
        KQConfigValues.SILVERSET_BURN_CHANCE.set(SILVERSET_BURN_CHANCE.get());
        KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER.set(HOLLOWSET_HEALING_MULTIPLIER.get());
        KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER.set(DRAGONSET_DAMAGE_MULTIPLIER.get());
        KQConfigValues.WITHERSET_WITHER_CHANCE.set(WITHERSET_WITHER_CHANCE.get());
        KQConfigValues.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.set(SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.get());
        KQConfigValues.WARLORD_SET_EFFECT_RADIUS.set(WARLORD_SET_EFFECT_RADIUS.get());
        KQConfigValues.ZOMBIESET_HEALING_AMOUNT.set(ZOMBIESET_HEALING_AMOUNT.get());
        KQConfigValues.ZOMBIESET_HEALING_TICKS.set(ZOMBIESET_HEALING_TICKS.get());
        KQConfigValues.DEEPSLATE_FALL_DAMAGE_MULTIPLIER.set(DEEPSLATE_FALL_DAMAGE_MULTIPLIER.get());
        KQConfigValues.EVOKER_DARKNESS_CHANCE.set(EVOKER_DARKNESS_CHANCE.get());
        KQConfigValues.SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.set(SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.get());
        KQConfigValues.BLAZE_FIRE_CHANCE.set(BLAZE_FIRE_CHANCE.get());
        KQConfigValues.BLAZE_FIRE_DURATION_MIN.set(BLAZE_FIRE_DURATION_MIN.get());
        KQConfigValues.BLAZE_FIRE_DURATION_MAX.set(BLAZE_FIRE_DURATION_MAX.get());
        KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.set(CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get());
        KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT.set(SILVERFISH_EFFECT_MAX_HEIGHT.get());
        KQConfigValues.SKULK_MAX_LIGHT_LEVEL.set(SKULK_MAX_LIGHT_LEVEL.get());

        // Armor Set Passives Enabler Configuration Section
        KQConfigValues.DEEPSLATESET.set(ENABLE_DEEPSLATESET.get());
        KQConfigValues.EVOKERSET.set(ENABLE_EVOKERSET.get());
        KQConfigValues.SQUIRESET.set(ENABLE_SQUIRESET.get());
        KQConfigValues.BLAZESET.set(ENABLE_BLAZESET.get());
        KQConfigValues.DRAGONSET.set(ENABLE_DRAGONSET.get());
        KQConfigValues.BAMBOOSET_GREEN.set(ENABLE_BAMBOOSET_GREEN.get());
        KQConfigValues.SHINOBI.set(ENABLE_SHINOBI.get());
        KQConfigValues.BAMBOOSET.set(ENABLE_BAMBOOSET.get());
        KQConfigValues.PATHSET.set(ENABLE_PATHSET.get());
        KQConfigValues.BOWSET.set(ENABLE_BOWSET.get());
        KQConfigValues.BATSET.set(ENABLE_BATSET.get());
        KQConfigValues.SHIELDSET.set(ENABLE_SHIELDSET.get());
        KQConfigValues.PHANTOMSET.set(ENABLE_PHANTOMSET.get());
        KQConfigValues.HORNSET.set(ENABLE_HORNSET.get());
        KQConfigValues.SEASET.set(ENABLE_SEASET.get());
        KQConfigValues.PIRATESET.set(ENABLE_PIRATESET.get());
        KQConfigValues.SPIDERSET.set(ENABLE_SPIDERSET.get());
        KQConfigValues.NETHERSET.set(ENABLE_NETHERSET.get());
        KQConfigValues.SKULK.set(ENABLE_SKULK.get());
        KQConfigValues.STRAWHATSET.set(ENABLE_STRAWHATSET.get());
        KQConfigValues.ENDERMANSET.set(ENABLE_ENDERMANSET.get());
        KQConfigValues.VETERANSET.set(ENABLE_VETERANSET.get());
        KQConfigValues.FORZESET.set(ENABLE_FORZESET.get());
        KQConfigValues.CREEPERSET.set(ENABLE_CREEPERSET.get());
        KQConfigValues.POLAR.set(ENABLE_POLAR.get());
        KQConfigValues.SILVERSET.set(ENABLE_SILVERSET.get());
        KQConfigValues.HOLLOWSET.set(ENABLE_HOLLOWSET.get());
        KQConfigValues.WITHERSET.set(ENABLE_WITHERSET.get());
        KQConfigValues.APPLE_SET.set(ENABLE_APPLE_SET.get());
        KQConfigValues.CONQUISTADORSET.set(ENABLE_CONQUISTADORSET.get());
        KQConfigValues.WITCH.set(ENABLE_WITCH.get());
        KQConfigValues.TENGU_HELMET.set(ENABLE_TENGU_HELMET.get());
        KQConfigValues.HUSKSET.set(ENABLE_HUSKSET.get());
        KQConfigValues.BAMBOOSET_BLUE.set(ENABLE_BAMBOOSET_BLUE.get());
        KQConfigValues.WARLORDSET.set(ENABLE_WARLORDSET.get());
        KQConfigValues.ZOMBIESET.set(ENABLE_ZOMBIESET.get());
        KQConfigValues.SILVERFISHSET.set(ENABLE_SILVERFISHSET.get());
        KQConfigValues.SKELETONSET.set(ENABLE_SKELETONSET.get());

        // Weapon Enabler Configuration Section
        KQConfigValues.CLEAVER.set(ENABLE_CLEAVER.get());
        KQConfigValues.KHOPESH.set(ENABLE_KHOPESH.get());
        KQConfigValues.KUKRI.set(ENABLE_KUKRI.get());
        KQConfigValues.NAIL.set(ENABLE_NAIL.get());
        KQConfigValues.PALADIN.set(ENABLE_PALADIN.get());
        KQConfigValues.UCHIGATANA.set(ENABLE_UCHIGATANA.get());
    }
}