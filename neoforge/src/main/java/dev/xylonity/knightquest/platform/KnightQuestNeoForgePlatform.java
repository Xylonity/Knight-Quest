package dev.xylonity.knightquest.platform;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.armor.GeoItemArmor;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class KnightQuestNeoForgePlatform implements KnightQuestPlatform {

    private static final String TOOLTIP_ITEM_PATH = "tooltip.item.knightquest.";

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return KnightQuest.ITEMS.register(id, item);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return KnightQuest.SOUNDS.register(id, sound);
    }

    @Override
    public <T extends ParticleType<?>> Supplier<T> registerParticle(String id, boolean overrideLimiter) {
        return KnightQuest.PARTICLES.register(id, () -> (T) new SimpleParticleType(overrideLimiter));
    }

    @Override
    public <T extends Item> Supplier<T> registerGeoArmorItem(String id, Supplier<Holder<ArmorMaterial>> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip) {
        if (containsExtraTooltip)
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new GeoItemArmor(armorMaterial.get(), armorType, new Item.Properties(), id, containsTooltip) {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
        else
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new GeoItemArmor(armorMaterial.get(), armorType, new Item.Properties(), id, containsTooltip));
    }

    @Override
    public <T extends Item> Supplier<T> registerAxeItem(String id, KQItemMaterials itemMaterial, float extraDamageBoost, float speedMalus, Item.Properties properties) {
        return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new AxeItem(itemMaterial, properties.attributes(AxeItem.createAttributes(itemMaterial, extraDamageBoost, speedMalus))));
    }

    @Override
    public <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, float speedMalus, boolean containsTooltip, Item.Properties properties) {
        if (containsTooltip)
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new SwordItem(itemMaterial, properties.attributes(SwordItem.createAttributes(itemMaterial, 4, speedMalus))) {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
        else
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new SwordItem(itemMaterial, properties.attributes(SwordItem.createAttributes(itemMaterial, 4, speedMalus))));
    }

    @Override
    public <T extends Item> Supplier<T> registerArmorItem(String id, Supplier<Holder<ArmorMaterial>> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip) {
        return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new KQArmorItem(armorMaterial.get(), armorType, new Item.Properties(), containsTooltip));
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return KnightQuest.CREATIVE_TABS.register(id, tab);
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        return CreativeModeTab.builder();
    }

}
