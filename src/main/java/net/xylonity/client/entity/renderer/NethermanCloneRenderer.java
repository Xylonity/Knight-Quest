package net.xylonity.client.entity.renderer;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.client.entity.model.NethermanCloneModel;
import net.xylonity.common.entity.boss.NethermanCloneEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethermanCloneRenderer extends GeoEntityRenderer<NethermanCloneEntity> {

    public NethermanCloneRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new NethermanCloneModel());
    }

    @Override
    protected float getDeathMaxRotation(NethermanCloneEntity animatable) {
        return 0;
    }

    @Override
    public Identifier getTextureLocation(NethermanCloneEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/netherman_clone.png");
    }

    @Override
    public @Nullable RenderLayer getRenderType(NethermanCloneEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(NethermanCloneEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

