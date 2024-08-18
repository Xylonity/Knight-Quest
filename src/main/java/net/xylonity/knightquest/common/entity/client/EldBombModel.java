package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldBombEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldBombModel extends AnimatedGeoModel<EldBombEntity> {

    @Override
    public ResourceLocation getModelLocation(EldBombEntity eldBombEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/eldbomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EldBombEntity eldBombEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EldBombEntity eldBombEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/eldbomb.animation.json");
    }
}
