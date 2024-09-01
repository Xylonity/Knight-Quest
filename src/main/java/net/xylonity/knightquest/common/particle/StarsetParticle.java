package net.xylonity.knightquest.common.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.xylonity.knightquest.common.block.ChaliceBlock;
import net.xylonity.knightquest.registry.KnightQuestBlocks;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class StarsetParticle extends TextureSheetParticle {
    private final SpriteSet spritesset;
    private static Quaternionf QUATERNION = new Quaternionf(0F, -0.7F, 0.7F, 0F);

    StarsetParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites, double velX, double velY, double velZ) {
        super(world, x, y + 0.5, z, 0.0, 0.0, 0.0);

        BlockPos blockPos = BlockPos.containing(x, y + 1, z);

        if (world.getBlockState(blockPos).getBlock() == KnightQuestBlocks.GREAT_CHALICE.get()) {
            int fillValue = world.getBlockState(blockPos).getValue(ChaliceBlock.fill);

            this.quadSize = switch (fillValue) {
                case 1 -> 1f;
                case 2 -> 1.2f;
                case 3 -> 1.4f;
                case 4 -> 1.8f;
                default -> 0f;
            };

        } else {
            this.quadSize = (float) velX;
        }

        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;
        this.lifetime = (int) (11 + (Math.floor(velX / 5)));
        this.setSpriteFromAge(sprites);
        this.spritesset = sprites;
        this.setParticleSpeed(0,0,0);
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return 0xF000F0;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        Vec3 vec3 = pRenderInfo.getPosition();
        float x = (float)(Mth.lerp(pPartialTicks, this.xo, this.x) - vec3.x());
        float y = (float)(Mth.lerp(pPartialTicks, this.yo, this.y) - vec3.y());
        float z = (float)(Mth.lerp(pPartialTicks, this.zo, this.z) - vec3.z());

        Vector3f[] vector3fs = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
        Vector3f[] vector3fsBottom = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, -1.0F, 0.0F)};

        float f4 = this.getQuadSize(pPartialTicks);

        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f = vector3fs[i];
            vector3f.rotate(QUATERNION);
            vector3f.mul(f4);
            vector3f.add(x, y, z);

            Vector3f vector3fBottom = vector3fsBottom[i];
            vector3fBottom.rotate(QUATERNION);
            vector3fBottom.mul(f4);
            vector3fBottom.add(x, y - 0.1F, z);
        }

        float f7 = this.getU0();
        float f8 = this.getU1();
        float f5 = this.getV0();
        float f6 = this.getV1();
        int light = this.getLightColor(pPartialTicks);

        pBuffer.addVertex(vector3fs[0].x(), vector3fs[0].y(), vector3fs[0].z()).setUv(f8, f6).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        pBuffer.addVertex(vector3fs[1].x(), vector3fs[1].y(), vector3fs[1].z()).setUv(f8, f5).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        pBuffer.addVertex(vector3fs[2].x(), vector3fs[2].y(), vector3fs[2].z()).setUv(f7, f5).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        pBuffer.addVertex(vector3fs[3].x(), vector3fs[3].y(), vector3fs[3].z()).setUv(f7, f6).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);

        pBuffer.addVertex(vector3fs[3].x(), vector3fs[3].y(), vector3fs[3].z()).setUv(f7, f6).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        pBuffer.addVertex(vector3fs[2].x(), vector3fs[2].y(), vector3fs[2].z()).setUv(f7, f5).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        pBuffer.addVertex(vector3fs[1].x(), vector3fs[1].y(), vector3fs[1].z()).setUv(f8, f5).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
        pBuffer.addVertex(vector3fs[0].x(), vector3fs[0].y(), vector3fs[0].z()).setUv(f8, f6).setColor(this.rCol, this.gCol, this.bCol, this.alpha).setLight(light);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(spritesset);
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
            return new StarsetParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }

}
