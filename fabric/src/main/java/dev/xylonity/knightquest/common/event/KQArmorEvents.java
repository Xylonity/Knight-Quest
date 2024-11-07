package dev.xylonity.knightquest.common.event;

import dev.xylonity.knightquest.common.item.KQFullSetChecker;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;

import java.util.*;

import static dev.xylonity.knightquest.common.item.KQArmorItem.effectAppliedByArmorMap;

public class KQArmorEvents {

    private static final MobEffectInstance HUSK_ARMOR =  new MobEffectInstance (MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, true);
    private static final MobEffectInstance BAMBOO_BLUE =  new MobEffectInstance (MobEffects.MOVEMENT_SPEED, -1, 1, false, false, true);
    private static final MobEffectInstance SILVERFISH_ARMOR =  new MobEffectInstance (MobEffects.DIG_SPEED, -1, 0, false, false, true);

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
                if (KQConfigValues.EVOKERSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.EVOKERSET)) {
                        RandomSource random = entity.level().getRandom();
                        if (source.getEntity() instanceof LivingEntity && random.nextFloat() < (float) KQConfigValues.EVOKER_DARKNESS_CHANCE.getFloat())
                            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 120, 0, false, false, true));
                    }

                // BLAZESET
                if (KQConfigValues.BLAZESET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BLAZESET)) {
                        RandomSource random = entity.level().getRandom();
                        if (source.getEntity() != null && random.nextFloat() < KQConfigValues.BLAZE_FIRE_CHANCE.getFloat())
                            source.getEntity().igniteForSeconds(random.nextInt(KQConfigValues.BLAZE_FIRE_DURATION_MIN.getInt(), KQConfigValues.BLAZE_FIRE_DURATION_MAX.getInt()));
                    }

                // DRAGONSET
                if (KQConfigValues.DRAGONSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.DRAGONSET))
                        if (source.is(DamageTypes.DRAGON_BREATH))
                            return false;

                // BAMBOOSET_GREEN
                if (KQConfigValues.BAMBOOSET_GREEN.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.BAMBOOSET_GREEN))
                        if (player.hasEffect(MobEffects.POISON) && (source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC))) {
                            player.removeEffect(MobEffects.POISON);
                            return false;
                        }

                // SHINOBI
                if (KQConfigValues.SHINOBI.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SHINOBI) && source.getEntity() != null) {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 1, false, false, true));
                    }

                // BAMBOOSET
                if (KQConfigValues.BAMBOOSET.getBoolean())
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

                        Class<? extends Entity> classToPush = KQConfigValues.BAMBOOSET_PUSH_PLAYERS.getBoolean() ? Entity.class : Player.class;

                        player.level().getEntitiesOfClass(classToPush, player.getBoundingBox().inflate(3.5)).forEach(entity1 -> {
                            Vec3 direction = entity1.position().subtract(player.position()).normalize().scale(amount * 0.5);
                            entity1.push(direction.x, direction.y + 0.5, direction.z);
                        });

                    }

                // ENDERMANSET
                if (KQConfigValues.ENDERMANSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.ENDERMANSET) && source.getEntity() != null) {

                        RandomSource random = entity.level().getRandom();
                        if (random.nextFloat() < 0.3) {
                            int radius = KQConfigValues.TELEPORT_RADIUS_ENDERMANSET.getInt();
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
                if (KQConfigValues.VETERANSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.VETERANSET) && player.getHealth() < player.getMaxHealth() * 0.5) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false, true));
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1, false, false, true));
                    }

                // FORZESET
                if (KQConfigValues.FORZESET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.FORZESET)) {
                        RandomSource random = entity.level().getRandom();
                        if (source.getEntity() != null && random.nextFloat() < KQConfigValues.FORZESET_DEFLECT_CHANCE.getFloat()) {
                            isProcessingDamage.set(true);
                            try {
                                source.getEntity().hurt(source, amount * KQConfigValues.FORZESET_DEFLECT_DAMAGE.getFloat());
                            } finally {
                                isProcessingDamage.set(false);
                            }
                        }
                    }

                // POLAR
                if (KQConfigValues.POLAR.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.POLAR)) {
                        if (source.getEntity() != null && (source.is(DamageTypes.FREEZE)))
                            return false;
                    }

            }

            // Attacker: Player

            if (source.getEntity() instanceof Player player) {

                // SILVERSET
                if (KQConfigValues.SILVERSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SILVERSET) && player.level().isNight()) {
                        RandomSource random = entity.level().getRandom();
                        if (random.nextFloat() < KQConfigValues.SILVERSET_BURN_CHANCE.getFloat()) {
                            entity.igniteForTicks(random.nextIntBetweenInclusive(2, 8) * 20);
                        }
                    }

                // HOLLOWSET
                if (KQConfigValues.HOLLOWSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.HOLLOWSET) && source.getEntity() instanceof LivingEntity livingEntity) {
                        isProcessingDamage.set(true);
                        try {
                            player.heal(Math.min(amount * KQConfigValues.HOLLOWSET_HEALING_MULTIPLIER.getFloat(), livingEntity.getHealth()));
                        } finally {
                            isProcessingDamage.set(false);
                        }
                    }

                // WITHERSET
                if (KQConfigValues.WITHERSET.getBoolean())
                    if (source.is(DamageTypes.ARROW) && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.WITHERSET)) {
                        RandomSource random = entity.level().getRandom();
                        if (random.nextFloat() < KQConfigValues.WITHERSET_WITHER_CHANCE.getFloat())
                            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 120, 0, false, false, false));
                    }
            }

            return true;

        }
    }

    public static class OnEntityJoinWorldEvent implements ServerEntityEvents.Load {

        @Override
        public void onLoad(Entity entity, ServerLevel world) {
            if (KQConfigValues.SKELETONSET.getBoolean())
                if (entity instanceof AbstractArrow arrow)
                    if (arrow.getOwner() instanceof Player player && KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SKELETONSET)) {}
                        //arrow.setPierceLevel((byte) 5);
        }

    }

    public static class OnEntityDeathWorldEvent implements ServerLivingEntityEvents.AfterDeath {

        @Override
        public void afterDeath(LivingEntity entity, DamageSource damageSource) {
            if (entity != null && damageSource.getEntity() instanceof Player player) {

                if (KQConfigValues.CONQUISTADORSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.CONQUISTADORSET)) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1, false, true, true));
                    }

                if (KQConfigValues.WITCH.getBoolean())
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

            if (KQConfigValues.TENGU_HELMET.getBoolean() && player.getInventory().getArmor(3).getItem() == KnightQuestItems.TENGU_HELMET.get()) {

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

                if (KQConfigValues.HUSKSET.getBoolean())
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

                if (KQConfigValues.BAMBOOSET_BLUE.getBoolean())
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

                if (KQConfigValues.WARLORDSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.WARLORDSET)) {
                        for (Entity entity : player.level().getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(KQConfigValues.WARLORD_SET_EFFECT_RADIUS.getFloat()))) {
                            if (KQConfigValues.SHOULD_WARLORD_SET_EFFECT_APPLY_TO_ITSELF.getBoolean()) {
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

                    if (KQConfigValues.ZOMBIESET.getBoolean())
                        if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.ZOMBIESET) && player.level().isNight()) {
                            if (player.tickCount % KQConfigValues.ZOMBIESET_HEALING_TICKS.getInt() == 0) {
                                player.heal(KQConfigValues.ZOMBIESET_HEALING_AMOUNT.getFloat());
                            }
                        }

                    if (KQConfigValues.APPLE_SET.getBoolean())
                        if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.APPLE_SET)) {
                            if (player.tickCount % 400 == 0) {
                                player.heal(1.0F);
                            }
                        }

                }

                if (KQConfigValues.POLAR.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.POLAR)) {
                        player.setTicksFrozen(0);
                    }

                if (KQConfigValues.SILVERFISHSET.getBoolean())
                    if (KQFullSetChecker.hasFullSetOn(player, KQArmorMaterials.SILVERFISHSET) && player.getY() < (int) KQConfigValues.SILVERFISH_EFFECT_MAX_HEIGHT.getInt()) {
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
