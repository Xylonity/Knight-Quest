package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.RatmanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RatmanModel extends AnimatedGeoModel<RatmanEntity> {

    @Override
    public ResourceLocation getModelResource(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/ratman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ratman" + animatable.getVariation() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(RatmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/ratman.animation.json");
    }

}
