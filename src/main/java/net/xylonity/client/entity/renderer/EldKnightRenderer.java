package net.xylonity.client.entity.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.client.entity.model.EldKnightModel;
import net.xylonity.common.entity.entities.EldKnightEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class EldKnightRenderer extends GeoEntityRenderer<EldKnightEntity> {

    public EldKnightRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new EldKnightModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(EldKnightEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/eldknight.png");
    }

    @Override
    public void render(EldKnightEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

