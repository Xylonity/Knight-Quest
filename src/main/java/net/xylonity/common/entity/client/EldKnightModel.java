package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.EldKnightEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EldKnightModel extends GeoModel<EldKnightEntity> {

    @Override
    public Identifier getModelResource(EldKnightEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/eldknight.geo.json");
    }

    @Override
    public Identifier getTextureResource(EldKnightEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/eldknight.png");
    }

    @Override
    public Identifier getAnimationResource(EldKnightEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/eldknight.animation.json");
    }

    @Override
    public void setCustomAnimations(EldKnightEntity animatable, long instanceId, AnimationState<EldKnightEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}