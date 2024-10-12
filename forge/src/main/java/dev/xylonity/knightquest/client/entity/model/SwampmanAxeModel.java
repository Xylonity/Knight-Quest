package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

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
