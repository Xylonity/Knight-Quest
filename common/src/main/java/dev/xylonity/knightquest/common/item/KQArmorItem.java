package dev.xylonity.knightquest.common.item;

import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class KQArmorItem extends ArmorItem {

    /**
     * Dual Hashmap that inherits old declared effects when a new set is equipped, preventing
     * incompatibility when either post-indirect or pre-direct status effects are applied.
     */

    public static final Map<UUID, Map<Holder<ArmorMaterial>, Boolean>> effectAppliedByArmorMap = new HashMap<>();

    private final String bonusTooltip;
    private final boolean hasTooltip;

    public KQArmorItem(Holder<ArmorMaterial> material, Type type, Properties settings, boolean hasTooltip) {
        super(material, type, settings);
        this.bonusTooltip = KQArmorMaterials.getKeyNameFromMaterial(material);
        this.hasTooltip = hasTooltip;
    }

    /**
     * Enables or disables tooltips based on the "enable" boolean value in the configuration file
     */

    public enum ArmorSet {
        DEEPSLATE(KQConfigValues.DEEPSLATESET.getBoolean()),
        EVOKER(KQConfigValues.EVOKERSET.getBoolean()),
        SQUIRE(KQConfigValues.SQUIRESET.getBoolean()),
        BLAZE(KQConfigValues.BLAZESET.getBoolean()),
        DRAGON(KQConfigValues.DRAGONSET.getBoolean()),
        BAMBOO_GREEN(KQConfigValues.BAMBOOSET_GREEN.getBoolean()),
        SHINOBI(KQConfigValues.SHINOBI.getBoolean()),
        BAMBOO(KQConfigValues.BAMBOOSET.getBoolean()),
        PATH(KQConfigValues.PATHSET.getBoolean()),
        BOW(KQConfigValues.BOWSET.getBoolean()),
        BAT(KQConfigValues.BATSET.getBoolean()),
        SHIELD(KQConfigValues.SHIELDSET.getBoolean()),
        PHANTOM(KQConfigValues.PHANTOMSET.getBoolean()),
        HORN(KQConfigValues.HORNSET.getBoolean()),
        SEA(KQConfigValues.SEASET.getBoolean()),
        PIRATE(KQConfigValues.PIRATESET.getBoolean()),
        SPIDER(KQConfigValues.SPIDERSET.getBoolean()),
        NETHER(KQConfigValues.NETHERSET.getBoolean()),
        SKULK(KQConfigValues.SKULK.getBoolean()),
        STRAWHAT(KQConfigValues.STRAWHATSET.getBoolean()),
        ENDERMAN(KQConfigValues.ENDERMANSET.getBoolean()),
        VETERAN(KQConfigValues.VETERANSET.getBoolean()),
        FORZE(KQConfigValues.FORZESET.getBoolean()),
        CREEPER(KQConfigValues.CREEPERSET.getBoolean()),
        POLAR(KQConfigValues.POLAR.getBoolean()),
        SILVER(KQConfigValues.SILVERSET.getBoolean()),
        HOLLOW(KQConfigValues.HOLLOWSET.getBoolean()),
        WITHER(KQConfigValues.WITHERSET.getBoolean()),
        APPLE(KQConfigValues.APPLE_SET.getBoolean()),
        CONQUISTADOR(KQConfigValues.CONQUISTADORSET.getBoolean()),
        WITCH(KQConfigValues.WITCH.getBoolean()),
        TENGU(KQConfigValues.TENGU_HELMET.getBoolean()),
        HUSK(KQConfigValues.HUSKSET.getBoolean()),
        BAMBOO_BLUE(KQConfigValues.BAMBOOSET_BLUE.getBoolean()),
        WARLORD(KQConfigValues.WARLORDSET.getBoolean()),
        ZOMBIE(KQConfigValues.ZOMBIESET.getBoolean()),
        SILVERFISH(KQConfigValues.SILVERFISHSET.getBoolean()),
        SKELETON(KQConfigValues.SKELETONSET.getBoolean());

        private final Boolean configValue;

        ArmorSet(Boolean configValue) {
            this.configValue = configValue;
        }

        public boolean isEnabled() {
            return this.configValue;
        }
    }

    private boolean isArmorSetConfigEnabled(String bonusTooltip) {
        try {
            ArmorSet armorSet = ArmorSet.valueOf(bonusTooltip.toUpperCase());
            return armorSet.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Adds `passive ability` tooltips to each KQArmorItem corresponding to each registered armor set. Uses the
     * same name as the material registered for each individual armor set, as they are essentially identical.
     */

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        System.out.println("se cumple?" + (hasTooltip && isArmorSetConfigEnabled(bonusTooltip)));
        if (hasTooltip && isArmorSetConfigEnabled(bonusTooltip))
            if (!Objects.equals(bonusTooltip, "chainmail") && !Objects.equals(bonusTooltip, "tengu")) {
                tooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_set_bonus"));
                tooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
            } else if (Objects.equals(bonusTooltip, "tengu")) {
                tooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_helmet_bonus"));
                tooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
            }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    /**
     * Declaration of static effects for certain armors.
     */

    private static final MobEffectInstance SHIELD_ARMOR = new MobEffectInstance (MobEffects.DAMAGE_RESISTANCE, -1, 0, false, false, true);
    private static final MobEffectInstance BAT_ARMOR = new MobEffectInstance (MobEffects.NIGHT_VISION, -1, 0, false, false, true);
    private static final MobEffectInstance PATH_ARMOR = new MobEffectInstance (MobEffects.MOVEMENT_SPEED, -1, 1, false, true, true);
    private static final MobEffectInstance BOW_ARMOR = new MobEffectInstance (MobEffects.MOVEMENT_SPEED, -1, 1, false, false, true);
    private static final MobEffectInstance HORN_ARMOR = new MobEffectInstance (MobEffects.DAMAGE_BOOST, 400, 1, false, false, true);
    private static final MobEffectInstance SEA_ARMOR = new MobEffectInstance (MobEffects.DOLPHINS_GRACE, -1, 0, false, false, true);
    private static final MobEffectInstance PIRATE_ARMOR = new MobEffectInstance (MobEffects.LUCK, -1, 0, false, false, true);
    private static final MobEffectInstance SPIDER_ARMOR = new MobEffectInstance (MobEffects.JUMP, -1, 1, false, false, false);
    private static final MobEffectInstance PHANTOM_ARMOR =  new MobEffectInstance (MobEffects.MOVEMENT_SPEED, -1, 0, false, false, true);
    private static final MobEffectInstance NETHER_ARMOR =  new MobEffectInstance (MobEffects.FIRE_RESISTANCE, -1, 0, false, false, true);
    private static final MobEffectInstance SKULK_ARMOR =  new MobEffectInstance (MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance STRAWHAT_ARMOR =  new MobEffectInstance (MobEffects.WATER_BREATHING, -1, 0, false, false, true);

    /**
     * Ticks the inventory of each player.
     */

    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide() && entity instanceof Player player) {

            UUID playerUUID = player.getUUID();

            if (KQConfigValues.PATHSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.PATHSET) && level.isDay()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PATHSET, false))) {
                        player.addEffect(PATH_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PATHSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PATHSET, false))) {
                        player.removeEffect(MobEffects.MOVEMENT_SPEED);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PATHSET, false);
                    }
                }

            if (KQConfigValues.BOWSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BOWSET) && player.getMainHandItem().getItem() instanceof ProjectileWeaponItem) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BOWSET, false))) {
                        player.addEffect(BOW_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BOWSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BOWSET, false))) {
                        player.removeEffect(MobEffects.MOVEMENT_SPEED);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BOWSET, false);
                    }
                }

            if (KQConfigValues.BATSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BATSET) && level.isNight()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BATSET, false))) {
                        player.addEffect(BAT_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BATSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BATSET, false))) {
                        player.removeEffect(MobEffects.NIGHT_VISION);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BATSET, false);
                    }
                }

            if (KQConfigValues.SHIELDSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SHIELDSET)) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SHIELDSET, false))) {
                        player.addEffect(SHIELD_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SHIELDSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SHIELDSET, false))) {
                        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SHIELDSET, false);
                    }
                }

            if (KQConfigValues.PHANTOMSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.PHANTOMSET) && level.isNight()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PHANTOMSET, false))) {
                        player.addEffect(PHANTOM_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PHANTOMSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PHANTOMSET, false))) {
                        player.removeEffect(MobEffects.MOVEMENT_SPEED);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PHANTOMSET, false);
                    }
                }

            if (KQConfigValues.HORNSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.HORNSET) && player.getLastAttacker() != null) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HORNSET, false))) {
                        player.addEffect(HORN_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.HORNSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HORNSET, false))) {
                        player.removeEffect(MobEffects.DAMAGE_BOOST);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.HORNSET, false);
                    }
                }

            if (KQConfigValues.SEASET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SEASET) && player.isUnderWater()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SEASET, false))) {
                        player.addEffect(SEA_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SEASET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SEASET, false))) {
                        player.removeEffect(MobEffects.DOLPHINS_GRACE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SEASET, false);
                    }
                }

            if (KQConfigValues.PIRATESET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.PIRATESET)) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PIRATESET, false))) {
                        player.addEffect(PIRATE_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PIRATESET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PIRATESET, false))) {
                        player.removeEffect(MobEffects.LUCK);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PIRATESET, false);
                    }
                }

            if (KQConfigValues.SPIDERSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SPIDERSET) && player.isShiftKeyDown()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SPIDERSET, false))) {
                        player.addEffect(SPIDER_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SPIDERSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SPIDERSET, false))) {
                        player.removeEffect(MobEffects.JUMP);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SPIDERSET, false);
                    }
                }

            if (KQConfigValues.NETHERSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.NETHERSET)) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.NETHERSET, false))) {
                        player.addEffect(NETHER_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.NETHERSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.NETHERSET, false))) {
                        player.removeEffect(MobEffects.FIRE_RESISTANCE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.NETHERSET, false);
                    }
                }

            if (KQConfigValues.SKULK.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SKULK) && player.level().getMaxLocalRawBrightness(player.blockPosition()) <= KQConfigValues.SKULK_MAX_LIGHT_LEVEL.getInt()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SKULK, false))) {
                        player.addEffect(SKULK_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SKULK, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SKULK, false))) {
                        player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SKULK, false);
                    }
                }

            if (KQConfigValues.STRAWHATSET.getBoolean())
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.STRAWHATSET) && player.isUnderWater()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.STRAWHATSET, false))) {
                        player.addEffect(STRAWHAT_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.STRAWHATSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.STRAWHATSET, false))) {
                        player.removeEffect(MobEffects.WATER_BREATHING);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.STRAWHATSET, false);
                    }
                }

        }

        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

}