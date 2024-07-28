package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.LaserBeamEntity;
import net.xylonity.knightquest.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LaserBeamRenderer extends GeoEntityRenderer<LaserBeamEntity> {

    public LaserBeamRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LaserBeamModel());
    }

    @Override
    public ResourceLocation getTextureLocation(LaserBeamEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/laser_beam.png");
    }

    @Override
    public void render(LaserBeamEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(0.85f, 0.85f, 0.85f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
