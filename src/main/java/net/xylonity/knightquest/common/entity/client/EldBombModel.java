package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldBombEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldBombModel extends AnimatedGeoModel<EldBombEntity> {

    @Override
    public ResourceLocation getModelResource(EldBombEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/eldbomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EldBombEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EldBombEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/eldbomb.animation.json");
    }

}
