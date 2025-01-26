package dev.xylonity.knightquest.client.armor;

import dev.xylonity.knightquest.KnightQuest;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GeoItemArmorModel extends GeoModel<GeoItemArmor> {

    private final String resourceKey;
    private final String resourceKey2;

    public GeoItemArmorModel(String resourceKey, String resourceKey2) {
        this.resourceKey = resourceKey;
        this.resourceKey2 = resourceKey2;
    }

    @Override
    public ResourceLocation getModelResource(GeoItemArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, resourceKey2);
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, resourceKey);
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmor animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}