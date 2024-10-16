package dev.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.GhastlingEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ShieldModel extends GeoModel<GhastlingEntity> {

    @Override
    public ResourceLocation getModelResource(GhastlingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhastlingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GhastlingEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

    @Override
    public void setCustomAnimations(GhastlingEntity animatable, long instanceId, AnimationState<GhastlingEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
