package dev.xylonity.knightquest.platform;

import dev.xylonity.knightlib.registry.KnightLibBlocks;
import dev.xylonity.knightlib.registry.KnightLibItems;
import dev.xylonity.knightlib.registry.KnightLibParticles;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.armor.GeoItemArmor;
import dev.xylonity.knightquest.common.item.ChaoticEssenceItem;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.common.item.KnightQuestItem;
import dev.xylonity.knightquest.common.item.RadiantEssenceItem;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import dev.xylonity.knightquest.registry.KnightQuestWeapons;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class KnightQuestFabricPlatform implements KnightQuestPlatform {

    private static final String TOOLTIP_ITEM_PATH = "tooltip.item.knightquest.";

    @Override
    public Supplier<Item> getGreatEssence() {
        return KnightLibItems.GREAT_ESSENCE;
    }

    @Override
    public Supplier<Item> getSmallEssence() {
        return KnightLibItems.SMALL_ESSENCE;
    }

    @Override
    public Supplier<Block> getGreatChalice() {
        return KnightLibBlocks.GREAT_CHALICE;
    }

    @Override
    public Supplier<ParticleOptions> getStartsetParticle() {
        return KnightLibParticles.STARSET::get;
    }

    @Override
    public Supplier<Item> getPaladinSword() {
        return () -> KnightQuestWeapons.PALADIN_SWORD;
    }

    @Override
    public <T extends Item> Supplier<T> registerSpecificItem(String id, Item.Properties properties, KnightQuestItems.KQItemType type) {
        if (type == KnightQuestItems.KQItemType.CHAOTIC_ESSENCE) {
            return (Supplier<T>) registerItem(id, () -> new ChaoticEssenceItem(properties, id));
        } else if (type == KnightQuestItems.KQItemType.RADIANT_ESSENCE) {
            return (Supplier<T>) registerItem(id, () -> new RadiantEssenceItem(properties, id));
        }

        return (Supplier<T>) registerItem(id, () -> new KnightQuestItem(properties, id));
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        return FabricItemGroup.builder();
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return registerSupplier(BuiltInRegistries.ITEM, id, item);
    }

    @Override
    public <T extends Mob> Supplier<Item> registerSpawnEggItem(String id, Supplier<EntityType<T>> entity, int primaryEggColour, int secondaryEggColour) {
        return registerItem(id, () -> new SpawnEggItem(entity.get(), primaryEggColour, secondaryEggColour, new Item.Properties()));
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
    public <T extends Item> Supplier<T> registerGeoArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip, Item.Properties properties, int durabilityAmount) {
        if (containsExtraTooltip)
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new GeoItemArmor(armorMaterial, armorType, properties.durability(armorType.getDurability(durabilityAmount)), id, containsTooltip) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
        else
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new GeoItemArmor(armorMaterial, armorType, properties.durability(armorType.getDurability(durabilityAmount)), id, containsTooltip));
    }

    @Override
    public <T extends Item> Supplier<T> registerArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, Item.Properties properties, int durabilityAmount) {
        return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new KQArmorItem(armorMaterial, armorType, properties.durability(armorType.getDurability(durabilityAmount)), containsTooltip));
    }

    @Override
    public <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, Item.Properties properties, float speedMalus, boolean containsTooltip) {
        if (containsTooltip)
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new SwordItem(itemMaterial, properties.attributes(SwordItem.createAttributes(itemMaterial, 4, speedMalus))) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
        else
            return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new SwordItem(itemMaterial, properties.attributes(SwordItem.createAttributes(itemMaterial, 4, speedMalus))));
    }

    @Override
    public <T extends Item> Supplier<T> registerAxeItem(String id, KQItemMaterials itemMaterial, Item.Properties properties, float damageBoost, float speedMalus) {
        return (Supplier<T>) registerSupplier(BuiltInRegistries.ITEM, id, () -> new AxeItem(itemMaterial, properties.attributes(AxeItem.createAttributes(itemMaterial, damageBoost, speedMalus))));
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity) {
        return registerSupplier(BuiltInRegistries.ENTITY_TYPE, id, entity);
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return registerSupplier(BuiltInRegistries.CREATIVE_MODE_TAB, id, tab);
    }

    @Override
    public <T extends Item> Supplier<T> registerMusicDisc(String id, int signal, Supplier<SoundEvent> soundEvent, Item.Properties properties, int length) {
        return (Supplier<T>) registerItem(id, () -> new Item(properties.jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, id)))));
    }

    @Override
    public <T extends ArmorMaterial> Holder<T> registerArmorMaterial(String id, Supplier<T> armorMaterial) {
        return Registry.registerForHolder((Registry<T>) BuiltInRegistries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, id), armorMaterial.get());
    }

    private static <T, R extends Registry<? super T>> Supplier<T> registerSupplier(R registry, String id, Supplier<T> object) {
        final T registeredObject = Registry.register((Registry<T>) registry, ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, id), object.get());

        return () -> registeredObject;
    }

}
