package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.RatmanModel;
import dev.xylonity.knightquest.common.entity.entities.RatmanEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class RatmanRenderer extends GeoEntityRenderer<RatmanEntity> {

    public RatmanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RatmanModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RatmanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/ratman" + animatable.getVariation() + ".png");
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

