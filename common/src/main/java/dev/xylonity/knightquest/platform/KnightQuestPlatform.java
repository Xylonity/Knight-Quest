package dev.xylonity.knightquest.platform;

import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

public interface KnightQuestPlatform {

    <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item);
    <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound);
    <T extends ParticleType<?>> Supplier<T> registerParticle(String id, boolean overrideLimiter);
    <T extends Item> Supplier<T> registerGeoArmorItem(String id, KQArmorMaterials armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip);
    <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, float attackMalus, boolean containsTooltip);
    <T extends Item> Supplier<T> registerArmorItem(String id, KQArmorMaterials armorMaterial, ArmorItem.Type armorType, boolean containsTooltip);
    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab);
    CreativeModeTab.Builder creativeTabBuilder();

}
