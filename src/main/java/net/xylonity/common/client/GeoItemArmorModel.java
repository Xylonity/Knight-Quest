package net.xylonity.common.client;

import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import software.bernie.geckolib.model.GeoModel;

public class GeoItemArmorModel extends GeoModel<GeoItemArmor> {

    String path;
    String helmet;

    public GeoItemArmorModel(String path, String helmet) {
        this.path = path;
        this.helmet = helmet;
    }

    @Override
    public Identifier getModelResource(GeoItemArmor animatable) {
        return new Identifier(KnightQuest.MOD_ID, helmet);
    }

    @Override
    public Identifier getTextureResource(GeoItemArmor animatable) {
        return new Identifier(KnightQuest.MOD_ID, path);
    }

    @Override
    public Identifier getAnimationResource(GeoItemArmor animatable) {
        return new Identifier(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
