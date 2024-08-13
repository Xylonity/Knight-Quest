package net.xylonity.knightquest.common.particle.explosiveenhancement.red;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.particle.explosiveenhancement.BlastWaveParticle;

public class RedBlastWaveParticle extends BlastWaveParticle {

    RedBlastWaveParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(world, x, y + 0.5, z, sprites, velX, 0.0, 0);
        this.quadSize = (float) velX;
        this.setParticleSpeed(0D, 0D, 0D);
        this.lifetime = (int) (15 + (Math.floor(velX / 5)));
        this.setSpriteFromAge(sprites);
        setColor(65, 63, 200);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new RedBlastWaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
