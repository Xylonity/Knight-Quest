package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.LaserBeamEntity;
import net.xylonity.knightquest.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LaserBeamModel extends GeoModel<LaserBeamEntity> {

    @Override
    public ResourceLocation getModelResource(LaserBeamEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/laser_beam.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LaserBeamEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/laser_beam.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LaserBeamEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/bad_patch.animation.json");
    }

    @Override
    public void setCustomAnimations(LaserBeamEntity animatable, long instanceId, AnimationState<LaserBeamEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
