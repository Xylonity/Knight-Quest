package net.xylonity.common.entity.boss.ai;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.xylonity.common.entity.boss.NethermanEntity;

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
    public boolean canStart() {
        return true;
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

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    private boolean isValidTeleportPosition(BlockPos pos) {
        if (this.netherman.getWorld().getBlockState(pos).getBlock() != Blocks.AIR) {
            return false;
        }

        BlockPos blockBelow = pos.down();
        BlockState stateBelow = this.netherman.getWorld().getBlockState(blockBelow);
        if (stateBelow.getBlock() == Blocks.AIR || stateBelow.getBlock() == Blocks.LAVA || stateBelow.getBlock() == Blocks.WATER) {
            return false;
        }

        Box boundingBox = new Box(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 0.5, pos.getY() + this.netherman.getHeight(), pos.getZ() + 0.5);
        return this.netherman.getWorld().canCollide(this.netherman, boundingBox);
    }

    private boolean isBetterPosition(BlockPos pos, BlockPos bestPos) {
        double currentDistance = this.netherman.getPos().distanceTo(Vec3d.ofCenter(pos));
        double bestDistance = this.netherman.getPos().distanceTo(Vec3d.ofCenter(bestPos));
        return currentDistance < bestDistance;
    }

    private void teleportAroundTarget() {
        BlockPos bestPos = null;
        Entity target = this.netherman.getTarget();
        Random random = this.netherman.getRandom();

        for (int attempt = 0; attempt < 50; attempt++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = 5 + random.nextDouble() * 15;
            assert target != null;
            double x = target.getX() + Math.cos(angle) * distance;
            double z = target.getZ() + Math.sin(angle) * distance;
            double y = target.getY() + (random.nextDouble() - 0.5) * 2;

            BlockPos targetPos = new BlockPos((int) x, (int) y, (int) z);
            if (isValidTeleportPosition(targetPos)) {
                BlockPos blockBelow = targetPos.down();
                this.netherman.saveBlockState(blockBelow);
                this.netherman.getWorld().setBlockState(blockBelow, Blocks.LAVA.getDefaultState(), 3);

                for (PlayerEntity player : this.netherman.getWorld().getPlayers()) {
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        for (int u = 0; u < 20; ++u) {
                            double speed = 0.1 + this.netherman.getRandom().nextDouble() * 0.2;
                            double ux = this.netherman.getX() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                            double uy = this.netherman.getY() + this.netherman.getEyeY() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;
                            double uz = this.netherman.getZ() + (this.netherman.getRandom().nextDouble() - 0.5) * 0.2;

                            Vec3d look = this.netherman.getRotationVector();

                            double vx = look.x * speed;
                            double vy = look.y * speed;
                            double vz = look.z * speed;

                            ParticleS2CPacket particlePacket = new ParticleS2CPacket(
                                    ParticleTypes.FLAME,
                                    true,
                                    ux, uy, uz,
                                    (float) vx, (float) vy, (float) vz,
                                    0.3f,
                                    4
                            );
                            serverPlayer.networkHandler.sendPacket(particlePacket);
                        }
                    }
                }

                this.netherman.getWorld().playSound(null, this.netherman.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1f, 1f);
                this.netherman.teleport(x, y, z, true);
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

            if (livingentity.distanceTo(this.netherman) < 4096.0D && this.netherman.canSee(livingentity)) {

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