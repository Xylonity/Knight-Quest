package net.xylonity.common.particle.explosiveenhancement.red;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.particle.explosiveenhancement.FireballParticle;

public class RedFireballParticle extends FireballParticle {

    boolean important;

    RedFireballParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, double velX, double velY, double velZ) {
        super(world, x, y, z, spriteProvider, velX, velY, velZ);
        this.lifetime = (int) (9 + Math.floor(velX / 5));
        this.quadSize = (float) velX;
        important = velY == 1;
        this.setParticleSpeed(0D, 0D, 0D);
        this.setSpriteFromAge(spriteProvider);
        setColor(65, 63, 200);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
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
            return new RedFireballParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
