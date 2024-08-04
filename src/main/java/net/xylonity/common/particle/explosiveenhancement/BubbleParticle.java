package net.xylonity.common.particle.explosiveenhancement;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BubbleParticle extends TextureSheetParticle {

    private final SpriteSet sprites;
    int startingAirTick = 0;
    int extraTimeBeforePopping = this.random.nextIntBetweenInclusive(1, 10);
    boolean startAirTick = true;

    BubbleParticle(ClientLevel clientWorld, double x, double y, double z, SpriteSet spriteProvider, double velX, double velY, double velZ) {
        super(clientWorld, x, y, z);
        this.sprites = spriteProvider;
        this.setSize(0.02F, 0.02F);
        this.quadSize *= this.random.nextFloat() * 1.5F + 0.2F;
        double theta = this.random.nextDouble() * 2 * Math.PI;
        double phi = this.random.nextDouble() * Math.PI;
        this.xd = Math.sin(phi) * Math.cos(theta) * (this.random.nextDouble() * 0.5 + 0.5);
        this.yd = Math.abs(this.random.nextDouble() * 0.5 + 0.5);
        this.zd = Math.sin(phi) * Math.sin(theta) * (this.random.nextDouble() * 0.5 + 0.5);
        this.lifetime = 120 + this.random.nextIntBetweenInclusive(0, 40);
        this.setSpriteFromAge(spriteProvider);
        this.age = this.lifetime;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
            this.level.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, this.xd, this.yd, this.zd);
        } else {
            this.yd += 0.002;
            this.move(this.xd, this.yd, this.zd);
            this.yd *= 0.8200000238418579;
            if(this.lifetime >= this.age * 0.97) {
                this.xd *= 0.8300000238418579;
                this.zd *= 0.8300000238418579;
            } else {
                this.xd *= 0.6200000238418579;
                this.zd *= 0.6200000238418579;
            }
            if (!this.level.getFluidState(new BlockPos((int) this.x, (int) this.y, (int) this.z)).is(FluidTags.WATER)) {
                this.yd -= 0.002;
                if(startAirTick) {
                    startingAirTick = this.lifetime;
                    this.yd = 0;
                    startAirTick = false;
                }
                if(!startAirTick) {
                    if(this.lifetime == startingAirTick - extraTimeBeforePopping) {
                        this.remove();
                        this.level.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, this.xd, this.yd, this.zd);
                        this.level.playSound(null, this.x, this.y, this.z, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.AMBIENT, 0.5f, 1f);
                    }
                }
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
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
            return new BubbleParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
