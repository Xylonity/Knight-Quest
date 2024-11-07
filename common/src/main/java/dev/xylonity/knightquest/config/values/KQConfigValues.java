package dev.xylonity.knightquest.config.values;

/**
 * Auxiliary configuration values in case the dedicated configuration file is not detected,
 * allowing for hot-reloading (this is particularly useful in client-sided configurations).
 */

public enum KQConfigValues {

    // Eld Knight Configuration Section
    POISON_ELDKNIGHT(true),
    NUM_ELDBOMB_ELDKNIGHT(3),
    HEAL_ELDKNIGHT(3.0f),

    // Drop Chance Configuration Section
    DROP_CHANCE_RATMAN_EYE(0.40f),
    DROP_CHANCE_LIZZY_SCALE(0.30f),

    // Gremlin Configuration Section
    CAN_TAKE_GOLD_GREMLIN(true),
    MULTIPLIER_GREMLIN_MOVEMENT_SPEED(1.15f),
    MULTIPLIER_GREMLIN_ATTACK_SPEED(1.15f),
    MULTIPLIER_GREMLIN_ATTACK_DAMAGE(1.15f),

    // Ghosty Configuration Section
    INVULNERABILITY_RADIUS_GHOSTY(7.0f),

    // Swampman Configuration Section
    PHASE_2_HEALING_SWAMPMAN(0.0f),
    CAN_CHANGE_PHASE_SWAMPMAN(true),
    POISON_PHASE_2_SWAMPMAN(true),

    // Netherman Configuration Section
    WINTER_STORM_RADIUS(50.0f),
    FROZEN_TICKS(4),
    CAN_SUMMON_NETHERMAN(true),
    SPAWN_LIGHTNING_ON_SPAWN(true),
    GENERATE_PARTICLES_ON_SUMMON(true),
    TELEPORT_PROBABILITY(0.5f),
    RESTORE_BLOCKS_POST_DEATH(true),
    EXPERIENCE_DROP_AMOUNT(500),
    LIGHTNING_STRIKE_IN_PHASE_THREE(true),
    LIGHTNING_TICK_INTERVAL(40),
    SNOW_PARTICLE_SPEED(1.5f),
    SNOW_PARTICLE_COUNT(60),

    // Armor Passives Configuration Section
    BAMBOOSET_PUSH_PLAYERS(false),
    TELEPORT_RADIUS_ENDERMANSET(10),
    FORZESET_DEFLECT_CHANCE(0.3f),
    FORZESET_DEFLECT_DAMAGE(0.5f),
    SILVERSET_BURN_CHANCE(0.3f),
    HOLLOWSET_HEALING_MULTIPLIER(0.25f),
    DRAGONSET_DAMAGE_MULTIPLIER(1.15f),
    WITHERSET_WITHER_CHANCE(0.3f),
    SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF(false),
    WARLORD_SET_EFFECT_RADIUS(15),
    ZOMBIESET_HEALING_AMOUNT(1.0f),
    ZOMBIESET_HEALING_TICKS(120),
    DEEPSLATE_FALL_DAMAGE_MULTIPLIER(0.2f),
    EVOKER_DARKNESS_CHANCE(0.25f),
    SQUIRE_DAMAGE_RECEIVED_MULTIPLIER(0.85f),
    BLAZE_FIRE_CHANCE(0.4f),
    BLAZE_FIRE_DURATION_MIN(2),
    BLAZE_FIRE_DURATION_MAX(8),
    CREEPER_EXPLOSION_DAMAGE_MULTIPLIER(0.1f),
    SILVERFISH_EFFECT_MAX_HEIGHT(50),
    SKULK_MAX_LIGHT_LEVEL(4),

    // Armor Passives Enabler Configuration Section
    DEEPSLATESET(true),
    EVOKERSET(true),
    SQUIRESET(true),
    BLAZESET(true),
    DRAGONSET(true),
    BAMBOOSET_GREEN(true),
    SHINOBI(true),
    BAMBOOSET(true),
    PATHSET(true),
    BOWSET(true),
    BATSET(true),
    SHIELDSET(true),
    PHANTOMSET(true),
    HORNSET(true),
    SEASET(true),
    PIRATESET(true),
    SPIDERSET(true),
    NETHERSET(true),
    SKULK(true),
    STRAWHATSET(true),
    ENDERMANSET(true),
    VETERANSET(true),
    FORZESET(true),
    CREEPERSET(true),
    POLAR(true),
    SILVERSET(true),
    HOLLOWSET(true),
    WITHERSET(true),
    APPLE_SET(true),
    CONQUISTADORSET(true),
    WITCH(true),
    TENGU_HELMET(true),
    HUSKSET(true),
    BAMBOOSET_BLUE(true),
    WARLORDSET(true),
    ZOMBIESET(true),
    SILVERFISHSET(true),
    SKELETONSET(true);

    private final Object defaultValue;
    private Object configValue;

    KQConfigValues(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public <T> void setConfigValue(T configValue) {
        this.configValue = configValue;
    }

    @SuppressWarnings("unchecked")
    private <T> T get() {
        return (T) (configValue != null ? configValue : defaultValue);
    }

    public int getInt() {
        return get();
    }

    public boolean getBoolean() {
        return get();
    }

    public float getFloat() {
        return get();
    }

}
