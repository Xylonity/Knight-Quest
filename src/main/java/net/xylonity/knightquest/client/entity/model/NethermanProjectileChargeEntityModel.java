package net.xylonity.knightquest.client.entity.model;

import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanProjectileChargeEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NethermanProjectileChargeEntityModel extends AnimatedGeoModel<NethermanProjectileChargeEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanProjectileChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/netherman_projectile_charge.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanProjectileChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman_projectile_charge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanProjectileChargeEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman_projectile_charge.animation.json");
    }

}
