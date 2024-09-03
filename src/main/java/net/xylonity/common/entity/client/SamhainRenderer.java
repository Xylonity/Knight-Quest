package net.xylonity.common.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.xylonity.KnightQuest;
import net.xylonity.common.entity.entities.SamhainEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class SamhainRenderer extends GeoEntityRenderer<SamhainEntity> {
    protected ItemStack mainHandItem;
    protected ItemStack offhandItem;

    public SamhainRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SamhainModel());

        this.addRenderLayer(new AutoGlowingGeoLayer<>(this));
        this.addRenderLayer(new ItemArmorGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, SamhainEntity animatable) {
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
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, SamhainEntity animatable) {
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
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, SamhainEntity animatable, BipedEntityModel<?> baseModel) {
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
            protected ItemStack getStackForBone(GeoBone bone, SamhainEntity animatable) {
                ItemStack var10000;
                switch (bone.getName()) {
                    case "bipedHandLeft" -> var10000 = animatable.isLeftHanded() ? SamhainRenderer.this.mainHandItem : SamhainRenderer.this.offhandItem;
                    case "bipedHandRight" -> var10000 = animatable.isLeftHanded() ? SamhainRenderer.this.offhandItem : SamhainRenderer.this.mainHandItem;
                    default -> var10000 = null;
                }

                return var10000;
            }

            @Override
            protected ModelTransformationMode getTransformTypeForStack(GeoBone bone, ItemStack stack, SamhainEntity animatable) {
                return switch (bone.getName()) {
                    case "bipedHandLeft", "bipedHandRight" -> ModelTransformationMode.THIRD_PERSON_RIGHT_HAND;
                    default -> ModelTransformationMode.NONE;
                };
            }

            @Override
            protected void renderStackForBone(MatrixStack poseStack, GeoBone bone, ItemStack stack, SamhainEntity animatable, VertexConsumerProvider bufferSource, float partialTick, int packedLight, int packedOverlay) {

                AnimationController<?> controller = animatable.getAnimatableInstanceCache().getManagerForId(animatable.getId()).getAnimationControllers().get("controller");
                if (controller != null && controller.getCurrentAnimation() != null) {
                    String currentAnimationName = controller.getCurrentAnimation().animation().name();
                    if (currentAnimationName.startsWith("sit")) {
                        return;
                    }
                }

                if (stack == SamhainRenderer.this.mainHandItem) {
                    poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0F));
                    poseStack.translate(0.05, 0.1, -0.45);
                    poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0.0F));
                    poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0.0F));
                } else if (stack == SamhainRenderer.this.offhandItem) {
                    poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90.0F));
                    poseStack.translate(0.05, 0.1, -0.45);
                    poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
                    poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0.0F));
                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public Identifier getTextureLocation(SamhainEntity animatable) {
        return Identifier.of(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public void render(SamhainEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public void preRender(MatrixStack poseStack, SamhainEntity animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        this.mainHandItem = animatable.getMainHandStack();
        this.offhandItem = animatable.getOffHandStack();
    }
}

