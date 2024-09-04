package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldBombEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class EldBombRenderer extends GeoEntityRenderer<EldBombEntity> {
    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    private static final ResourceLocation WHITE_TEXTURE = new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb_white.png");

    public EldBombRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EldBombModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EldBombEntity animatable) {
        if (animatable.getSwell() > 10) {
            if ((animatable.tickCount / 5) % 2 == 0) {
                return WHITE_TEXTURE;
            } else {
                return DEFAULT_TEXTURE;
            }
        }

        return DEFAULT_TEXTURE;
    }

    @Override
    public void render(EldBombEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {

        float f = entity.getSwelling(partialTick);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        poseStack.scale(f2, f3, f2);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
