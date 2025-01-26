package dev.xylonity.knightquest.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class GhostyParticle extends TextureSheetParticle {
    private float rotSpeed;
    private final float particleRandom;
    private final float spinAcceleration;

    GhostyParticle(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet pSpriteSet) {
        super(pLevel, pX, pY, pZ);
        this.setSprite(pSpriteSet.get(this.random.nextInt(12), 12));
        this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.lifetime = 300;
        this.gravity = 7.5E-4F;
        float $$5 = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.quadSize = $$5;
        this.setSize($$5, $$5);
        this.friction = 1.0F;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float $$0 = (float)(300 - this.lifetime);
            float $$1 = Math.min($$0 / 300.0F, 1.0F);
            double $$2 = Math.cos(Math.toRadians(this.particleRandom * 60.0F)) * 2.0 * Math.pow($$1, 1.25);
            double $$3 = Math.sin(Math.toRadians(this.particleRandom * 60.0F)) * 2.0 * Math.pow($$1, 1.25);
            this.xd += $$2 * 0.0024999999441206455;
            this.zd += $$3 * 0.0024999999441206455;
            this.yd -= this.gravity;
            this.rotSpeed += this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll += this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < 299 && (this.xd == 0.0 || this.zd == 0.0)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd *= this.friction;
                this.yd *= this.friction;
                this.zd *= this.friction;
            }
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new GhostyParticle(level, x, y, z, this.sprites);
        }
    }
}
