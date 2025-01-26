package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuest;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KnightQuestParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KnightQuest.MOD_ID);

    public static final RegistryObject<SimpleParticleType> GREMLIN_PARTICLE = register("gremlin");
    public static final RegistryObject<SimpleParticleType> YELLOW_PARTICLE = register("yellow");
    public static final RegistryObject<SimpleParticleType> GHOSTY_PARTICLE = register("ghosty");
    public static final RegistryObject<SimpleParticleType> SNOWFLAKE_PARTICLE = register("snowflake");
    public static final RegistryObject<SimpleParticleType> POISON_PARTICLE = register("poison");
    public static final RegistryObject<SimpleParticleType> POISON_CLOUD_PARTICLE = register("poison_cloud");

    private static RegistryObject<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(true));
    }

    private static RegistryObject<SimpleParticleType> register2(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

}
