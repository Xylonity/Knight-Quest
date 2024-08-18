package net.xylonity.knightquest.common.client.leg;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoItemArmorModelLeg extends AnimatedGeoModel<GeoItemArmorLeg> {

    @Override
    public ResourceLocation getModelLocation(GeoItemArmorLeg geoItemArmorLeg) {
        return new ResourceLocation(KnightQuest.MOD_ID, geoItemArmorLeg.getModelResource());
    }

    @Override
    public ResourceLocation getTextureLocation(GeoItemArmorLeg geoItemArmorLeg) {
        return new ResourceLocation(KnightQuest.MOD_ID, geoItemArmorLeg.getTextureResource());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GeoItemArmorLeg geoItemArmorLeg) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");

    }
}
