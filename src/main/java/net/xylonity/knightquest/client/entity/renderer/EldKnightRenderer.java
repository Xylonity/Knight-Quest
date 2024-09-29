package net.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.client.entity.model.EldKnightModel;
import net.xylonity.knightquest.common.entity.entities.EldKnightEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EldKnightRenderer extends GeoEntityRenderer<EldKnightEntity> {

    public EldKnightRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EldKnightModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldknight.png");
    }

    @Override
    public void render(EldKnightEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

