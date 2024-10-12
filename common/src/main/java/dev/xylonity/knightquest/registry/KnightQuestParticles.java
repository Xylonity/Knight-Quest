package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class KnightQuestParticles {

    public static void init() { ;; }

    public static final Supplier<SimpleParticleType> GREMLIN_PARTICLE = registerParticle("gremlin", true);
    public static final Supplier<SimpleParticleType> YELLOW_PARTICLE = registerParticle("yellow", true);
    public static final Supplier<SimpleParticleType> GHOSTY_PARTICLE = registerParticle("ghosty", true);
    public static final Supplier<SimpleParticleType> SNOWFLAKE_PARTICLE = registerParticle("snowflake", true);
    public static final Supplier<SimpleParticleType> POISON_PARTICLE = registerParticle("poison", true);
    public static final Supplier<SimpleParticleType> POISON_CLOUD_PARTICLE = registerParticle("poison_cloud", true);

    public static final Supplier<SimpleParticleType> BLASTWAVE = registerParticle("blastwave", false);
    public static final Supplier<SimpleParticleType> FIREBALL = registerParticle("fireball", false);
    public static final Supplier<SimpleParticleType> BLANK_FIREBALL = registerParticle("blank_fireball", false);
    public static final Supplier<SimpleParticleType> SMOKE = registerParticle("smoke", false);
    public static final Supplier<SimpleParticleType> SPARKS = registerParticle("sparks", false);
    public static final Supplier<SimpleParticleType> BUBBLE = registerParticle("bubble", false);
    public static final Supplier<SimpleParticleType> SHOCKWAVE = registerParticle("shockwave", false);
    public static final Supplier<SimpleParticleType> BLANK_SHOCKWAVE = registerParticle("blank_shockwave", false);
    public static final Supplier<SimpleParticleType> UNDERWATERBLASTWAVE = registerParticle("underwaterblastwave", false);
    public static final Supplier<SimpleParticleType> UNDERWATERSPARKS = registerParticle("underwatersparks", false);

    public static final Supplier<SimpleParticleType> BLUEBLASTWAVE = registerParticle("blue_blastwave", false);
    public static final Supplier<SimpleParticleType> BLUEFIREBALL = registerParticle("blue_fireball", false);

    public static final Supplier<SimpleParticleType> REDBLASTWAVE = registerParticle("red_blastwave", false);
    public static final Supplier<SimpleParticleType> REDFIREBALL = registerParticle("red_fireball", false);

    private static <T extends SimpleParticleType> Supplier<T> registerParticle(String id, boolean overrideLimiter) {
        return KnightQuestCommon.COMMON_PLATFORM.registerParticle(id, overrideLimiter);
    }

}
