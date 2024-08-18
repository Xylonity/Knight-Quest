package net.xylonity.knightquest.common.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.xylonity.knightquest.KnightQuest;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GeoItemArmorModel extends AnimatedGeoModel<GeoItemArmor> {

    @Override
    public ResourceLocation getModelLocation(GeoItemArmor geoItemArmor) {
        return new ResourceLocation(KnightQuest.MOD_ID, geoItemArmor.getModelResource());
    }

    @Override
    public ResourceLocation getTextureLocation(GeoItemArmor geoItemArmor) {
        return new ResourceLocation(KnightQuest.MOD_ID, geoItemArmor.getTextureResource());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GeoItemArmor geoItemArmor) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
