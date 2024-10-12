package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.EldBombModel;
import dev.xylonity.knightquest.common.entity.entities.EldBombEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class EldBombRenderer extends GeoEntityRenderer<EldBombEntity> {
    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    private static final ResourceLocation WHITE_TEXTURE = new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb_white.png");

    public EldBombRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EldBombModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(EldBombEntity animatable) {
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
    public void render(EldBombEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
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
