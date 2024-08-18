package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.GhostyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhostyModel extends AnimatedGeoModel<GhostyEntity> {

    @Override
    public ResourceLocation getModelLocation(GhostyEntity ghostyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/ghosty.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GhostyEntity ghostyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/ghosty.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GhostyEntity ghostyEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }
}
