package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.boss.NethermanEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NethermanRenderer extends GeoEntityRenderer<NethermanEntity> {

    public NethermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NethermanModel());
    }

    @Override
    protected float getDeathMaxRotation(NethermanEntity animatable) {
        return 0;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull NethermanEntity animatable) {
        String path = switch (animatable.getPhase()) {
            case 1 -> "textures/entity/netherman_fire.png";
            case 2 -> "textures/entity/netherman_ice.png";
            default -> "textures/entity/netherman_magic.png";
        };

        return ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, path);
    }

    @Override
    public void render(@NotNull NethermanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

