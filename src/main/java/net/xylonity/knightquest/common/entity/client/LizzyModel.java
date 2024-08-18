package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.LizzyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LizzyModel extends AnimatedGeoModel<LizzyEntity> {

    @Override
    public ResourceLocation getModelLocation(LizzyEntity lizzyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/lizzy.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(LizzyEntity lizzyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/lizzy.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(LizzyEntity lizzyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/lizzy.animation.json");
    }
}
