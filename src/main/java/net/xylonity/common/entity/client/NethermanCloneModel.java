package net.xylonity.common.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.boss.NethermanCloneEntity;
import net.xylonity.common.entity.boss.NethermanEntity;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NethermanCloneModel extends GeoModel<NethermanCloneEntity> {

    @Override
    public Identifier getModelResource(NethermanCloneEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "geo/netherman.geo.json");
    }

    @Override
    public Identifier getTextureResource(NethermanCloneEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/netherman_clone.png");
    }

    @Override
    public Identifier getAnimationResource(NethermanCloneEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "animations/netherman.animation.json");
    }

    @Override
    public void setCustomAnimations(NethermanCloneEntity animatable, long instanceId, AnimationState<NethermanCloneEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("body");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }

}
