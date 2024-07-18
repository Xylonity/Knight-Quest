package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.custom.EldBombEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EldBombModel extends GeoModel<EldBombEntity> {

    @Override
    public ResourceLocation getModelResource(EldBombEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/eldbomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EldBombEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EldBombEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/eldbomb.animation.json");
    }

    @Override
    public void setCustomAnimations(EldBombEntity animatable, long instanceId, AnimationState<EldBombEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("Body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
