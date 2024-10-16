package dev.xylonity.knightquest;

import dev.xylonity.knightquest.config.KnightQuestCommonConfigs;
import dev.xylonity.knightquest.datagen.KQLootModifiers;
import dev.xylonity.knightquest.registry.KnightQuestEntities;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest {

    public static final String MOD_ID = KnightQuestCommon.MOD_ID;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, KnightQuest.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KnightQuest.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(Registries.PARTICLE_TYPE, KnightQuest.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, KnightQuest.MOD_ID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, KnightQuest.MOD_ID);

    public KnightQuest(IEventBus modEventBus, ModContainer modContainer) {

        KnightQuestEntities.ENTITY.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);

        ARMOR_MATERIALS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        PARTICLES.register(modEventBus);
        SOUNDS.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

        KnightQuestCommon.init();

    }

}