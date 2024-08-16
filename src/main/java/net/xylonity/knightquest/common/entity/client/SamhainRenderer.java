package net.xylonity.knightquest.common.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class SamhainRenderer extends GeoEntityRenderer<SamhainEntity> {

    public SamhainRenderer(EntityRendererProvider.Context renderManager) {
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
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, SamhainEntity animatable, HumanoidModel<?> baseModel) {
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
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SamhainEntity animatable) {
        return new ResourceLocation(KnightQuest.MOD_ID, "textures/entity/samhain.png");
    }

    @Override
    public void render(SamhainEntity entity, float entityYaw, float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.35f, 0.35f, 0.35f);
        } else {
            poseStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}

