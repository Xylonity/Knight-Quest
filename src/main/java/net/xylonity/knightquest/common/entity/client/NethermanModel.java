package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.xylonity.knightquest.common.entity.entities.GremlinEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NethermanModel extends GeoModel<NethermanEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/netherman.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanEntity animatable) {
        String path = switch (animatable.getPhase()) {
            case 1 -> "textures/entity/netherman_fire.png";
            case 2 -> "textures/entity/netherman_ice.png";
            default -> "textures/entity/netherman_magic.png";
        };

        return new ResourceLocation(KnightQuest.MOD_ID, path);
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanEntity animatable, long instanceId, AnimationState<NethermanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
