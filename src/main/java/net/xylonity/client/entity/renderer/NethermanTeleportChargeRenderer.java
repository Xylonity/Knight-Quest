package net.xylonity.client.entity.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.client.entity.model.NethermanTeleportChargeModel;
import net.xylonity.common.entity.boss.NethermanTeleportChargeEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethermanTeleportChargeRenderer extends GeoEntityRenderer<NethermanTeleportChargeEntity> {

    public NethermanTeleportChargeRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new NethermanTeleportChargeModel());
    }

    @Override
    public Identifier getTextureLocation(NethermanTeleportChargeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/netherman_teleport_charge.png");
    }

    @Override
    public void render(NethermanTeleportChargeEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(0.85f, 0.85f, 0.85f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

