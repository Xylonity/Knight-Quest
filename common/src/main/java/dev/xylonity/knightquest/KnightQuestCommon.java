package dev.xylonity.knightquest;

import dev.xylonity.knightlib.KnightLib;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.platform.KnightQuestPlatform;
import dev.xylonity.knightquest.registry.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class KnightQuestCommon {

    public static final String MOD_ID = "knightquest";
    public static final Logger LOGGER = LoggerFactory.getLogger("Knight Quest");

    public static final KnightQuestPlatform COMMON_PLATFORM = ServiceLoader.load(KnightQuestPlatform.class).findFirst().orElseThrow();

    public static void init() {
        KQArmorMaterials.init();
        KnightQuestItems.init();
        KnightQuestSounds.init();
        KnightQuestParticles.init();
        KnightQuestCreativeModeTabs.init();
        KnightQuestEntities.init();

        KnightLib.initialize(
                KnightLib.Usage.COPPER_GRAILS,
                KnightLib.Usage.GREAT_CHALICE,
                KnightLib.Usage.GREEN_ESSENCES
        );
    }

}