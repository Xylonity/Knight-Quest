package net.xylonity.knightquest.common.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class PoisonParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected PoisonParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ);
        this.friction = 1.0F;
        this.sprites = pSprites;
        this.xd = pXSpeed + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.yd = pYSpeed + (Math.random() * 2.0D - 1.0D) * (double)0.05F;
        this.zd = pZSpeed + (Math.random() * 2.0D - 1.0D) * (double)0.05F;

        float minSize = 1.5F;
        float maxSize = 3F;
        this.quadSize = minSize + this.random.nextFloat() * (maxSize - minSize);

        this.lifetime = (int)(10.0D / ((double)this.random.nextFloat() * 0.5D + 0.2D)) + 2;
        this.setSpriteFromAge(pSprites);
    }

    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.xd *= 0.95F;
        this.yd *= 0.9F;
        this.zd *= 0.95F;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            PoisonParticle snowflakeparticle = new PoisonParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprites);
            snowflakeparticle.setColor(0.923F, 0.964F, 0.999F);
            return snowflakeparticle;
        }
    }
}