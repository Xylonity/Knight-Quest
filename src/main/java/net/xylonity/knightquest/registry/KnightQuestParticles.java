package net.xylonity.knightquest.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.xylonity.knightquest.KnightQuest;

public class KnightQuestParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, KnightQuest.MOD_ID);

    public static final RegistryObject<SimpleParticleType> STARSET_PARTICLE =
            PARTICLES.register("starset", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GREMLIN_PARTICLE =
            PARTICLES.register("gremlin", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> YELLOW_PARTICLE =
            PARTICLES.register("yellow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GHOSTY_PARTICLE =
            PARTICLES.register("ghosty", () -> new SimpleParticleType(true));

}
