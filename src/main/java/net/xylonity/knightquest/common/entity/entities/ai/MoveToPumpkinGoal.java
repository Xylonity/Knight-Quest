package net.xylonity.knightquest.common.entity.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.xylonity.knightquest.common.entity.entities.SamhainEntity;

import java.util.EnumSet;
import java.util.Random;

public class MoveToPumpkinGoal extends Goal {

    private final SamhainEntity entity;
    private final double speed;
    private BlockPos targetPumpkinPos;
    private final int searchRadius = 10;
    private final double circleRadius = 3.0;
    private double angle = 0;
    private final double angleIncrement = Math.PI / 8;
    private final Random random = new Random();
    private int ticksCircling = 0;
    private final int maxTicksCircling = 80;

    public MoveToPumpkinGoal(SamhainEntity entity, double speed) {
        this.entity = entity;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        BlockPos entityPos = entity.blockPosition();
        Level level = entity.level;

        targetPumpkinPos = findNearestPumpkin(level, entityPos, searchRadius);
        return targetPumpkinPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        return targetPumpkinPos != null && entity.level.getBlockState(targetPumpkinPos).is(Blocks.PUMPKIN);
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
            if (ticksCircling < maxTicksCircling) {
                if (entity.getNavigation().isDone()) {
                    angle += angleIncrement;
                    moveToNextPosition();
                }
                ticksCircling++;
                if (random.nextInt(20) == 0) {
                    entity.getJumpControl().jump();
                }
            } else {
                entity.getNavigation().moveTo(targetPumpkinPos.getX() + 0.5, targetPumpkinPos.getY(), targetPumpkinPos.getZ() + 0.5, speed);
            }
        }
    }

    private void moveToNextPosition() {
        double xOffset = targetPumpkinPos.getX() + circleRadius * Math.cos(angle);
        double zOffset = targetPumpkinPos.getZ() + circleRadius * Math.sin(angle);
        entity.getLookControl().setLookAt(targetPumpkinPos.getX(), targetPumpkinPos.getY(), targetPumpkinPos.getZ());
        entity.getNavigation().moveTo(xOffset, targetPumpkinPos.getY(), zOffset, speed);
    }

    private BlockPos findNearestPumpkin(Level level, BlockPos entityPos, int radius) {
        for (BlockPos pos : BlockPos.betweenClosed(entityPos.offset(-radius, -2, -radius), entityPos.offset(radius, 2, radius))) {
            if (level.getBlockState(pos).is(Blocks.PUMPKIN)) {
                return pos;
            }
        }
        return null;
    }

}