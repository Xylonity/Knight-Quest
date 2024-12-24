package dev.xylonity.knightquest.config.values;

import dev.xylonity.knightquest.config.KnightQuestCommonConfigs;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Auxiliary configuration values in case the dedicated configuration file is not detected,
 * allowing for hot-reloading (this is particularly useful in client-sided configurations).
 */

public class KQConfigValues {

    static Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("knightquest.toml");
    private static final boolean V = Files.exists(CONFIG_PATH) && FabricLoader.getInstance().isModLoaded("forgeconfigapiport");

    // General Configuration Section
    public static int REQUIRED_ARMOR_PIECES = V ? KnightQuestCommonConfigs.REQUIRED_ARMOR_PIECES.get() : 4;

    // Eld Knight Configuration Section
    public static boolean POISON_ELDKNIGHT = V ? KnightQuestCommonConfigs.POISON_ELDKNIGHT.get() : true;
    public static int NUM_ELDBOMB_ELDKNIGHT = V ? KnightQuestCommonConfigs.NUM_ELDBOMB_ELDKNIGHT.get() : 3;
    public static float HEAL_ELDKNIGHT = (float) (V ? KnightQuestCommonConfigs.HEAL_ELDKNIGHT.get() : 3.0);

    // Drop Chance Configuration Section
    // public static float DROP_CHANCE_SMALL_ESSENCE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_SMALL_ESSENCE.get() : 0.15);
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
    public static double NETHERMAN_HEALTH = V ? KnightQuestCommonConfigs.NETHERMAN_HEALTH.get() : 450.0;
    public static double NETHERMAN_DAMAGE = V ? KnightQuestCommonConfigs.NETHERMAN_DAMAGE.get() : 16.0;
    public static boolean TELEPORT_ON_HIT = V ? KnightQuestCommonConfigs.TELEPORT_ON_HIT.get() : true;
    public static int FIRE_ATTACK_MIN_TIME = V ? KnightQuestCommonConfigs.FIRE_ATTACK_MIN_TIME.get() : 3;
    public static int FIRE_ATTACK_MAX_TIME = V ? KnightQuestCommonConfigs.FIRE_ATTACK_MAX_TIME.get() : 7;
    public static int MAX_NETHERMAN_CLONES = V ? KnightQuestCommonConfigs.MAX_NETHERMAN_CLONES.get() : 4;
    public static int ICE_ATTACK_FREEZE_TICKS = V ? KnightQuestCommonConfigs.ICE_ATTACK_FREEZE_TICKS.get() : 300;
    public static int DARKNESS_ATTACK_MIN_TIME = V ? KnightQuestCommonConfigs.DARKNESS_ATTACK_MIN_TIME.get() : 3;
    public static int DARKNESS_ATTACK_MAX_TIME = V ? KnightQuestCommonConfigs.DARKNESS_ATTACK_MAX_TIME.get() : 7;
    public static int CLONE_EXPLOSION_FREEZE_TICKS = V ? KnightQuestCommonConfigs.CLONE_EXPLOSION_FREEZE_TICKS.get() : 200;
    public static double NETHERMAN_PROJECTILE_EXPLOSION_RADIUS = V ? KnightQuestCommonConfigs.NETHERMAN_PROJECTILE_EXPLOSION_RADIUS.get() : 3.0;
    public static boolean RESTORE_BLOCKS_POST_DEATH = V ? KnightQuestCommonConfigs.RESTORE_BLOCKS_POST_DEATH.get() : true;
    public static int EXPERIENCE_DROP_AMOUNT = V ? KnightQuestCommonConfigs.EXPERIENCE_DROP_AMOUNT.get() : 500;

    // Armor Passives Configuration Section
    public static boolean BAMBOOSET_PUSH_PLAYERS = V ? KnightQuestCommonConfigs.ENABLE_BAMBOOSET_PUSH_PLAYERS.get() : false;
    public static int TELEPORT_RADIUS_ENDERMANSET = V ? KnightQuestCommonConfigs.TELEPORT_RADIUS_ENDERMANSET.get() : 10;
    public static double CHANCE_ENDERMANSET = V ? KnightQuestCommonConfigs.CHANCE_ENDERMANSET.get() : 0.4;
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

    public static boolean CLEAVER = V ? KnightQuestCommonConfigs.ENABLE_CLEAVER.get() : true;
    public static boolean KHOPESH = V ? KnightQuestCommonConfigs.ENABLE_KHOPESH.get() : true;
    public static boolean KUKRI = V ? KnightQuestCommonConfigs.ENABLE_KUKRI.get() : true;
    public static boolean NAIL = V ? KnightQuestCommonConfigs.ENABLE_NAIL.get() : true;
    public static boolean PALADIN = V ? KnightQuestCommonConfigs.ENABLE_PALADIN.get() : true;
    public static boolean UCHIGATANA = V ? KnightQuestCommonConfigs.ENABLE_UCHIGATANA.get() : true;

    public static int COOLDOWN_CLEAVER = V ? KnightQuestCommonConfigs.COOLDOWN_CLEAVER.get() : 1800;
    public static int COOLDOWN_KHOPESH = V ? KnightQuestCommonConfigs.COOLDOWN_KHOPESH.get() : 500;
    public static int COOLDOWN_KUKRI = V ? KnightQuestCommonConfigs.COOLDOWN_KUKRI.get() : 300;
    public static int COOLDOWN_NAIL = V ? KnightQuestCommonConfigs.COOLDOWN_NAIL.get() : 100;
    public static int COOLDOWN_PALADIN = V ? KnightQuestCommonConfigs.COOLDOWN_PALADIN.get() : 500;
    public static int COOLDOWN_UCHIGATANA = V ? KnightQuestCommonConfigs.COOLDOWN_UCHIGATANA.get() : 400;

    public static int SPEED_TICKS_KUKRI = V ? KnightQuestCommonConfigs.SPEED_TICKS_KUKRI.get() : 120;
    public static int FREEZE_TICKS_KUKRI = V ? KnightQuestCommonConfigs.FREEZE_TICKS_KUKRI.get() : 125;
    public static int INV_TICKS_PALADIN = V ? KnightQuestCommonConfigs.INV_TICKS_PALADIN.get() : 100;
    public static double DASH_POWER_NAIL = V ? KnightQuestCommonConfigs.DASH_POWER_NAIL.get() : 1.5;
    public static double EXTRA_DAMAGE_UCHIGATANA = V ? KnightQuestCommonConfigs.EXTRA_DAMAGE_UCHIGATANA.get() : 0.6;
    public static double EXTRA_DAMAGE_PASSIVE_UCHIGATANA = V ? KnightQuestCommonConfigs.EXTRA_DAMAGE_PASSIVE_UCHIGATANA.get() : 0.2;
    public static double ENEMY_HEALTH_PASSIVE_UCHIGATANA = V ? KnightQuestCommonConfigs.ENEMY_HEALTH_PASSIVE_UCHIGATANA.get() : 0.5;
    public static int REFLECTION_TIME_KHOPESH = V ? KnightQuestCommonConfigs.REFLECTION_TIME_KHOPESH.get() : 160;
    public static double CHANCE_BURN_KHOPESH = V ? KnightQuestCommonConfigs.CHANCE_BURN_KHOPESH.get() : 0.15;
    public static double REGEN_MAX_PALADIN = V ? KnightQuestCommonConfigs.REGEN_MAX_PALADIN.get() : 0.50;
    public static int REGEN_TICKS_PALADIN = V ? KnightQuestCommonConfigs.REGEN_TICKS_PALADIN.get() : 30;
    public static int REGEN_HP_PALADIN = V ? KnightQuestCommonConfigs.REGEN_HP_PALADIN.get() : 1;
    public static int TICKS_CLEAVER = V ? KnightQuestCommonConfigs.TICKS_CLEAVER.get() : 600;
    public static double EXTRA_DAMAGE_PASSIVE_CLEAVER = V ? KnightQuestCommonConfigs.EXTRA_DAMAGE_PASSIVE_CLEAVER.get() : 0.2;
    public static double ENEMY_HEALTH_PASSIVE_CLEAVER = V ? KnightQuestCommonConfigs.ENEMY_HEALTH_PASSIVE_CLEAVER.get() : 0.5;
}
