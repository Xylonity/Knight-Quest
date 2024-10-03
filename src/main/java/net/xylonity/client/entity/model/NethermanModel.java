package net.xylonity.client.entity.model;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.boss.NethermanEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NethermanModel extends GeoModel<NethermanEntity> {

    @Override
    public Identifier getModelResource(NethermanEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/netherman.geo.json");
    }

    @Override
    public Identifier getTextureResource(NethermanEntity animatable) {
        String path = switch (animatable.getPhase()) {
            case 1 -> "textures/entity/netherman_fire.png";
            case 2 -> "textures/entity/netherman_ice.png";
            default -> "textures/entity/netherman_magic.png";
        };

        return Identifier.of(KnightQuest.MOD_ID, path);
    }

    @Override
    public Identifier getAnimationResource(NethermanEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/netherman.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanEntity animatable, long instanceId, AnimationState<NethermanEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
