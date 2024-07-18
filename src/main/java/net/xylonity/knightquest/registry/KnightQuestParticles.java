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

    private static RegistryObject<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(true));
    }

}
