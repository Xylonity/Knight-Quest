package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.BadPatchModel;
import dev.xylonity.knightquest.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BadPatchRenderer extends GeoEntityRenderer<BadPatchEntity> {

    public BadPatchRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BadPatchModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BadPatchEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/bad_patch.png");
    }

    @Override
    public void render(BadPatchEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(0.85f, 0.85f, 0.85f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
