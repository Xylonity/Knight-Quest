package dev.xylonity.knightquest.config;

import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.neoforged.neoforge.common.ModConfigSpec;

public class KnightQuestCommonConfigs {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

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
    public static final ModConfigSpec.BooleanValue CAN_SUMMON_NETHERMAN;
    public static final ModConfigSpec.BooleanValue SPAWN_LIGHTNING_ON_SPAWN;
    public static final ModConfigSpec.BooleanValue GENERATE_PARTICLES_ON_SUMMON;
    public static final ModConfigSpec.DoubleValue TELEPORT_PROBABILITY;
    public static final ModConfigSpec.BooleanValue RESTORE_BLOCKS_POST_DEATH;
    public static final ModConfigSpec.IntValue EXPERIENCE_DROP_AMOUNT;
    public static final ModConfigSpec.BooleanValue LIGHTNING_STRIKE_IN_PHASE_THREE;
    public static final ModConfigSpec.IntValue LIGHTNING_TICK_INTERVAL;
    public static final ModConfigSpec.DoubleValue SNOW_PARTICLE_SPEED;
    public static final ModConfigSpec.IntValue SNOW_PARTICLE_COUNT;
    public static final ModConfigSpec.DoubleValue WINTER_STORM_RADIUS;
    public static final ModConfigSpec.IntValue FROZEN_TICKS;

    // Armor Set Configurations
    public static final ModConfigSpec.BooleanValue ENABLE_BAMBOOSET_PUSH_PLAYERS;
    public static final ModConfigSpec.IntValue TELEPORT_RADIUS_ENDERMANSET;
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

    static {
        // Eld Knight Configuration Section
        BUILDER.push("Eld Knight Configuration");
        POISON_ELDKNIGHT = BUILDER.define("Should do the poison passive attack", true);
        NUM_ELDBOMB_ELDKNIGHT = BUILDER.defineInRange("Number of Eld Bombs generated at half hp", 3, 0, 6);
        HEAL_ELDKNIGHT = BUILDER.defineInRange("Quantity of healing each 4 seconds", 3.0f, 0.0f, 20.0f);
        BUILDER.pop();

        // Ghosty Configuration Section
        BUILDER.push("Ghosty Configuration");
        INVULNERABILITY_RADIUS_GHOSTY = BUILDER.defineInRange("Ghosty invulnerability radius", 7.0f, 0.0f, 25.0f);
        BUILDER.pop();

        // Gremlin Configuration Section
        BUILDER.push("Gremlin Configuration");
        CAN_TAKE_GOLD_GREMLIN = BUILDER.define("Can take gold from a player", true);
        MULTIPLIER_GREMLIN_MOVEMENT_SPEED = BUILDER.defineInRange("Second phase movement speed multipler", 1.1f, 1.0f, 10.0f);
        MULTIPLIER_GREMLIN_ATTACK_SPEED = BUILDER.defineInRange("Second phase attack speed multipler", 1.15f, 1.0f, 10.0f);
        MULTIPLIER_GREMLIN_ATTACK_DAMAGE = BUILDER.defineInRange("Second phase attack damage multipler", 1.2f, 1.0f, 10.0f);
        BUILDER.pop();

        // Swampman Configuration Section
        BUILDER.push("Swampman Configuration");
        PHASE_2_HEALING_SWAMPMAN = BUILDER.defineInRange("Amount of healing per second on second phase", 0.0f, 0.0f, 20.0f);
        CAN_CHANGE_PHASE_SWAMPMAN = BUILDER.define("Can change phase", true);
        POISON_PHASE_2_SWAMPMAN = BUILDER.define("Should axe throwables apply poison effect", false);
        BUILDER.pop();

        // Netherman Configuration Section
        BUILDER.push("Netherman Configuration");
        CAN_SUMMON_NETHERMAN = BUILDER.define("Can the Netherman be summoned?", true);
        SPAWN_LIGHTNING_ON_SPAWN = BUILDER.define("Should spawn a lightning bolt when summoned?", true);
        GENERATE_PARTICLES_ON_SUMMON = BUILDER.define("Should generate particles when spawning?", true);
        TELEPORT_PROBABILITY = BUILDER.comment("Probability of teleporting when hit").defineInRange("Teleport Probability", 0.5f, 0.0f, 1.0f);
        RESTORE_BLOCKS_POST_DEATH = BUILDER.comment("Should it restore blocks converted to lava back to their original state after dying?").define("Restore Blocks Post Death", true);
        EXPERIENCE_DROP_AMOUNT = BUILDER.comment("Amount of experience dropped upon death").defineInRange("Experience Drop Amount", 500, 0, 3000);
        LIGHTNING_STRIKE_IN_PHASE_THREE = BUILDER.comment("Should lightning strike in its third phase?").define("Lightning Strike in Phase Three", true);
        LIGHTNING_TICK_INTERVAL = BUILDER.comment("How often should a lightning bolt fall in the third phase (20 ticks = 1 second)?").defineInRange("Lightning Tick Interval", 40, 10, 200);
        SNOW_PARTICLE_SPEED = BUILDER.defineInRange("Speed of the snow particles in the winter storm", 1.5f, 1.0f, 3.0f);
        SNOW_PARTICLE_COUNT = BUILDER.defineInRange("Number of particles generated in the winter storm", 60, 10, 200);
        WINTER_STORM_RADIUS = BUILDER.comment("Defines the radius for Netherman's Winter Storm Attack").defineInRange("Winter Storm Attack Radius", 26.0f, 1.0f, 30.0f);
        FROZEN_TICKS = BUILDER.comment("Defines the speed at which players freeze during the Winter Attack").defineInRange("Frozen Ticks", 4, 0, 20);
        BUILDER.pop();

        // Drop Chance Configuration Section
        BUILDER.push("Drop Chance Configuration");
        // DROP_CHANCE_SMALL_ESSENCE = BUILDER.comment("Drop chance for small essence").defineInRange("Drop chance for small essence", 0.15, 0, 1);
        BUILDER.comment("Drop chance for small essence must be changed inside knightlib.toml");
        DROP_CHANCE_RATMAN_EYE = BUILDER.defineInRange("Drop chance for ratman eye", 0.40f, 0f, 1f);
        DROP_CHANCE_LIZZY_SCALE = BUILDER.defineInRange("Drop chance for lizzy scale", 0.30f, 0f, 1f);
        BUILDER.pop();

        // Armor Set Passives Configuration Section
        BUILDER.push("Armor Set Passives Configuration");
        ENABLE_BAMBOOSET_PUSH_PLAYERS = BUILDER.define("Should Bamboo Set push players?", false);
        TELEPORT_RADIUS_ENDERMANSET = BUILDER.defineInRange("Teleport radius for Enderman Set", 10, 5, 30);
        FORZESET_DEFLECT_CHANCE = BUILDER.defineInRange("Chance for Forze Set to deflect", 0.3f, 0.1f, 1.0f);
        FORZESET_DEFLECT_DAMAGE = BUILDER.defineInRange("Damage multiplier for Forze Set deflection", 0.5f, 0.1f, 2.0f);
        SILVERSET_BURN_CHANCE = BUILDER.defineInRange("Chance for Silver Set to burn", 0.3f, 0.1f, 1.0f);
        HOLLOWSET_HEALING_MULTIPLIER = BUILDER.defineInRange("Healing multiplier per hit for Hollow Set (healing won't be higher than victim's health)", 0.25f, 0.1f, 2.0f);
        DRAGONSET_DAMAGE_MULTIPLIER = BUILDER.defineInRange("Damage multiplier for Dragon Set", 1.15f, 1.0f, 2.0f);
        WITHERSET_WITHER_CHANCE = BUILDER.defineInRange("Chance of applying Wither with Wither Set", 0.3f, 0.1f, 1.0f);
        SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF = BUILDER.define("Should Warlord Set effect apply to itself?", false);
        WARLORD_SET_EFFECT_RADIUS = BUILDER.defineInRange("Effect radius for Warlord Set", 15, 1, 40);
        ZOMBIESET_HEALING_AMOUNT = BUILDER.defineInRange("Healing amount for Zombie Set", 1.0f, 0.5f, 10.0f);
        ZOMBIESET_HEALING_TICKS = BUILDER.defineInRange("Time in ticks for Zombie Set healing interval", 120, 1, 1000);
        DEEPSLATE_FALL_DAMAGE_MULTIPLIER = BUILDER.defineInRange("Fall damage multiplier for Deepslate Set", 0.2f, 0.0f, 1.0f);
        EVOKER_DARKNESS_CHANCE = BUILDER.defineInRange("Chance to apply Darkness for Evoker Set", 0.25f, 0.0f, 1.0f);
        SQUIRE_DAMAGE_RECEIVED_MULTIPLIER = BUILDER.defineInRange("Damage received multiplier for Squire Set", 0.85f, 0.0f, 1.0f);
        BLAZE_FIRE_CHANCE = BUILDER.defineInRange("Chance to apply Fire for Blaze Set", 0.4f, 0.0f, 1.0f);
        BLAZE_FIRE_DURATION_MIN = BUILDER.defineInRange("Minimum seconds on fire for Blaze Set", 2, 1, 100);
        BLAZE_FIRE_DURATION_MAX = BUILDER.defineInRange("Maximum seconds on fire for Blaze Set", 8, 1, 200);
        CREEPER_EXPLOSION_DAMAGE_MULTIPLIER = BUILDER.defineInRange("Explosion damage multiplier for Creeper Set", 0.1f, 0.0f, 1.0f);
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
        SPEC = BUILDER.build();

    }

    public static void assignValues() {
        // Eld Knight Configurations
        KQConfigValues.POISON_ELDKNIGHT.setConfigValue(POISON_ELDKNIGHT.get());
        KQConfigValues.NUM_ELDBOMB_ELDKNIGHT.setConfigValue(NUM_ELDBOMB_ELDKNIGHT.get());
        KQConfigValues.HEAL_ELDKNIGHT.setConfigValue(HEAL_ELDKNIGHT.get());

        // Drop Chance Configurations
        // KQConfigValues.DROP_CHANCE_SMALL_ESSENCE.setConfigValue(DROP_CHANCE_SMALL_ESSENCE);
        KQConfigValues.DROP_CHANCE_RATMAN_EYE.setConfigValue(DROP_CHANCE_RATMAN_EYE.get());
        KQConfigValues.DROP_CHANCE_LIZZY_SCALE.setConfigValue(DROP_CHANCE_LIZZY_SCALE.get());

        // Gremlin Configurations
        KQConfigValues.CAN_TAKE_GOLD_GREMLIN.setConfigValue(CAN_TAKE_GOLD_GREMLIN.get());
        KQConfigValues.MULTIPLIER_GREMLIN_MOVEMENT_SPEED.setConfigValue(MULTIPLIER_GREMLIN_MOVEMENT_SPEED.get());
        KQConfigValues.MULTIPLIER_GREMLIN_ATTACK_SPEED.setConfigValue(MULTIPLIER_GREMLIN_ATTACK_SPEED.get());
        KQConfigValues.MULTIPLIER_GREMLIN_ATTACK_DAMAGE.setConfigValue(MULTIPLIER_GREMLIN_ATTACK_DAMAGE.get());

        // Ghosty Configurations
        KQConfigValues.INVULNERABILITY_RADIUS_GHOSTY.setConfigValue(INVULNERABILITY_RADIUS_GHOSTY.get());

        // Swampman Configurations
        KQConfigValues.PHASE_2_HEALING_SWAMPMAN.setConfigValue(PHASE_2_HEALING_SWAMPMAN.get());
        KQConfigValues.CAN_CHANGE_PHASE_SWAMPMAN.setConfigValue(CAN_CHANGE_PHASE_SWAMPMAN.get());
        KQConfigValues.POISON_PHASE_2_SWAMPMAN.setConfigValue(POISON_PHASE_2_SWAMPMAN.get());

        // Netherman Configurations
        KQConfigValues.WINTER_STORM_RADIUS.setConfigValue(WINTER_STORM_RADIUS.get());
        KQConfigValues.FROZEN_TICKS.setConfigValue(FROZEN_TICKS.get());
        KQConfigValues.CAN_SUMMON_NETHERMAN.setConfigValue(CAN_SUMMON_NETHERMAN.get());
        KQConfigValues.SPAWN_LIGHTNING_ON_SPAWN.setConfigValue(SPAWN_LIGHTNING_ON_SPAWN.get());
        KQConfigValues.GENERATE_PARTICLES_ON_SUMMON.setConfigValue(GENERATE_PARTICLES_ON_SUMMON.get());
        KQConfigValues.TELEPORT_PROBABILITY.setConfigValue(TELEPORT_PROBABILITY.get());
        KQConfigValues.RESTORE_BLOCKS_POST_DEATH.setConfigValue(RESTORE_BLOCKS_POST_DEATH.get());
        KQConfigValues.EXPERIENCE_DROP_AMOUNT.setConfigValue(EXPERIENCE_DROP_AMOUNT.get());
        KQConfigValues.LIGHTNING_STRIKE_IN_PHASE_THREE.setConfigValue(LIGHTNING_STRIKE_IN_PHASE_THREE.get());
        KQConfigValues.LIGHTNING_TICK_INTERVAL.setConfigValue(LIGHTNING_TICK_INTERVAL.get());
        KQConfigValues.SNOW_PARTICLE_SPEED.setConfigValue(SNOW_PARTICLE_SPEED.get());
        KQConfigValues.SNOW_PARTICLE_COUNT.setConfigValue(SNOW_PARTICLE_COUNT.get());

        // Armor Passives Configurations
        KQConfigValues.BAMBOOSET_PUSH_PLAYERS.setConfigValue(ENABLE_BAMBOOSET_PUSH_PLAYERS.get());
        KQConfigValues.TELEPORT_RADIUS_ENDERMANSET.setConfigValue(TELEPORT_RADIUS_ENDERMANSET.get());
        KQConfigValues.FORZESET_DEFLECT_CHANCE.setConfigValue(FORZESET_DEFLECT_CHANCE.get());
        KQConfigValues.FORZESET_DEFLECT_DAMAGE.setConfigValue(FORZESET_DEFLECT_DAMAGE.get());
        KQConfigValues.SILVERSET_BURN_CHANCE.setConfigValue(SILVERSET_BURN_CHANCE.get());
        KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER.setConfigValue(HOLLOWSET_HEALING_MULTIPLIER.get());
        KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER.setConfigValue(DRAGONSET_DAMAGE_MULTIPLIER.get());
        KQConfigValues.WITHERSET_WITHER_CHANCE.setConfigValue(WITHERSET_WITHER_CHANCE.get());
        KQConfigValues.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.setConfigValue(SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.get());
        KQConfigValues.WARLORD_SET_EFFECT_RADIUS.setConfigValue(WARLORD_SET_EFFECT_RADIUS.get());
        KQConfigValues.ZOMBIESET_HEALING_AMOUNT.setConfigValue(ZOMBIESET_HEALING_AMOUNT.get());
        KQConfigValues.ZOMBIESET_HEALING_TICKS.setConfigValue(ZOMBIESET_HEALING_TICKS.get());
        KQConfigValues.DEEPSLATE_FALL_DAMAGE_MULTIPLIER.setConfigValue(DEEPSLATE_FALL_DAMAGE_MULTIPLIER.get());
        KQConfigValues.EVOKER_DARKNESS_CHANCE.setConfigValue(EVOKER_DARKNESS_CHANCE.get());
        KQConfigValues.SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.setConfigValue(SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.get());
        KQConfigValues.BLAZE_FIRE_CHANCE.setConfigValue(BLAZE_FIRE_CHANCE.get());
        KQConfigValues.BLAZE_FIRE_DURATION_MIN.setConfigValue(BLAZE_FIRE_DURATION_MIN.get());
        KQConfigValues.BLAZE_FIRE_DURATION_MAX.setConfigValue(BLAZE_FIRE_DURATION_MAX.get());
        KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.setConfigValue(CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get());
        KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT.setConfigValue(SILVERFISH_EFFECT_MAX_HEIGHT.get());
        KQConfigValues.SKULK_MAX_LIGHT_LEVEL.setConfigValue(SKULK_MAX_LIGHT_LEVEL.get());

        // Armor Passives Enabler Configurations
        KQConfigValues.DEEPSLATESET.setConfigValue(ENABLE_DEEPSLATESET.get());
        KQConfigValues.EVOKERSET.setConfigValue(ENABLE_EVOKERSET.get());
        KQConfigValues.SQUIRESET.setConfigValue(ENABLE_SQUIRESET.get());
        KQConfigValues.BLAZESET.setConfigValue(ENABLE_BLAZESET.get());
        KQConfigValues.DRAGONSET.setConfigValue(ENABLE_DRAGONSET.get());
        KQConfigValues.BAMBOOSET_GREEN.setConfigValue(ENABLE_BAMBOOSET_GREEN.get());
        KQConfigValues.SHINOBI.setConfigValue(ENABLE_SHINOBI.get());
        KQConfigValues.BAMBOOSET.setConfigValue(ENABLE_BAMBOOSET.get());
        KQConfigValues.PATHSET.setConfigValue(ENABLE_PATHSET.get());
        KQConfigValues.BOWSET.setConfigValue(ENABLE_BOWSET.get());
        KQConfigValues.BATSET.setConfigValue(ENABLE_BATSET.get());
        KQConfigValues.SHIELDSET.setConfigValue(ENABLE_SHIELDSET.get());
        KQConfigValues.PHANTOMSET.setConfigValue(ENABLE_PHANTOMSET.get());
        KQConfigValues.HORNSET.setConfigValue(ENABLE_HORNSET.get());
        KQConfigValues.SEASET.setConfigValue(ENABLE_SEASET.get());
        KQConfigValues.PIRATESET.setConfigValue(ENABLE_PIRATESET.get());
        KQConfigValues.SPIDERSET.setConfigValue(ENABLE_SPIDERSET.get());
        KQConfigValues.NETHERSET.setConfigValue(ENABLE_NETHERSET.get());
        KQConfigValues.SKULK.setConfigValue(ENABLE_SKULK.get());
        KQConfigValues.STRAWHATSET.setConfigValue(ENABLE_STRAWHATSET.get());
        KQConfigValues.ENDERMANSET.setConfigValue(ENABLE_ENDERMANSET.get());
        KQConfigValues.VETERANSET.setConfigValue(ENABLE_VETERANSET.get());
        KQConfigValues.FORZESET.setConfigValue(ENABLE_FORZESET.get());
        KQConfigValues.CREEPERSET.setConfigValue(ENABLE_CREEPERSET.get());
        KQConfigValues.POLAR.setConfigValue(ENABLE_POLAR.get());
        KQConfigValues.SILVERSET.setConfigValue(ENABLE_SILVERSET.get());
        KQConfigValues.HOLLOWSET.setConfigValue(ENABLE_HOLLOWSET.get());
        KQConfigValues.WITHERSET.setConfigValue(ENABLE_WITHERSET.get());
        KQConfigValues.APPLE_SET.setConfigValue(ENABLE_APPLE_SET.get());
        KQConfigValues.CONQUISTADORSET.setConfigValue(ENABLE_CONQUISTADORSET.get());
        KQConfigValues.WITCH.setConfigValue(ENABLE_WITCH.get());
        KQConfigValues.TENGU_HELMET.setConfigValue(ENABLE_TENGU_HELMET.get());
        KQConfigValues.HUSKSET.setConfigValue(ENABLE_HUSKSET.get());
        KQConfigValues.BAMBOOSET_BLUE.setConfigValue(ENABLE_BAMBOOSET_BLUE.get());
        KQConfigValues.WARLORDSET.setConfigValue(ENABLE_WARLORDSET.get());
        KQConfigValues.ZOMBIESET.setConfigValue(ENABLE_ZOMBIESET.get());
        KQConfigValues.SILVERFISHSET.setConfigValue(ENABLE_SILVERFISHSET.get());
        KQConfigValues.SKELETONSET.setConfigValue(ENABLE_SKELETONSET.get());
    }
}