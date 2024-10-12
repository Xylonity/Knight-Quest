package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.GhostyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GhostyModel extends GeoModel<GhostyEntity> {

    @Override
    public ResourceLocation getModelResource(GhostyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/ghosty.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhostyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ghosty.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GhostyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/ghosty.animation.json");
    }

    @Override
    public void setCustomAnimations(GhostyEntity animatable, long instanceId, AnimationState<GhostyEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
