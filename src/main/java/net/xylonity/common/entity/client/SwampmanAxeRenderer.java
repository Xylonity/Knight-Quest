package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.SwampmanAxeEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class SwampmanAxeRenderer extends GeoEntityRenderer<SwampmanAxeEntity> {

    public SwampmanAxeRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SwampmanAxeModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(SwampmanAxeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public void render(SwampmanAxeEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.push();

        poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(partialTick, entity.prevYaw, entity.getYaw()) + 90.0F));
        poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(partialTick, entity.prevPitch, entity.getPitch())));

        float f9 = (float)entity.shake - partialTick;
        if (f9 > 0.0F) {
            float f10 = -MathHelper.sin(f9 * 3.0F) * f9;
            poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(f10));
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

        poseStack.pop();
    }
}

