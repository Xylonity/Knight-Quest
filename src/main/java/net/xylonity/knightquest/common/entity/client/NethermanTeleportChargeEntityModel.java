package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NethermanTeleportChargeEntityModel extends AnimatedGeoModel<NethermanTeleportChargeEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanTeleportChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/netherman_teleport_charge.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanTeleportChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman_teleport_charge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanTeleportChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman_teleport_charge.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanTeleportChargeEntity animatable, int instanceId) {
        IBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            float rotationAngle = animatable.getRotationAngle(); // Get rotation angle from entity
            head.setPositionX(rotationAngle * Mth.DEG_TO_RAD);
            head.setRotationY(0);
            head.setRotationZ(rotationAngle * Mth.DEG_TO_RAD); // Convert to radians
        }
    }

}
