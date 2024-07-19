package net.xylonity.knightquest.common.client;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GeoItemArmorRenderer extends GeoArmorRenderer<GeoItemArmor> {
    public GeoItemArmorRenderer(String resourceKey) {
        super(new GeoItemArmorModel(resourceKey));
    }
}
