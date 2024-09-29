package net.xylonity.knightquest.client.armor.chest;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GeoItemArmorChestRenderer extends GeoArmorRenderer<GeoItemArmorChest> {
    public GeoItemArmorChestRenderer() {
        super(new GeoItemArmorModelChest());

        this.headBone = null;
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = null;
        this.leftLegBone = null;
        this.rightBootBone = null;
        this.leftBootBone = null;
    }
}
