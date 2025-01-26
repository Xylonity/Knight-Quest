package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class NethermanTeleport2PlayerGoal extends Goal {
    private final NethermanEntity netherman;
    private int idleTime;

    public NethermanTeleport2PlayerGoal(NethermanEntity netherman) {
        this.netherman = netherman;
    }

    @Override
    public boolean canUse() {
        return this.netherman.getTarget() == null && (
                (this.netherman.getPhase() == 1 && this.netherman.tickCount > 100) ||
                (this.netherman.getPhase() == 2 && this.netherman.getCounterSwitchPhase2() == 130) ||
                (this.netherman.getPhase() == 3 && this.netherman.getCounterSwitchPhase3() == 160)
        );
    }

    @Override
    public void start() {
        this.idleTime = 100;
    }

    @Override
    public void stop() {
        this.idleTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    private boolean isValidTeleportPosition(BlockPos pos) {
        if (this.netherman.level().getBlockState(pos).getBlock() != Blocks.AIR) {
            return false;
        }

        BlockPos blockBelow = pos.below();
        BlockState stateBelow = this.netherman.level().getBlockState(blockBelow);
        if (stateBelow.getBlock() == Blocks.AIR || stateBelow.getBlock() == Blocks.LAVA || stateBelow.getBlock() == Blocks.WATER) {
            return false;
        }

        AABB boundingBox = new AABB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 0.5, pos.getY() + this.netherman.getBbHeight(), pos.getZ() + 0.5);
        return this.netherman.level().noCollision(this.netherman, boundingBox);
    }

    private void teleportNearPlayer() {
        List<? extends Player> players = this.netherman.level().players();
        Player closestPlayer = players.stream()
                .filter(player -> !player.isCreative() && !player.isSpectator())
                .min((p1, p2) -> Double.compare(p1.distanceTo(this.netherman), p2.distanceTo(this.netherman)))
                .orElse(null);

        if (closestPlayer != null) {
            RandomSource random = this.netherman.getRandom();
            for (int attempt = 0; attempt < 50; attempt++) {
                double angle = random.nextDouble() * 2 * Math.PI;
                double distance = 3 + random.nextDouble() * 7;
                double x = closestPlayer.getX() + Math.cos(angle) * distance;
                double z = closestPlayer.getZ() + Math.sin(angle) * distance;
                double y = closestPlayer.getY() + (random.nextDouble() - 0.5) * 2;

                BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);
                if (isValidTeleportPosition(targetPos)) {
                    this.netherman.level().playSound(null, this.netherman.getX(), this.netherman.getY(), this.netherman.getZ(), SoundEvents.ENDERMAN_TELEPORT, this.netherman.getSoundSource(), 1.0F, 1.0F);
                    this.netherman.teleportTo(x, y, z);
                    this.netherman.level().playSound(null, x, y, z, SoundEvents.ENDERMAN_TELEPORT, this.netherman.getSoundSource(), 1.0F, 1.0F);
                    return;
                }
            }
        }
    }

    @Override
    public void tick() {
        if (this.idleTime > 0) {
            --this.idleTime;
        }

        if (this.idleTime == 0) {
            teleportNearPlayer();
            this.idleTime = 100;
        }
    }
}