package net.xylonity.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;

public class KnightQuestParticles {
    public static final DefaultParticleType STARSET_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType CHALICE_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType GHOSTY_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType GREMLIN_PARTICLE = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(KnightQuest.MOD_ID, "starset"), STARSET_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(KnightQuest.MOD_ID, "chalice_particle"), CHALICE_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(KnightQuest.MOD_ID, "ghosty"), GHOSTY_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(KnightQuest.MOD_ID, "gremlin"), GREMLIN_PARTICLE);

    }
}
