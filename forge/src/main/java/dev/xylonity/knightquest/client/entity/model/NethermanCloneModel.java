package dev.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NethermanCloneModel extends GeoModel<NethermanCloneEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanCloneEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/netherman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanCloneEntity animatable) {

        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/netherman_clone.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanCloneEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/netherman.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanCloneEntity animatable, long instanceId, AnimationState<NethermanCloneEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
