package net.xylonity.knightquest.common.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import software.bernie.geckolib.model.GeoModel;

public class GeoItemArmorModel extends GeoModel<GeoItemArmor> {

    private final String a;
    private final String b;

    public GeoItemArmorModel(String a, String b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public ResourceLocation getModelResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, b);
    }

    @Override
    public ResourceLocation getTextureResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, a);
    }

    @Override
    public ResourceLocation getAnimationResource(GeoItemArmor animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }

}
