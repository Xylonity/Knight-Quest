package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.SwampmanAxeEntity;
import software.bernie.geckolib.model.GeoModel;

public class SwampmanAxeModel extends GeoModel<SwampmanAxeEntity> {

    @Override
    public Identifier getModelResource(SwampmanAxeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/swampman_axe.geo.json");
    }

    @Override
    public Identifier getTextureResource(SwampmanAxeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/swampman.png");
    }

    @Override
    public Identifier getAnimationResource(SwampmanAxeEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/swampman.animation.json");
    }


}