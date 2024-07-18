package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.custom.SamhainEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SamhainModel extends GeoModel<SamhainEntity> {

    @Override
    public ResourceLocation getModelResource(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/samhain_squire.geo.json");
        }
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/samhain.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/samhain_squire.png");
        }
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SamhainEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/samhain.animation.json");
    }

    @Override
    public void setCustomAnimations(SamhainEntity animatable, long instanceId, AnimationState<SamhainEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}