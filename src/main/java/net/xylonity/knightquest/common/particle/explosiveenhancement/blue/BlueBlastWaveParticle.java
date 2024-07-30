package net.xylonity.knightquest.common.particle.explosiveenhancement.blue;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.particle.explosiveenhancement.BlastWaveParticle;

public class BlueBlastWaveParticle extends BlastWaveParticle {

    BlueBlastWaveParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(world, x, y + 0.5, z, sprites, velX, 0.0, 0);
        this.quadSize = (float) velX;
        this.setParticleSpeed(0D, 0D, 0D);
        this.lifetime = (int) (15 + (Math.floor(velX / 5)));
        this.setSpriteFromAge(sprites);
        setColor(180, 20, 10);
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
            return new BlueBlastWaveParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
