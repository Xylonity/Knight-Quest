package net.xylonity.common.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class PoisonParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    protected PoisonParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.velocityMultiplier = 1.0F;
        this.spriteProvider = spriteProvider;
        this.velocityX = velocityX + (Math.random() * 2.0 - 1.0) * 0.05F;
        this.velocityY = velocityY + (Math.random() * 2.0 - 1.0) * 0.05F;
        this.velocityZ = velocityZ + (Math.random() * 2.0 - 1.0) * 0.05F;

        float minSize = 1.5F;
        float maxSize = 3F;
        this.scale = minSize + this.random.nextFloat() * (maxSize - minSize);
        this.maxAge = (int)(10.0D / ((double)this.random.nextFloat() * 0.5D + 0.2D)) + 2;

        this.setSpriteForAge(spriteProvider);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
        this.velocityX *= 0.95F;
        this.velocityY *= 0.9F;
        this.velocityZ *= 0.95F;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            PoisonParticle snowflakeParticle = new PoisonParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
            snowflakeParticle.setColor(0.923F, 0.964F, 0.999F);
            return snowflakeParticle;
        }
    }
}