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

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GREMLIN_PARTICLE = register("gremlin");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_PARTICLE = register("yellow");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GHOSTY_PARTICLE = register("ghosty");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SNOWFLAKE_PARTICLE = register("snowflake");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POISON_PARTICLE = register("poison");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> POISON_CLOUD_PARTICLE = register("poison_cloud");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLASTWAVE = register2("blastwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FIREBALL = register2("fireball");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLANK_FIREBALL = register2("blank_fireball");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SMOKE = register2("smoke");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPARKS = register2("sparks");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BUBBLE = register2("bubble");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHOCKWAVE = register2("shockwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLANK_SHOCKWAVE = register2("blank_shockwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> UNDERWATERBLASTWAVE = register2("underwaterblastwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> UNDERWATERSPARKS = register2("underwatersparks");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLUEBLASTWAVE = register2("blue_blastwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLUEFIREBALL = register2("blue_fireball");

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> REDBLASTWAVE = register2("red_blastwave");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> REDFIREBALL = register2("red_fireball");

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(true));
    }

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> register2(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

}
