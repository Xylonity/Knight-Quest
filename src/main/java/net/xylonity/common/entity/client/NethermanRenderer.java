package net.xylonity.common.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.boss.NethermanEntity;
import net.xylonity.common.entity.entities.RatmanEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class NethermanRenderer extends GeoEntityRenderer<NethermanEntity> {

    public NethermanRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new NethermanModel());
    }

    @Override
    public Identifier getTextureLocation(NethermanEntity animatable) {
        String path = switch (animatable.getPhase()) {
            case 1 -> "textures/entity/netherman_fire.png";
            case 2 -> "textures/entity/netherman_ice.png";
            default -> "textures/entity/netherman_magic.png";
        };

        return Identifier.of(KnightQuest.MOD_ID, path);
    }

    @Override
    protected float getDeathMaxRotation(NethermanEntity animatable) {
        return 0;
    }

    @Override
    public void render(NethermanEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        poseStack.scale(1.2f, 1.2f, 1.2f);

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}

