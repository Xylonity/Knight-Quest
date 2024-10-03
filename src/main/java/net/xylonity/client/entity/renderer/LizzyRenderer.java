package net.xylonity.client.entity.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.client.entity.model.LizzyModel;
import net.xylonity.common.entity.entities.LizzyEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LizzyRenderer extends GeoEntityRenderer<LizzyEntity> {

    public LizzyRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new LizzyModel());
    }

    @Override
    public Identifier getTextureLocation(LizzyEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/lizzy.png");
    }

    @Override
    public void render(LizzyEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
