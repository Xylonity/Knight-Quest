package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.GhastlingEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ShieldModel extends GeoModel<GhastlingEntity> {

    @Override
    public Identifier getModelResource(GhastlingEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/shield.geo.json");
    }

    @Override
    public Identifier getTextureResource(GhastlingEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public Identifier getAnimationResource(GhastlingEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

    @Override
    public void setCustomAnimations(GhastlingEntity animatable, long instanceId, AnimationState<GhastlingEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
