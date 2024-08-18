package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SamhainRenderer extends GeoEntityRenderer<SamhainEntity> {

    public SamhainRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SamhainModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain_squire.png");
        }
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public void render(SamhainEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}

