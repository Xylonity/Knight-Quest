package net.xylonity.common.particle.explosiveenhancement.blue;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.xylonity.common.particle.explosiveenhancement.BlastWaveParticle;
import net.xylonity.common.particle.explosiveenhancement.red.RedBlastWaveParticle;

public class BlueBlastWaveParticle extends BlastWaveParticle {

    BlueBlastWaveParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider sprites) {
        super(world, x, y + 0.5, z, velX, 0.0, 0, sprites);
        setColor(65, 63, 200);
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider sprites) implements ParticleFactory<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new BlueBlastWaveParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        }
    }

}
