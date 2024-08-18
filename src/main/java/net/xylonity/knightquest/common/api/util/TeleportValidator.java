package net.xylonity.knightquest.common.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TeleportValidator {

    public static boolean isValidTeleportPosition(Entity entity, BlockPos pos) {

        Level level = entity.level;

        if (level.getBlockState(pos).getBlock() != Blocks.AIR) {
            return false;
        }

        BlockPos blockBelow = pos.below();
        BlockState stateBelow = level.getBlockState(blockBelow);
        if (stateBelow.getBlock() == Blocks.AIR || stateBelow.getBlock() == Blocks.LAVA || stateBelow.getBlock() == Blocks.WATER) {
            return false;
        }

        AABB boundingBox = new AABB(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 0.5, pos.getY() + entity.getBbHeight(), pos.getZ() + 0.5);
        return level.noCollision(entity, boundingBox);
    }

    public static boolean isBetterPosition(Entity entity, BlockPos pos, BlockPos bestPos) {
        double currentDistance = entity.position().distanceTo(Vec3.atCenterOf(pos));
        double bestDistance = entity.position().distanceTo(Vec3.atCenterOf(bestPos));
        return currentDistance < bestDistance;
    }

}
