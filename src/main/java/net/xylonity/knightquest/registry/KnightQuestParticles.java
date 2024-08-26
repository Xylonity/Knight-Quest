package net.xylonity.knightquest.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KnightQuest.MOD_ID);

    public static final RegistryObject<SimpleParticleType> STARSET_PARTICLE = register("starset");
    public static final RegistryObject<SimpleParticleType> GREMLIN_PARTICLE = register("gremlin");
    public static final RegistryObject<SimpleParticleType> YELLOW_PARTICLE = register("yellow");
    public static final RegistryObject<SimpleParticleType> GHOSTY_PARTICLE = register("ghosty");
    public static final RegistryObject<SimpleParticleType> SNOWFLAKE_PARTICLE = register("snowflake");
    public static final RegistryObject<SimpleParticleType> POISON_PARTICLE = register("poison");
    public static final RegistryObject<SimpleParticleType> POISON_CLOUD_PARTICLE = register("poison_cloud");

    public static final RegistryObject<SimpleParticleType> BLASTWAVE = register2("blastwave");
    public static final RegistryObject<SimpleParticleType> FIREBALL = register2("fireball");
    public static final RegistryObject<SimpleParticleType> BLANK_FIREBALL = register2("blank_fireball");
    public static final RegistryObject<SimpleParticleType> SMOKE = register2("smoke");
    public static final RegistryObject<SimpleParticleType> SPARKS = register2("sparks");
    public static final RegistryObject<SimpleParticleType> BUBBLE = register2("bubble");
    public static final RegistryObject<SimpleParticleType> SHOCKWAVE = register2("shockwave");
    public static final RegistryObject<SimpleParticleType> BLANK_SHOCKWAVE = register2("blank_shockwave");
    public static final RegistryObject<SimpleParticleType> UNDERWATERBLASTWAVE = register2("underwaterblastwave");
    public static final RegistryObject<SimpleParticleType> UNDERWATERSPARKS = register2("underwatersparks");

    public static final RegistryObject<SimpleParticleType> BLUEBLASTWAVE = register2("blue_blastwave");
    public static final RegistryObject<SimpleParticleType> BLUEFIREBALL = register2("blue_fireball");

    public static final RegistryObject<SimpleParticleType> REDBLASTWAVE = register2("red_blastwave");
    public static final RegistryObject<SimpleParticleType> REDFIREBALL = register2("red_fireball");

    private static RegistryObject<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(true));
    }

    private static RegistryObject<SimpleParticleType> register2(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

}
