package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.common.entity.boss.NethermanProjectileChargeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NethermanProjectileChargeEntityModel extends GeoModel<NethermanProjectileChargeEntity> {

    @Override
    public ResourceLocation getModelResource(NethermanProjectileChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "geo/netherman_projectile_charge.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NethermanProjectileChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "textures/entity/netherman_projectile_charge.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanProjectileChargeEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "animations/netherman_projectile_charge.animation.json");
    }

}
