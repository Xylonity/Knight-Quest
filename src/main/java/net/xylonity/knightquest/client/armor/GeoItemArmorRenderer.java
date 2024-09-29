package net.xylonity.knightquest.client.armor;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GeoItemArmorRenderer extends GeoArmorRenderer<GeoItemArmor> {
    public GeoItemArmorRenderer() {
        super(new GeoItemArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = null;
        this.rightArmBone = null;
        this.leftArmBone = null;
        this.rightBootBone = null;
        this.leftBootBone = null;
        this.rightLegBone = null;
        this.leftLegBone = null;
    }
}
