package net.xylonity.common.entity.entities.ai;


import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.xylonity.common.entity.entities.SamhainEntity;

import java.util.EnumSet;
import java.util.Random;

public class MoveToPumpkinGoal extends Goal {

    private final SamhainEntity entity;
    private final double speed;
    private BlockPos targetPumpkinPos;
    private double angle = 0;
    private final Random random = new Random();
    private int ticksCircling = 0;

    public MoveToPumpkinGoal(SamhainEntity entity, double speed) {
        this.entity = entity;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        BlockPos entityPos = entity.getBlockPos();
        World world = entity.getWorld();

        targetPumpkinPos = findNearestPumpkin(world, entityPos);
        return targetPumpkinPos != null;
    }

    @Override
    public boolean shouldContinue() {
        return targetPumpkinPos != null && entity.getWorld().getBlockState(targetPumpkinPos).isOf(Blocks.PUMPKIN);
    }

    @Override
    public void start() {
        if (targetPumpkinPos != null) {
            moveToNextPosition();
            ticksCircling = 0;
        }
    }

    @Override
    public void stop() {
        targetPumpkinPos = null;
    }

    @Override
    public void tick() {
        if (targetPumpkinPos != null) {
            int maxTicksCircling = 80;
            if (ticksCircling < maxTicksCircling) {
                if (entity.getNavigation().isIdle()) {
                    double angleIncrement = Math.PI / 8;
                    angle += angleIncrement;
                    moveToNextPosition();
                }
                ticksCircling++;
                if (random.nextInt(20) == 0) {
                    entity.getJumpControl().setActive();
                }
            } else {
                entity.getNavigation().startMovingTo(targetPumpkinPos.getX() + 0.5, targetPumpkinPos.getY(), targetPumpkinPos.getZ() + 0.5, speed);
            }
        }
    }

    private void moveToNextPosition() {
        double circleRadius = 3.0;
        double xOffset = targetPumpkinPos.getX() + circleRadius * MathHelper.cos((float) angle);
        double zOffset = targetPumpkinPos.getZ() + circleRadius * MathHelper.sin((float) angle);
        entity.getLookControl().lookAt(targetPumpkinPos.getX(), targetPumpkinPos.getY(), targetPumpkinPos.getZ());
        entity.getNavigation().startMovingTo(xOffset, targetPumpkinPos.getY(), zOffset, speed);
    }

    private BlockPos findNearestPumpkin(World world, BlockPos entityPos) {
        for (BlockPos pos : BlockPos.iterate(entityPos.add(-10, -2, -10), entityPos.add(10, 2, 10))) {
            if (world.getBlockState(pos).isOf(Blocks.PUMPKIN)) {
                return pos;
            }
        }
        return null;
    }

}