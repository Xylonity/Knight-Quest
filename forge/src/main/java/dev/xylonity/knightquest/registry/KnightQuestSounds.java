package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KnightQuestSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KnightQuest.MOD_ID);

    public static final RegistryObject<SoundEvent> NETHERMAN_BOSS_MUSIC = register("netherman_boss_music");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(KnightQuest.MOD_ID, name)));
    }
}
