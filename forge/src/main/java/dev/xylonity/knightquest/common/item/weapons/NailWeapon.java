package dev.xylonity.knightquest.common.item.weapons;

import dev.xylonity.knightquest.common.item.KQWeaponItem;
import dev.xylonity.knightquest.common.material.KQItemMaterials;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

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
        return 0;
    }

    @Override
    public String getName() {
        return "nail";
    }

    private static void handleClientSideDoubleJump(Player player) {
        boolean canDash = doubleJumpStates.getOrDefault(player.getUUID(), true);

        if (canDash) {
            if (player.level().isClientSide) {
                doubleJumpStates.put(player.getUUID(), false);

                double dashSpeed = 1.5;

                player.setDeltaMovement(player.getLookAngle().scale(dashSpeed));
            }

            if (!player.level().isClientSide && player.level() instanceof ServerLevel level) {
                for (int i = 0; i < 360; i += 30) {
                    double angleRadians = Math.toRadians(i);

                    double particleX = player.getX() + 0.4 * Math.cos(angleRadians);
                    double particleZ = player.getZ() + 0.4 * Math.sin(angleRadians);

                    level.sendParticles(ParticleTypes.CLOUD, particleX, player.getY(), particleZ, 1, 0d, 0.35d, 0d, 0d);
                }
            }
        }

    }

}
