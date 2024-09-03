package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.GremlinEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GremlinRenderer extends GeoEntityRenderer<GremlinEntity> {

    public GremlinRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GremlinModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(GremlinEntity animatable) {
        if (animatable.getPhase() == 2) {
            return Identifier.of(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return Identifier.of(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public void render(GremlinEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

