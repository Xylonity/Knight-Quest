package dev.xylonity.knightquest.platform;

import dev.xylonity.knightlib.compat.registry.KnightLibBlocks;
import dev.xylonity.knightlib.compat.registry.KnightLibItems;
import dev.xylonity.knightlib.compat.registry.KnightLibParticles;
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.client.armor.GeoItemArmor;
import dev.xylonity.knightquest.common.item.KQArmorItem;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import dev.xylonity.knightquest.registry.KnightQuestSounds;
import dev.xylonity.knightquest.registry.KnightQuestWeapons;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
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
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class KnightQuestNeoForgePlatform implements KnightQuestPlatform {

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
        return KnightLibBlocks.GREAT_CHALICE::get;
    }

    @Override
    public Supplier<ParticleOptions> getStartsetParticle() {
        return KnightLibParticles.STARSET_PARTICLE::get;
    }

    @Override
    public Supplier<Item> getPaladinSword() {
        return KnightQuestWeapons.PALADIN_SWORD;
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        return CreativeModeTab.builder();
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return KnightQuest.ITEMS.register(id, item);
    }

    @Override
    public <T extends Mob> Supplier<Item> registerSpawnEggItem(String id, Supplier<EntityType<T>> entity, int primaryEggColour, int secondaryEggColour) {
        return registerItem(id, () -> new DeferredSpawnEggItem(entity, primaryEggColour, secondaryEggColour, new Item.Properties()));
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
    public <T extends Item> Supplier<T> registerGeoArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, boolean containsExtraTooltip, Item.Properties properties, int durabilityAmount) {
        if (containsExtraTooltip)
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new GeoItemArmor(armorMaterial, armorType, properties.durability(armorType.getDurability(durabilityAmount)), id, containsTooltip) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
        else
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new GeoItemArmor(armorMaterial, armorType, properties.durability(armorType.getDurability(durabilityAmount)), id, containsTooltip));
    }

    @Override
    public <T extends Item> Supplier<T> registerSwordItem(String id, KQItemMaterials itemMaterial, Item.Properties properties, float speedMalus, boolean containsTooltip) {
        if (containsTooltip)
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new SwordItem(itemMaterial, properties.attributes(SwordItem.createAttributes(itemMaterial, 4, speedMalus))) {
                @Override
                public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable(TOOLTIP_ITEM_PATH + id));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
        else
            return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new SwordItem(itemMaterial, properties.attributes(SwordItem.createAttributes(itemMaterial, 4, speedMalus))));
    }

    @Override
    public <T extends Item> Supplier<T> registerAxeItem(String id, KQItemMaterials itemMaterial, Item.Properties properties, float damageBoost, float speedMalus) {
        return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new AxeItem(itemMaterial, properties.attributes(AxeItem.createAttributes(itemMaterial, damageBoost, speedMalus))));
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(String id, Supplier<EntityType<T>> entity) {
        return KnightQuest.ENTITY.register(id, entity);
    }

    @Override
    public <T extends Item> Supplier<T> registerArmorItem(String id, Holder<ArmorMaterial> armorMaterial, ArmorItem.Type armorType, boolean containsTooltip, Item.Properties properties, int durabilityAmount) {
        return (Supplier<T>) KnightQuest.ITEMS.register(id, () -> new KQArmorItem(armorMaterial, armorType, properties, containsTooltip));
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return KnightQuest.CREATIVE_TABS.register(id, tab);
    }

    @Override
    public <T extends Item> Supplier<T> registerMusicDisc(String id, int signal, Supplier<SoundEvent> soundEvent, Item.Properties properties, int length) {
        return (Supplier<T>) registerItem(id, () -> new Item(properties.jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(KnightQuest.MOD_ID, "the_architect_of_chaos")))));
    }

    @Override
    public <T extends ArmorMaterial> Holder<T> registerArmorMaterial(String id, Supplier<T> armorMaterial) {
        return (Holder<T>) KnightQuest.ARMOR_MATERIALS.register(id, armorMaterial);
    }

}
