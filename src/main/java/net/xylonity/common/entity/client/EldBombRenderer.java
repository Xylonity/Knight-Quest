package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.EldBombEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class EldBombRenderer extends GeoEntityRenderer<EldBombEntity> {
    private static final Identifier DEFAULT_TEXTURE = Identifier.of(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    private static final Identifier WHITE_TEXTURE = Identifier.of(KnightQuest.MOD_ID, "textures/entity/eldbomb_white.png");

    public EldBombRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new EldBombModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(EldBombEntity animatable) {
        if (animatable.getSwell() > 10) {
            if ((animatable.age / 5) % 2 == 0) {
                return WHITE_TEXTURE;
            } else {
                return DEFAULT_TEXTURE;
            }
        }

        return DEFAULT_TEXTURE;
    }

    @Override
    public void render(EldBombEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        float f = entity.getClientFuseTime(partialTick);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        poseStack.scale(f2, f3, f2);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
