package net.xylonity.knightquest.common.client;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GeoItemArmorRenderer extends GeoArmorRenderer<GeoItemArmor> {
    public GeoItemArmorRenderer(String textureResource, String modelResource) {
        super(new GeoItemArmorModel(textureResource, modelResource));
    }
}
