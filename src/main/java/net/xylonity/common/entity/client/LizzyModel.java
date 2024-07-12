package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.LizzyEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class LizzyModel extends GeoModel<LizzyEntity> {

    @Override
    public Identifier getModelResource(LizzyEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "geo/lizzy.geo.json");
    }

    @Override
    public Identifier getTextureResource(LizzyEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "textures/entity/lizzy.png");
    }

    @Override
    public Identifier getAnimationResource(LizzyEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "animations/lizzy.animation.json");
    }

    @Override
    public void setCustomAnimations(LizzyEntity animatable, long instanceId, AnimationState<LizzyEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
