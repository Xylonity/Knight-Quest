package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SwampmanAxeEntity;
import software.bernie.geckolib.model.GeoModel;

public class SwampmanAxeModel extends GeoModel<SwampmanAxeEntity> {

    @Override
    public ResourceLocation getModelResource(SwampmanAxeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "geo/swampman_axe.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SwampmanAxeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SwampmanAxeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }

}
