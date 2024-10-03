package net.xylonity.client.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GeoItemArmorRenderer extends GeoArmorRenderer<GeoItemArmor> {
    public GeoItemArmorRenderer(String path, String helmet) {
        super(new GeoItemArmorModel(path, helmet));
    }
}
