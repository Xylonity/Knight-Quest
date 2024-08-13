package net.xylonity.knightquest.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.xylonity.knightquest.KnightQuest;

import java.util.function.Supplier;

public class KnightQuestSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, KnightQuest.MOD_ID);

    public static final Supplier<SoundEvent> NETHERMAN_BOSS_MUSIC = register("netherman_boss_music");

    private static Supplier<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, name)));
    }
}
