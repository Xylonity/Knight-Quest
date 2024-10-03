package net.xylonity.client.entity.model;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.boss.NethermanTeleportChargeEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;

public class NethermanTeleportChargeModel extends GeoModel<NethermanTeleportChargeEntity> {

    @Override
    public Identifier getModelResource(NethermanTeleportChargeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/netherman_teleport_charge.geo.json");
    }

    @Override
    public Identifier getTextureResource(NethermanTeleportChargeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/netherman_teleport_charge.png");
    }

    @Override
    public Identifier getAnimationResource(NethermanTeleportChargeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/netherman_teleport_charge.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanTeleportChargeEntity animatable, long instanceId, AnimationState<NethermanTeleportChargeEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            float rotationAngle = animatable.getRotationAngle();
            head.setRotX(rotationAngle * MathHelper.DEGREES_PER_RADIAN);
            head.setRotY(0);
            head.setRotZ(rotationAngle * MathHelper.DEGREES_PER_RADIAN);
        }
    }

}
