package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class KnightQuestSounds {

    public static void init() { ;; }

    public static final Supplier<?> NETHERMAN_BOSS_MUSIC = registerSound("netherman_boss_music", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "netherman_boss_music")));

    private static <T extends SoundEvent> Supplier<?> registerSound(String id, Supplier<T> sound) {
        return KnightQuestCommon.COMMON_PLATFORM.registerSound(id, sound);
    }

}
