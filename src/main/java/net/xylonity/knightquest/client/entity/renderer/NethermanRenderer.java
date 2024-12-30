package net.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.client.entity.model.NethermanModel;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NethermanRenderer extends GeoEntityRenderer<NethermanEntity> {

    private final String TEXTURE_PATH = "textures/entity/";

    public NethermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NethermanModel());
    }

    @Override
    protected float getDeathMaxRotation(NethermanEntity animatable) {
        return 0;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull NethermanEntity animatable) {

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
                    ? TEXTURE_PATH + "netherman_magic.png"
                    : TEXTURE_PATH + "netherman_ice.png";
        };

        return new ResourceLocation(KnightQuest.MOD_ID, path);
    }

    @Override
    public RenderType getRenderType(NethermanEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(@NotNull NethermanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

