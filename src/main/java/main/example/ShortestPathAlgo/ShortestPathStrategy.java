package main.example.ShortestPathAlgo;

import java.util.ArrayList;

public interface ShortestPathStrategy {
    String shortestPath(int start, int end);
    int shortestPathDistance(int start, int end);
    void shortestPathRun(ArrayList<ArrayList<Integer>> Graph, int start);
}
