package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.ShieldEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShieldRenderer extends GeoEntityRenderer<ShieldEntity> {

    public ShieldRenderer(EntityRendererFactory.Context  renderManager) {
        super(renderManager, new ShieldModel());
    }

    @Override
    public Identifier getTextureLocation(ShieldEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public void render(ShieldEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(1.15f, 1.15f, 1.15f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

