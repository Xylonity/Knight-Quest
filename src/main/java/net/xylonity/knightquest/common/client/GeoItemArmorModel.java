package net.xylonity.knightquest.common.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.xylonity.knightquest.KnightQuest;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoItemArmorModel extends AnimatedGeoModel<GeoItemArmor> {

    @Override
    public ResourceLocation getModelResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, animatable.getModelResource());
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, animatable.getTextureResource());
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
