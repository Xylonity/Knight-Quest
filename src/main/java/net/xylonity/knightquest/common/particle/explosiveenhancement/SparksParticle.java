package net.xylonity.knightquest.common.particle.explosiveenhancement;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveValues;

public class SparksParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    SparksParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, double velX, double velY, double velZ) {
        super(world, x, y, z);
        this.sprites = spriteProvider;
        this.lifetime = (int) (5 + Math.floor(velX / 5));
        if(velX == 0) {
            this.quadSize = (float) ExplosiveValues.sparkSize;
        } else {
            this.quadSize = (float) (ExplosiveValues.sparkSize * (velX * 0.25f));
        }
        this.setParticleSpeed(0D, 0D, 0D);
        this.alpha = (float) ExplosiveValues.sparkOpacity;
        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.setSpriteFromAge(this.sprites);
        }
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return ExplosiveValues.emissiveExplosion ? 15728880 : super.getLightColor(pPartialTick);
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
            return new SparksParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
