package dev.xylonity.knightquest.platform;

import dev.xylonity.knightquest.common.material.KQItemMaterials;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface KnightQuestPlatform {

    <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item);
    <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound);
    <T extends ParticleType<?>> Supplier<T> registerParticle(String id, boolean overrideLimiter);
    <T extends Item> Supplier<T> registerSpecificItem(String id, Item.Properties properties, KnightQuestItems.KQItemType type);
    <T extends Item> Supplier<T> registerArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, Item.Properties properties, int durabilityAmount);
    <T extends Item> Supplier<T> registerGeoArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip, Item.Properties properties, int durabilityAmount);
    <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, Item.Properties properties, float attackMalus, boolean containsTooltip);
    <T extends Item> Supplier<T> registerAxeItem(String id, KQItemMaterials itemMaterial, Item.Properties properties, float extraDamageBoost, float attackMalus);
    <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity);
    <T extends Mob> Supplier<Item> registerSpawnEggItem(String id, Supplier<EntityType<T>> entity, int primaryEggColour, int secondaryEggColour);
    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab);
    <T extends Item> Supplier<T> registerMusicDisc(String id, int signal, Supplier<SoundEvent> soundEvent, Item.Properties properties, int length);

    <T extends ArmorMaterial> Holder<T> registerArmorMaterial(String id, Supplier<T> armorMaterial);

    Supplier<Item> getGreatEssence();
    Supplier<Item> getSmallEssence();
    Supplier<Block> getGreatChalice();
    Supplier<ParticleOptions> getStartsetParticle();
    Supplier<Item> getPaladinSword();

    CreativeModeTab.Builder creativeTabBuilder();

}
