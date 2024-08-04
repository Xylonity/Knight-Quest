package net.xylonity.common.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeleportValidator {

    public static boolean isValidTeleportPosition(Entity entity, BlockPos pos) {

        World level = entity.getWorld();

        if (level.getBlockState(pos).getBlock() != Blocks.AIR) {
            return false;
        }

        BlockPos blockBelow = pos.down();
        BlockState stateBelow = level.getBlockState(blockBelow);
        if (stateBelow.getBlock() == Blocks.AIR || stateBelow.getBlock() == Blocks.LAVA || stateBelow.getBlock() == Blocks.WATER) {
            return false;
        }

        Box boundingBox = new Box(pos.getX() - 0.5, pos.getY(), pos.getZ() - 0.5, pos.getX() + 0.5, pos.getY() + entity.getHeight(), pos.getZ() + 0.5);
        return level.canCollide(entity, boundingBox);
    }

    public static boolean isBetterPosition(Entity entity, BlockPos pos, BlockPos bestPos) {
        double currentDistance = entity.getPos().distanceTo(Vec3d.ofCenter(pos));
        double bestDistance = entity.getPos().distanceTo(Vec3d.ofCenter(bestPos));
        return currentDistance < bestDistance;
    }

}
