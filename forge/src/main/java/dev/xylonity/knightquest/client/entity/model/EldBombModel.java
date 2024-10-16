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

public class EldBombModel extends GeoModel<EldBombEntity> {
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

    @Override
    public void setCustomAnimations(EldBombEntity animatable, long instanceId, AnimationState<EldBombEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("Body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
