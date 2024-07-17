package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.BadPatchEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BadPatchModel extends AnimatedGeoModel<BadPatchEntity> {

    @Override
    public ResourceLocation getModelResource(BadPatchEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/bad_patch.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BadPatchEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/bad_patch.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BadPatchEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/bad_patch.animation.json");
    }

}
