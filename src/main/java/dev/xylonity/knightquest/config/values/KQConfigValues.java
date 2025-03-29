package dev.xylonity.knightquest.config.values;

import java.util.HashMap;
import java.util.Map;

/**
 * Auxiliary configuration values in case the dedicated configuration file is not detected,
 * allowing for hot-reloading (this is particularly useful in client-sided configurations).
 */

public class KQConfigValues {
    private static final Map<String, Object> values = new HashMap<>();

    // General Configuration Section
    public static final ConfigValue<Integer> REQUIRED_ARMOR_PIECES = register("REQUIRED_ARMOR_PIECES", 4);

    // Weapon Configuration Section
    public static final ConfigValue<Integer> COOLDOWN_CLEAVER = register("COOLDOWN_CLEAVER", 1800);
    public static final ConfigValue<Integer> COOLDOWN_KHOPESH = register("COOLDOWN_KHOPESH", 500);
    public static final ConfigValue<Integer> COOLDOWN_KUKRI = register("COOLDOWN_KUKRI", 300);
    public static final ConfigValue<Integer> COOLDOWN_NAIL = register("COOLDOWN_NAIL", 100);
    public static final ConfigValue<Integer> COOLDOWN_PALADIN = register("COOLDOWN_PALADIN", 500);
    public static final ConfigValue<Integer> COOLDOWN_UCHIGATANA = register("COOLDOWN_UCHIGATANA", 400);
    public static final ConfigValue<Integer> SPEED_TICKS_KUKRI = register("SPEED_TICKS_KUKRI", 120);
    public static final ConfigValue<Integer> FREEZE_TICKS_KUKRI = register("FREEZE_TICKS_KUKRI", 125);
    public static final ConfigValue<Integer> INV_TICKS_PALADIN = register("INV_TICKS_PALADIN", 100);
    public static final ConfigValue<Double> DASH_POWER_NAIL = register("DASH_POWER_NAIL", 1.5);
    public static final ConfigValue<Double> EXTRA_DAMAGE_UCHIGATANA = register("EXTRA_DAMAGE_UCHIGATANA", 0.6);
    public static final ConfigValue<Double> EXTRA_DAMAGE_PASSIVE_UCHIGATANA = register("EXTRA_DAMAGE_PASSIVE_UCHIGATANA", 0.2);
    public static final ConfigValue<Double> ENEMY_HEALTH_PASSIVE_UCHIGATANA = register("ENEMY_HEALTH_PASSIVE_UCHIGATANA", 0.5);
    public static final ConfigValue<Integer> REFLECTION_TIME_KHOPESH = register("REFLECTION_TIME_KHOPESH", 160);
    public static final ConfigValue<Double> CHANCE_BURN_KHOPESH = register("CHANCE_BURN_KHOPESH", 0.15);
    public static final ConfigValue<Integer> REGEN_TICKS_PALADIN = register("REGEN_TICKS_PALADIN", 30);
    public static final ConfigValue<Double> REGEN_MAX_PALADIN = register("REGEN_MAX_PALADIN", 0.5);
    public static final ConfigValue<Integer> REGEN_HP_PALADIN = register("REGEN_HP_PALADIN", 1);
    public static final ConfigValue<Integer> TICKS_CLEAVER = register("TICKS_CLEAVER", 600);
    public static final ConfigValue<Double> EXTRA_DAMAGE_PASSIVE_CLEAVER = register("EXTRA_DAMAGE_PASSIVE_CLEAVER", 0.2);
    public static final ConfigValue<Double> ENEMY_HEALTH_PASSIVE_CLEAVER = register("ENEMY_HEALTH_PASSIVE_CLEAVER", 0.5);

    // Eld Knight Configuration Section
    public static final ConfigValue<Boolean> POISON_ELDKNIGHT = register("POISON_ELDKNIGHT", true);
    public static final ConfigValue<Integer> NUM_ELDBOMB_ELDKNIGHT = register("NUM_ELDBOMB_ELDKNIGHT", 3);
    public static final ConfigValue<Double> HEAL_ELDKNIGHT = register("HEAL_ELDKNIGHT", 3.0);

    // Ghosty Configuration Section
    public static final ConfigValue<Double> INVULNERABILITY_RADIUS_GHOSTY = register("INVULNERABILITY_RADIUS_GHOSTY", 7.0);

    // Gremlin Configuration Section
    public static final ConfigValue<Boolean> CAN_TAKE_GOLD_GREMLIN = register("CAN_TAKE_GOLD_GREMLIN", true);
    public static final ConfigValue<Double> MULTIPLIER_GREMLIN_MOVEMENT_SPEED = register("MULTIPLIER_GREMLIN_MOVEMENT_SPEED", 1.1);
    public static final ConfigValue<Double> MULTIPLIER_GREMLIN_ATTACK_SPEED = register("MULTIPLIER_GREMLIN_ATTACK_SPEED", 1.15);
    public static final ConfigValue<Double> MULTIPLIER_GREMLIN_ATTACK_DAMAGE = register("MULTIPLIER_GREMLIN_ATTACK_DAMAGE", 1.2);

    // Swampman Configuration Section
    public static final ConfigValue<Double> PHASE_2_HEALING_SWAMPMAN = register("PHASE_2_HEALING_SWAMPMAN", 0.0);
    public static final ConfigValue<Boolean> CAN_CHANGE_PHASE_SWAMPMAN = register("CAN_CHANGE_PHASE_SWAMPMAN", true);
    public static final ConfigValue<Boolean> POISON_PHASE_2_SWAMPMAN = register("POISON_PHASE_2_SWAMPMAN", false);

    // Netherman Configuration Section
    public static final ConfigValue<Double> NETHERMAN_HEALTH = register("NETHERMAN_HEALTH", 450.0);
    public static final ConfigValue<Double> NETHERMAN_DAMAGE = register("NETHERMAN_DAMAGE", 16.0);
    public static final ConfigValue<Boolean> TELEPORT_ON_HIT = register("TELEPORT_ON_HIT", true);
    public static final ConfigValue<Integer> FIRE_ATTACK_MIN_TIME = register("FIRE_ATTACK_MIN_TIME", 3);
    public static final ConfigValue<Integer> FIRE_ATTACK_MAX_TIME = register("FIRE_ATTACK_MAX_TIME", 7);
    public static final ConfigValue<Integer> MAX_NETHERMAN_CLONES = register("MAX_NETHERMAN_CLONES", 4);
    public static final ConfigValue<Integer> ICE_ATTACK_FREEZE_TICKS = register("ICE_ATTACK_FREEZE_TICKS", 300);
    public static final ConfigValue<Integer> DARKNESS_ATTACK_MIN_TIME = register("DARKNESS_ATTACK_MIN_TIME", 3);
    public static final ConfigValue<Integer> DARKNESS_ATTACK_MAX_TIME = register("DARKNESS_ATTACK_MAX_TIME", 7);
    public static final ConfigValue<Integer> CLONE_EXPLOSION_FREEZE_TICKS = register("CLONE_EXPLOSION_FREEZE_TICKS", 200);
    public static final ConfigValue<Double> NETHERMAN_PROJECTILE_EXPLOSION_RADIUS = register("NETHERMAN_PROJECTILE_EXPLOSION_RADIUS", 3.0d);
    public static final ConfigValue<Boolean> RESTORE_BLOCKS_POST_DEATH = register("RESTORE_BLOCKS_POST_DEATH", true);
    public static final ConfigValue<Integer> EXPERIENCE_DROP_AMOUNT = register("EXPERIENCE_DROP_AMOUNT", 500);

    // Drop Chance Configuration Section
    public static final ConfigValue<Double> DROP_CHANCE_RATMAN_EYE = register("DROP_CHANCE_RATMAN_EYE", 0.4);
    public static final ConfigValue<Double> DROP_CHANCE_LIZZY_SCALE = register("DROP_CHANCE_LIZZY_SCALE", 0.3);

    // Armor Set Passives Configuration Section
    public static final ConfigValue<Boolean> ENABLE_BAMBOOSET_PUSH_PLAYERS = register("ENABLE_BAMBOOSET_PUSH_PLAYERS", false);
    public static final ConfigValue<Double> CHANCE_ENDERMANSET = register("CHANCE_ENDERMANSET", 0.4);
    public static final ConfigValue<Integer> TELEPORT_RADIUS_ENDERMANSET = register("TELEPORT_RADIUS_ENDERMANSET", 10);
    public static final ConfigValue<Double> FORZESET_DEFLECT_CHANCE = register("FORZESET_DEFLECT_CHANCE", 0.3);
    public static final ConfigValue<Double> FORZESET_DEFLECT_DAMAGE = register("FORZESET_DEFLECT_DAMAGE", 0.5);
    public static final ConfigValue<Double> SILVERSET_BURN_CHANCE = register("SILVERSET_BURN_CHANCE", 0.3);
    public static final ConfigValue<Double> HOLLOWSET_HEALING_MULTIPLIER = register("HOLLOWSET_HEALING_MULTIPLIER", 0.25);
    public static final ConfigValue<Double> DRAGONSET_DAMAGE_MULTIPLIER = register("DRAGONSET_DAMAGE_MULTIPLIER", 1.15);
    public static final ConfigValue<Double> WITHERSET_WITHER_CHANCE = register("WITHERSET_WITHER_CHANCE", 0.3);
    public static final ConfigValue<Boolean> SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF = register("SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF", false);
    public static final ConfigValue<Integer> WARLORD_SET_EFFECT_RADIUS = register("WARLORD_SET_EFFECT_RADIUS", 15);
    public static final ConfigValue<Double> ZOMBIESET_HEALING_AMOUNT = register("ZOMBIESET_HEALING_AMOUNT", 1.0);
    public static final ConfigValue<Integer> ZOMBIESET_HEALING_TICKS = register("ZOMBIESET_HEALING_TICKS", 120);
    public static final ConfigValue<Double> DEEPSLATE_FALL_DAMAGE_MULTIPLIER = register("DEEPSLATE_FALL_DAMAGE_MULTIPLIER", 0.2);
    public static final ConfigValue<Double> EVOKER_DARKNESS_CHANCE = register("EVOKER_DARKNESS_CHANCE", 0.25);
    public static final ConfigValue<Double> SQUIRE_DAMAGE_RECEIVED_MULTIPLIER = register("SQUIRE_DAMAGE_RECEIVED_MULTIPLIER", 0.85);
    public static final ConfigValue<Double> BLAZE_FIRE_CHANCE = register("BLAZE_FIRE_CHANCE", 0.4);
    public static final ConfigValue<Integer> BLAZE_FIRE_DURATION_MIN = register("BLAZE_FIRE_DURATION_MIN", 2);
    public static final ConfigValue<Integer> BLAZE_FIRE_DURATION_MAX = register("BLAZE_FIRE_DURATION_MAX", 8);
    public static final ConfigValue<Double> CREEPER_EXPLOSION_DAMAGE_MULTIPLIER = register("CREEPER_EXPLOSION_DAMAGE_MULTIPLIER", 0.1);
    public static final ConfigValue<Integer> SILVERFISH_EFFECT_MAX_HEIGHT = register("SILVERFISH_EFFECT_MAX_HEIGHT", 50);
    public static final ConfigValue<Integer> SKULK_MAX_LIGHT_LEVEL = register("SKULK_MAX_LIGHT_LEVEL", 4);

    // Armor Set Passives Enabler Configuration Section
    public static final ConfigValue<Boolean> DEEPSLATESET = register("ENABLE_DEEPSLATESET", true);
    public static final ConfigValue<Boolean> EVOKERSET = register("ENABLE_EVOKERSET", true);
    public static final ConfigValue<Boolean> SQUIRESET = register("ENABLE_SQUIRESET", true);
    public static final ConfigValue<Boolean> BLAZESET = register("ENABLE_BLAZESET", true);
    public static final ConfigValue<Boolean> DRAGONSET = register("ENABLE_DRAGONSET", true);
    public static final ConfigValue<Boolean> BAMBOOSET_GREEN = register("ENABLE_BAMBOOSET_GREEN", true);
    public static final ConfigValue<Boolean> SHINOBI = register("ENABLE_SHINOBI", true);
    public static final ConfigValue<Boolean> BAMBOOSET = register("ENABLE_BAMBOOSET", true);
    public static final ConfigValue<Boolean> PATHSET = register("ENABLE_PATHSET", true);
    public static final ConfigValue<Boolean> BOWSET = register("ENABLE_BOWSET", true);
    public static final ConfigValue<Boolean> BATSET = register("ENABLE_BATSET", true);
    public static final ConfigValue<Boolean> SHIELDSET = register("ENABLE_SHIELDSET", true);
    public static final ConfigValue<Boolean> PHANTOMSET = register("ENABLE_PHANTOMSET", true);
    public static final ConfigValue<Boolean> HORNSET = register("ENABLE_HORNSET", true);
    public static final ConfigValue<Boolean> SEASET = register("ENABLE_SEASET", true);
    public static final ConfigValue<Boolean> PIRATESET = register("ENABLE_PIRATESET", true);
    public static final ConfigValue<Boolean> SPIDERSET = register("ENABLE_SPIDERSET", true);
    public static final ConfigValue<Boolean> NETHERSET = register("ENABLE_NETHERSET", true);
    public static final ConfigValue<Boolean> SKULK = register("ENABLE_SKULK", true);
    public static final ConfigValue<Boolean> STRAWHATSET = register("ENABLE_STRAWHATSET", true);
    public static final ConfigValue<Boolean> ENDERMANSET = register("ENABLE_ENDERMANSET", true);
    public static final ConfigValue<Boolean> VETERANSET = register("ENABLE_VETERANSET", true);
    public static final ConfigValue<Boolean> FORZESET = register("ENABLE_FORZESET", true);
    public static final ConfigValue<Boolean> CREEPERSET = register("ENABLE_CREEPERSET", true);
    public static final ConfigValue<Boolean> POLAR = register("ENABLE_POLAR", true);
    public static final ConfigValue<Boolean> SILVERSET = register("ENABLE_SILVERSET", true);
    public static final ConfigValue<Boolean> HOLLOWSET = register("ENABLE_HOLLOWSET", true);
    public static final ConfigValue<Boolean> WITHERSET = register("ENABLE_WITHERSET", true);
    public static final ConfigValue<Boolean> APPLE_SET = register("ENABLE_APPLE_SET", true);
    public static final ConfigValue<Boolean> CONQUISTADORSET = register("ENABLE_CONQUISTADORSET", true);
    public static final ConfigValue<Boolean> WITCH = register("ENABLE_WITCH", true);
    public static final ConfigValue<Boolean> TENGU_HELMET = register("ENABLE_TENGU_HELMET", true);
    public static final ConfigValue<Boolean> HUSKSET = register("ENABLE_HUSKSET", true);
    public static final ConfigValue<Boolean> BAMBOOSET_BLUE = register("ENABLE_BAMBOOSET_BLUE", true);
    public static final ConfigValue<Boolean> WARLORDSET = register("ENABLE_WARLORDSET", true);
    public static final ConfigValue<Boolean> ZOMBIESET = register("ENABLE_ZOMBIESET", true);
    public static final ConfigValue<Boolean> SILVERFISHSET = register("ENABLE_SILVERFISHSET", true);
    public static final ConfigValue<Boolean> SKELETONSET = register("ENABLE_SKELETONSET", true);

    // Weapon Enabler Configuration Section
    public static final ConfigValue<Boolean> CLEAVER = register("ENABLE_CLEAVER", true);
    public static final ConfigValue<Boolean> KHOPESH = register("ENABLE_KHOPESH", true);
    public static final ConfigValue<Boolean> KUKRI = register("ENABLE_KUKRI", true);
    public static final ConfigValue<Boolean> NAIL = register("ENABLE_NAIL", true);
    public static final ConfigValue<Boolean> PALADIN = register("ENABLE_PALADIN", true);
    public static final ConfigValue<Boolean> UCHIGATANA = register("ENABLE_UCHIGATANA", true);

    public static final ConfigValue<Double> BADPATCH_MAX_HEALTH = register("BADPATCH_MAX_HEALTH", 7.0);
    public static final ConfigValue<Double> ELDBOMB_MAX_HEALTH = register("ELDBOMB_MAX_HEALTH", 7.0);
    public static final ConfigValue<Double> ELDKNIGHT_MAX_HEALTH = register("ELDKNIGHT_MAX_HEALTH", 90.0);
    public static final ConfigValue<Double> FALLENKNIGHT_MAX_HEALTH = register("FALLENKNIGHT_MAX_HEALTH", 35.0);
    public static final ConfigValue<Double> GREMLIN_MAX_HEALTH = register("GREMLIN_MAX_HEALTH", 35.0);
    public static final ConfigValue<Double> LIZZY_MAX_HEALTH = register("LIZZY_MAX_HEALTH", 10.0);
    public static final ConfigValue<Double> RATMAN_MAX_HEALTH = register("RATMAN_MAX_HEALTH", 22.0);
    public static final ConfigValue<Double> SWAMPMAN_MAX_HEALTH = register("SWAMPMAN_MAX_HEALTH", 50.0);

    private static <T> ConfigValue<T> register(String key, T defaultValue) {
        values.put(key, defaultValue);
        return new ConfigValue<>(key);
    }

    public static class ConfigValue<T> {
        private final String key;

        private ConfigValue(String key) {
            this.key = key;
        }

        public T get() {
            @SuppressWarnings("unchecked")
            T value = (T) values.get(key);
            return value;
        }

        public void set(T value) {
            values.put(key, value);
        }

    }
}