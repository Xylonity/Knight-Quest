package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SamhainModel extends AnimatedGeoModel<SamhainEntity> {

    @Override
    public ResourceLocation getModelResource(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return new ResourceLocation(KnightQuest.MOD_ID, "geo/samhain_squire.geo.json");
        }
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/samhain.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SamhainEntity animatable) {
        if (animatable.hasArmor()) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain_squire.png");
        }
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SamhainEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/samhain.animation.json");
    }

}