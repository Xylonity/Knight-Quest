package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanTeleportChargeEntity;
import net.xylonity.knightquest.common.entity.entities.SwampmanAxeEntity;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
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
