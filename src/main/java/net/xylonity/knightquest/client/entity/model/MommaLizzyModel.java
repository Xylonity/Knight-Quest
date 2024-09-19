package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.MommaLizzyEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MommaLizzyModel extends GeoModel<MommaLizzyEntity> {

    @Override
    public ResourceLocation getModelResource(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/momma_lizzy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/momma_lizzy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/momma_lizzy.animation.json");
    }

    @Override
    public void setCustomAnimations(MommaLizzyEntity animatable, long instanceId, AnimationState<MommaLizzyEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
