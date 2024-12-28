package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

import java.util.function.Supplier;

public class KnightQuestSounds {

    public static void init() { ;; }

    public static final Supplier<SoundEvent> THE_ARCHITECT_OF_CHAOS = registerSound("the_architect_of_chaos", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "the_architect_of_chaos"), 70F));

    private static <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return KnightQuestCommon.COMMON_PLATFORM.registerSound(id, sound);
    }

}
