package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.GremlinEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GremlinModel extends GeoModel<GremlinEntity> {

    @Override
    public Identifier getModelResource(GremlinEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "geo/gremlin.geo.json");
    }

    @Override
    public Identifier getTextureResource(GremlinEntity animatable) {
        if (animatable.getHealth() < animatable.getMaxHealth() * 0.5) {
            return new Identifier(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return new Identifier(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public Identifier getAnimationResource(GremlinEntity animatable) {
        return new Identifier(KnightQuest.MOD_ID, "animations/gremlin.animation.json");
    }

    @Override
    public void setCustomAnimations(GremlinEntity animatable, long instanceId, AnimationState<GremlinEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
