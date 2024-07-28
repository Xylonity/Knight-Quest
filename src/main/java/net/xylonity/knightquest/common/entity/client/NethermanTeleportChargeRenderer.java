package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import net.xylonity.knightquest.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethermanTeleportChargeRenderer extends GeoEntityRenderer<NethermanTeleportChargeEntity> {

    public NethermanTeleportChargeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NethermanTeleportChargeEntityModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NethermanTeleportChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman_teleport_charge.png");
    }

    @Override
    public void render(NethermanTeleportChargeEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.85f, 0.85f, 0.85f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
