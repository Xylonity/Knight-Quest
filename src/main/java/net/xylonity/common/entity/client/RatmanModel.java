package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.RatmanEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class RatmanModel extends GeoModel<RatmanEntity> {

    @Override
    public Identifier getModelResource(RatmanEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "geo/ratman.geo.json");
    }

    @Override
    public Identifier getTextureResource(RatmanEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "textures/entity/ratman.png");
    }

    @Override
    public Identifier getAnimationResource(RatmanEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "animations/ratman.animation.json");
    }

    @Override
    public void setCustomAnimations(RatmanEntity animatable, long instanceId, AnimationState<RatmanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
