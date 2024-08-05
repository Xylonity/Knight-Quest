package net.xylonity.common.particle.explosiveenhancement;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.xylonity.common.api.explosiveenhancement.ExplosiveValues;

public class SparkParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    SparkParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
//        var config = ExplosiveEnhancementClient.getConfig();
        this.spriteProvider = spriteProvider;
        this.maxAge = (int) (5 + Math.floor(velX / 5));
        if(velX == 0) {
            this.scale = (float) ExplosiveValues.sparkSize;
        } else {
            this.scale = (float) (ExplosiveValues.sparkSize * (velX * 0.25f));
        }
        this.setVelocity(0D, 0D, 0D);
        this.alpha = (float) ExplosiveValues.sparkOpacity;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.velocityY -= (double)this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.setSpriteForAge(this.spriteProvider);
        }
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getBrightness(float tint) {
        return super.getBrightness(tint);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new SparkParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}