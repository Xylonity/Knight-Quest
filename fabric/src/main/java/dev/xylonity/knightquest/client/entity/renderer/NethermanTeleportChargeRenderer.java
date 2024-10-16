package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.NethermanTeleportChargeEntityModel;
import dev.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethermanTeleportChargeRenderer extends GeoEntityRenderer<NethermanTeleportChargeEntity> {

    public NethermanTeleportChargeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NethermanTeleportChargeEntityModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NethermanTeleportChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/netherman_teleport_charge.png");
    }

    @Override
    public void render(NethermanTeleportChargeEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(0.85f, 0.85f, 0.85f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
