package dev.xylonity.knightquest.client.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GeoItemArmorRenderer extends GeoArmorRenderer<GeoItemArmor> {
    public GeoItemArmorRenderer(String resourceKey, String resourceKey2) {
        super(new GeoItemArmorModel(resourceKey, resourceKey2));
    }
}