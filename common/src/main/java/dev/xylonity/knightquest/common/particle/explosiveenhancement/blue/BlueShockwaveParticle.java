package dev.xylonity.knightquest.common.particle.explosiveenhancement.blue;

import dev.xylonity.knightquest.common.particle.explosiveenhancement.ShockwaveParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

public class BlueShockwaveParticle extends ShockwaveParticle {

    boolean important;

    BlueShockwaveParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, double velX, double velY, double velZ) {
        super(world, x, y, z, spriteProvider, velX, velY, velZ);
        this.lifetime = (int) (9 + Math.floor(velX / 5));
        this.quadSize = (float) velX;
        important = velY == 1;
        this.setParticleSpeed(0D, 0D, 0D);
        this.setSpriteFromAge(spriteProvider);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new BlueShockwaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
