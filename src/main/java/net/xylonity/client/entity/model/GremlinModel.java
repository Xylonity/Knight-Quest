package net.xylonity.client.entity.model;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.GremlinEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class GremlinModel extends GeoModel<GremlinEntity> {

    @Override
    public Identifier getModelResource(GremlinEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/gremlin.geo.json");
    }

    @Override
    public Identifier getTextureResource(GremlinEntity animatable) {
        if (animatable.getPhase() == 2) {
            return Identifier.of(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return Identifier.of(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public Identifier getAnimationResource(GremlinEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/gremlin.animation.json");
    }

}
