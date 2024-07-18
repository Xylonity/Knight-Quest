package net.xylonity.knightquest.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, KnightQuest.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> STARSET_PARTICLE =
            PARTICLES.register("starset", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GREMLIN_PARTICLE =
            PARTICLES.register("gremlin", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_PARTICLE =
            PARTICLES.register("yellow", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GHOSTY_PARTICLE =
            PARTICLES.register("ghosty", () -> new SimpleParticleType(true));

}
