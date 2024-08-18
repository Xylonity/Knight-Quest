package net.xylonity.knightquest.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class KnightQuestCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue POISON_ELDKNIGHT;
    public static final ForgeConfigSpec.IntValue NUM_ELDBOMB_ELDKNIGHT;
    public static final ForgeConfigSpec.DoubleValue HEAL_ELDKNIGHT;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_SMALL_ESSENCE;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_RATMAN_EYE;
    public static final ForgeConfigSpec.DoubleValue DROP_CHANCE_LIZZY_SCALE;
    public static final ForgeConfigSpec.DoubleValue INVULNERABILITY_RADIUS_GHOSTY;

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
        DROP_CHANCE_RATMAN_EYE = BUILDER.defineInRange("Drop chance for ratman eye", 0.40, 0, 1);
        DROP_CHANCE_LIZZY_SCALE = BUILDER.defineInRange("Drop chance for lizzy scale", 0.30, 0, 1);

        BUILDER.comment("");
        INVULNERABILITY_RADIUS_GHOSTY = BUILDER.defineInRange("Ghosty invulnerability radius", 7.0, 0.0, 25.0);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
