package dev.xylonity.knightquest.platform;

import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.armor.GeoItemArmor;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class KnightQuestFabricPlatform implements KnightQuestPlatform {

    private static final String TOOLTIP_ITEM_PATH = "tooltip.item.knightquest.";

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return registerSupplier(BuiltInRegistries.ITEM, id, item);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return registerSupplier(BuiltInRegistries.SOUND_EVENT, id, sound);
    }

    @Override
    public <T extends ParticleType<?>> Supplier<T> registerParticle(String id, boolean overrideLimiter) {
        return registerSupplier(BuiltInRegistries.PARTICLE_TYPE, id, () -> (T) FabricParticleTypes.simple());
    }

    @Override
    public <T extends Item> Supplier<T> registerGeoArmorItem(String id, KQArmorMaterials armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip) {
        if (containsExtraTooltip)
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new GeoItemArmor(armorMaterial, armorType, new Item.Properties(), id, containsTooltip) {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
        else
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new GeoItemArmor(armorMaterial, armorType, new Item.Properties(), id, containsTooltip));
    }

    @Override
    public <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, float speedMalus, boolean containsTooltip) {
        if (containsTooltip)
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new SwordItem(itemMaterial, 4, speedMalus, new Item.Properties()) {
                @Override
                public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
                    pTooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
                }
            });
        else
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new SwordItem(itemMaterial, 4, speedMalus, new Item.Properties()));
    }

    @Override
    public <T extends Item> Supplier<T> registerAxeItem(String id, KQItemMaterials itemMaterial, float damageBoost, float speedMalus) {
        return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new AxeItem(itemMaterial, damageBoost, speedMalus, new Item.Properties()));
    }

    @Override
    public <T extends Item> Supplier<T> registerArmorItem(String id, KQArmorMaterials armorMaterial, ArmorItem.Type armorType, boolean containsTooltip) {
        return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new KQArmorItem(armorMaterial, armorType, new Item.Properties(), containsTooltip));
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return registerSupplier(BuiltInRegistries.CREATIVE_MODE_TAB, id, tab);
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        return FabricItemGroup.builder();
    }

    private static <T, R extends Registry<? super T>> Supplier<T> registerSupplier(R registry, String id, Supplier<T> object) {
        final T registeredObject = Registry.register((Registry<T>) registry, new ResourceLocation(KnightQuest.MOD_ID, id), object.get());

        return () -> registeredObject;
    }

}
