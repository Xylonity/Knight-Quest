package dev.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.GremlinEntity;
import software.bernie.geckolib.model.GeoModel;

public class GremlinModel extends GeoModel<GremlinEntity> {

    @Override
    public ResourceLocation getModelResource(GremlinEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/gremlin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GremlinEntity animatable) {
        if (animatable.getPhase() == 2) {
            return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(GremlinEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/gremlin.animation.json");
    }

}
