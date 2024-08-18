package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.GhastlingEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShieldModel extends AnimatedGeoModel<GhastlingEntity> {

    @Override
    public ResourceLocation getModelLocation(GhastlingEntity ghastlingEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/shield.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GhastlingEntity ghastlingEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/shield.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GhastlingEntity ghastlingEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/helmet.animation.json");
    }
}
