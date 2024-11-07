package dev.xylonity.knightquest.client.armor;

import dev.xylonity.knightquest.KnightQuestCommon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GeoItemArmorModel extends GeoModel<GeoItemArmor> {

    private final String resourceKey;

    public GeoItemArmorModel(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    @Override
    public ResourceLocation getModelResource(GeoItemArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "geo/" + resourceKey + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "textures/armor/" + resourceKey + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "animations/helmet.animation.json");
    }

}