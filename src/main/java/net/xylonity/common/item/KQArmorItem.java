package net.xylonity.common.item;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
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
import net.xylonity.common.material.KQArmorMaterials;
import net.xylonity.registry.KnightQuestItems;

import java.util.*;

public class KQArmorItem extends ArmorItem {

    /**
     * Manages individual jump states for each player on the server, ensuring that there will
     * not be infinite jumps when there are more than two players present.
     */

    private static final Map<UUID, Boolean> doubleJumpStates = new HashMap<>();

    /**
     * Dual Hashmap that inherits old declared effects when a new set is equipped, preventing
     * incompatibility when either post-indirect or pre-direct status effects are applied.
     */

    private static final Map<UUID, Map<RegistryEntry<ArmorMaterial>, Boolean>> effectAppliedByArmorMap = new HashMap<>();

    private final String bonusTooltip;

    public KQArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings.maxCount(1).maxDamage(type.getMaxDamage(37)));
        this.bonusTooltip = KQArmorMaterials.getKeyNameFromMaterial(material);
    }

    /**
     * Adds `passive ability` tooltips to each KQArmorItem corresponding to each registered armor set. Uses the
     * same name as the material registered for each individual armor set, as they are essentially identical.
     */

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!Objects.equals(bonusTooltip, "knightquest.chainmail") && !Objects.equals(bonusTooltip, "knightquest.tengu")) {
            tooltip.add(Text.translatable("tooltip.item.knightquest.full_set_bonus"));
            tooltip.add(Text.translatable("tooltip.item." + bonusTooltip + "_helmet.bonus"));
        } else if (Objects.equals(bonusTooltip, "knightquest.tengu")) {
            tooltip.add(Text.translatable("tooltip.item.knightquest.full_helmet_bonus"));
            tooltip.add(Text.translatable("tooltip.item." + bonusTooltip + "_helmet.bonus"));
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

            if (hasFullSetOn(player, KQArmorMaterials.SKULK) && player.getWorld().getLightLevel(player.getBlockPos()) <= 4) {
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

        @Override
        public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {

            // Victim: Player (Source ~-> Attacker)

            if (entity instanceof PlayerEntity player) {

                if (source.isOf(DamageTypes.FALL) && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DEEPSLATESET)) {
                    player.damage(player.getDamageSources().generic(), amount * 0.20F);
                    return false;
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.EVOKERSET)) {
                    Random random = new Random();
                    if (source.getSource() != null && source.getSource() instanceof LivingEntity && random.nextFloat() < 0.25)
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 120, 0, false, false, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SQUIRESET)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1, false, false, false));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BLAZESET)) {
                    Random random = new Random();
                    if (source.getSource() != null && random.nextFloat() < 0.4)
                        source.getSource().setFireTicks(random.nextInt(2, 8) * 20);
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DRAGONSET))
                    if (source.isOf(DamageTypes.DRAGON_BREATH))
                        return false;

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                    if (player.hasStatusEffect(StatusEffects.POISON) && (source.isOf(DamageTypes.MAGIC) || source.isOf(DamageTypes.INDIRECT_MAGIC))) {
                        player.removeStatusEffect(StatusEffects.POISON);
                        return false;
                    }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SHINOBI) && source.getSource() != null) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 1, false, false, true));
                }

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

                    player.getWorld().getEntitiesByClass(HostileEntity.class, player.getBoundingBox().expand(3.5), HostileEntity::isLiving).forEach(entity1 -> {
                        Vec3d direction = entity1.getPos().subtract(player.getPos()).normalize().multiply(amount * 0.5);
                        entity1.addVelocity(direction.x, direction.y + 0.5, direction.z);
                    });

                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.ENDERMANSET) && source.getSource() != null) {

                    Random random = new Random();
                    if (random.nextFloat() < 0.3) {
                        int radius = 10;
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

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.VETERANSET) && player.getHealth() < player.getMaxHealth() * 0.5) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false, true));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, false, false, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.FORZESET)) {
                    Random random = new Random();
                    if (source.getSource() != null && random.nextFloat() < 0.3)
                        source.getSource().damage(source.getSource().getDamageSources().generic(), amount * 0.5F);
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.CREEPERSET)) {
                    if (source.getSource() != null && (source.isOf(DamageTypes.EXPLOSION) || source.isOf(DamageTypes.PLAYER_EXPLOSION))) {
                        player.damage(player.getDamageSources().generic(), amount * 0.1F);
                    }
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.POLAR)) {
                    if (source.getSource() != null && (source.isOf(DamageTypes.FREEZE)))
                        return false;
                }

            }

            // Attacker: Player

            if (source.getAttacker() instanceof PlayerEntity player) {

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SILVERSET) && player.getWorld().isNight()) {
                    Random random = new Random();
                    if (random.nextFloat() < 0.30) {
                        entity.setFireTicks(random.nextInt(2, 8) * 20);
                    }
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.HOLLOWSET) && source.getSource() instanceof LivingEntity livingEntity) {
                    player.heal(Math.min((float) (amount * 0.25), livingEntity.getHealth()));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DRAGONSET)) {
                    entity.damage(Objects.requireNonNull(source.getSource()).getDamageSources().generic(), (float) (amount * 1.15));
                    return false;
                }

                if (source.isOf(DamageTypes.ARROW) && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WITHERSET)) {
                    Random random = new Random();
                    if (random.nextFloat() < 0.3)
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 0, false, false, false));
                }
            }

            return true;

        }
    }

    public static class OnEntityJoinWorldEvent implements ServerEntityEvents.Load {

        @Override
        public void onLoad(Entity entity, ServerWorld world) {
            if (entity instanceof PersistentProjectileEntity arrow) {
                if (arrow.getOwner() instanceof PlayerEntity player && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SKELETONSET)) {
                    //arrow.setPierceLevel((byte) 2);
                }
            }
        }

    }

    public static class OnEntityDeathWorldEvent implements ServerLivingEntityEvents.AfterDeath {

        @Override
        public void afterDeath(LivingEntity entity, DamageSource damageSource) {
            if (entity != null && damageSource.getSource() instanceof PlayerEntity player) {

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.CONQUISTADORSET)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1, false, true, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WITCH)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0, false, true, true));
                }
            }
        }

    }

    public static class OnEntityTickEvent implements ServerTickEvents.EndTick {

        // This method generates a crash on dedicated servers. Temporary disabled.

        //private static void handleClientSideDoubleJump(PlayerEntity player) {
        //    if (MinecraftClient.getInstance().options.jumpKey.isPressed()) {
        //        boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUuid(), true);
        //        if (canDoubleJump) {
        //            doubleJumpStates.put(player.getUuid(), false);
        //
        //            for (int i = 0; i < 360; i += 60) {
        //                double angleRadians = Math.toRadians(i);
        //
        //                double particleX = player.getX() + 0.4 * Math.cos(angleRadians);
        //                double particleZ = player.getZ() + 0.4 * Math.sin(angleRadians);
        //
        //                ((ServerPlayerEntity) player).networkHandler.sendPacket(new ParticleS2CPacket(
        //                        ParticleTypes.CLOUD,
        //                        true,
        //                        particleX,
        //                        player.getY(),
        //                        particleZ,
        //                        0.0f,
        //                        0.35f,
        //                        0.0f,
        //                        0.0f,
        //                        1
        //                ));
        //
        //            }
        //
        //            player.jump();
        //            player.velocityModified = true;
        //        }
        //    }
        //}

        @Override
        public void onEndTick(MinecraftServer server) {

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

                if (player.getInventory().getArmorStack(3).getItem().equals(KnightQuestItems.TENGU_HELMET)) {
                    boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUuid(), true);

                    if (!player.isOnGround() && player.getVelocity().y < 0 && canDoubleJump) {

                        //handleClientSideDoubleJump(player);

                        player.jump();
                        player.velocityModified = true;
                        doubleJumpStates.put(player.getUuid(), false);
                    }

                    if (player.isOnGround())
                        doubleJumpStates.put(player.getUuid(), true);
                }

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

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WARLORDSET)) {
                    for (Entity entity : player.getWorld().getEntitiesByClass(PlayerEntity.class, player.getBoundingBox().expand(15.0), PlayerEntity::isPlayer)) {
                        if (entity instanceof PlayerEntity nearbyPlayer && entity != player) {
                            nearbyPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0, false, false, true));
                        }
                    }
                }

                if (!player.getWorld().isClient()) {

                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.ZOMBIESET) && player.getWorld().isNight()) {
                        if (player.age % 120 == 0) {
                            player.heal(1.0F);
                        }
                    }

                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.APPLE_SET)) {
                        if (player.age % 400 == 0) {
                            player.heal(1.0F);
                        }
                    }

                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.POLAR)) {
                    player.setFrozenTicks(0);
                }

                if (hasFullSetOn(player, KQArmorMaterials.SILVERFISHSET) && player.getY() < 50) {
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