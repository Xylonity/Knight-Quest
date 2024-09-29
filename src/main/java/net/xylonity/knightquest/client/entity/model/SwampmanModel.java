package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SwampmanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SwampmanModel extends AnimatedGeoModel<SwampmanEntity> {

    @Override
    public ResourceLocation getModelResource(SwampmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/swampman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwampmanEntity animatable) {
        if (animatable.getPhase() == 2) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman_2.png");
        } else {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(SwampmanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }

}