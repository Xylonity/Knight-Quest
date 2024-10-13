package dev.xylonity.knightquest.common.item;

import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class KQArmorItem extends ArmorItem {

    /**
     * Dual Hashmap that inherits old declared effects when a new set is equipped, preventing
     * incompatibility when either post-indirect or pre-direct status effects are applied.
     */

    private static final Map<UUID, Map<KQArmorMaterials, Boolean>> effectAppliedByArmorMap = new HashMap<>();

    private final String bonusTooltip;
    private final boolean hasTooltip;

    public KQArmorItem(KQArmorMaterials material, Type type, Properties settings, boolean hasTooltip) {
        super(material, type, settings);
        this.bonusTooltip = material.getKeyName();
        this.hasTooltip = hasTooltip;
    }

    /**
     * Enables or disables tooltips based on the "enable" boolean value in the configuration file
     */

    public enum ArmorSet {
        DEEPSLATE(KQConfigValues.DEEPSLATESET),
        EVOKER(KQConfigValues.EVOKERSET),
        SQUIRE(KQConfigValues.SQUIRESET),
        BLAZE(KQConfigValues.BLAZESET),
        DRAGON(KQConfigValues.DRAGONSET),
        BAMBOO_GREEN(KQConfigValues.BAMBOOSET_GREEN),
        SHINOBI(KQConfigValues.SHINOBI),
        BAMBOO(KQConfigValues.BAMBOOSET),
        PATH(KQConfigValues.PATHSET),
        BOW(KQConfigValues.BOWSET),
        BAT(KQConfigValues.BATSET),
        SHIELD(KQConfigValues.SHIELDSET),
        PHANTOM(KQConfigValues.PHANTOMSET),
        HORN(KQConfigValues.HORNSET),
        SEA(KQConfigValues.SEASET),
        PIRATE(KQConfigValues.PIRATESET),
        SPIDER(KQConfigValues.SPIDERSET),
        NETHER(KQConfigValues.NETHERSET),
        SKULK(KQConfigValues.SKULK),
        STRAWHAT(KQConfigValues.STRAWHATSET),
        ENDERMAN(KQConfigValues.ENDERMANSET),
        VETERAN(KQConfigValues.VETERANSET),
        FORZE(KQConfigValues.FORZESET),
        CREEPER(KQConfigValues.CREEPERSET),
        POLAR(KQConfigValues.POLAR),
        SILVER(KQConfigValues.SILVERSET),
        HOLLOW(KQConfigValues.HOLLOWSET),
        WITHER(KQConfigValues.WITHERSET),
        APPLE(KQConfigValues.APPLE_SET),
        CONQUISTADOR(KQConfigValues.CONQUISTADORSET),
        WITCH(KQConfigValues.WITCH),
        TENGU(KQConfigValues.TENGU_HELMET),
        HUSK(KQConfigValues.HUSKSET),
        BAMBOO_BLUE(KQConfigValues.BAMBOOSET_BLUE),
        WARLORD(KQConfigValues.WARLORDSET),
        ZOMBIE(KQConfigValues.ZOMBIESET),
        SILVERFISH(KQConfigValues.SILVERFISHSET),
        SKELETON(KQConfigValues.SKELETONSET);

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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag context) {
        if (hasTooltip && isArmorSetConfigEnabled(bonusTooltip))
            if (!Objects.equals(bonusTooltip, "chainmail") && !Objects.equals(bonusTooltip, "tengu")) {
                tooltip.add(Component.translatable("tooltip.item.knightquest.full_set_bonus"));
                tooltip.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
            } else if (Objects.equals(bonusTooltip, "tengu")) {
                tooltip.add(Component.translatable("tooltip.item.knightquest.full_helmet_bonus"));
                tooltip.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
            }
        super.appendHoverText(stack, world, tooltip, context);
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
    private static final MobEffectInstance HUSK_ARMOR =  new MobEffectInstance (MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance BAMBOO_BLUE =  new MobEffectInstance (MobEffects.MOVEMENT_SPEED, -1, 1, false, false, true);
    private static final MobEffectInstance SILVERFISH_ARMOR =  new MobEffectInstance (MobEffects.DIG_SPEED, -1, 0, false, false, true);
    private static final MobEffectInstance SKULK_ARMOR =  new MobEffectInstance (MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance STRAWHAT_ARMOR =  new MobEffectInstance (MobEffects.WATER_BREATHING, -1, 0, false, false, true);

    /**
     * Ticks the inventory of each player.
     */

    @Override
    public void inventoryTick(@NotNull ItemStack stack, Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide() && entity instanceof Player player) {

            UUID playerUUID = player.getUUID();

            if (KQConfigValues.PATHSET)
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

            if (KQConfigValues.BOWSET)
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

            if (KQConfigValues.BATSET)
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

            if (KQConfigValues.SHIELDSET)
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

            if (KQConfigValues.PHANTOMSET)
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

            if (KQConfigValues.HORNSET)
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

            if (KQConfigValues.SEASET)
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

            if (KQConfigValues.PIRATESET)
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

            if (KQConfigValues.SPIDERSET)
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

            if (KQConfigValues.NETHERSET)
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

            if (KQConfigValues.SKULK)
                if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SKULK) && player.level().getMaxLocalRawBrightness(player.blockPosition()) <= KQConfigValues.SKULK_MAX_LIGHT_LEVEL) {
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

            if (KQConfigValues.STRAWHATSET)
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

    private static boolean isTeleportPositionValid(Level level, BlockPos pos) {
        return !level.getBlockState(pos.below()).isAir() && level.getBlockState(pos).isAir() && level.getBlockState(pos.above()).isAir();
    }

    public static class OnHurtPlayerHandler implements ServerLivingEntityEvents.AllowDamage {

        private static final ThreadLocal<Boolean> isProcessingDamage = ThreadLocal.withInitial(() -> false);

        @Override
        public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {

            if (isProcessingDamage.get()) {
                return true;
            }

            // Victim: Player (Source ~-> Attacker)

            if (entity instanceof Player player) {

                // EVOKERSET
                if (KQConfigValues.EVOKERSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.EVOKERSET)) {
                        RandomSource random = entity.level().getRandom();
                        if (source.getEntity() instanceof LivingEntity && random.nextFloat() < KQConfigValues.EVOKER_DARKNESS_CHANCE)
                            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 120, 0, false, false, true));
                    }

                // SQUIRESET
                if (KQConfigValues.SQUIRESET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SQUIRESET)) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1, false, false, false));
                    }

                // BLAZESET
                if (KQConfigValues.BLAZESET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BLAZESET)) {
                        RandomSource random = entity.level().getRandom();
                        if (source.getEntity() != null && random.nextFloat() < KQConfigValues.BLAZE_FIRE_CHANCE)
                            source.getEntity().setSecondsOnFire(random.nextIntBetweenInclusive(KQConfigValues.BLAZE_FIRE_DURATION_MIN, KQConfigValues.BLAZE_FIRE_DURATION_MAX));
                    }

                // DRAGONSET
                if (KQConfigValues.DRAGONSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.DRAGONSET))
                        if (source.is(DamageTypes.DRAGON_BREATH))
                            return false;

                // BAMBOOSET_GREEN
                if (KQConfigValues.BAMBOOSET_GREEN)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                        if (player.hasEffect(MobEffects.POISON) && (source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC))) {
                            player.removeEffect(MobEffects.POISON);
                            return false;
                        }

                // SHINOBI
                if (KQConfigValues.SHINOBI)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SHINOBI) && source.getEntity() != null) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 1, false, false, true));
                    }

                // BAMBOOSET
                if (KQConfigValues.BAMBOOSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BAMBOOSET) && source.is(DamageTypes.FALL)) {

                        ServerPlayer serverPlayer = (ServerPlayer) player;

                        int particleCount = 80;
                        double particleRadius = 1.2;

                        for (int i = 0; i < particleCount; i++) {
                            double angleOffset = (2 * Math.PI / particleCount) * i;
                            double xParticleOffset = particleRadius * Math.cos(angleOffset);
                            double zParticleOffset = particleRadius * Math.sin(angleOffset);

                            serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                                    true,
                                    player.getX() + xParticleOffset,
                                    player.getY() + 0.1,
                                    player.getZ() + zParticleOffset,
                                    0.2f,
                                    0.05f,
                                    0.2f,
                                    0.0f,
                                    1
                            ));
                        }

                        Class<? extends Entity> classToPush = KQConfigValues.BAMBOOSET_PUSH_PLAYERS ? Entity.class : Player.class;

                        player.level().getEntitiesOfClass(classToPush, player.getBoundingBox().inflate(3.5)).forEach(entity1 -> {
                            Vec3 direction = entity1.position().subtract(player.position()).normalize().scale(amount * 0.5);
                            entity1.push(direction.x, direction.y + 0.5, direction.z);
                        });

                    }

                // ENDERMANSET
                if (KQConfigValues.ENDERMANSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.ENDERMANSET) && source.getEntity() != null) {

                        RandomSource random = entity.level().getRandom();
                        if (random.nextFloat() < 0.3) {
                            int radius = KQConfigValues.TELEPORT_RADIUS_ENDERMANSET;
                            BlockPos playerPos = player.blockPosition();
                            List<BlockPos> validPositions = new ArrayList<>();

                            for (int x = -radius; x <= radius; x++) {
                                for (int y = -radius; y <= radius; y++) {
                                    for (int z = -radius; z <= radius; z++) {
                                        BlockPos targetPos = playerPos.offset(x, y, z);

                                        if (isTeleportPositionValid(player.level(), targetPos)) {
                                            validPositions.add(targetPos);
                                        }
                                    }
                                }
                            }

                            if (!validPositions.isEmpty()) {
                                BlockPos randomPos = validPositions.get(random.nextInt(validPositions.size()));

                                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                                player.teleportTo(randomPos.getX(), randomPos.getY(), randomPos.getZ());

                                return false;
                            }

                        }
                    }

                // VETERANSET
                if (KQConfigValues.VETERANSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.VETERANSET) && player.getHealth() < player.getMaxHealth() * 0.5) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false, true));
                    }

                // FORZESET
                if (KQConfigValues.FORZESET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.FORZESET)) {
                        RandomSource random = entity.level().getRandom();
                        if (source.getEntity() != null && random.nextFloat() < KQConfigValues.FORZESET_DEFLECT_CHANCE) {
                            isProcessingDamage.set(true);
                            try {
                                source.getEntity().hurt(source, amount * (float) KQConfigValues.FORZESET_DEFLECT_DAMAGE);
                            } finally {
                                isProcessingDamage.set(false);
                            }
                        }
                    }

                // POLAR
                if (KQConfigValues.POLAR)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.POLAR)) {
                        if (source.getEntity() != null && (source.is(DamageTypes.FREEZE)))
                            return false;
                    }

            }

            // Attacker: Player

            if (source.getEntity() instanceof Player player) {

                // SILVERSET
                if (KQConfigValues.SILVERSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SILVERSET) && player.level().isNight()) {
                        RandomSource random = entity.level().getRandom();
                        if (random.nextFloat() < KQConfigValues.SILVERSET_BURN_CHANCE) {
                            entity.setSecondsOnFire(random.nextIntBetweenInclusive(2, 8));
                        }
                    }

                // HOLLOWSET
                if (KQConfigValues.HOLLOWSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.HOLLOWSET) && source.getEntity() instanceof LivingEntity livingEntity) {
                        isProcessingDamage.set(true);
                        try {
                            player.heal(Math.min((float) (amount * KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER), livingEntity.getHealth()));
                        } finally {
                            isProcessingDamage.set(false);
                        }
                    }

                // WITHERSET
                if (KQConfigValues.WITHERSET)
                    if (source.is(DamageTypes.ARROW) && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.WITHERSET)) {
                        RandomSource random = entity.level().getRandom();
                        if (random.nextFloat() < KQConfigValues.WITHERSET_WITHER_CHANCE)
                            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 0, false, false, false));
                    }
            }

            return true;

        }
    }

    public static class OnEntityJoinWorldEvent implements ServerEntityEvents.Load {

        @Override
        public void onLoad(Entity entity, ServerLevel world) {
            if (KQConfigValues.SKELETONSET)
                if (entity instanceof AbstractArrow arrow)
                    if (arrow.getOwner() instanceof Player player && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SKELETONSET))
                        arrow.setPierceLevel((byte) 5);
        }

    }

    public static class OnEntityDeathWorldEvent implements ServerLivingEntityEvents.AfterDeath {

        @Override
        public void afterDeath(LivingEntity entity, DamageSource damageSource) {
            if (entity != null && damageSource.getEntity() instanceof Player player) {

                if (KQConfigValues.CONQUISTADORSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.CONQUISTADORSET)) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, true, true));
                    }

                if (KQConfigValues.WITCH)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.WITCH)) {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0, false, true, true));
                    }
            }
        }

    }

    @Environment(EnvType.CLIENT)
    public static class ClientEventHandlers {

        public static void registerClientEvents() {
            ClientTickEvents.END_CLIENT_TICK.register(ClientEventHandlers::onClientTick);
        }

        /**
         * Manages individual jump states for each player on the server, ensuring that there will
         * not be infinite jumps when there are more than two players present.
         */

        private static final Map<UUID, Boolean> doubleJumpStates = new HashMap<>();

        private static void onClientTick(Minecraft client) {
            LocalPlayer player = client.player;
            if (player == null) return;

            if (KQConfigValues.TENGU_HELMET && player.getInventory().getArmor(3).getItem() == KnightQuestItems.TENGU_HELMET.get()) {

                boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUUID(), true);

                if (!player.onGround() && player.getDeltaMovement().y < 0 && canDoubleJump) {
                    if (client.options.keyJump.isDown()) {
                        handleClientSideDoubleJump(player);
                    }
                }

                if (player.onGround()) {
                    doubleJumpStates.put(player.getUUID(), true);
                }
            }
        }

        private static void handleClientSideDoubleJump(LocalPlayer player) {
            UUID playerUUID = player.getUUID();
            boolean canDoubleJump = doubleJumpStates.getOrDefault(playerUUID, true);

            if (canDoubleJump) {
                doubleJumpStates.put(playerUUID, false);

                for (int i = 0; i < 360; i += 60) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = player.getX() + 0.4 * Math.cos(angleRadians);
                    double particleZ = player.getZ() + 0.4 * Math.sin(angleRadians);

                    player.level().addParticle(ParticleTypes.CLOUD, particleX, player.getY(), particleZ, 0d, 0.35d, 0d);
                }

                player.jumpFromGround();
            }
        }
    }

    public static class OnEntityTickEvent implements ServerTickEvents.EndTick {

        @Override
        public void onEndTick(MinecraftServer server) {

            for (ServerPlayer player : server.getPlayerList().getPlayers()) {

                if (KQConfigValues.HUSKSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.HUSKSET) && (player.level().getBiome(player.blockPosition()).is(Biomes.DESERT)
                            || player.level().getBiome(player.blockPosition()).is(Biomes.BADLANDS)
                            || player.level().getBiome(player.blockPosition()).is(Biomes.BEACH))) {
                        if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUUID(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HUSKSET, false))) {
                            player.addEffect(HUSK_ARMOR);
                            effectAppliedByArmorMap.get(player.getUUID()).put(KQArmorMaterials.HUSKSET, true);
                        }
                    } else {
                        if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUUID(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HUSKSET, false))) {
                            player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
                            effectAppliedByArmorMap.get(player.getUUID()).put(KQArmorMaterials.HUSKSET, false);
                        }
                    }

                if (KQConfigValues.BAMBOOSET_BLUE)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_BLUE) && (player.level().getBiome(player.blockPosition()).is(Biomes.JUNGLE)
                            || player.level().getBiome(player.blockPosition()).is(Biomes.BAMBOO_JUNGLE)
                            || player.level().getBiome(player.blockPosition()).is(Biomes.SPARSE_JUNGLE))) {
                        if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUUID(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BAMBOOSET_BLUE, false))) {
                            player.addEffect(BAMBOO_BLUE);
                            effectAppliedByArmorMap.get(player.getUUID()).put(KQArmorMaterials.BAMBOOSET_BLUE, true);
                        }
                    } else {
                        if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUUID(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BAMBOOSET_BLUE, false))) {
                            player.removeEffect(MobEffects.MOVEMENT_SPEED);
                            effectAppliedByArmorMap.get(player.getUUID()).put(KQArmorMaterials.BAMBOOSET_BLUE, false);
                        }
                    }

                if (KQConfigValues.WARLORDSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.WARLORDSET)) {
                        for (Entity entity : player.level().getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(KQConfigValues.WARLORD_SET_EFFECT_RADIUS))) {
                            if (KQConfigValues.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF) {
                                if (entity instanceof Player nearbyPlayer) {
                                    nearbyPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0, false, false, true));
                                }
                            } else {
                                if (entity instanceof Player nearbyPlayer && entity != player) {
                                    nearbyPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0, false, false, true));
                                }
                            }
                        }
                    }

                if (!player.level().isClientSide()) {

                    if (KQConfigValues.ZOMBIESET)
                        if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.ZOMBIESET) && player.level().isNight()) {
                            if (player.tickCount % KQConfigValues.ZOMBIESET_HEALING_TICKS == 0) {
                                player.heal((float) KQConfigValues.ZOMBIESET_HEALING_AMOUNT);
                            }
                        }

                    if (KQConfigValues.APPLE_SET)
                        if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.APPLE_SET)) {
                            if (player.tickCount % 400 == 0) {
                                player.heal(1.0F);
                            }
                        }

                }

                if (KQConfigValues.POLAR)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.POLAR)) {
                        player.setTicksFrozen(0);
                    }

                if (KQConfigValues.SILVERFISHSET)
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SILVERFISHSET) && player.getY() < KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT) {
                        if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUUID(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SILVERFISHSET, false))) {
                            player.addEffect(SILVERFISH_ARMOR);
                            effectAppliedByArmorMap.get(player.getUUID()).put(KQArmorMaterials.SILVERFISHSET, true);
                        }
                    } else {
                        if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUUID(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SILVERFISHSET, false))) {
                            player.removeEffect(MobEffects.DIG_SPEED);
                            effectAppliedByArmorMap.get(player.getUUID()).put(KQArmorMaterials.SILVERFISHSET, false);
                        }
                    }

            }
        }

    }

}