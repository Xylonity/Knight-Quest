package dev.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.GhostyEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GhostyModel extends GeoModel<GhostyEntity> {

    @Override
    public ResourceLocation getModelResource(GhostyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/ghosty.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhostyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/ghosty.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GhostyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

    @Override
    public void setCustomAnimations(GhostyEntity animatable, long instanceId, AnimationState<GhostyEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
