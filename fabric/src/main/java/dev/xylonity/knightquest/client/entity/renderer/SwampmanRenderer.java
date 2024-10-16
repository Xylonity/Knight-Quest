package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.SwampmanModel;
import dev.xylonity.knightquest.common.entity.entities.SwampmanEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class SwampmanRenderer extends GeoEntityRenderer<SwampmanEntity> {

    public SwampmanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SwampmanModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SwampmanEntity animatable) {
        if (animatable.getPhase() == 2) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman_2.png");
        } else {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
        }
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

