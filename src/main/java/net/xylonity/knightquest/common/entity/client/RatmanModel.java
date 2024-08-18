package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.RatmanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RatmanModel extends AnimatedGeoModel<RatmanEntity> {

    @Override
    public ResourceLocation getModelLocation(RatmanEntity ratmanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/ratman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RatmanEntity ratmanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ratman.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RatmanEntity ratmanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/ratman.animation.json");
    }
}
