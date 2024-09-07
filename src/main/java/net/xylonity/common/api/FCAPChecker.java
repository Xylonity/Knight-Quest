package net.xylonity.common.api;

import net.fabricmc.loader.api.*;

public class FCAPChecker {

    static boolean isModDetected = FabricLoader.getInstance().isModLoaded("forgeconfigapiport");

    /**
     * Checks if the `Forge Config API Port` is loaded and using the correct version.
     * @return true if the check is correct and false if not
     */

    public static boolean isLoaded() {
        if (isModDetected) {
            ModContainer modContainer = FabricLoader.getInstance().getModContainer("forgeconfigapiport").orElse(null);
            if (modContainer != null) {
                try {
                    SemanticVersion minimumRequiredVersion = SemanticVersion.parse("21.0.2");

                    Version modVersion = modContainer.getMetadata().getVersion();

                    if (modVersion.compareTo(minimumRequiredVersion) >= 0)
                        return true;

                } catch (VersionParsingException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return false;
    }

}
