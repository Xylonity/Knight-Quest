package dev.xylonity.knightquest;

import dev.xylonity.knightquest.platform.KnightQuestPlatform;
import dev.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import dev.xylonity.knightquest.registry.KnightQuestParticles;
import dev.xylonity.knightquest.registry.KnightQuestSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class KnightQuestCommon {

    public static final String MOD_ID = "knightquest";
    public static final Logger LOGGER = LoggerFactory.getLogger("Knight Quest");

    public static final KnightQuestPlatform COMMON_PLATFORM = ServiceLoader.load(KnightQuestPlatform.class).findFirst().orElseThrow();

    public static void init() {
        KnightQuestItems.init();
        KnightQuestCreativeModeTabs.init();
        KnightQuestSounds.init();
        KnightQuestParticles.init();
    }

}