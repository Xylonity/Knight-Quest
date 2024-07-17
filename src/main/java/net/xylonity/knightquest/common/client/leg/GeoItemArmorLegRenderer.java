package net.xylonity.knightquest.common.client.leg;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GeoItemArmorLegRenderer extends GeoArmorRenderer<GeoItemArmorLeg> {
    public GeoItemArmorLegRenderer() {
        super(new GeoItemArmorModelLeg());

        this.headBone = null;
        this.bodyBone = "armorBody";
        this.rightArmBone = null;
        this.leftArmBone = null;
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = null;
        this.leftBootBone = null;
    }
}
