package dev.xylonity.knightquest.common.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.joml.Quaternionf;

public class YellowParticle extends TextureSheetParticle {
    private final SpriteSet spritesset;
    private static Quaternionf QUATERNION = new Quaternionf(0F, -0.7F, 0.7F, 0F);

    YellowParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(world, x, y + 0.5, z, 0.0, 0.0, 0.0);

        this.quadSize = 0.27f;
        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;
        this.lifetime = (int) (11 + (Math.floor(velX / 5)));
        this.setSpriteFromAge(sprites);
        this.spritesset = sprites;
        this.setParticleSpeed(0,0,0);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        super.render(pBuffer, pRenderInfo, pPartialTicks);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(spritesset);
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new YellowParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
