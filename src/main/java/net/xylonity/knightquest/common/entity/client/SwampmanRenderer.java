package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SwampmanEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SwampmanRenderer extends GeoEntityRenderer<SwampmanEntity> {

    public SwampmanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SwampmanModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SwampmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public void render(SwampmanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

