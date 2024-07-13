package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.RatmanEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class RatmanRenderer extends GeoEntityRenderer<RatmanEntity> {

    public RatmanRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new RatmanModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(RatmanEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/ratman.png");
    }

    @Override
    public void render(RatmanEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

