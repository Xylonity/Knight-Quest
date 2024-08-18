package net.xylonity.knightquest.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KnightQuest.MOD_ID);

    public static final RegistryObject<SoundEvent> NETHERMAN_BOSS_MUSIC = register("netherman_boss_music");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(KnightQuest.MOD_ID, name)));
    }
}
