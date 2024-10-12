package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class KnightQuestSounds {

    public static void init() { ;; }

    public static final SoundEvent NETHERMAN_BOSS_MUSIC = register("netherman_boss_music");

    private static SoundEvent register(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation(KnightQuest.MOD_ID, name), SoundEvent.createVariableRangeEvent(new ResourceLocation(KnightQuest.MOD_ID, name)));
    }

}
