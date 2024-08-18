package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SwampmanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SwampmanModel extends AnimatedGeoModel<SwampmanEntity> {

    @Override
    public ResourceLocation getModelLocation(SwampmanEntity swampmanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/swampman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SwampmanEntity swampmanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SwampmanEntity swampmanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }
}