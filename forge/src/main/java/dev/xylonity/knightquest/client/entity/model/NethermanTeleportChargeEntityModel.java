package dev.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class NethermanTeleportChargeEntityModel extends GeoModel<NethermanTeleportChargeEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanTeleportChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/netherman_teleport_charge.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanTeleportChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/netherman_teleport_charge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanTeleportChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/netherman_teleport_charge.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanTeleportChargeEntity animatable, long instanceId, AnimationState<NethermanTeleportChargeEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            float rotationAngle = animatable.getRotationAngle(); // Get rotation angle from entity
            head.setRotX(rotationAngle * Mth.DEG_TO_RAD);
            head.setRotY(0);
            head.setRotZ(rotationAngle * Mth.DEG_TO_RAD); // Convert to radians
        }
    }

}
