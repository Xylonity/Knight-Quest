package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.config.values.KQConfigValues;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NailWeapon extends KQWeaponItem {

    private static final Map<UUID, Boolean> doubleJumpStates = new HashMap<>();

    public NailWeapon(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void interaction(Level level, Player player, InteractionHand hand) {
        boolean canDash = doubleJumpStates.getOrDefault(player.getUUID(), true);

        if (canDash) {
            handleClientSideDoubleJump(player);
        }

        doubleJumpStates.put(player.getUUID(), true);
    }

    @Override
    public int getCooldownTicks() {
        return KQConfigValues.COOLDOWN_NAIL;
    }

    @Override
    public String getName() {
        return "nail";
    }

    @Override
    protected boolean isEnabled() {
        return KQConfigValues.NAIL;
    }

    private static void handleClientSideDoubleJump(Player player) {
        boolean canDash = doubleJumpStates.getOrDefault(player.getUUID(), true);

        if (canDash) {
            if (player.level().isClientSide) {
                doubleJumpStates.put(player.getUUID(), false);

                double dashSpeed = KQConfigValues.DASH_POWER_NAIL;

                player.setDeltaMovement(player.getLookAngle().scale(dashSpeed));
            }

            if (!player.level().isClientSide && player.level() instanceof ServerLevel level) {
                Vec3 playerPos = player.position().add(0, 1.0, 0);
                Vec3 dashDirection = player.getLookAngle().scale(0.5);

                for (int i = 0; i < 20; i++) {
                    double randomOffsetX = (Math.random() - 0.5) * 0.3;
                    double randomOffsetY = (Math.random() - 0.5) * 0.1;
                    double randomOffsetZ = (Math.random() - 0.5) * 0.3;

                    Vec3 particlePos = playerPos.add(randomOffsetX, randomOffsetY, randomOffsetZ);

                    level.sendParticles(
                            ParticleTypes.CLOUD,
                            particlePos.x,
                            particlePos.y,
                            particlePos.z,
                            1,
                            dashDirection.x,
                            dashDirection.y,
                            dashDirection.z,
                            0.1
                    );
                }
            }
        }

    }

}
