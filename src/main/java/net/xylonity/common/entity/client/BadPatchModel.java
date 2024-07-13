package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BadPatchModel extends GeoModel<BadPatchEntity> {

    @Override
    public Identifier getModelResource(BadPatchEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/bad_patch.geo.json");
    }

    @Override
    public Identifier getTextureResource(BadPatchEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/bad_patch.png");
    }

    @Override
    public Identifier getAnimationResource(BadPatchEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/bad_patch.animation.json");
    }

    @Override
    public void setCustomAnimations(BadPatchEntity animatable, long instanceId, AnimationState<BadPatchEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
