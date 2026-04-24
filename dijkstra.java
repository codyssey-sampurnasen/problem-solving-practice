import java.util.*;

class Dijkstra {
    static class Pair {
        int node, distance;
        Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void dijkstra(List<List<Pair>> graph, int source) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));

        dist[source] = 0;
        pq.add(new Pair(source, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.node;

            for (Pair neighbor : graph.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.distance;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        System.out.println("Shortest distances from source:");
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + " -> " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges
        graph.get(0).add(new Pair(1, 2));
        graph.get(0).add(new Pair(2, 4));
        graph.get(1).add(new Pair(2, 1));
        graph.get(1).add(new Pair(3, 7));
        graph.get(2).add(new Pair(4, 3));
        graph.get(3).add(new Pair(4, 1));

        dijkstra(graph, 0);
    }
}
