package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldBombEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class EldBombRenderer extends GeoEntityRenderer<EldBombEntity> {

    public EldBombRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EldBombModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EldBombEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    }

    @Override
    public void render(EldBombEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {

        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
