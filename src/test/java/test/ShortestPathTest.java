package main.example;

import main.example.ShortestPathAlgo.*;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShortestPathTest {

    private ArrayList<ArrayList<Pair<Integer, Integer>>> readGraphFromFile(String filePath, int[] nodes) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        nodes[0] = n;

        ArrayList<ArrayList<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);
            graph.get(u).add(new Pair<>(v, w));
        }
        return graph;
    }

    private void runAllTestsOnGraph(String filePath) throws IOException {
        int[] nodes = new int[1];
        ArrayList<ArrayList<Pair<Integer, Integer>>> graph = readGraphFromFile(filePath, nodes);
        int start = 1;

        boolean hasNegativeWeight = false;
        for (ArrayList<Pair<Integer, Integer>> edges : graph) {
            for (Pair<Integer, Integer> edge : edges) {
                if (edge.second < 0) {
                    hasNegativeWeight = true;
                    break;
                }
            }
            if (hasNegativeWeight) break;
        }

        // Dijkstra (only if no negative weights)
        if (!hasNegativeWeight) {
            long startTime = System.nanoTime();
            ShortestPathStrategy dijkstra = new Dijkstra();
            dijkstra.shortestPathRun(graph, start);
            long endTime = System.nanoTime();
            System.out.println("Dijkstra Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            System.out.println("Dijkstra Distance Path - : "+ dijkstra.shortestPathDistance(1,nodes[0]));
            System.out.println("Dijkstra - : " + dijkstra.shortestPath(1, nodes[0]));
            assertFalse(dijkstra.hasNegativeCycle());
        } else {
            System.out.println("Dijkstra skipped due to negative edge weights.");
        }

        System.out.println("+++++++++++++++++++++++++++++++++");;

        // Bellman-Ford
        long bfStart = System.nanoTime();
        ShortestPathStrategy bellmanFord = new BellmanFord();
        bellmanFord.shortestPathRun(graph, start);
        long bfEnd = System.nanoTime();
        System.out.println("Bellman-Ford Time: " + (bfEnd - bfStart) / 1_000_000.0 + " ms");
        System.out.println("Bellman-Ford Distance - : "+ bellmanFord.shortestPathDistance(1,nodes[0]));
        System.out.println("Bellman-Ford Path - : " + bellmanFord.shortestPath(1, nodes[0]));
        System.out.println("Has Negative Cycle: " + bellmanFord.hasNegativeCycle());

        System.out.println("+++++++++++++++++++++++++++++++++");

        // Floyd-Warshall
        long fwStart = System.nanoTime();
        ShortestPathStrategy floyd = new floydWarshall();
        floyd.shortestPathRun(graph, start);
        long fwEnd = System.nanoTime();
        System.out.println("Floyd-Warshall Time: " + (fwEnd - fwStart) / 1_000_000.0 + " ms");
        System.out.println("Floyd-Warshall Distance - : "+ floyd.shortestPathDistance(1,nodes[0]));
        System.out.println("Floyd-Warshall Path - : " + floyd.shortestPath(1, nodes[0]));
        System.out.println("Has Negative Cycle: " + floyd.hasNegativeCycle());

    }


    @Test
    public void testAllGraphs() throws IOException {

        for (int count = 1; count <= 6; count++) {
            System.out.println("TestCase " + count);
            runAllTestsOnGraph("C:\\Users\\kero samy\\OneDrive - Alexandria University\\Desktop\\shortest path algorithms\\src\\main\\java\\main\\example\\ShortestPathAlgo\\TestCases\\Graph " + count);
            System.out.println("------------------------------------------------------------------------");
        }
    }
}
