package net.xylonity.knightquest.common.particle.explosiveenhancement;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.api.explosiveenhancement.ExplosiveValues;

public class SmokeParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    public SmokeParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, double velX, double velY, double velZ) {
        super(world, x, y, z);
        this.friction = 0.6F;
        this.sprites = spriteProvider;
        this.lifetime = this.random.nextInt(35);
        if(velZ == 0) {
            quadSize = (float) velX * 0.25f;
            this.lifetime += (int) (velX * this.random.nextInt(3, 22));
            this.xd = 0;
            this.zd = 0;
        } else if(velX == 0.15 || velX == -0.15) {
            quadSize = (float) velZ * 0.25f;
            this.lifetime += (int) (velZ * this.random.nextInt(3, 22));
            this.xd = velX * (velZ * 0.5);
            this.zd = 0;
        } else if(velZ == 0.15 || velZ == -0.15) {
            quadSize = (float) velX * 0.25f;
            this.lifetime += (int) (velX * this.random.nextInt(3, 22));
            this.xd = 0;
            this.zd = velZ * (velX * 0.5);
        }
        this.yd = velY / 1.85;
        this.gravity = 3.0E-6F;
        this.hasPhysics = true;
        this.setSpriteFromAge(spriteProvider);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
            if (this.age == 12) {
                this.xd = 0;
                this.yd = 0.05;
                this.zd = 0;
            }
            this.move(this.xd, this.yd, this.zd);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float tint) {
        if(ExplosiveValues.emissiveExplosion && this.age <= this.lifetime * 0.12) {
            return 15728880;
        } else if (ExplosiveValues.emissiveExplosion && this.age <= this.lifetime * 0.17) {
            return Mth.clamp(super.getLightColor(tint) + this.age + 30, super.getLightColor(tint), 15728880);
        } else {
            return super.getLightColor(tint);
        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new SmokeParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
