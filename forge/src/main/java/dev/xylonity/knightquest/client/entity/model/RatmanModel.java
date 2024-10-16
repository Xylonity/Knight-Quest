package dev.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.RatmanEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RatmanModel extends GeoModel<RatmanEntity> {

    @Override
    public ResourceLocation getModelResource(RatmanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/ratman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RatmanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/ratman" + animatable.getVariation() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RatmanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/ratman.animation.json");
    }

    @Override
    public void setCustomAnimations(RatmanEntity animatable, long instanceId, AnimationState<RatmanEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
