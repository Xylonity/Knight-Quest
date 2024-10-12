package dev.xylonity.knightquest.platform;

import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class KnightQuestServices {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        KnightQuestCommon.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);

        return loadedService;
    }
}