package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldKnightEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldKnightModel extends AnimatedGeoModel<EldKnightEntity> {

    @Override
    public ResourceLocation getModelLocation(EldKnightEntity eldKnightEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/eldknight.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EldKnightEntity eldKnightEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldknight.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EldKnightEntity eldKnightEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/eldknight.animation.json");
    }
}