package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.EldBombEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class EldBombModel extends GeoModel<EldBombEntity> {

    @Override
    public Identifier getModelResource(EldBombEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/eldbomb.geo.json");
    }

    @Override
    public Identifier getTextureResource(EldBombEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    }

    @Override
    public Identifier getAnimationResource(EldBombEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/eldbomb.animation.json");
    }

    @Override
    public void setCustomAnimations(EldBombEntity animatable, long instanceId, AnimationState<EldBombEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("Body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
