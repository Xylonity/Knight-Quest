package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SamhainModel extends GeoModel<SamhainEntity> {

    @Override
    public ResourceLocation getModelResource(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return new ResourceLocation(KnightQuest.MOD_ID, "geo/samhain_squire.geo.json");
        }
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/samhain.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain_squire.png");
        }
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SamhainEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/samhain.animation.json");
    }

    @Override
    public void setCustomAnimations(SamhainEntity animatable, long instanceId, AnimationState<SamhainEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}