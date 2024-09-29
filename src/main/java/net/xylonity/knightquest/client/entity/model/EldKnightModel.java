package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldKnightEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldKnightModel extends AnimatedGeoModel<EldKnightEntity> {

    @Override
    public ResourceLocation getModelResource(EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/eldknight.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldknight.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EldKnightEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/eldknight.animation.json");
    }

}