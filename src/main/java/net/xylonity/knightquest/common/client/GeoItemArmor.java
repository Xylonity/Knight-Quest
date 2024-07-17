package net.xylonity.knightquest.common.client;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.material.KQArmorMaterials;
import net.xylonity.knightquest.registry.KnightQuestCreativeModeTabs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.Objects;

public class GeoItemArmor extends GeoArmorItem implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final String modelResource;
    private final String textureResource;
    private final String bonusTooltip;

    public GeoItemArmor(KQArmorMaterials material, EquipmentSlot type, Properties properties, String textureResource, String modelResource) {
        super(material, type, properties.tab(KnightQuest.CREATIVE_MODE_TAB));
        this.bonusTooltip = material.getKeyName();
        this.textureResource = textureResource;
        this.modelResource = modelResource;
    }

    public String getModelResource() {
        return modelResource;
    }

    public String getTextureResource() {
        return textureResource;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (!Objects.equals(bonusTooltip, "chainmail") && !Objects.equals(bonusTooltip, "tengu")) {
            pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_set_bonus"));
            pTooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
        } else if (Objects.equals(bonusTooltip, "tengu")) {
            pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_helmet_bonus"));
            pTooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private PlayState predicate(AnimationEvent<?> animationState) {
        animationState.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}

