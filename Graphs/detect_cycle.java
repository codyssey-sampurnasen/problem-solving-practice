import java.util.*;

public class CycleDetection {
    public static boolean hasCycle(int node, int parent, boolean[] visited, List<List<Integer>> graph) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (hasCycle(neighbor, node, visited, graph)) return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }
}
