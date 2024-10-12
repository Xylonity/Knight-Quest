package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.*;
import dev.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

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
