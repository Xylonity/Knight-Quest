package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.FallenKnightEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FallenKnightModel extends GeoModel<FallenKnightEntity> {

    @Override
    public ResourceLocation getModelResource(FallenKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/fallenknight.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FallenKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/fallenknight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FallenKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/fallenknight.animation.json");
    }

}