package dev.xylonity.knightquest.common.event;

import dev.xylonity.knightquest.common.item.weapons.*;
import dev.xylonity.knightquest.registry.KnightQuestItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.component.CustomData;
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
import dev.xylonity.knightquest.KnightQuest;
import dev.xylonity.knightquest.common.material.KQArmorMaterials;
import dev.xylonity.knightquest.config.values.KQConfigValues;

import java.util.*;

import static dev.xylonity.knightquest.common.item.KQArmorItem.effectAppliedByArmorMap;
import static dev.xylonity.knightquest.common.item.KQFullSetChecker.hasFullSetOn;

public class KQArmorEvents {

    /**
     * Manages individual jump states for each player on the server, ensuring that there will
     * not be infinite jumps when there are more than two players present.
     */

    private static final Map<UUID, Boolean> doubleJumpStates = new HashMap<>();

    private static final MobEffectInstance HUSK_ARMOR = new MobEffectInstance (MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance BAMBOO_BLUE = new MobEffectInstance (MobEffects.MOVEMENT_SPEED, -1, 1, false, false, true);
    private static final MobEffectInstance SILVERFISH_ARMOR = new MobEffectInstance (MobEffects.DIG_SPEED, -1, 0, false, false, true);

    private static boolean isTeleportPositionValid(Level level, BlockPos pos) {
        return !level.getBlockState(pos.below()).isAir() && level.getBlockState(pos).isAir() && level.getBlockState(pos.above()).isAir();
    }

    @Mod.EventBusSubscriber(modid = KnightQuest.MOD_ID)
    public static class ArmorStatusManagerEvents {

        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {

            // Kukri
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                if (livingEntity.getMainHandItem().getItem() == KnightQuestItems.KUKRI.get() && KQConfigValues.KUKRI.get()) {
                    event.getEntity().setTicksFrozen(event.getEntity().getTicksFrozen() + KQConfigValues.FREEZE_TICKS_KUKRI.get());
                }
            }

            if (event.getSource().getEntity() instanceof Player player && event.getEntity() != null) {

                ItemStack stack = player.getMainHandItem();

                // Uchigatana
                if (stack.getItem() instanceof UchigatanaWeapon && KQConfigValues.UCHIGATANA.get()) {

                    CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                    CompoundTag dataTag = customData.copyTag();

                    if (dataTag.getBoolean("ShouldDoActiveAttack")) {
                        dataTag.putBoolean("ShouldDoActiveAttack", false);
                        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(dataTag));

                        event.setAmount(event.getAmount() + event.getAmount() * KQConfigValues.EXTRA_DAMAGE_UCHIGATANA.get().floatValue());
                        event.getEntity().addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 1, false, false));
                    }
                    if (event.getEntity().getHealth() < KQConfigValues.ENEMY_HEALTH_PASSIVE_UCHIGATANA.get().floatValue() * event.getEntity().getMaxHealth()) {
                        event.setAmount(event.getAmount() + event.getAmount() * KQConfigValues.EXTRA_DAMAGE_PASSIVE_UCHIGATANA.get().floatValue());
                    }
                }

                // Cleaver
                if (stack.getItem() instanceof CleaverWeapon && KQConfigValues.CLEAVER.get()) {
                    if (event.getEntity().getHealth() > KQConfigValues.ENEMY_HEALTH_PASSIVE_CLEAVER.get().floatValue() * event.getEntity().getMaxHealth()) {
                        event.setAmount(event.getAmount() + event.getAmount() * KQConfigValues.EXTRA_DAMAGE_PASSIVE_CLEAVER.get().floatValue());
                    }
                }
            }

            // Khopesh
            if (event.getEntity() instanceof Player player) {
                ItemStack stack = player.getMainHandItem();
                if (stack.getItem() instanceof KhopeshWeapon && event.getSource().getEntity() != null && KQConfigValues.KHOPESH.get()) {

                    CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                    CompoundTag dataTag = customData.copyTag();

                    if (player.level().getGameTime() - dataTag.getLong("KhopeshActive") < KQConfigValues.REFLECTION_TIME_KHOPESH.get().floatValue()) {
                        event.getSource().getEntity().hurt(event.getSource(), event.getAmount() * 0.5F);
                    }
                }
            }

            // Khopesh
            if (event.getSource().getEntity() instanceof LivingEntity livingEntity) {
                if (livingEntity.getMainHandItem().getItem() == KnightQuestItems.KHOPESH.get() && KQConfigValues.KHOPESH.get() && livingEntity.getRandom().nextFloat() <= KQConfigValues.CHANCE_BURN_KHOPESH.get().floatValue()) {
                    event.getEntity().setRemainingFireTicks((livingEntity.getRandom().nextInt(7) + 1) * 20);
                }
            }

            // Victim: Player (Source ~-> Attacker)

            if (event.getEntity() instanceof Player player) {

                if (KQConfigValues.DEEPSLATESET.get())
                    if (event.getSource().is(DamageTypes.FALL) && hasFullSetOn(player, KQArmorMaterials.DEEPSLATESET)) {
                        float originalDamage = event.getAmount();
                        float reducedDamage = originalDamage * KQConfigValues.DEEPSLATE_FALL_DAMAGE_MULTIPLIER.get().floatValue();
                        event.setAmount(reducedDamage);
                    }

                if (KQConfigValues.EVOKERSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.EVOKERSET)) {
                        Random random = new Random();
                        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof LivingEntity entity && random.nextFloat() < KQConfigValues.EVOKER_DARKNESS_CHANCE.get().floatValue())
                            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 120, 0, false, false, true));
                    }

                if (KQConfigValues.SQUIRESET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.SQUIRESET)) {
                        event.setAmount(event.getAmount() * KQConfigValues.SQUIRE_DAMAGE_RECEIVED_MULTIPLIER.get().floatValue());
                    }

                if (KQConfigValues.BLAZESET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.BLAZESET)) {
                        Random random = new Random();
                        if (event.getSource().getEntity() != null && random.nextFloat() < KQConfigValues.BLAZE_FIRE_CHANCE.get().floatValue())
                            event.getSource().getEntity().setRemainingFireTicks(random.nextInt(KQConfigValues.BLAZE_FIRE_DURATION_MIN.get(), KQConfigValues.BLAZE_FIRE_DURATION_MAX.get()) * 20);
                    }

                if (KQConfigValues.DRAGONSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.DRAGONSET))
                        if (event.getSource().is(DamageTypes.DRAGON_BREATH))
                            event.setAmount(0);

                if (KQConfigValues.BAMBOOSET_GREEN.get())
                    if (hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                        if (player.hasEffect(MobEffects.POISON)) {
                            player.removeEffect(MobEffects.POISON);
                            event.setCanceled(true);
                        }

                if (KQConfigValues.SHINOBI.get())
                    if (hasFullSetOn(player, KQArmorMaterials.SHINOBI) && event.getSource().getEntity() != null) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 1, false, false, true));
                    }

                if (KQConfigValues.BAMBOOSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.BAMBOOSET) && event.getSource().is(DamageTypes.FALL) ) {

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

                        Class<? extends Entity> classToPush = KQConfigValues.ENABLE_BAMBOOSET_PUSH_PLAYERS.get() ? Entity.class : Monster.class;

                        player.level().getEntitiesOfClass(classToPush, player.getBoundingBox().inflate(3.5)).forEach(entity -> {
                            Vec3 direction = entity.position().subtract(player.position()).normalize().scale(event.getAmount() * 0.5);
                            entity.push(direction.x, direction.y + 0.5, direction.z);
                        });

                    }

                if (KQConfigValues.ENDERMANSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.ENDERMANSET) && event.getSource().getEntity() != null) {

                        Random random = new Random();
                        if (random.nextFloat() < 0.3) {
                            int radius = KQConfigValues.TELEPORT_RADIUS_ENDERMANSET.get();
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

                if (KQConfigValues.VETERANSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.VETERANSET) && player.getHealth() < player.getMaxHealth() * 0.5) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false, true));
                    }

                if (KQConfigValues.FORZESET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.FORZESET)) {
                        Random random = new Random();
                        if (event.getSource().getEntity() != null && random.nextFloat() < KQConfigValues.FORZESET_DEFLECT_CHANCE.get().floatValue())
                            event.getSource().getEntity().hurt(event.getSource(), event.getAmount() * KQConfigValues.FORZESET_DEFLECT_DAMAGE.get().floatValue());
                    }

                if (KQConfigValues.CREEPERSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.CREEPERSET)) {
                        if (event.getSource().getEntity() != null && (event.getSource().is(DamageTypes.EXPLOSION) || event.getSource().is(DamageTypes.PLAYER_EXPLOSION)))
                            event.setAmount(event.getAmount() * KQConfigValues.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue());
                    }

                if (KQConfigValues.POLAR.get())
                    if (hasFullSetOn(player, KQArmorMaterials.POLAR)) {
                        if (event.getSource().getEntity() != null && (event.getSource().is(DamageTypes.FREEZE)))
                            event.setAmount(0);
                    }

            }

            // Attacker: Player

            if (event.getSource().getEntity() instanceof Player player) {

                if (KQConfigValues.SILVERSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.SILVERSET) && player.level().isNight()) {
                        Random random = new Random();
                        if (random.nextFloat() < KQConfigValues.SILVERSET_BURN_CHANCE.get().floatValue()) {
                            event.getEntity().setRemainingFireTicks(random.nextInt(2, 8) * 20);
                        }
                    }

                if (KQConfigValues.HOLLOWSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.HOLLOWSET))
                        player.heal(Math.min((event.getAmount() * KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER.get().floatValue()), event.getEntity().getHealth()));

                if (KQConfigValues.DRAGONSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.DRAGONSET))
                        event.setAmount(event.getAmount() * KQConfigValues.DRAGONSET_DAMAGE_MULTIPLIER.get().floatValue());

                if (KQConfigValues.WITHERSET.get())
                    if (event.getSource().is(DamageTypes.ARROW) && hasFullSetOn(player, KQArmorMaterials.WITHERSET)) {
                        Random random = new Random();
                        if (event.getSource().getEntity() != null && random.nextFloat() < KQConfigValues.WITHERSET_WITHER_CHANCE.get().floatValue())
                            event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 0, false, false, false));
                    }
            }
        }

        @SubscribeEvent
        public static void onLivingUpdate(LivingEntityUseItemEvent.Finish event) {
            if (event.getEntity() instanceof Player player)
                if (KQConfigValues.APPLE_SET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.APPLE_SET))
                        if (event.getItem().getItem().equals(Items.APPLE))
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0, false, true, true));
        }

        @SubscribeEvent
        public static void onLivingDead(LivingDeathEvent event) {
            if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player) {

                if (KQConfigValues.CONQUISTADORSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.CONQUISTADORSET)) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, true, true));
                    }

                if (KQConfigValues.WITCH.get())
                    if (hasFullSetOn(player, KQArmorMaterials.WITCH)) {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 80, 0, false, true, true));
                    }

            }
        }

        @SubscribeEvent
        public static void onArrowHit(EntityJoinLevelEvent event) {
            if (event.getEntity() instanceof AbstractArrow arrow) {
                if (KQConfigValues.SKELETONSET.get())
                    if (arrow.getOwner() instanceof Player player && hasFullSetOn(player, KQArmorMaterials.SKELETONSET)) {
                        arrow.setPierceLevel((byte) 5);
                    }
            }
        }

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {
            if (event.getEntity() instanceof Player player) {
                ItemStack helmet = player.getInventory().getArmor(3);
                ItemStack stack = player.getMainHandItem();

                CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                CompoundTag dataTag = customData.copyTag();

                if (stack.getItem() instanceof PaladinWeapon && KQConfigValues.PALADIN.get()) {
                    if (player.tickCount % KQConfigValues.REGEN_TICKS_PALADIN.get() == 0 && player.getHealth() < player.getMaxHealth() * KQConfigValues.REGEN_MAX_PALADIN.get().floatValue() && dataTag.getBoolean("Activated"))
                        player.heal(KQConfigValues.REGEN_HP_PALADIN.get().floatValue());
                }

                if (KQConfigValues.TENGU_HELMET.get() || KQConfigValues.NAIL.get()) {

                    if (helmet.getItem().equals(KnightQuestItems.TENGU_HELMET.get()) || (stack.getItem() instanceof NailWeapon && dataTag.getBoolean("Activated"))) {
                        boolean canDoubleJump = doubleJumpStates.getOrDefault(player.getUUID(), true);

                        if (!player.onGround() && player.getDeltaMovement().y < 0 && canDoubleJump) {
                            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> handleClientSideDoubleJump(player));
                        }

                        if (player.onGround())
                            doubleJumpStates.put(player.getUUID(), true);
                    }
                }

                if (KQConfigValues.HUSKSET.get())
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

                if (KQConfigValues.BAMBOOSET_GREEN.get())
                    if (hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                        if (player.hasEffect(MobEffects.POISON)) {
                            player.removeEffect(MobEffects.POISON);
                        }

                if (KQConfigValues.BAMBOOSET_BLUE.get())
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

                if (KQConfigValues.WARLORDSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.WARLORDSET)) {
                        for (Entity entity : player.level().getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(KQConfigValues.WARLORD_SET_EFFECT_RADIUS.get()))) {
                            if (KQConfigValues.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.get()) {
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

                if (KQConfigValues.ZOMBIESET.get())
                    if (!player.level().isClientSide) {
                        if (hasFullSetOn(player, KQArmorMaterials.ZOMBIESET) && player.level().isNight()) {
                            if (player.tickCount % KQConfigValues.ZOMBIESET_HEALING_TICKS.get() == 0) {
                                player.heal(KQConfigValues.ZOMBIESET_HEALING_AMOUNT.get().floatValue());
                            }
                        }
                    }

                if (KQConfigValues.POLAR.get())
                    if (hasFullSetOn(player, KQArmorMaterials.POLAR) && player.getTicksFrozen() > 0)
                        player.setTicksFrozen(0);

                if (KQConfigValues.SILVERFISHSET.get())
                    if (hasFullSetOn(player, KQArmorMaterials.SILVERFISHSET) && player.getY() < KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT.get()) {
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
