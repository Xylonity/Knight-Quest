package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BadPatchRenderer extends GeoEntityRenderer<BadPatchEntity> {

    public BadPatchRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BadPatchModel());
    }

    @Override
    public Identifier getTextureLocation(BadPatchEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "textures/entity/bad_patch.png");
    }

    @Override
    public void render(BadPatchEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(0.85f, 0.85f, 0.85f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

