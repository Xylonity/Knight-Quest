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
    public @NotNull ResourceLocation getTextureLocation(@NotNull NethermanEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman.png");
    }

    @Override
    public void render(@NotNull NethermanEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

