package net.xylonity.common.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;

public class ChaliceParticle extends SpriteBillboardParticle {
    private final SpriteProvider sprites;

    ChaliceParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
                    SpriteProvider spriteSet, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.velocityMultiplier = 0.2F;
        this.sprites = spriteSet;
        this.x = xd;
        this.y = yd;
        this.z = zd;
        this.scale *= 0.45F;
        this.maxAge = 30;
        this.setSpriteForAge(spriteSet);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        this.alpha = (-(1/(float)maxAge) * age);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new ChaliceParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }

    }
}