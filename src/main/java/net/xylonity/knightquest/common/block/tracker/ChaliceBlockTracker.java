package net.xylonity.knightquest.common.block.tracker;

import net.minecraft.core.BlockPos;
import net.xylonity.knightquest.common.block.ChaliceBlock;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks the great chalice tick counter for each block instance to avoid inheriting multiple
 * scheduled ticks into a single chalice, which would "kill" in some way vanilla parallelism.
 */

public class ChaliceBlockTracker {
    private static final Map<BlockPos, ChaliceBlock> chaliceMap = new HashMap<>();
    private static final Map<BlockPos, Integer> tickCounts = new HashMap<>();

    public static void addChalice(BlockPos pos, ChaliceBlock chaliceBlock) {
        chaliceMap.put(pos, chaliceBlock);
        tickCounts.put(pos, 0);
    }

    public static void removeChalice(BlockPos pos) {
        chaliceMap.remove(pos);
        tickCounts.remove(pos);
    }

    public static int getTickCount(BlockPos pos) {
        return tickCounts.getOrDefault(pos, 0);
    }

    public static void incrementTickCount(BlockPos pos) {
        tickCounts.put(pos, getTickCount(pos) + 1);
    }

    public static ChaliceBlock getChalice(BlockPos pos) {
        return chaliceMap.get(pos);
    }

    public static void resetTickCount(BlockPos pos) {
        tickCounts.put(pos, 0);
    }
}
