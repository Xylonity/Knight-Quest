package dev.xylonity.knightquest.common.ai.navigator;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.*;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BonusPathFinder extends PathFinder {

    public BonusPathFinder(NodeEvaluator processor, int maxVisitedNodes) {
        super(processor, maxVisitedNodes);
    }

    @Nullable
    @Override
    public Path findPath(@NotNull PathNavigationRegion region, @NotNull Mob mob, @NotNull Set<BlockPos> targetPositions, float maxRange, int accuracy, float searchDepthMultiplier) {
        Path path = super.findPath(region, mob, targetPositions, maxRange, accuracy, searchDepthMultiplier);
        return path == null ? null : new CachedPath(path);
    }

    static class CachedPath extends Path {

        private final Cache<Integer, Vec3> cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(5, TimeUnit.SECONDS).build();

        public CachedPath(Path original) {
            super(copyNodes(original), original.getTarget(), original.canReach());
        }

        @Override
        public @NotNull Vec3 getEntityPosAtNode(@NotNull Entity entity, int index) {
            try {
                return cache.get(index, () -> computeEntityPosAtNode(entity, index));
            } catch (Exception e) {
                return computeEntityPosAtNode(entity, index);
            }
        }

        private Vec3 computeEntityPosAtNode(Entity entity, int index) {
            Node point = this.getNode(index);
            double offsetX = Mth.floor(entity.getBbWidth() + 1.0F) * 0.5D;
            double x = point.x + offsetX;
            double y = point.y;
            double z = point.z + offsetX;
            return new Vec3(x, y, z);
        }

        private static List<Node> copyNodes(Path original) {
            return IntStream.range(0, original.getNodeCount()).mapToObj(original::getNode).collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        }
    }
}