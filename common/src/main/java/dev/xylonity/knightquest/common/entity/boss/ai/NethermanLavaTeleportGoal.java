package dev.xylonity.knightquest.common.entity.boss.ai;

import dev.xylonity.knightquest.common.entity.boss.NethermanEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class NethermanLavaTeleportGoal extends Goal {
    private final NethermanEntity netherman;
    public int chargeTime;

    public NethermanLavaTeleportGoal(NethermanEntity netherman) {
        this.netherman = netherman;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return this.netherman.getTarget() != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.chargeTime = 300;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.netherman.setCharging(false);
        this.chargeTime = 0;
    }

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

    private boolean isBetterPosition(BlockPos pos, BlockPos bestPos) {
        double currentDistance = this.netherman.position().distanceTo(Vec3.atCenterOf(pos));
        double bestDistance = this.netherman.position().distanceTo(Vec3.atCenterOf(bestPos));
        return currentDistance < bestDistance;
    }

    private void teleportAroundTarget() {
        BlockPos bestPos = null;
        Entity target = this.netherman.getTarget();
        RandomSource random = this.netherman.getRandom();

        for (int attempt = 0; attempt < 50; attempt++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = 5 + random.nextDouble() * 15;
            assert target != null;
            double x = target.getX() + Math.cos(angle) * distance;
            double z = target.getZ() + Math.sin(angle) * distance;
            double y = target.getY() + (random.nextDouble() - 0.5) * 2;

            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);
            if (isValidTeleportPosition(targetPos)) {
                BlockPos blockBelow = targetPos.below();
                this.netherman.saveBlockState(blockBelow);
                this.netherman.level().setBlock(blockBelow, Blocks.LAVA.defaultBlockState(), 3);

                for (Player player : this.netherman.level().players()) {
                    if (player instanceof ServerPlayer serverPlayer) {
                        for (int u = 0; u < 20; ++u) {
                            double speed = 0.1 + this.netherman.getRandom().nextDouble() * 0.2;
                            double ux = this.netherman.getX() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                            double uy = this.netherman.getY() + this.netherman.getEyeHeight() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                            double uz = this.netherman.getZ() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;

                            Vec3 look = this.netherman.getLookAngle();

                            double vx = look.x * speed;
                            double vy = look.y * speed;
                            double vz = look.z * speed;

                            serverPlayer.connection.send(new ClientboundLevelParticlesPacket(
                                    ParticleTypes.FLAME,
                                    true,
                                    ux, uy, uz,
                                    (float) vx, (float) vy, (float) vz,
                                    0.3f,
                                    4
                            ));
                        }
                    }
                }

                this.netherman.level().playSound(null, this.netherman.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1f, 1f);
                this.netherman.teleportTo(x, y, z);
                return;
            } else if (bestPos == null || isBetterPosition(targetPos, bestPos)) {
                bestPos = targetPos;
            }
        }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        LivingEntity livingentity = this.netherman.getTarget();
        if (livingentity != null && this.netherman.getPhase() == 1 && this.netherman.getHealth() >= this.netherman.getMaxHealth() * 0.7) {

            if (livingentity.distanceToSqr(this.netherman) < 4096.0D && this.netherman.hasLineOfSight(livingentity)) {

                if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                if (this.chargeTime < 20) {
                    if (chargeTime % 2 == 0) {
                        teleportAroundTarget();
                    }
                }

                if (this.chargeTime == 0) {
                    this.chargeTime = 300;
                }

            } else {
                this.chargeTime = 300;
            }

            this.netherman.setCharging(this.chargeTime > 0);
        } else {
            this.chargeTime = 300;
        }
    }
}