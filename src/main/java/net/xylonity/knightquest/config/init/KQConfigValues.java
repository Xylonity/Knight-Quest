package net.xylonity.knightquest.config.init;

import net.neoforged.fml.loading.FMLPaths;
import net.xylonity.knightquest.config.KnightQuestCommonConfigs;

import java.nio.file.Files;
import java.nio.file.Path;

public class KQConfigValues {

    static Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("knightquest.toml");
    private static final boolean V = Files.exists(CONFIG_PATH);
    public static boolean POISON_ELDKNIGHT = V ? KnightQuestCommonConfigs.POISON_ELDKNIGHT.get() : true;
    public static int NUM_ELDBOMB_ELDKNIGHT = V ? KnightQuestCommonConfigs.NUM_ELDBOMB_ELDKNIGHT.get() : 3;
    public static float HEAL_ELDKNIGHT = (float) (V ? KnightQuestCommonConfigs.HEAL_ELDKNIGHT.get() : 3.0);
    public static float DROP_CHANCE_SMALL_ESSENCE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_SMALL_ESSENCE.get() : 0.15);
    public static float DROP_CHANCE_RATMAN_EYE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_RATMAN_EYE.get() : 0.2);
    public static float DROP_CHANCE_LIZZY_SCALE  = (float) (V ? KnightQuestCommonConfigs.DROP_CHANCE_LIZZY_SCALE.get() : 0.3);
    public static float INVULNERABILITY_RADIUS_GHOSTY  = (float) (V ? KnightQuestCommonConfigs.INVULNERABILITY_RADIUS_GHOSTY.get() : 7.0);
}
