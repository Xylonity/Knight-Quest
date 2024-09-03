package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.SwampmanEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class SwampmanRenderer extends GeoEntityRenderer<SwampmanEntity> {

    public SwampmanRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SwampmanModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(SwampmanEntity animatable) {
        if (animatable.getPhase() == 2) {
            return Identifier.of(KnightQuest.MOD_ID, "textures/entity/swampman_2.png");
        } else {
            return Identifier.of(KnightQuest.MOD_ID, "textures/entity/swampman.png");
        }
    }

    @Override
    public void render(SwampmanEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

