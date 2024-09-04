package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.state.BlockState;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.client.DefaultBipedBoneIdents;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.ExtendedGeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SamhainRenderer extends ExtendedGeoEntityRenderer<SamhainEntity> {

    protected ItemStack mainHandItem, offHandItem;

    public SamhainRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SamhainModel());
    }

    @Override
    public void renderEarly(SamhainEntity animatable, PoseStack poseStack, float partialTick, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float partialTicks) {
        super.renderEarly(animatable, poseStack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, partialTicks);

        this.mainHandItem = animatable.getItemBySlot(EquipmentSlot.MAINHAND);
        this.offHandItem = animatable.getItemBySlot(EquipmentSlot.OFFHAND);
    }

    @Nullable
    @Override
    protected ItemStack getHeldItemForBone(String s, SamhainEntity samhainEntity) {
        return switch (s) {
            case DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT -> samhainEntity.isLeftHanded() ? mainHandItem : offHandItem;
            case DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT -> samhainEntity.isLeftHanded() ? offHandItem : mainHandItem;
            default -> null;
        };
    }

    @Override
    protected ItemTransforms.TransformType getCameraTransformForItemAtBone(ItemStack itemStack, String s) {
        return switch (s) {
            case DefaultBipedBoneIdents.LEFT_HAND_BONE_IDENT, DefaultBipedBoneIdents.RIGHT_HAND_BONE_IDENT -> ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND;
            default -> ItemTransforms.TransformType.NONE;
        };
    }

    @Nullable
    @Override
    protected BlockState getHeldBlockForBone(String s, SamhainEntity samhainEntity) {
        return null;
    }

    @Override
    protected void preRenderItem(PoseStack poseStack, ItemStack itemStack, String s, SamhainEntity samhainEntity, IBone iBone) {

        AnimationController<?> controller = animatable.getFactory().getOrCreateAnimationData(animatable.getId()).getAnimationControllers().get("controller");
        if (controller != null && controller.getCurrentAnimation() != null) {
            String currentAnimationName = controller.getCurrentAnimation().animationName.toLowerCase();
            if (currentAnimationName.contains("sit")) {
                poseStack.scale(0, 0, 0);
                return;
            }
        }

        if (itemStack == SamhainRenderer.this.mainHandItem) {
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            poseStack.translate(0.05, 0.1, -0.45);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(0.0F));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(0.0F));
        } else if (itemStack == SamhainRenderer.this.offHandItem) {
            poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            poseStack.translate(0.05, 0.1, -0.45);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(0.0F));
        }
    }

    @Override
    protected void preRenderBlock(PoseStack poseStack, BlockState blockState, String s, SamhainEntity samhainEntity) {}

    @Override
    protected void postRenderItem(PoseStack poseStack, ItemStack itemStack, String s, SamhainEntity samhainEntity, IBone iBone) {}

    @Override
    protected void postRenderBlock(PoseStack poseStack, BlockState blockState, String s, SamhainEntity samhainEntity) {}

    @Override
    public ResourceLocation getTextureLocation(SamhainEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    protected boolean isArmorBone(GeoBone geoBone) {
        return geoBone.getName().startsWith("armor");
    }

    @Nullable
    @Override
    protected ResourceLocation getTextureForBone(String s, SamhainEntity samhainEntity) {
        return null;
    }

    @Override
    public void render(SamhainEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}