package net.xylonity.knightquest.common.client.chest;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.client.GeoItemArmor;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoItemArmorModelChest extends AnimatedGeoModel<GeoItemArmorChest> {

    @Override
    public ResourceLocation getModelLocation(GeoItemArmorChest geoItemArmorChest) {
        return new ResourceLocation(KnightQuest.MOD_ID, geoItemArmorChest.getModelResource());
    }

    @Override
    public ResourceLocation getTextureLocation(GeoItemArmorChest geoItemArmorChest) {
        return new ResourceLocation(KnightQuest.MOD_ID, geoItemArmorChest.getTextureResource());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GeoItemArmorChest geoItemArmorChest) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
