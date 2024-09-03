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
    public static final SimpleParticleType SNOWFLAKE_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType POISON_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType POISON_CLOUD_PARTICLE = FabricParticleTypes.simple();

    public static final SimpleParticleType BLASTWAVE = FabricParticleTypes.simple();
    public static final SimpleParticleType BLUEBLASTWAVE = FabricParticleTypes.simple();
    public static final SimpleParticleType REDBLASTWAVE = FabricParticleTypes.simple();
    public static final SimpleParticleType FIREBALL = FabricParticleTypes.simple();
    public static final SimpleParticleType BLUEFIREBALL = FabricParticleTypes.simple();
    public static final SimpleParticleType REDFIREBALL = FabricParticleTypes.simple();
    public static final SimpleParticleType BLANK_FIREBALL = FabricParticleTypes.simple();
    public static final SimpleParticleType SMOKE = FabricParticleTypes.simple();
    public static final SimpleParticleType SPARKS = FabricParticleTypes.simple();
    public static final SimpleParticleType BUBBLE = FabricParticleTypes.simple();
    public static final SimpleParticleType SHOCKWAVE = FabricParticleTypes.simple();
    public static final SimpleParticleType BLANK_SHOCKWAVE = FabricParticleTypes.simple();
    public static final SimpleParticleType UNDERWATERBLASTWAVE = FabricParticleTypes.simple();
    public static final SimpleParticleType UNDERWATERSPARKS = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "starset"), STARSET_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "chalice_particle"), CHALICE_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "ghosty"), GHOSTY_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "gremlin"), GREMLIN_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "snowflake"), SNOWFLAKE_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "poison"), POISON_PARTICLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "poison_cloud"), POISON_CLOUD_PARTICLE);

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "blastwave"), BLASTWAVE);

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "fireball"), FIREBALL);

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "blank_fireball"), BLANK_FIREBALL);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "smoke"), SMOKE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "bubble"), BUBBLE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "shockwave"), SHOCKWAVE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "blank_shockwave"), BLANK_SHOCKWAVE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "underwaterblastwave"), UNDERWATERBLASTWAVE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "sparks"), SPARKS);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "underwatersparks"), UNDERWATERSPARKS);

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "blue_fireball"), BLUEFIREBALL);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "red_fireball"), REDFIREBALL);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "red_blastwave"), REDBLASTWAVE);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(KnightQuest.MOD_ID, "blue_blastwave"), BLUEBLASTWAVE);
    }
}
