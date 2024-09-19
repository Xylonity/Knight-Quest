package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldKnightEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EldKnightModel extends GeoModel<EldKnightEntity> {

    @Override
    public ResourceLocation getModelResource(EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/eldknight.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldknight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/eldknight.animation.json");
    }

    @Override
    public void setCustomAnimations(EldKnightEntity animatable, long instanceId, AnimationState<EldKnightEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}