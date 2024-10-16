package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.BadPatchEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BadPatchModel extends GeoModel<BadPatchEntity> {

    @Override
    public ResourceLocation getModelResource(BadPatchEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/bad_patch.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BadPatchEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/bad_patch.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BadPatchEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/bad_patch.animation.json");
    }

    @Override
    public void setCustomAnimations(BadPatchEntity animatable, long instanceId, AnimationState<BadPatchEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
