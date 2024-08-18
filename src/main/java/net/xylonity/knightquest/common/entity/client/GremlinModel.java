package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.GremlinEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GremlinModel extends AnimatedGeoModel<GremlinEntity> {

    @Override
    public ResourceLocation getModelLocation(GremlinEntity gremlinEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/gremlin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GremlinEntity gremlinEntity) {
        if (gremlinEntity.getHealth() < gremlinEntity.getMaxHealth() * 0.5) {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/gremlin_angry.png");
        } else {
            return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/gremlin.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GremlinEntity gremlinEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/gremlin.animation.json");
    }
}
