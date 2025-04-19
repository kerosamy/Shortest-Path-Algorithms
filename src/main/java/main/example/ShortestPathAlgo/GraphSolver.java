package main.example.ShortestPathAlgo;

import java.util.ArrayList;

public class GraphSolver {
    private ShortestPathStrategy strategy;

    public void setStrategy(ShortestPathStrategy strategy) {
        this.strategy = strategy;
    }

    public void run(ArrayList<ArrayList<Pair<Integer, Integer>>> graph, int start) {
        strategy.shortestPathRun(graph, start);
    }

    public String getPath(int start, int end) {
        return strategy.shortestPath(start, end);
    }

    public String getDistance(int start, int end) {
        return strategy.shortestPathDistance(start, end);
    }
}
