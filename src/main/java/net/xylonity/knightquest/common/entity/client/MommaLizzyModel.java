package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.MommaLizzyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MommaLizzyModel extends AnimatedGeoModel<MommaLizzyEntity> {

    @Override
    public ResourceLocation getModelLocation(MommaLizzyEntity mommaLizzyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/momma_lizzy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MommaLizzyEntity mommaLizzyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/momma_lizzy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MommaLizzyEntity mommaLizzyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/momma_lizzy.animation.json");
    }
}
