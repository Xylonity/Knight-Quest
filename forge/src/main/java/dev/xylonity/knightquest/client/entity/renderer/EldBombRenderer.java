package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.EldBombModel;
import dev.xylonity.knightquest.common.entity.entities.EldBombEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class EldBombRenderer extends GeoEntityRenderer<EldBombEntity> {
    private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    private static final ResourceLocation WHITE_TEXTURE = ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/eldbomb_white.png");

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
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
