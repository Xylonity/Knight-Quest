package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.GremlinEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GremlinRenderer extends GeoEntityRenderer<GremlinEntity> {

    public GremlinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GremlinModel());
    }

    @Override
    public ResourceLocation getTextureLocation(GremlinEntity animatable) {
        if (animatable.getHealth() < animatable.getMaxHealth() * 0.5) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
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

