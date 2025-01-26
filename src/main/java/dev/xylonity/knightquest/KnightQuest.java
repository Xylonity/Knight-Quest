package dev.xylonity.knightquest;

import com.mojang.logging.LogUtils;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.KnightQuestCommonConfigs;
import dev.xylonity.knightquest.datagen.KQLootModifiers;
import dev.xylonity.knightquest.registry.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(KnightQuest.MOD_ID)
public class KnightQuest {

    public static final String MOD_ID = "knightquest";

    public KnightQuest() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        KnightQuestItems.ITEMS.register(modEventBus);
        KnightQuestCreativeModeTabs.CREATIVE_TABS.register(modEventBus);
        KnightQuestEntities.ENTITY.register(modEventBus);
        KQLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        KnightQuestParticles.PARTICLES.register(modEventBus);
        KnightQuestSounds.SOUNDS.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightQuestCommonConfigs.SPEC, "knightquest.toml");

    }

}
