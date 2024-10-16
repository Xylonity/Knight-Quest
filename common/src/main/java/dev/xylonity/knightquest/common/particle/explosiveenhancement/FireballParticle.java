package dev.xylonity.knightquest.common.particle.explosiveenhancement;

import dev.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveValues;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class FireballParticle extends TextureSheetParticle {

    private final SpriteSet sprites;
    boolean important;
    private double velX;
    private double velY;
    private double velZ;

    public FireballParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, double velX, double velY, double velZ) {
        super(world, x, y, z);
        this.sprites = spriteProvider;
        this.lifetime = (int) (9 + Math.floor(velX / 5));
        this.quadSize = (float) velX;
        important = velY == 1;
        this.setParticleSpeed(0D, 0D, 0D);
        this.setSpriteFromAge(spriteProvider);
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
            if(this.age >= this.lifetime * 0.65 && ExplosiveValues.showSparks) {
                // this.level.addParticle(KnightQuestP.SPARKS.get(), important, this.x, this.y, this.z, quadSize, this.yd, this.zd);
            }
            this.setSpriteFromAge(this.sprites);
        }
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return ExplosiveValues.emissiveExplosion ? 15728880 : super.getLightColor(pPartialTick);
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new FireballParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
