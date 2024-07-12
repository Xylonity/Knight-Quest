package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.SwampmanEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SwampmanModel extends GeoModel<SwampmanEntity> {

    @Override
    public Identifier getModelResource(SwampmanEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "geo/swampman.geo.json");
    }

    @Override
    public Identifier getTextureResource(SwampmanEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public Identifier getAnimationResource(SwampmanEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }

    @Override
    public void setCustomAnimations(SwampmanEntity animatable, long instanceId, AnimationState<SwampmanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}