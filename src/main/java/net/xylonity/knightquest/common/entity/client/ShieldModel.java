package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.ShieldEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShieldModel extends AnimatedGeoModel<ShieldEntity> {

    @Override
    public ResourceLocation getModelResource(ShieldEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShieldEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShieldEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
