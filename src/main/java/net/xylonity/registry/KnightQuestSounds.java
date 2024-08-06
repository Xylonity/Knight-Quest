package net.xylonity.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;

public class KnightQuestSounds {
    public static final SoundEvent NETHERMAN_BOSS_MUSIC = register("netherman_boss_music");

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(KnightQuest.MOD_ID, name), SoundEvent.of(Identifier.of(KnightQuest.MOD_ID, name)));
    }

    public static void registerSounds() {
        KnightQuest.LOGGER.info("Registering sounds for " + KnightQuest.MOD_ID + "...");
    }
}
