package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.RatmanEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RatmanModel extends GeoModel<RatmanEntity> {

    @Override
    public ResourceLocation getModelResource(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/ratman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ratman" + animatable.getVariation() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/ratman.animation.json");
    }

    @Override
    public void setCustomAnimations(RatmanEntity animatable, long instanceId, AnimationState<RatmanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
