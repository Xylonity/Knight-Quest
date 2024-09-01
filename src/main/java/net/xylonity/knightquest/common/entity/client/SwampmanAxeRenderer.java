package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SwampmanAxeEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SwampmanAxeRenderer extends GeoEntityRenderer<SwampmanAxeEntity> {

    public SwampmanAxeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SwampmanAxeModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SwampmanAxeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    public void vertex(Matrix4f pMatrix, Matrix3f pNormal, VertexConsumer pConsumer, int pX, int pY, int pZ, float pU, float pV, int pNormalX, int pNormalZ, int pNormalY, int pPackedLight) {
        pConsumer.vertex(pMatrix, (float)pX, (float)pY, (float)pZ).color(255, 255, 255, 255).uv(pU, pV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(pNormal, (float)pNormalX, (float)pNormalY, (float)pNormalZ).endVertex();
    }

    @Override
    public void render(SwampmanAxeEntity entity, float entityYaw, float partialTick, PoseStack pPoseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {

        pPoseStack.pushPose();

        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, entity.yRotO, entity.getYRot()) + 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.getXRot())));

        float f9 = (float)entity.shakeTime - partialTick;
        if (f9 > 0.0F) {
            float f10 = -Mth.sin(f9 * 3.0F) * f9;
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(f10));
        }

        super.render(entity, entityYaw, partialTick, pPoseStack, bufferSource, packedLight);

        pPoseStack.popPose();
    }
}
