package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.entity.model.*;
import dev.xylonity.knightquest.common.entity.boss.NethermanCloneEntity;
import dev.xylonity.knightquest.common.entity.entities.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class NethermanCloneRenderer extends GeoEntityRenderer<NethermanCloneEntity> {

    public NethermanCloneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NethermanCloneModel());
    }

    @Override
    protected float getDeathMaxRotation(NethermanCloneEntity animatable) {
        return 0;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull NethermanCloneEntity animatable) {

        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/netherman_clone.png");
    }

    @Override
    public RenderType getRenderType(NethermanCloneEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

    @Override
    public void render(@NotNull NethermanCloneEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {

        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);

    }
}

