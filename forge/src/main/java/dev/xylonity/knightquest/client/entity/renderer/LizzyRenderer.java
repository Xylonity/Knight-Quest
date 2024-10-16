package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.LizzyModel;
import dev.xylonity.knightquest.common.entity.entities.LizzyEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LizzyRenderer extends GeoEntityRenderer<LizzyEntity> {

    public LizzyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LizzyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(LizzyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/lizzy.png");
    }

    @Override
    public void render(LizzyEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
