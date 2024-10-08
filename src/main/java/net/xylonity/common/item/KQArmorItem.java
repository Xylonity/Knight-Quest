package net.xylonity.common.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import net.xylonity.common.api.FCAPChecker;
import net.xylonity.common.material.KQArmorMaterials;
import net.xylonity.config.values.KQConfigValues;
import net.xylonity.registry.KnightQuestItems;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class KQArmorItem extends ArmorItem {

    /**
     * Dual Hashmap that inherits old declared effects when a new set is equipped, preventing
     * incompatibility when either post-indirect or pre-direct status effects are applied.
     */

    private static final Map<UUID, Map<RegistryEntry<ArmorMaterial>, Boolean>> effectAppliedByArmorMap = new HashMap<>();

    private final String bonusTooltip;

    public KQArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings.maxCount(1));
        this.bonusTooltip = KQArmorMaterials.getKeyNameFromMaterial(material);
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
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isArmorSetConfigEnabled(bonusTooltip))
            if (!Objects.equals(bonusTooltip, "chainmail") && !Objects.equals(bonusTooltip, "tengu")) {
                tooltip.add(Text.translatable("tooltip.item.knightquest.full_set_bonus"));
                tooltip.add(Text.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
            } else if (Objects.equals(bonusTooltip, "tengu")) {
                tooltip.add(Text.translatable("tooltip.item.knightquest.full_helmet_bonus"));
                tooltip.add(Text.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
            }

        super.appendTooltip(stack, context, tooltip, type);
    }

    /**
     * Declaration of static effects for certain armors.
     */

    private static final StatusEffectInstance SHIELD_ARMOR = new StatusEffectInstance(StatusEffects.RESISTANCE, -1, 0, false, false, true);
    private static final StatusEffectInstance BAT_ARMOR = new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false, true);
    private static final StatusEffectInstance PATH_ARMOR = new StatusEffectInstance(StatusEffects.SPEED, -1, 1, false, true, true);
    private static final StatusEffectInstance BOW_ARMOR = new StatusEffectInstance(StatusEffects.SPEED, -1, 1, false, false, true);
    private static final StatusEffectInstance HORN_ARMOR = new StatusEffectInstance(StatusEffects.STRENGTH, 400, 1, false, false, true);
    private static final StatusEffectInstance SEA_ARMOR = new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, -1, 0, false, false, true);
    private static final StatusEffectInstance PIRATE_ARMOR = new StatusEffectInstance(StatusEffects.LUCK, -1, 0, false, false, true);
    private static final StatusEffectInstance SPIDER_ARMOR = new StatusEffectInstance(StatusEffects.JUMP_BOOST, -1, 1, false, false, false);
    private static final StatusEffectInstance PHANTOM_ARMOR =  new StatusEffectInstance(StatusEffects.SPEED, -1, 0, false, false, true);
    private static final StatusEffectInstance NETHER_ARMOR =  new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, -1, 0, false, false, true);
    private static final StatusEffectInstance HUSK_ARMOR =  new StatusEffectInstance(StatusEffects.RESISTANCE, -1, 1, false, false, true);
    private static final StatusEffectInstance BAMBOO_BLUE =  new StatusEffectInstance(StatusEffects.SPEED, -1, 1, false, false, true);
    private static final StatusEffectInstance SILVERFISH_ARMOR =  new StatusEffectInstance(StatusEffects.HASTE, -1, 0, false, false, true);
    private static final StatusEffectInstance SKULK_ARMOR =  new StatusEffectInstance(StatusEffects.RESISTANCE, -1, 1, false, false, true);
    private static final StatusEffectInstance STRAWHAT_ARMOR =  new StatusEffectInstance(StatusEffects.WATER_BREATHING, -1, 0, false, false, true);

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {

            UUID playerUUID = player.getUuid();

            if (KQConfigValues.PATHSET)
                if (hasFullSetOn(player, KQArmorMaterials.PATHSET) && player.isSneaking()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PATHSET, false))) {
                        player.addStatusEffect(PATH_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PATHSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PATHSET, false))) {
                        player.removeStatusEffect(StatusEffects.SPEED);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PATHSET, false);
                    }
                }

            if (KQConfigValues.BOWSET)
                if (hasFullSetOn(player, KQArmorMaterials.BOWSET) && player.getMainHandStack().getItem() instanceof RangedWeaponItem) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BOWSET, false))) {
                        player.addStatusEffect(BOW_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BOWSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BOWSET, false))) {
                        player.removeStatusEffect(StatusEffects.SPEED);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BOWSET, false);
                    }
                }

            if (KQConfigValues.BATSET)
                if (hasFullSetOn(player, KQArmorMaterials.BATSET) && world.isNight()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BATSET, false))) {
                        player.addStatusEffect(BAT_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BATSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BATSET, false))) {
                        player.removeStatusEffect(StatusEffects.NIGHT_VISION);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.BATSET, false);
                    }
                }

            if (KQConfigValues.SHIELDSET)
                if (hasFullSetOn(player, KQArmorMaterials.SHIELDSET)) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SHIELDSET, false))) {
                        player.addStatusEffect(SHIELD_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SHIELDSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SHIELDSET, false))) {
                        player.removeStatusEffect(StatusEffects.RESISTANCE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SHIELDSET, false);
                    }
                }

            if (KQConfigValues.PHANTOMSET)
                if (hasFullSetOn(player, KQArmorMaterials.PHANTOMSET) && world.isNight()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PHANTOMSET, false))) {
                        player.addStatusEffect(PHANTOM_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PHANTOMSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PHANTOMSET, false))) {
                        player.removeStatusEffect(StatusEffects.SPEED);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PHANTOMSET, false);
                    }
                }

            if (KQConfigValues.HORNSET)
                if (hasFullSetOn(player, KQArmorMaterials.HORNSET) && player.getLastAttacker() != null) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HORNSET, false))) {
                        player.addStatusEffect(HORN_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.HORNSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HORNSET, false))) {
                        player.removeStatusEffect(StatusEffects.STRENGTH);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.HORNSET, false);
                    }
                }

            if (KQConfigValues.SEASET)
                if (hasFullSetOn(player, KQArmorMaterials.SEASET) && player.isSubmergedInWater()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SEASET, false))) {
                        player.addStatusEffect(SEA_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SEASET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SEASET, false))) {
                        player.removeStatusEffect(StatusEffects.DOLPHINS_GRACE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SEASET, false);
                    }
                }

            if (KQConfigValues.PIRATESET)
                if (hasFullSetOn(player, KQArmorMaterials.PIRATESET)) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PIRATESET, false))) {
                        player.addStatusEffect(PIRATE_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PIRATESET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PIRATESET, false))) {
                        player.removeStatusEffect(StatusEffects.LUCK);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PIRATESET, false);
                    }
                }

            if (KQConfigValues.SPIDERSET)
                if (hasFullSetOn(player, KQArmorMaterials.SPIDERSET) && player.isSneaking()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SPIDERSET, false))) {
                        player.addStatusEffect(SPIDER_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SPIDERSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SPIDERSET, false))) {
                        player.removeStatusEffect(StatusEffects.JUMP_BOOST);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SPIDERSET, false);
                    }
                }

            if (KQConfigValues.NETHERSET)
                if (hasFullSetOn(player, KQArmorMaterials.NETHERSET)) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.NETHERSET, false))) {
                        player.addStatusEffect(NETHER_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.NETHERSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.NETHERSET, false))) {
                        player.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.NETHERSET, false);
                    }
                }

            if (KQConfigValues.SKULK)
                if (hasFullSetOn(player, KQArmorMaterials.SKULK) && player.getWorld().getLightLevel(player.getBlockPos()) <= KQConfigValues.SKULK_MAX_LIGHT_LEVEL) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SKULK, false))) {
                        player.addStatusEffect(SKULK_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SKULK, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SKULK, false))) {
                        player.removeStatusEffect(StatusEffects.RESISTANCE);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.SKULK, false);
                    }
                }

            if (KQConfigValues.STRAWHATSET)
                if (hasFullSetOn(player, KQArmorMaterials.STRAWHATSET) && player.isSubmergedInWater()) {
                    if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.STRAWHATSET, false))) {
                        player.addStatusEffect(STRAWHAT_ARMOR);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.STRAWHATSET, true);
                    }
                } else {
                    if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.STRAWHATSET, false))) {
                        player.removeStatusEffect(StatusEffects.WATER_BREATHING);
                        effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.STRAWHATSET, false);
                    }
                }

        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private static boolean hasFullSetOn(PlayerEntity player, RegistryEntry<ArmorMaterial> material) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        if (helmet.isEmpty() || chestplate.isEmpty() || leggings.isEmpty() || boots.isEmpty()) {
            return false;
        }

        if (!(helmet.getItem() instanceof ArmorItem helmetArmor) ||
                !(chestplate.getItem() instanceof ArmorItem chestplateArmor) ||
                !(leggings.getItem() instanceof ArmorItem leggingsArmor) ||
                !(boots.getItem() instanceof ArmorItem bootsArmor)) {
            return false;
        }

        return helmetArmor.getMaterial() == material &&
                chestplateArmor.getMaterial() == material &&
                leggingsArmor.getMaterial() == material &&
                bootsArmor.getMaterial() == material;
    }

    private static boolean isTeleportPositionValid(World level, BlockPos pos) {
        return !level.getBlockState(pos.down()).isAir() && level.getBlockState(pos).isAir() && level.getBlockState(pos.up()).isAir();
    }

    public static class OnHurtPlayerHandler implements ServerLivingEntityEvents.AllowDamage {

        private static final ThreadLocal<Boolean> isProcessingDamage = ThreadLocal.withInitial(() -> false);

        @Override
        public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {

            // Victim: Player (Source ~-> Attacker)

            if (isProcessingDamage.get()) {
                return true;
            }

            if (entity instanceof PlayerEntity player) {

                if (KQConfigValues.DEEPSLATESET)
                    if (source.isOf(DamageTypes.FALL) && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DEEPSLATESET)) {
                        isProcessingDamage.set(true);
                        try {
                            player.damage(source, amount * (float) KQConfigValues.DEEPSLATE_FALL_DAMAGE_MULTIPLIER);
                            return false;
                        } finally {
                            isProcessingDamage.set(false);
                        }
                    }

                if (KQConfigValues.EVOKERSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.EVOKERSET)) {
                        Random random = new Random();
                        if (source.getSource() != null && source.getSource() instanceof LivingEntity && random.nextFloat() < KQConfigValues.EVOKER_DARKNESS_CHANCE)
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 120, 0, false, false, true));
                    }

                if (KQConfigValues.SQUIRESET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SQUIRESET)) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1, false, false, false));
                    }

                if (KQConfigValues.BLAZESET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BLAZESET)) {
                        Random random = new Random();
                        if (source.getSource() != null && random.nextFloat() < KQConfigValues.BLAZE_FIRE_CHANCE)
                            source.getSource().setOnFireFor(random.nextInt(KQConfigValues.BLAZE_FIRE_DURATION_MIN, KQConfigValues.BLAZE_FIRE_DURATION_MAX));
                    }

                if (KQConfigValues.DRAGONSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DRAGONSET))
                        if (source.isOf(DamageTypes.DRAGON_BREATH))
                            return false;

                if (KQConfigValues.BAMBOOSET_GREEN)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                        if (player.hasStatusEffect(StatusEffects.POISON) && (source.isOf(DamageTypes.MAGIC) || source.isOf(DamageTypes.INDIRECT_MAGIC))) {
                            player.removeStatusEffect(StatusEffects.POISON);
                            return false;
                        }

                if (KQConfigValues.SHINOBI)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SHINOBI) && source.getSource() != null) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 1, false, false, true));
                    }

                if (KQConfigValues.BAMBOOSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BAMBOOSET) && source.isOf(DamageTypes.FALL)) {

                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

                        int particleCount = 80;
                        double particleRadius = 1.2;

                        for (int i = 0; i < particleCount; i++) {
                            double angleOffset = (2 * Math.PI / particleCount) * i;
                            double xParticleOffset = particleRadius * Math.cos(angleOffset);
                            double zParticleOffset = particleRadius * Math.sin(angleOffset);

                            serverPlayer.networkHandler.sendPacket(new ParticleS2CPacket(
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

                        Class<? extends Entity> classToPush = KQConfigValues.BAMBOOSET_PUSH_PLAYERS ? Entity.class : HostileEntity.class;

                        player.getWorld().getNonSpectatingEntities(classToPush, player.getBoundingBox().expand(3.5)).forEach(entity1 -> {
                            Vec3d direction = entity1.getPos().subtract(player.getPos()).normalize().multiply(0 * 0.5);
                            entity1.addVelocity(direction.x, direction.y + 0.5, direction.z);
                        });

                    }

                if (KQConfigValues.ENDERMANSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.ENDERMANSET) && source.getSource() != null) {

                        Random random = new Random();
                        if (random.nextFloat() < 0.3) {
                            int radius = KQConfigValues.TELEPORT_RADIUS_ENDERMANSET;
                            BlockPos playerPos = player.getBlockPos();
                            List<BlockPos> validPositions = new ArrayList<>();

                            for (int x = -radius; x <= radius; x++) {
                                for (int y = -radius; y <= radius; y++) {
                                    for (int z = -radius; z <= radius; z++) {
                                        BlockPos targetPos = new BlockPos(playerPos.getX() + x, playerPos.getY() + y, playerPos.getZ() + z);

                                        if (isTeleportPositionValid(player.getWorld(), targetPos)) {
                                            validPositions.add(targetPos);
                                        }
                                    }
                                }
                            }

                            if (!validPositions.isEmpty()) {
                                BlockPos randomPos = validPositions.get(random.nextInt(validPositions.size()));

                                player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                player.teleport(randomPos.getX(), randomPos.getY(), randomPos.getZ(), true);

                                return false;
                            }

                        }
                    }

                if (KQConfigValues.VETERANSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.VETERANSET) && player.getHealth() < player.getMaxHealth() * 0.5) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false, true));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, false, false, true));
                    }

                if (KQConfigValues.FORZESET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.FORZESET)) {
                        Random random = ThreadLocalRandom.current();
                        if (source.getSource() != null && random.nextFloat() < KQConfigValues.FORZESET_DEFLECT_CHANCE) {
                            isProcessingDamage.set(true);
                            try {
                                source.getSource().damage(source, amount * (float) KQConfigValues.FORZESET_DEFLECT_DAMAGE);
                            } finally {
                                isProcessingDamage.set(false);
                            }
                        }
                    }

                if (KQConfigValues.CREEPERSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.CREEPERSET)) {
                        if (source.isOf(DamageTypes.EXPLOSION) || source.isOf(DamageTypes.PLAYER_EXPLOSION)) {
                            isProcessingDamage.set(true);
                            try {
                                player.damage(source, amount * (float) KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER);
                                return false;
                            } finally {
                                isProcessingDamage.set(false);
                            }
                        }
                    }

                if (KQConfigValues.POLAR)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.POLAR)) {
                        if (source.getSource() != null && (source.isOf(DamageTypes.FREEZE)))
                            return false;
                    }

            }

            // Attacker: Player

            if (source.getAttacker() instanceof PlayerEntity player) {

                if (KQConfigValues.SILVERSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SILVERSET) && player.getWorld().isNight()) {
                        Random random = new Random();
                        if (random.nextFloat() < KQConfigValues.SILVERSET_BURN_CHANCE) {
                            entity.setFireTicks(random.nextInt(2, 8) * 20);
                        }
                    }

                if (KQConfigValues.HOLLOWSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.HOLLOWSET) && source.getSource() instanceof LivingEntity livingEntity) {
                        isProcessingDamage.set(true);
                        try {
                            player.heal(Math.min((float) (amount * KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER), livingEntity.getHealth()));
                        } finally {
                            isProcessingDamage.set(false);
                        }
                    }

                if (KQConfigValues.DRAGONSET) {
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DRAGONSET)) {
                        isProcessingDamage.set(true);
                        try {
                            entity.damage(source, (float) (amount * KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER));
                            return false;
                        } finally {
                            isProcessingDamage.set(false);
                        }
                    }
                }

                if (KQConfigValues.WITHERSET)
                    if (source.isOf(DamageTypes.ARROW) && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WITHERSET)) {
                        Random random = new Random();
                        if (random.nextFloat() < KQConfigValues.WITHERSET_WITHER_CHANCE)
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0, false, false, false));
                    }
            }

            return true;

        }
    }

    public static class OnEntityJoinWorldEvent implements ServerEntityEvents.Load {

        @Override
        public void onLoad(Entity entity, ServerWorld world) {
            if (entity instanceof PersistentProjectileEntity arrow && KQConfigValues.SKELETONSET) {
                if (arrow.getOwner() instanceof PlayerEntity player && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SKELETONSET)) {
                    arrow.setDamage(arrow.getDamage() * 2);
                }
            }
        }

    }

    public static class OnEntityDeathWorldEvent implements ServerLivingEntityEvents.AfterDeath {

        @Override
        public void afterDeath(LivingEntity entity, DamageSource damageSource) {
            if (entity != null && damageSource.getSource() instanceof PlayerEntity player) {

                if (KQConfigValues.CONQUISTADORSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.CONQUISTADORSET)) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1, false, true, true));
                    }

                if (KQConfigValues.WITCH)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WITCH)) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0, false, true, true));
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

        private static void onClientTick(MinecraftClient client) {
            ClientPlayerEntity player = client.player;
            if (player == null) return;

            if (KQConfigValues.TENGU_HELMET && player.getInventory().getArmorStack(3).getItem().equals(KnightQuestItems.TENGU_HELMET)) {
                boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUuid(), true);

                if (!player.isOnGround() && player.getVelocity().y < 0 && canDoubleJump) {
                    if (client.options.jumpKey.isPressed()) {
                        handleClientSideDoubleJump(player);
                    }
                }

                if (player.isOnGround()) {
                    doubleJumpStates.put(player.getUuid(), true);
                }
            }
        }

        private static void handleClientSideDoubleJump(ClientPlayerEntity player) {
            UUID playerUUID = player.getUuid();
            boolean canDoubleJump = doubleJumpStates.getOrDefault(playerUUID, true);

            if (canDoubleJump) {
                doubleJumpStates.put(playerUUID, false);

                for (int i = 0; i < 360; i += 60) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = player.getX() + 0.4 * Math.cos(angleRadians);
                    double particleZ = player.getZ() + 0.4 * Math.sin(angleRadians);

                    player.getWorld().addParticle(ParticleTypes.CLOUD, particleX, player.getY(), particleZ, 0d, 0.35d, 0d);
                }

                player.jump();
            }
        }
    }

    public static class OnEntityTickEvent implements ServerTickEvents.EndTick {

        @Override
        public void onEndTick(MinecraftServer server) {

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

                if (KQConfigValues.HUSKSET)
                    if (hasFullSetOn(player, KQArmorMaterials.HUSKSET) && (player.getWorld().getBiome(player.getBlockPos()).matchesKey(BiomeKeys.DESERT)
                            || player.getWorld().getBiome(player.getBlockPos()).matchesKey(BiomeKeys.BADLANDS)
                            || player.getWorld().getBiome(player.getBlockPos()).matchesKey(BiomeKeys.BEACH))) {
                        if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUuid(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HUSKSET, false))) {
                            player.addStatusEffect(HUSK_ARMOR);
                            effectAppliedByArmorMap.get(player.getUuid()).put(KQArmorMaterials.HUSKSET, true);
                        }
                    } else {
                        if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUuid(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.HUSKSET, false))) {
                            player.removeStatusEffect(StatusEffects.RESISTANCE);
                            effectAppliedByArmorMap.get(player.getUuid()).put(KQArmorMaterials.HUSKSET, false);
                        }
                    }

                if (KQConfigValues.BAMBOOSET_BLUE)
                    if (hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_BLUE) && (player.getWorld().getBiome(player.getBlockPos()).matchesKey(BiomeKeys.JUNGLE)
                            || player.getWorld().getBiome(player.getBlockPos()).matchesKey(BiomeKeys.BAMBOO_JUNGLE)
                                || player.getWorld().getBiome(player.getBlockPos()).matchesKey(BiomeKeys.SPARSE_JUNGLE))) {
                        if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUuid(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BAMBOOSET_BLUE, false))) {
                            player.addStatusEffect(BAMBOO_BLUE);
                            effectAppliedByArmorMap.get(player.getUuid()).put(KQArmorMaterials.BAMBOOSET_BLUE, true);
                        }
                    } else {
                        if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUuid(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.BAMBOOSET_BLUE, false))) {
                            player.removeStatusEffect(StatusEffects.SPEED);
                            effectAppliedByArmorMap.get(player.getUuid()).put(KQArmorMaterials.BAMBOOSET_BLUE, false);
                        }
                    }

                if (KQConfigValues.WARLORDSET)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WARLORDSET)) {
                        for (Entity entity : player.getWorld().getNonSpectatingEntities(PlayerEntity.class, player.getBoundingBox().expand(KQConfigValues.WARLORD_SET_EFFECT_RADIUS))) {
                            if (KQConfigValues.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF) {
                                if (entity instanceof PlayerEntity nearbyPlayer) {
                                    nearbyPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0, false, false, true));
                                }
                            } else {
                                if (entity instanceof PlayerEntity nearbyPlayer && entity != player) {
                                    nearbyPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0, false, false, true));
                                }
                            }
                        }
                    }

                if (!player.getWorld().isClient()) {

                    if (KQConfigValues.ZOMBIESET)
                        if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.ZOMBIESET) && player.getWorld().isNight()) {
                            if (player.age % KQConfigValues.ZOMBIESET_HEALING_TICKS == 0) {
                                player.heal((float) KQConfigValues.ZOMBIESET_HEALING_AMOUNT);
                            }
                        }

                    if (KQConfigValues.APPLE_SET)
                        if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.APPLE_SET)) {
                            if (player.age % 400 == 0) {
                                player.heal(1.0F);
                            }
                        }

                }

                if (KQConfigValues.POLAR)
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.POLAR)) {
                        player.setFrozenTicks(0);
                    }

                if (KQConfigValues.SILVERFISHSET)
                    if (hasFullSetOn(player, KQArmorMaterials.SILVERFISHSET) && player.getY() < KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT) {
                        if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUuid(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SILVERFISHSET, false))) {
                            player.addStatusEffect(SILVERFISH_ARMOR);
                            effectAppliedByArmorMap.get(player.getUuid()).put(KQArmorMaterials.SILVERFISHSET, true);
                        }
                    } else {
                        if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(player.getUuid(), k -> new HashMap<>()).getOrDefault(KQArmorMaterials.SILVERFISHSET, false))) {
                            player.removeStatusEffect(StatusEffects.HASTE);
                            effectAppliedByArmorMap.get(player.getUuid()).put(KQArmorMaterials.SILVERFISHSET, false);
                        }
                    }

            }
        }

    }

}