package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.boss.NethermanCloneEntity;
import net.xylonity.common.entity.boss.NethermanEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethermanCloneRenderer extends GeoEntityRenderer<NethermanCloneEntity> {

    public NethermanCloneRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new NethermanCloneModel());
    }

    @Override
    public Identifier getTextureLocation(NethermanCloneEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/netherman_clone.png");
    }

    @Override
    public void render(NethermanCloneEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

