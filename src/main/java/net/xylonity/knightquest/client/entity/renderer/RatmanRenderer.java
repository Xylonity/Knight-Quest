package net.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.client.entity.model.RatmanModel;
import net.xylonity.knightquest.common.entity.entities.RatmanEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RatmanRenderer extends GeoEntityRenderer<RatmanEntity> {

    public RatmanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RatmanModel());
    }

    @Override
    public ResourceLocation getTextureLocation(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ratman" + animatable.getVariation() + ".png");
    }

    @Override
    public void render(RatmanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

