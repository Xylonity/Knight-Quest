package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SwampmanModel extends GeoModel<SwampmanEntity> {

    @Override
    public ResourceLocation getModelResource(SwampmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/swampman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwampmanEntity animatable) {
        if (animatable.getPhase() == 2) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman_2.png");
        } else {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(SwampmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }

    @Override
    public void setCustomAnimations(SwampmanEntity animatable, long instanceId, AnimationState<SwampmanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}