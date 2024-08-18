package net.xylonity.knightquest.common.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NethermanModel extends AnimatedGeoModel<NethermanEntity> {

    @Override
    public ResourceLocation getModelLocation(NethermanEntity nethermanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "geo/netherman.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NethermanEntity nethermanEntity) {
        String path = switch (nethermanEntity.getPhase()) {
            case 1 -> "textures/entity/netherman_fire.png";
            case 2 -> "textures/entity/netherman_ice.png";
            default -> "textures/entity/netherman_magic.png";
        };

        return new ResourceLocation(KnightQuest.MOD_ID, path);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NethermanEntity nethermanEntity) {
        return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman.animation.json");
    }
}
