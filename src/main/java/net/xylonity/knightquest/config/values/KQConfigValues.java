package net.xylonity.knightquest.config.values;

import net.neoforged.fml.loading.FMLPaths;
import net.xylonity.knightquest.config.KnightQuestCommonConfigs;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Auxiliary configuration values in case the dedicated configuration file is not detected,
 * allowing for hot-reloading (this is particularly useful in client-sided configurations).
 */

public class KQConfigValues {

    static Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("knightquest.toml");
    private static final boolean V = Files.exists(CONFIG_PATH);

    // Eld Knight Configuration Section
    public static boolean POISON_ELDKNIGHT = V ? KnightQuestCommonConfigs.POISON_ELDKNIGHT.get() : true;
    public static int NUM_ELDBOMB_ELDKNIGHT = V ? KnightQuestCommonConfigs.NUM_ELDBOMB_ELDKNIGHT.get() : 3;
    public static float HEAL_ELDKNIGHT = (float) (V ? KnightQuestCommonConfigs.HEAL_ELDKNIGHT.get() : 3.0);

    // Drop Chance Configuration Section
    //public static float DROP_CHANCE_SMALL_ESSENCE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_SMALL_ESSENCE.get() : 0.15);
    public static float DROP_CHANCE_RATMAN_EYE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_RATMAN_EYE.get() : 0.4);
    public static float DROP_CHANCE_LIZZY_SCALE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_LIZZY_SCALE.get() : 0.3);

    // Gremlin Configuration Section
    public static boolean CAN_TAKE_GOLD_GREMLIN = V ? KnightQuestCommonConfigs.CAN_TAKE_GOLD_GREMLIN.get() : true;
    public static float MULTIPLIER_GREMLIN_MOVEMENT_SPEED = (float) (V ? KnightQuestCommonConfigs.MULTIPLIER_GREMLIN_MOVEMENT_SPEED.get() : 1.15);
    public static float MULTIPLIER_GREMLIN_ATTACK_SPEED = (float) (V ? KnightQuestCommonConfigs.MULTIPLIER_GREMLIN_ATTACK_SPEED.get() : 1.15);
    public static float MULTIPLIER_GREMLIN_ATTACK_DAMAGE = (float) (V ? KnightQuestCommonConfigs.MULTIPLIER_GREMLIN_ATTACK_DAMAGE.get() : 1.15);

    // Ghosty Configuration Section
    public static float INVULNERABILITY_RADIUS_GHOSTY  = (float) (V ? KnightQuestCommonConfigs.INVULNERABILITY_RADIUS_GHOSTY.get() : 7.0);

    // Swampman Configuration Section
    public static float PHASE_2_HEALING_SWAMPMAN  = (float) (V ? KnightQuestCommonConfigs.PHASE_2_HEALING_SWAMPMAN.get() : 0.0);
    public static boolean CAN_CHANGE_PHASE_SWAMPMAN = V ? KnightQuestCommonConfigs.CAN_CHANGE_PHASE_SWAMPMAN.get() : true;
    public static boolean POISON_PHASE_2_SWAMPMAN = V ? KnightQuestCommonConfigs.POISON_PHASE_2_SWAMPMAN.get() : true;

    // Netherman Configuration Section
    public static double WINTER_STORM_RADIUS = V ? KnightQuestCommonConfigs.WINTER_STORM_RADIUS.get() : 50.0;
    public static int FROZEN_TICKS = V ? KnightQuestCommonConfigs.FROZEN_TICKS.get() : 4;
    public static boolean CAN_SUMMON_NETHERMAN = V ? KnightQuestCommonConfigs.CAN_SUMMON_NETHERMAN.get() : true;
    public static boolean SPAWN_LIGHTNING_ON_SPAWN = V ? KnightQuestCommonConfigs.SPAWN_LIGHTNING_ON_SPAWN.get() : true;
    public static boolean GENERATE_PARTICLES_ON_SUMMON = V ? KnightQuestCommonConfigs.GENERATE_PARTICLES_ON_SUMMON.get() : true;
    public static double TELEPORT_PROBABILITY = V ? KnightQuestCommonConfigs.TELEPORT_PROBABILITY.get() : 0.5;
    public static boolean RESTORE_BLOCKS_POST_DEATH = V ? KnightQuestCommonConfigs.RESTORE_BLOCKS_POST_DEATH.get() : true;
    public static int EXPERIENCE_DROP_AMOUNT = V ? KnightQuestCommonConfigs.EXPERIENCE_DROP_AMOUNT.get() : 500;
    public static boolean LIGHTNING_STRIKE_IN_PHASE_THREE = V ? KnightQuestCommonConfigs.LIGHTNING_STRIKE_IN_PHASE_THREE.get() : true;
    public static int LIGHTNING_TICK_INTERVAL = V ? KnightQuestCommonConfigs.LIGHTNING_TICK_INTERVAL.get() : 40;
    public static double SNOW_PARTICLE_SPEED = V ? KnightQuestCommonConfigs.SNOW_PARTICLE_SPEED.get() : 1.5;
    public static int SNOW_PARTICLE_COUNT = V ? KnightQuestCommonConfigs.SNOW_PARTICLE_COUNT.get() : 60;

    // Armor Passives Configuration Section
    public static boolean BAMBOOSET_PUSH_PLAYERS = V ? KnightQuestCommonConfigs.ENABLE_BAMBOOSET_PUSH_PLAYERS.get() : false;
    public static int TELEPORT_RADIUS_ENDERMANSET = V ? KnightQuestCommonConfigs.TELEPORT_RADIUS_ENDERMANSET.get() : 10;
    public static double FORZESET_DEFLECT_CHANCE = V ? KnightQuestCommonConfigs.FORZESET_DEFLECT_CHANCE.get() : 0.3;
    public static double FORZESET_DEFLECT_DAMAGE = V ? KnightQuestCommonConfigs.FORZESET_DEFLECT_DAMAGE.get() : 0.5;
    public static double SILVERSET_BURN_CHANCE = V ? KnightQuestCommonConfigs.SILVERSET_BURN_CHANCE.get() : 0.3;
    public static double HOLLOWSET_HEALING_MULTIPLIER = V ? KnightQuestCommonConfigs.HOLLOWSET_HEALING_MULTIPLIER.get() : 0.25;
    public static double DRAGONSET_DAMAGE_MULTIPLIER = V ? KnightQuestCommonConfigs.DRAGONSET_DAMAGE_MULTIPLIER.get() : 1.15;
    public static double WITHERSET_WITHER_CHANCE = V ? KnightQuestCommonConfigs.WITHERSET_WITHER_CHANCE.get() : 0.3;
    public static boolean SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF = V ? KnightQuestCommonConfigs.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.get() : false;
    public static int WARLORD_SET_EFFECT_RADIUS = V ? KnightQuestCommonConfigs.WARLORD_SET_EFFECT_RADIUS.get() : 15;
    public static double ZOMBIESET_HEALING_AMOUNT = V ? KnightQuestCommonConfigs.ZOMBIESET_HEALING_AMOUNT.get() : 1.0;
    public static int ZOMBIESET_HEALING_TICKS = V ? KnightQuestCommonConfigs.ZOMBIESET_HEALING_TICKS.get() : 120;
    public static double DEEPSLATE_FALL_DAMAGE_MULTIPLIER = V ? KnightQuestCommonConfigs.DEEPSLATE_FALL_DAMAGE_MULTIPLIER.get() : 0.2f;
    public static double EVOKER_DARKNESS_CHANCE = V ? KnightQuestCommonConfigs.EVOKER_DARKNESS_CHANCE.get() : 0.25f;
    public static double SQUIRE_DAMAGE_RECEIVED_MULTIPLIER = V ? KnightQuestCommonConfigs.SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.get() : 0.85f;
    public static double BLAZE_FIRE_CHANCE = V ? KnightQuestCommonConfigs.BLAZE_FIRE_CHANCE.get() : 0.4f;
    public static int BLAZE_FIRE_DURATION_MIN = V ? KnightQuestCommonConfigs.BLAZE_FIRE_DURATION_MIN.get() : 2;
    public static int BLAZE_FIRE_DURATION_MAX = V ? KnightQuestCommonConfigs.BLAZE_FIRE_DURATION_MAX.get() : 8;
    public static double CREEPER_EXPLOSION_DAMAGE_MULTIPLIER = V ? KnightQuestCommonConfigs.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get() : 0.1f;
    public static int SILVERFISH_EFFECT_MAX_HEIGHT = V ? KnightQuestCommonConfigs.SILVERFISH_EFFECT_MAX_HEIGHT.get() : 50;
    public static int SKULK_MAX_LIGHT_LEVEL = V ? KnightQuestCommonConfigs.SKULK_MAX_LIGHT_LEVEL.get() : 4;

    // Armor Passives Enabler Configuration Section
    public static boolean DEEPSLATESET = V ? KnightQuestCommonConfigs.ENABLE_DEEPSLATESET.get() : true;
    public static boolean EVOKERSET = V ? KnightQuestCommonConfigs.ENABLE_EVOKERSET.get() : true;
    public static boolean SQUIRESET = V ? KnightQuestCommonConfigs.ENABLE_SQUIRESET.get() : true;
    public static boolean BLAZESET = V ? KnightQuestCommonConfigs.ENABLE_BLAZESET.get() : true;
    public static boolean DRAGONSET = V ? KnightQuestCommonConfigs.ENABLE_DRAGONSET.get() : true;
    public static boolean BAMBOOSET_GREEN = V ? KnightQuestCommonConfigs.ENABLE_BAMBOOSET_GREEN.get() : true;
    public static boolean SHINOBI = V ? KnightQuestCommonConfigs.ENABLE_SHINOBI.get() : true;
    public static boolean BAMBOOSET = V ? KnightQuestCommonConfigs.ENABLE_BAMBOOSET.get() : true;
    public static boolean PATHSET = V ? KnightQuestCommonConfigs.ENABLE_PATHSET.get() : true;
    public static boolean BOWSET = V ? KnightQuestCommonConfigs.ENABLE_BOWSET.get() : true;
    public static boolean BATSET = V ? KnightQuestCommonConfigs.ENABLE_BATSET.get() : true;
    public static boolean SHIELDSET = V ? KnightQuestCommonConfigs.ENABLE_SHIELDSET.get() : true;
    public static boolean PHANTOMSET = V ? KnightQuestCommonConfigs.ENABLE_PHANTOMSET.get() : true;
    public static boolean HORNSET = V ? KnightQuestCommonConfigs.ENABLE_HORNSET.get() : true;
    public static boolean SEASET = V ? KnightQuestCommonConfigs.ENABLE_SEASET.get() : true;
    public static boolean PIRATESET = V ? KnightQuestCommonConfigs.ENABLE_PIRATESET.get() : true;
    public static boolean SPIDERSET = V ? KnightQuestCommonConfigs.ENABLE_SPIDERSET.get() : true;
    public static boolean NETHERSET = V ? KnightQuestCommonConfigs.ENABLE_NETHERSET.get() : true;
    public static boolean SKULK = V ? KnightQuestCommonConfigs.ENABLE_SKULK.get() : true;
    public static boolean STRAWHATSET = V ? KnightQuestCommonConfigs.ENABLE_STRAWHATSET.get() : true;
    public static boolean ENDERMANSET = V ? KnightQuestCommonConfigs.ENABLE_ENDERMANSET.get() : true;
    public static boolean VETERANSET = V ? KnightQuestCommonConfigs.ENABLE_VETERANSET.get() : true;
    public static boolean FORZESET = V ? KnightQuestCommonConfigs.ENABLE_FORZESET.get() : true;
    public static boolean CREEPERSET = V ? KnightQuestCommonConfigs.ENABLE_CREEPERSET.get() : true;
    public static boolean POLAR = V ? KnightQuestCommonConfigs.ENABLE_POLAR.get() : true;
    public static boolean SILVERSET = V ? KnightQuestCommonConfigs.ENABLE_SILVERSET.get() : true;
    public static boolean HOLLOWSET = V ? KnightQuestCommonConfigs.ENABLE_HOLLOWSET.get() : true;
    public static boolean WITHERSET = V ? KnightQuestCommonConfigs.ENABLE_WITHERSET.get() : true;
    public static boolean APPLE_SET = V ? KnightQuestCommonConfigs.ENABLE_APPLE_SET.get() : true;
    public static boolean CONQUISTADORSET = V ? KnightQuestCommonConfigs.ENABLE_CONQUISTADORSET.get() : true;
    public static boolean WITCH = V ? KnightQuestCommonConfigs.ENABLE_WITCH.get() : true;
    public static boolean TENGU_HELMET = V ? KnightQuestCommonConfigs.ENABLE_TENGU_HELMET.get() : true;
    public static boolean HUSKSET = V ? KnightQuestCommonConfigs.ENABLE_HUSKSET.get() : true;
    public static boolean BAMBOOSET_BLUE = V ? KnightQuestCommonConfigs.ENABLE_BAMBOOSET_BLUE.get() : true;
    public static boolean WARLORDSET = V ? KnightQuestCommonConfigs.ENABLE_WARLORDSET.get() : true;
    public static boolean ZOMBIESET = V ? KnightQuestCommonConfigs.ENABLE_ZOMBIESET.get() : true;
    public static boolean SILVERFISHSET = V ? KnightQuestCommonConfigs.ENABLE_SILVERFISHSET.get() : true;
    public static boolean SKELETONSET = V ? KnightQuestCommonConfigs.ENABLE_SKELETONSET.get() : true;
}
