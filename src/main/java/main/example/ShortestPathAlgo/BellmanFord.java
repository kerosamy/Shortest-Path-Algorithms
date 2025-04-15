package main.example.ShortestPathAlgo;

import java.util.*;

public class BellmanFord implements ShortestPathStrategy {
    private int[] dist;
    private int[] parent;
    private int n;

    @Override
    public void shortestPathRun(ArrayList<ArrayList<Pair<Integer,Integer>>> graph, int start) {
        this.n = graph.size();
        dist = new int[n];
        parent = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[start] = 0;

        // Relaxation process: Repeat for (n-1) times
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (Pair<Integer, Integer> edge : graph.get(u)) {
                    int v = edge.first;
                    int weight = edge.second;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        parent[v] = u;
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u = 0; u < n; u++) {
            for (Pair<Integer, Integer> edge : graph.get(u)) {
                int v = edge.first;
                int weight = edge.second;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    // If we find a shorter path, it means there is a negative cycle
                    System.out.println("Graph contains a negative weight cycle");
                    return;
                }
            }
        }
    }

    @Override
    public String shortestPath(int start, int end) {
        if (dist[end] == Integer.MAX_VALUE) {
            return "No Path";
        }

        List<Integer> path = new ArrayList<>();
        int x = end;

        // Trace the path from end to start using the parent array
        while (x != -1) {
            path.add(x);
            x = parent[x];
        }

        Collections.reverse(path);  // Reverse to get the path from start to end
        return path.toString();
    }

    @Override
    public int shortestPathDistance(int start, int end) {
        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }
}
