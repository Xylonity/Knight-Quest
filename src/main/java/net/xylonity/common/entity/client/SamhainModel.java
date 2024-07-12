package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.SamhainEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SamhainModel extends GeoModel<SamhainEntity> {

    @Override
    public Identifier getModelResource(SamhainEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "geo/samhain.geo.json");
    }

    @Override
    public Identifier getTextureResource(SamhainEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public Identifier getAnimationResource(SamhainEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "animations/samhain.animation.json");
    }

    @Override
    public void setCustomAnimations(SamhainEntity animatable, long instanceId, AnimationState<SamhainEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}