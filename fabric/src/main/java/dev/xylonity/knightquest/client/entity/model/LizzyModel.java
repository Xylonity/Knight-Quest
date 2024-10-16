package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.LizzyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LizzyModel extends GeoModel<LizzyEntity> {

    @Override
    public ResourceLocation getModelResource(LizzyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/lizzy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LizzyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/lizzy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LizzyEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/lizzy.animation.json");
    }

    @Override
    public void setCustomAnimations(LizzyEntity animatable, long instanceId, AnimationState<LizzyEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
