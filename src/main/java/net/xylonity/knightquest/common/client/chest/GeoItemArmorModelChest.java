package net.xylonity.knightquest.common.client.chest;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.client.GeoItemArmor;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoItemArmorModelChest extends AnimatedGeoModel<GeoItemArmorChest> {

    @Override
    public ResourceLocation getModelResource(GeoItemArmorChest animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, animatable.getModelResource());
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmorChest animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, animatable.getTextureResource());
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmorChest animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
