package net.xylonity.common.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.SimpleParticleType;

public class GhostyParticle extends SpriteBillboardParticle {
    private float rotSpeed;
    private final float particleRandom;
    private final float spinAcceleration;

    GhostyParticle(ClientWorld pLevel, double pX, double pY, double pZ, double velX, double velY, double velZ, SpriteProvider pSpriteSet) {
        super(pLevel, pX, pY, pZ);
        this.setSprite(pSpriteSet.getSprite(this.random.nextInt(12), 12));
        this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.maxAge = 300;
        this.gravityStrength = 7.5E-4F;
        float $$5 = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.scale = $$5;
        this.setBoundingBoxSpacing($$5, $$5);
        this.velocityMultiplier = 1.0F;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
        }

        if (!this.dead) {
            float $$0 = (float)(300 - this.maxAge);
            float $$1 = Math.min($$0 / 300.0F, 1.0F);
            double $$2 = Math.cos(Math.toRadians((double)(this.particleRandom * 60.0F))) * 2.0 * Math.pow((double)$$1, 1.25);
            double $$3 = Math.sin(Math.toRadians((double)(this.particleRandom * 60.0F))) * 2.0 * Math.pow((double)$$1, 1.25);
            this.velocityX += $$2 * 0.0024999999441206455;
            this.velocityY += $$3 * 0.0024999999441206455;
            this.velocityZ -= (double)this.gravityStrength;
            this.rotSpeed += this.spinAcceleration / 20.0F;
            this.prevAngle = this.angle;
            this.angle += this.rotSpeed / 20.0F;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.onGround || this.maxAge < 299 && (this.velocityX == 0.0 || this.velocityZ == 0.0)) {
                this.markDead();
            }

            if (!this.dead) {
                this.velocityX *= (double)this.velocityMultiplier;
                this.velocityY *= (double)this.velocityMultiplier;
                this.velocityZ *= (double)this.velocityMultiplier;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider sprites) implements ParticleFactory<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new GhostyParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        }
    }
}
