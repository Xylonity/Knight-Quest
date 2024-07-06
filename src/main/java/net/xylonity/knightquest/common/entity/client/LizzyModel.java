package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.LizzyEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LizzyModel extends GeoModel<LizzyEntity> {

    @Override
    public ResourceLocation getModelResource(LizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/lizzy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/lizzy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/lizzy.animation.json");
    }

    @Override
    public void setCustomAnimations(LizzyEntity animatable, long instanceId, AnimationState<LizzyEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
