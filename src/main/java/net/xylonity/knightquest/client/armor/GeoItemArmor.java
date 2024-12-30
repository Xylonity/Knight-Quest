package net.xylonity.knightquest.client.armor;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.item.KQArmorItem;
import net.xylonity.knightquest.common.material.KQArmorMaterials;
import net.xylonity.knightquest.config.values.KQConfigValues;
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

    private boolean isArmorSetConfigEnabled(String bonusTooltip) {
        try {
            KQArmorItem.ArmorSet armorSet = KQArmorItem.ArmorSet.valueOf(bonusTooltip.toUpperCase());
            return armorSet.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        if (isArmorSetConfigEnabled(bonusTooltip))
            if (!Objects.equals(bonusTooltip, "chainmail") && !Objects.equals(bonusTooltip, "tengu")) {
                if (KQConfigValues.REQUIRED_ARMOR_PIECES < 4) {
                    pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.set_bonus"));
                } else {
                    pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_set_bonus"));
                }

                pTooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus",
                        "§7§o-" + (int) Math.floor(KQConfigValues.EVOKER_DARKNESS_CHANCE * 100) + "%",
                        "§7§o-" + (int) Math.floor(KQConfigValues.BLAZE_FIRE_CHANCE * 100) + "%",
                        "§7§o-" + ((int) Math.floor(KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER * 100 - 100)) + "%",
                        "§7§o" + KQConfigValues.SKULK_MAX_LIGHT_LEVEL,
                        "§7§o-" + (int) Math.floor(KQConfigValues.CHANCE_ENDERMANSET * 100) + "%",
                        "§7§o" + KQConfigValues.TELEPORT_RADIUS_ENDERMANSET,
                        "§7§o-" + (int) Math.floor(KQConfigValues.FORZESET_DEFLECT_CHANCE * 100) + "%",
                        "§7§o" + (100 - KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER * 100) + "%",
                        "§7§o-" + (int) Math.floor(KQConfigValues.SILVERSET_BURN_CHANCE * 100) + "%",
                        "§7§o" + (int) Math.floor(KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER * 100) + "%",
                        "§7§o-" + (int) Math.floor(KQConfigValues.WITHERSET_WITHER_CHANCE * 100) + "%",
                        "§7§o" + Math.floor(KQConfigValues.ZOMBIESET_HEALING_AMOUNT),
                        "§7§o" + KQConfigValues.ZOMBIESET_HEALING_TICKS / 20,
                        "§7§o" + KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT));
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

