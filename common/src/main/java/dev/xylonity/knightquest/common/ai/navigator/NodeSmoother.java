package dev.xylonity.knightquest.common.ai.navigator;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NodeSmoother {

    public static List<Node> copyAndSmooth(@NotNull Path original) {
        List<Node> nodes = new ArrayList<>(original.getNodeCount());

        for (int i = 0; i < original.getNodeCount(); i++) {
            nodes.add(original.getNode(i));
        }

        return smoothNodes(nodes);
    }

    private static List<Node> smoothNodes(List<Node> nodes) {
        if (nodes.size() < 3) return ImmutableList.copyOf(nodes);

        List<Node> result = new ArrayList<>();
        result.add(nodes.get(0));

        for (int i = 1; i < nodes.size() - 1; i++) {
            Node prev = result.get(result.size() - 1);
            Node curr = nodes.get(i);
            Node next = nodes.get(i + 1);

            if (!isNearlyCollinear(prev, curr, next)) {
                result.add(curr);
            }
        }

        result.add(nodes.get(nodes.size() - 1));
        return ImmutableList.copyOf(result);
    }

    private static boolean isNearlyCollinear(Node prev, Node curr, Node next) {
        double v1x = curr.x - prev.x;
        double v1y = curr.y - prev.y;
        double v1z = curr.z - prev.z;

        double v2x = next.x - curr.x;
        double v2y = next.y - curr.y;
        double v2z = next.z - curr.z;

        double len1 = Math.sqrt(v1x * v1x + v1y * v1y + v1z * v1z);
        double len2 = Math.sqrt(v2x * v2x + v2y * v2y + v2z * v2z);

        if (len1 < 1e-6 || len2 < 1e-6) {
            return true;
        }

        double dot = v1x * v2x + v1y * v2y + v1z * v2z;
        double cos = dot / (len1 * len2);

        cos = Math.max(-1.0, Math.min(1.0, cos));

        double angle = Math.toDegrees(Math.acos(cos));
        return angle < 15F;
    }
}