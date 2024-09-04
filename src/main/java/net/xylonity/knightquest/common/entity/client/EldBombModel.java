package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.EldBombEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EldBombModel extends AnimatedGeoModel<EldBombEntity> {
    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb.png");
    private static final ResourceLocation WHITE_TEXTURE = new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/eldbomb_white.png");

    @Override
    public ResourceLocation getModelResource(EldBombEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/eldbomb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EldBombEntity animatable) {
        if (animatable.getSwell() > 10) {
            if ((animatable.tickCount / 5) % 2 == 0) {
                return WHITE_TEXTURE;
            } else {
                return DEFAULT_TEXTURE;
            }
        }

        return DEFAULT_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(EldBombEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/eldbomb.animation.json");
    }

}
