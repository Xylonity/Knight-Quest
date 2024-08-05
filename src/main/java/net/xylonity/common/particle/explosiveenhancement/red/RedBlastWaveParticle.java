package net.xylonity.common.particle.explosiveenhancement.red;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.xylonity.common.particle.explosiveenhancement.BlastWaveParticle;

public class RedBlastWaveParticle extends BlastWaveParticle {
    private final SpriteProvider sprites;

    public RedBlastWaveParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider sprites) {
        super(world, x, y + 0.5, z, velX, 0.0, 0, sprites);
        this.sprites = sprites;
        this.setSpriteForAge(sprites);
        setColor(65, 63, 200);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.sprites);
    }

    public record Factory(SpriteProvider sprites) implements ParticleFactory<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new RedBlastWaveParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
        }
    }

}
