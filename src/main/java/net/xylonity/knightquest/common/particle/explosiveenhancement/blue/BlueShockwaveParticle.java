package net.xylonity.knightquest.common.particle.explosiveenhancement.blue;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveValues;
import net.xylonity.knightquest.common.particle.explosiveenhancement.ShockwaveParticle;

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

    @OnlyIn(Dist.CLIENT)
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
