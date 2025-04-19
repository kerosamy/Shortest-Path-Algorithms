package main.example.ShortestPathAlgo;

import java.util.ArrayList;

public interface ShortestPathStrategy {
    String shortestPath(int start, int end);
    String shortestPathDistance(int start, int end);
    void shortestPathRun(ArrayList<ArrayList<Pair<Integer,Integer>>> Graph, int start);
    boolean hasNegativeCycle();
}
