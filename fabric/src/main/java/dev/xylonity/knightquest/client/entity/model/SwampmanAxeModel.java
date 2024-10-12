package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.SwampmanAxeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SwampmanAxeModel extends GeoModel<SwampmanAxeEntity> {

    @Override
    public ResourceLocation getModelResource(SwampmanAxeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/swampman_axe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwampmanAxeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SwampmanAxeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }

}
