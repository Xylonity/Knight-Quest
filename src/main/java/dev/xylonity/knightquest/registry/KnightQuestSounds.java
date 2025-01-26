package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class KnightQuestSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KnightQuest.MOD_ID);

    public static final Supplier<SoundEvent> THE_ARCHITECT_OF_CHAOS = registerSound("the_architect_of_chaos", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "the_architect_of_chaos"), 70F));

    private static <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return SOUNDS.register(id, sound);
    }
}
