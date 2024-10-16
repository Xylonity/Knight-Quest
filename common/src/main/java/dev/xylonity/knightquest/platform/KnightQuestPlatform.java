package dev.xylonity.knightquest.platform;

import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface KnightQuestPlatform {

    <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound);

    <T extends ParticleType<?>> Supplier<T> registerParticle(String id, boolean overrideLimiter);

    //<T extends Item> Supplier<T> registerItem(String id, Supplier<T> item);
    //<T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, float attackMalus, boolean containsTooltip, Item.Properties properties);
    //<T extends Item> Supplier<T> registerAxeItem(String id, KQItemMaterials itemMaterial, float extraDamageBoost, float attackMalus, Item.Properties properties);
//
    //<T extends Item> Supplier<T> registerArmorItem(String id, Supplier<Holder<ArmorMaterial>> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip);
    //<T extends Item> Supplier<T> registerGeoArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip);
//
    //<T extends ArmorMaterial> Holder<T> registerArmorMaterial(String id, Supplier<T> armorMaterial);
//
    //<T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab);
    //CreativeModeTab.Builder creativeTabBuilder();

}
