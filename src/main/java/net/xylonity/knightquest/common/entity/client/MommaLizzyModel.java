package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.MommaLizzyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MommaLizzyModel extends AnimatedGeoModel<MommaLizzyEntity> {

    @Override
    public ResourceLocation getModelResource(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/momma_lizzy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/momma_lizzy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MommaLizzyEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/momma_lizzy.animation.json");
    }

}
