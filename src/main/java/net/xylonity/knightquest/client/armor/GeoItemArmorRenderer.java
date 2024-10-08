package net.xylonity.knightquest.client.armor;

import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GeoItemArmorRenderer extends GeoArmorRenderer<GeoItemArmor> {
    public GeoItemArmorRenderer(String a, String b) {
        super(new GeoItemArmorModel(a, b));
    }
}
