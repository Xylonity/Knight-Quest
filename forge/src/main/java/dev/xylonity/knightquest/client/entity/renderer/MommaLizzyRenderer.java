package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.*;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class MommaLizzyRenderer extends GeoEntityRenderer<MommaLizzyEntity> {

    public MommaLizzyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MommaLizzyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/momma_lizzy.png");
    }

    @Override
    public void render(MommaLizzyEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
