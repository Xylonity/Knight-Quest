package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.GhostyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhostyModel extends AnimatedGeoModel<GhostyEntity> {

    @Override
    public ResourceLocation getModelResource(GhostyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/ghosty.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GhostyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ghosty.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GhostyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/ghosty.animation.json");
    }

}
