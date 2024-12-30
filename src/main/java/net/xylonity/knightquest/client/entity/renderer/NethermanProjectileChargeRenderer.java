package net.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.client.entity.model.NethermanProjectileChargeEntityModel;
import net.xylonity.knightquest.common.entity.boss.NethermanProjectileChargeEntity;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class NethermanProjectileChargeRenderer extends GeoProjectilesRenderer<NethermanProjectileChargeEntity> {

    public NethermanProjectileChargeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NethermanProjectileChargeEntityModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NethermanProjectileChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman_projectile_charge.png");
    }

    @Override
    public void render(NethermanProjectileChargeEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(2f, 2f, 2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}
