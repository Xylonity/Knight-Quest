package net.xylonity.knightquest.common.client.leg;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoItemArmorModelLeg extends AnimatedGeoModel<GeoItemArmorLeg> {

    @Override
    public ResourceLocation getModelResource(GeoItemArmorLeg animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, animatable.getModelResource());
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmorLeg animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, animatable.getTextureResource());
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmorLeg animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
