package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NethermanCloneModel extends AnimatedGeoModel<NethermanCloneEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanCloneEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/netherman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanCloneEntity animatable) {

        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman_clone.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanCloneEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman.animation.json");
    }

}
