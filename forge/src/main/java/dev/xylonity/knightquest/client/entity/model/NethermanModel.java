package dev.xylonity.knightquest.client.entity.model;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NethermanModel extends GeoModel<NethermanEntity> {

    private final String TEXTURE_PATH = "textures/entity/";

    @Override
    public ResourceLocation getModelResource(NethermanEntity animatable) {

        if (animatable.isDeadOrDying())
            return new ResourceLocation(KnightQuest.MOD_ID, "geo/netherman_magic.geo.json");

        String path = switch (animatable.getPhase()) {
            case 1 -> "geo/netherman_fire.geo.json";
            case 2 -> animatable.getCounterSwitchPhase2() == 130
                    ? "geo/netherman_ice.geo.json"
                    : "geo/netherman_fire.geo.json";
            default -> animatable.getCounterSwitchPhase3() == 160
                    ? "geo/netherman_magic.geo.json"
                    : "geo/netherman_ice.geo.json";
        };

        return new ResourceLocation(KnightQuest.MOD_ID, path);
    }

    @Override
    public ResourceLocation getTextureResource(NethermanEntity animatable) {

        if (animatable.isDeadOrDying())
            return new ResourceLocation(KnightQuest.MOD_ID, TEXTURE_PATH + "netherman_magic.png");

        // The mcmeta spritesheet solution could potentially desync the animation, so this is needed
        if (animatable.getPhase() == 2 && animatable.getCounterSwitchPhase2() < 130) {
            String path = switch (animatable.getCounterSwitchPhase2()) {
                default -> {
                    if (animatable.getCounterSwitchPhase2() < 50) {
                        yield TEXTURE_PATH + "netherman_fire.png";
                    } else if (animatable.getCounterSwitchPhase2() < 60) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_0.png";
                    } else if (animatable.getCounterSwitchPhase2() < 70) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_1.png";
                    } else if (animatable.getCounterSwitchPhase2() < 80) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_2.png";
                    } else if (animatable.getCounterSwitchPhase2() < 90) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_3.png";
                    } else if (animatable.getCounterSwitchPhase2() < 100) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_4.png";
                    } else if (animatable.getCounterSwitchPhase2() < 110) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_5.png";
                    } else if (animatable.getCounterSwitchPhase2() < 120) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase2_6.png";
                    } else if (animatable.getCounterSwitchPhase2() < 130) {
                        yield TEXTURE_PATH + "netherman_ice.png";
                    } else {
                        yield TEXTURE_PATH + "netherman_fire.png";
                    }
                }
            };

            return new ResourceLocation(KnightQuest.MOD_ID, path);
        }

        if (animatable.getPhase() == 3 && animatable.getCounterSwitchPhase3() < 160) {
            String path = switch (animatable.getCounterSwitchPhase3()) {
                default -> {
                    if (animatable.getCounterSwitchPhase3() < 50) {
                        yield TEXTURE_PATH + "netherman_ice.png";
                    } else if (animatable.getCounterSwitchPhase3() < 65) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_0.png";
                    } else if (animatable.getCounterSwitchPhase3() < 70) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_1.png";
                    } else if (animatable.getCounterSwitchPhase3() < 85) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_2.png";
                    } else if (animatable.getCounterSwitchPhase3() < 100) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_3.png";
                    } else if (animatable.getCounterSwitchPhase3() < 115) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_4.png";
                    } else if (animatable.getCounterSwitchPhase3() < 130) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_5.png";
                    } else if (animatable.getCounterSwitchPhase3() < 145) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_6.png";
                    } else if (animatable.getCounterSwitchPhase3() < 160) {
                        yield TEXTURE_PATH + "animated/netherman_switch_phase3_7.png";
                    } else {
                        yield TEXTURE_PATH + "netherman_magic.png";
                    }
                }
            };

            return new ResourceLocation(KnightQuest.MOD_ID, path);
        }

        String path = switch (animatable.getPhase()) {
            case 1 -> TEXTURE_PATH + "netherman_fire.png";
            case 2 -> animatable.getCounterSwitchPhase2() == 130
                    ? TEXTURE_PATH + "netherman_ice.png"
                    : TEXTURE_PATH + "netherman_fire.png";
            default -> animatable.getCounterSwitchPhase3() == 160
                    ? TEXTURE_PATH + "netherman_magic.json"
                    : TEXTURE_PATH + "netherman_ice.json";
        };

        return new ResourceLocation(KnightQuest.MOD_ID, path);
    }

    @Override
    public ResourceLocation getAnimationResource(NethermanEntity animatable) {

        if (animatable.isDeadOrDying())
            return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman_magic.animation.json");

        if (animatable.getCounterSwitchPhase2() == 130 && animatable.getPhase() == 2
                || (animatable.getCounterSwitchPhase3() != 160) && animatable.getPhase() == 3) {
            return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman_ice.animation.json");
        } else if (animatable.getCounterSwitchPhase3() == 160 && animatable.getPhase() == 3) {
            return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman_magic.animation.json");
        }

        return new ResourceLocation(KnightQuest.MOD_ID, "animations/netherman_fire.animation.json");
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
