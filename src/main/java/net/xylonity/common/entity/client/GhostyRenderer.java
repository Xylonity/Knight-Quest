package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.GhostyEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GhostyRenderer extends GeoEntityRenderer<GhostyEntity> {

    public GhostyRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GhostyModel());
    }

    @Override
    public Identifier getTextureLocation(GhostyEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/ghosty.png");
    }

    @Override
    public void render(GhostyEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(1.1f, 1.1f, 1.1f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
