package net.xylonity.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;

public class KnightQuestParticles {
    public static final SimpleParticleType STARSET_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType CHALICE_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType GHOSTY_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType GREMLIN_PARTICLE = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "starset"), STARSET_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "chalice_particle"), CHALICE_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "ghosty"), GHOSTY_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "gremlin"), GREMLIN_PARTICLE);

    }
}
