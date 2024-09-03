package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.GhastlingEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShieldRenderer extends GeoEntityRenderer<GhastlingEntity> {

    public ShieldRenderer(EntityRendererFactory.Context  renderManager) {
        super(renderManager, new ShieldModel());
    }

    @Override
    public Identifier getTextureLocation(GhastlingEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public void render(GhastlingEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(1.15f, 1.15f, 1.15f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

