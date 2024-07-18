package net.xylonity.knightquest.common.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import software.bernie.geckolib.model.GeoModel;

public class GeoItemArmorModel extends GeoModel<GeoItemArmor> {

    private final String textureResource;
    private final String modelResource;

    public GeoItemArmorModel(String textureResource, String modelResource) {
        this.textureResource = textureResource;
        this.modelResource = modelResource;
    }

    @Override
    public ResourceLocation getModelResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, modelResource);
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, textureResource);
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
