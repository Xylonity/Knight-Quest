package dev.xylonity.knightquest.registry;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

public class KnightQuestParticles {

    public static void init() { ;; }

    public static final Supplier<SimpleParticleType> GREMLIN_PARTICLE = registerParticle("gremlin");
    public static final Supplier<SimpleParticleType> YELLOW_PARTICLE = registerParticle("yellow");
    public static final Supplier<SimpleParticleType> GHOSTY_PARTICLE = registerParticle("ghosty");
    public static final Supplier<SimpleParticleType> SNOWFLAKE_PARTICLE = registerParticle("snowflake");
    public static final Supplier<SimpleParticleType> POISON_PARTICLE = registerParticle("poison");
    public static final Supplier<SimpleParticleType> POISON_CLOUD_PARTICLE = registerParticle("poison_cloud");

    private static <T extends SimpleParticleType> Supplier<T> registerParticle(String id) {
        return KnightQuestCommon.COMMON_PLATFORM.registerParticle(id, true);
    }

}
