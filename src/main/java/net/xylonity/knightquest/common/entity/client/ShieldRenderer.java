package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.custom.ShieldEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ShieldRenderer extends GeoEntityRenderer<ShieldEntity> {

    boolean sPhase = false;

    public ShieldRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ShieldModel());
    }

    @Override
    public ResourceLocation getTextureLocation(ShieldEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public void render(ShieldEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.15f, 1.15f, 1.15f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

