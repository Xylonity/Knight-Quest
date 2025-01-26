package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.GremlinModel;
import dev.xylonity.knightquest.common.entity.entities.GremlinEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GremlinRenderer extends GeoEntityRenderer<GremlinEntity> {

    public GremlinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GremlinModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(GremlinEntity animatable) {
        if (animatable.getPhase() == 2) {
            return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public void render(GremlinEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1.1f, 1.1f, 1.1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

