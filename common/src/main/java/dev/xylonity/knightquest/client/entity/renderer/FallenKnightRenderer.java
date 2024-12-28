package dev.xylonity.knightquest.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.xylonity.knightquest.KnightQuestCommon;
import dev.xylonity.knightquest.client.entity.model.FallenKnightModel;
import dev.xylonity.knightquest.common.entity.entities.FallenKnightEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class FallenKnightRenderer extends GeoEntityRenderer<FallenKnightEntity> {
    protected ItemStack mainHandItem;
    protected ItemStack offhandItem;

    public FallenKnightRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FallenKnightModel());

        this.addRenderLayer(new ItemArmorGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, FallenKnightEntity animatable) {
                return switch (bone.getName()) {
                    case "armorBipedLeftFoot", "armorBipedRightFoot" -> this.bootsStack;
                    case "armorBipedLeftLeg", "armorBipedRightLeg" -> this.leggingsStack;
                    case "armorBipedBody", "armorBipedRightArm", "armorBipedLeftArm" -> this.chestplateStack;
                    case "armorBipedHead" -> this.helmetStack;
                    default -> null;
                };
            }

            @NotNull
            @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, FallenKnightEntity animatable) {
                return switch (bone.getName()) {
                    case "armorBipedLeftFoot", "armorBipedRightFoot" -> EquipmentSlot.FEET;
                    case "armorBipedLeftLeg", "armorBipedRightLeg" -> EquipmentSlot.LEGS;
                    case "armorBipedRightArm" ->
                            !animatable.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                    case "armorBipedLeftArm" ->
                            animatable.isLeftHanded() ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                    case "armorBipedBody" -> EquipmentSlot.CHEST;
                    case "armorBipedHead" -> EquipmentSlot.HEAD;
                    default -> super.getEquipmentSlotForBone(bone, stack, animatable);
                };
            }
            @NotNull
            @Override
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, FallenKnightEntity animatable, HumanoidModel<?> baseModel) {
                return switch (bone.getName()) {
                    case "armorBipedLeftFoot", "armorBipedLeftLeg" -> baseModel.leftLeg;
                    case "armorBipedRightFoot", "armorBipedRightLeg" -> baseModel.rightLeg;
                    case "armorBipedRightArm" -> baseModel.rightArm;
                    case "armorBipedLeftArm" -> baseModel.leftArm;
                    case "armorBipedBody" -> baseModel.body;
                    case "armorBipedHead" -> baseModel.head;
                    default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
                };
            }
        });
        this.addRenderLayer(new BlockAndItemGeoLayer<>(this) {
            @Override
            protected ItemStack getStackForBone(GeoBone bone, FallenKnightEntity animatable) {
                ItemStack var10000;
                switch (bone.getName()) {
                    case "bipedHandLeft" -> var10000 = animatable.isLeftHanded() ? FallenKnightRenderer.this.mainHandItem : FallenKnightRenderer.this.offhandItem;
                    case "bipedHandRight" -> var10000 = animatable.isLeftHanded() ? FallenKnightRenderer.this.offhandItem : FallenKnightRenderer.this.mainHandItem;
                    default -> var10000 = null;
                }

                return var10000;
            }

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, FallenKnightEntity animatable) {
                return switch (bone.getName()) {
                    case "bipedHandLeft", "bipedHandRight" -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, FallenKnightEntity animatable, MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {

                if (stack == FallenKnightRenderer.this.mainHandItem) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    poseStack.translate(0.05, 0.1, -0.45);
                    poseStack.mulPose(Axis.YP.rotationDegrees(0.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                } else if (stack == FallenKnightRenderer.this.offhandItem) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                    poseStack.translate(0.05, 0.1, -0.45);
                    poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
                    poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull FallenKnightEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(KnightQuestCommon.MOD_ID, "textures/entity/fallenknight.png");
    }

    @Override
    public void preRender(PoseStack poseStack, FallenKnightEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        this.mainHandItem = animatable.getMainHandItem();
        this.offhandItem = animatable.getOffhandItem();
    }

}

