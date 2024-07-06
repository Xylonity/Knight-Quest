package net.xylonity.knightquest.common.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.xylonity.knightquest.KnightQuest;
import net.xylonity.knightquest.common.material.KQArmorMaterials;
import net.xylonity.knightquest.registry.KnightQuestItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class KQArmorItem extends ArmorItem {

    private final String bonusTooltip;

    public KQArmorItem(KQArmorMaterials material, Type type, Properties settings) {
        super(material, type, settings);
        this.bonusTooltip = material.getKeyName();
    }

    /**
     * Adds `passive ability` tooltips to each KQArmorItem corresponding to each registered armor set. Uses the
     * same name as the material registered for each individual armor set, as they are essentially identical.
     */

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
       if (!Objects.equals(bonusTooltip, "chainmail") && !Objects.equals(bonusTooltip, "tengu")) {
           pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_set_bonus"));
           pTooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
       } else if (Objects.equals(bonusTooltip, "tengu")) {
           pTooltipComponents.add(Component.translatable("tooltip.item.knightquest.full_helmet_bonus"));
           pTooltipComponents.add(Component.translatable("tooltip.item.knightquest." + bonusTooltip + "_helmet.bonus"));
       }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    /**
     * Declaration of static effects for certain armors.
     */

    private static final MobEffectInstance SHIELD_ARMOR = new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 0, false, false, true);
    private static final MobEffectInstance BAT_ARMOR = new MobEffectInstance(MobEffects.NIGHT_VISION, -1, 0, false, false, true);
    private static final MobEffectInstance PATH_ARMOR = new MobEffectInstance(MobEffects.INVISIBILITY, -1, 2, false, true, true);
    private static final MobEffectInstance BOW_ARMOR = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0, false, false, true);
    private static final MobEffectInstance HORN_ARMOR = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 0, false, false, true);
    private static final MobEffectInstance SEA_ARMOR = new MobEffectInstance(MobEffects.DOLPHINS_GRACE, -1, 0, false, false, true);
    private static final MobEffectInstance PIRATE_ARMOR = new MobEffectInstance(MobEffects.LUCK, -1, 0, false, false, true);
    private static final MobEffectInstance SPIDER_ARMOR = new MobEffectInstance(MobEffects.JUMP, -1, 1, false, false, false);
    private static final MobEffectInstance PHATOM_ARMOR =  new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0, false, false, true);
    private static final MobEffectInstance NETHER_ARMOR =  new MobEffectInstance(MobEffects.FIRE_RESISTANCE, -1, 0, false, false, true);
    private static final MobEffectInstance HUSK_ARMOR =  new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance BAMBOO_BLUE =  new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0, false, false, true);
    private static final MobEffectInstance SILVERFISH_ARMOR =  new MobEffectInstance(MobEffects.DIG_SPEED, -1, 0, false, false, true);
    private static final MobEffectInstance SKULK_ARMOR =  new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance STRAWHAT_ARMOR =  new MobEffectInstance(MobEffects.WATER_BREATHING, -1, 0, false, false, true);

    /**
     * Dual Hashmap that inherits old declared effects when a new set is equipped, preventing
     * incompatibility when either post-indirect or pre-direct status effects are applied.
     */

    private static final Map<UUID, Map<KQArmorMaterials, Boolean>> effectAppliedByArmorMap = new HashMap<>();

    @Override
    public void onInventoryTick(ItemStack stack, @NotNull Level level, Player player, int slotIndex, int selectedIndex) {
        if(!level.isClientSide()) {

            UUID playerUUID = player.getUUID();

            if (hasFullSetOn(player, KQArmorMaterials.PATHSET) && player.isShiftKeyDown()) {
                if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PATHSET, false))) {
                    player.addEffect(PATH_ARMOR);
                    effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PATHSET, true);
                }
            } else {
                if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PATHSET, false))) {
                    player.removeEffect(MobEffects.INVISIBILITY);
                    effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PATHSET, false);
                }
            }

            if (hasFullSetOn(player, KQArmorMaterials.BOWSET) && player.getMainHandItem().getItem() instanceof ProjectileWeaponItem) {
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

            if (hasFullSetOn(player, KQArmorMaterials.BATSET) && level.isNight()) {
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

            if (hasFullSetOn(player, KQArmorMaterials.SHIELDSET)) {
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

            if (hasFullSetOn(player, KQArmorMaterials.PHANTOMSET) && level.isNight()) {
                if (!Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PHANTOMSET, false))) {
                    player.addEffect(PHATOM_ARMOR);
                    effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PHANTOMSET, true);
                }
            } else {
                if (Boolean.TRUE.equals(effectAppliedByArmorMap.computeIfAbsent(playerUUID, k -> new HashMap<>()).getOrDefault(KQArmorMaterials.PHANTOMSET, false))) {
                    player.removeEffect(MobEffects.MOVEMENT_SPEED);
                    effectAppliedByArmorMap.get(playerUUID).put(KQArmorMaterials.PHANTOMSET, false);
                }
            }

            if (hasFullSetOn(player, KQArmorMaterials.HORNSET) && player.getLastAttacker() != null) {
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

            if (hasFullSetOn(player, KQArmorMaterials.SEASET) && player.isUnderWater()) {
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

            if (hasFullSetOn(player, KQArmorMaterials.PIRATESET)) {
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

            if (hasFullSetOn(player, KQArmorMaterials.SPIDERSET) && player.isShiftKeyDown()) {
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

            if (hasFullSetOn(player, KQArmorMaterials.NETHERSET)) {
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

            if (hasFullSetOn(player, KQArmorMaterials.SKULK) && player.level().getMaxLocalRawBrightness(player.blockPosition()) <= 4) {
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

            if (hasFullSetOn(player, KQArmorMaterials.STRAWHATSET) && player.isUnderWater()) {
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

        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    private static boolean hasFullSetOn(Player player, KQArmorMaterials material) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty()
                && ((ArmorItem) helmet.getItem()).getMaterial() == material
                && ((ArmorItem) breastplate.getItem()).getMaterial() == material
                && ((ArmorItem) leggings.getItem()).getMaterial() == material
                && ((ArmorItem) boots.getItem()).getMaterial() == material;
    }

    private static boolean isTeleportPositionValid(Level level, BlockPos pos) {
        return !level.getBlockState(pos.below()).isAir() && level.getBlockState(pos).isAir() && level.getBlockState(pos.above()).isAir();
    }

    @Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID)
    public class ArmorStatusManagerEvents {

        /**
         * Manages individual jump states for each player on the server, ensuring that there will
         * not be infinite jumps when there are more than two players present.
         */

        private static final Map<UUID, Boolean> doubleJumpStates = new HashMap<>();

        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {

            // Victim: Player (Source ~-> Attacker)

            if (event.getEntity() instanceof Player player) {

                if (event.getSource().is(DamageTypes.FALL) && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DEEPSLATESET)) {
                    float originalDamage = event.getAmount();
                    float reducedDamage = originalDamage * 0.25f;
                    event.setAmount(reducedDamage);
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.EVOKERSET)) {
                    Random random = new Random();
                    if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof LivingEntity entity && random.nextFloat() < 0.15)
                        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 160, 0, false, false, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SQUIRESET)) {
                    event.setAmount(event.getAmount() * 0.85F);
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BLAZESET)) {
                    Random random = new Random();
                    if (event.getSource().getEntity() != null && random.nextFloat() < 0.3)
                        event.getSource().getEntity().setSecondsOnFire(random.nextInt(1, 5));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DRAGONSET))
                    if (event.getSource().is(DamageTypes.DRAGON_BREATH))
                        event.setAmount(0);

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                    if (player.hasEffect(MobEffects.POISON) && (event.getSource().is(DamageTypes.MAGIC) || event.getSource().is(DamageTypes.INDIRECT_MAGIC))) {
                        event.setAmount(0);
                        player.removeEffect(MobEffects.POISON);
                    }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SHINOBI) && event.getSource().getEntity() != null) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 160, 0, false, false, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.BAMBOOSET) && event.getSource().is(DamageTypes.FALL) ) {

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

                    player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(3.5)).forEach(entity -> {
                        Vec3 direction = entity.position().subtract(player.position()).normalize().scale(event.getAmount() * 0.5);
                        entity.push(direction.x, direction.y + 0.5, direction.z);
                    });

                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.ENDERMANSET) && event.getSource().getEntity() != null) {

                    Random random = new Random();
                    if (random.nextFloat() < 0.3) {
                        int radius = 5;
                        BlockPos playerPos = player.blockPosition();
                        List<BlockPos> validPositions = new ArrayList<>();

                        for (int x = -radius; x <= radius; x++) {
                            for (int y = -radius; y <= radius; y++) {
                                for (int z = -radius; z <= radius; z++) {
                                    BlockPos targetPos = new BlockPos(playerPos.getX() + x, playerPos.getY() + y, playerPos.getZ() + z);

                                    if (isTeleportPositionValid(player.level(), targetPos)) {
                                        validPositions.add(targetPos);
                                    }
                                }
                            }
                        }

                        if (!validPositions.isEmpty()) {
                            BlockPos randomPos = validPositions.get(random.nextInt(validPositions.size()));
                            event.setAmount(0);

                            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                            player.teleportTo(randomPos.getX(), randomPos.getY(), randomPos.getZ());
                        }

                    }
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.VETERANSET) && player.getHealth() < player.getMaxHealth() * 0.5) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false, true));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.FORZESET)) {
                    Random random = new Random();
                    if (event.getSource().getEntity() != null && random.nextFloat() < 0.15)
                        event.getSource().getEntity().hurt(event.getSource(), event.getAmount() * 0.5F);
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.CREEPERSET)) {
                    if (event.getSource().getEntity() != null && (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)))
                        event.setAmount(event.getAmount() * 0.2F);
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.POLAR)) {
                    if (event.getSource().getEntity() != null && (event.getSource().is(DamageTypes.FREEZE)))
                        event.setAmount(0);
                }

            }

            // Attacker: Player

            if (event.getSource().getEntity() instanceof Player player && event.getEntity() != null) {

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SILVERSET) && player.level().isNight()) {
                    Random random = new Random();
                    if (random.nextFloat() < 0.20) {
                        event.getEntity().setSecondsOnFire(random.nextInt(1, 7));
                    }
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.HOLLOWSET))
                    player.heal(Math.min((float) (event.getAmount() * 0.15), event.getEntity().getHealth()));

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.DRAGONSET))
                    event.setAmount((float) (event.getAmount() * 1.15));

                if (event.getSource().is(DamageTypes.ARROW) && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WITHERSET)) {
                    Random random = new Random();
                    if (event.getSource().getEntity() != null && random.nextFloat() < 0.3)
                        event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0, false, false, false));
                }
            }
        }

        @SubscribeEvent
        public static void onLivingUpdate(LivingEntityUseItemEvent.Finish event) {
            if (event.getEntity() instanceof Player player)
                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.APPLE_SET))
                    if (event.getItem().getItem().equals(Items.APPLE))
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0, false, true, true));
        }

        @SubscribeEvent
        public static void onLivingDead(LivingDeathEvent event) {
            if (event.getEntity() != null && event.getSource().getEntity() instanceof Player player) {

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.CONQUISTADORSET)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, true, true));
                }

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WITCH)) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 1, false, true, true));
                }

            }
        }

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {
            if (event.getEntity() instanceof Player player) {
                ItemStack helmet = player.getInventory().getArmor(3);
                if (helmet.getItem().equals(KnightQuestItems.TENGU_HELMET.get())) {
                    boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUUID(), true);

                    if (!player.onGround() && player.getDeltaMovement().y < 0 && canDoubleJump) {
                        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClientSideDoubleJump(player));
                    }

                    if (player.onGround())
                        doubleJumpStates.put(player.getUUID(), true);
                }

                if (hasFullSetOn(player, KQArmorMaterials.HUSKSET) && (player.level().getBiome(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).is(Biomes.DESERT)
                        || player.level().getBiome(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).is(Biomes.BADLANDS)
                            || player.level().getBiome(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).is(Biomes.BEACH))) {
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

                if (hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_BLUE) && (player.level().getBiome(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).is(Biomes.JUNGLE)
                        || player.level().getBiome(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).is(Biomes.BAMBOO_JUNGLE)
                            || player.level().getBiome(new BlockPos((int) player.getX(), (int) player.getY(), (int) player.getZ())).is(Biomes.SPARSE_JUNGLE))) {
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

                if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.WARLORDSET)) {
                    for (Entity entity : player.level().getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(15.0))) {
                        if (entity instanceof Player nearbyPlayer && entity != player) {
                            nearbyPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0, false, false, true));
                        }
                    }
                }

                if (!player.level().isClientSide) {
                    if (KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.ZOMBIESET) && player.level().isNight()) {
                        if (player.tickCount % 200 == 0) {
                            player.heal(1.0F);
                        }
                    }
                }

                if (hasFullSetOn(player, KQArmorMaterials.SILVERFISHSET) && player.getY() < 50) {
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

        @SubscribeEvent
        public static void onArrowHit(EntityJoinLevelEvent event) {
            if (event.getEntity() instanceof AbstractArrow arrow) {
                if (arrow.getOwner() instanceof Player player && KQFullSetChecker.hasFullSuitOfArmorOn(player, KQArmorMaterials.SKELETONSET)) {
                    arrow.setPierceLevel((byte) 2);
                }
            }
        }

        @OnlyIn(Dist.CLIENT)
        private static void handleClientSideDoubleJump(Player player) {
            if (Minecraft.getInstance().options.keyJump.isDown()) {
                boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUUID(), true);

                if (canDoubleJump) {
                    doubleJumpStates.put(player.getUUID(), false);

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

    }

}
