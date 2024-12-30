package net.xylonity.knightquest.common.ai.navigator;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.*;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class GroundNavigator extends GroundPathNavigation {

    private static final float THETA = 1.0E-8F;

    private final Cache<BlockPos, Boolean> cache = CacheBuilder.newBuilder().maximumSize(10000).expireAfterAccess(5, TimeUnit.SECONDS).build();

    public GroundNavigator(Mob entity, Level world) {
        super(entity, world);
    }

    @Override
    protected @NotNull PathFinder createPathFinder(int maxVisitedNodes) {
        this.nodeEvaluator = new WalkNodeEvaluator();
        this.nodeEvaluator.setCanPassDoors(true);
        return new BonusPathFinder(this.nodeEvaluator, maxVisitedNodes);
    }

    @Override
    protected void followThePath() {
        if (this.path == null || this.path.isDone()) return;

        Vec3 entityPos = this.getTempMobPos();
        int nextNodeIndex = this.path.getNextNodeIndex();
        double entityYFloor = Math.floor(entityPos.y);
        int pathLength = this.path.getNodeCount();

        int lastNodeIndex = nextNodeIndex;
        while (lastNodeIndex < pathLength && this.path.getNode(lastNodeIndex).y == entityYFloor) {
            lastNodeIndex++;
        }

        Vec3 base = entityPos.subtract(this.mob.getBbWidth() * 0.5F, 0.0F, this.mob.getBbWidth() * 0.5F);

        if (this.attemptShortcut(this.path, entityPos, lastNodeIndex, base)) {
            if (this.hasReached(this.path, 0.6F) ||
                    (this.isAtElevationChange(this.path) && this.hasReached(this.path, this.mob.getBbWidth() * 0.5F))) {
                this.path.advance();
            }
        }

        this.doStuckDetection(entityPos);
    }

    private boolean hasReached(Path path, float threshold) {
        Vec3 pathPos = path.getNextEntityPos(this.mob);
        return Math.abs(this.mob.getX() - pathPos.x) < threshold &&
                Math.abs(this.mob.getZ() - pathPos.z) < threshold &&
                Math.abs(this.mob.getY() - pathPos.y) < 1.0D;
    }

    private boolean isAtElevationChange(Path path) {
        int currIndex = path.getNextNodeIndex();
        int endIndex = Math.min(path.getNodeCount(), currIndex + Mth.ceil(this.mob.getBbWidth() * 0.5F) + 1);
        int currY = path.getNode(currIndex).y;

        for (int i = currIndex + 1; i < endIndex; i++) {
            if (path.getNode(i).y != currY) {
                return true;
            }
        }

        return false;
    }

    private boolean attemptShortcut(Path path, Vec3 entityPos, int pathLength, Vec3 base) {
        int nextNodeIndex = path.getNextNodeIndex();

        for (int i = pathLength - 1; i > nextNodeIndex; i--) {
            Vec3 vec = path.getEntityPosAtNode(this.mob, i).subtract(entityPos);
            if (this.catchF(vec, base)) {
                path.setNextNodeIndex(i);
                return false;
            }
        }

        return true;
    }

    private boolean catchF(Vec3 vec, Vec3 base) {
        float maxT = (float) vec.length();
        if (maxT < THETA) return true;

        float dx = (float) vec.x / maxT;
        float dy = (float) vec.y / maxT;
        float dz = (float) vec.z / maxT;

        float tNextX, tNextY, tNextZ;
        float tDeltaX, tDeltaY, tDeltaZ;
        int stepX, stepY, stepZ;
        int currentX, currentY, currentZ;

        if (Math.abs(dx) < THETA) {
            tDeltaX = Float.POSITIVE_INFINITY;
            tNextX = Float.POSITIVE_INFINITY;
            stepX = 0;
        } else {
            stepX = dx > 0 ? 1 : -1;
            float voxelBoundaryX = stepX > 0 ? Mth.floor(base.x) + 1 : Mth.floor(base.x);
            tDeltaX = 1.0F / Math.abs(dx);
            tNextX = (float) ((voxelBoundaryX - base.x) / dx);
        }

        currentX = Mth.floor(base.x);

        if (Math.abs(dy) < THETA) {
            tDeltaY = Float.POSITIVE_INFINITY;
            tNextY = Float.POSITIVE_INFINITY;
            stepY = 0;
        } else {
            stepY = dy > 0 ? 1 : -1;
            float voxelBoundaryY = stepY > 0 ? Mth.floor(base.y) + 1 : Mth.floor(base.y);
            tDeltaY = 1.0F / Math.abs(dy);
            tNextY = (float) ((voxelBoundaryY - base.y) / dy);
        }

        currentY = Mth.floor(base.y);

        if (Math.abs(dz) < THETA) {
            tDeltaZ = Float.POSITIVE_INFINITY;
            tNextZ = Float.POSITIVE_INFINITY;
            stepZ = 0;
        } else {
            stepZ = dz > 0 ? 1 : -1;
            float voxelBoundaryZ = stepZ > 0 ? Mth.floor(base.z) + 1 : Mth.floor(base.z);
            tDeltaZ = 1.0F / Math.abs(dz);
            tNextZ = (float) ((voxelBoundaryZ - base.z) / dz);
        }

        currentZ = Mth.floor(base.z);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        float t = 0.0F;

        while (t <= maxT) {
            if (tNextX < tNextY) {
                if (tNextX < tNextZ) {
                    currentX += stepX;
                    t = tNextX;
                    tNextX += tDeltaX;
                } else {
                    currentZ += stepZ;
                    t = tNextZ;
                    tNextZ += tDeltaZ;
                }
            } else {
                if (tNextY < tNextZ) {
                    currentY += stepY;
                    t = tNextY;
                    tNextY += tDeltaY;
                } else {
                    currentZ += stepZ;
                    t = tNextZ;
                    tNextZ += tDeltaZ;
                }
            }

            pos.set(currentX, currentY, currentZ);
            BlockPos immutablePos = pos.immutable();

            Boolean isPathfindable = cache.getIfPresent(immutablePos);
            if (isPathfindable == null) {
                BlockState blockState = this.level.getBlockState(pos);
                isPathfindable = blockState.isPathfindable(this.level, pos, PathComputationType.LAND);
                cache.put(immutablePos, isPathfindable);
            }

            if (!isPathfindable)
                return false;

            BlockPathTypes pathType;
            int mobWidth = Mth.ceil(this.mob.getBbWidth());
            int mobHeight = Mth.ceil(this.mob.getBbHeight());
            boolean canOpenDoors = this.nodeEvaluator.canOpenDoors();
            boolean canEnterDoors = this.nodeEvaluator.canPassDoors();

            pathType = this.nodeEvaluator.getBlockPathType(this.level, currentX, currentY, currentZ, this.mob, mobWidth, mobHeight, 1, canOpenDoors, canEnterDoors);

            float malus = this.mob.getPathfindingMalus(pathType);

            if (malus < 0.0F || malus >= 8.0F || pathType == BlockPathTypes.DAMAGE_FIRE || pathType == BlockPathTypes.DANGER_FIRE || pathType == BlockPathTypes.DAMAGE_OTHER)
                return false;

        }
        return true;
    }
}