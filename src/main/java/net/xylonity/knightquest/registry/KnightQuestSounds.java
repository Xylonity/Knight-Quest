package net.xylonity.knightquest.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.xylonity.knightquest.KnightQuest;

import java.util.function.Supplier;

public class KnightQuestSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KnightQuest.MOD_ID);

    public static final Supplier<SoundEvent> NETHERMAN_BOSS_MUSIC = register("netherman_boss_music");

    private static Supplier<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, name)));
    }
}
