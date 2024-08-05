package net.xylonity.common.particle.explosiveenhancement.blue;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.xylonity.common.particle.explosiveenhancement.FireballParticle;
import net.xylonity.common.particle.explosiveenhancement.red.RedFireballParticle;

public class BlueFireballParticle extends FireballParticle {

    boolean important;

    BlueFireballParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velX, velY, velZ, spriteProvider);
        setColor(65, 63, 200);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new BlueFireballParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }

}
