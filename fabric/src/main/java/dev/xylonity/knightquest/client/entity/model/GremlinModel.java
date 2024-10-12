package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.GremlinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GremlinModel extends GeoModel<GremlinEntity> {

    @Override
    public ResourceLocation getModelResource(GremlinEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/gremlin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GremlinEntity animatable) {
        if (animatable.getPhase() == 2) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(GremlinEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/gremlin.animation.json");
    }

}
