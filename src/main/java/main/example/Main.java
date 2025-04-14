package main.example;

import main.example.ShortestPathAlgo.BellmanFord;
import main.example.ShortestPathAlgo.Pair;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
       //for dijkstra
        int numberOfNodes = 12+1;
        ArrayList<ArrayList<Pair<Integer, Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < numberOfNodes; i++) {
            adjList.add(new ArrayList<>());
        }
        BufferedReader br = new BufferedReader(new FileReader("/home/john/DSA-Labs/Shortest-Path-Algorithms/src/main/java/main/example/ShortestPathAlgo/fluid and bellman ford"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);

            adjList.get(from).add(new Pair<>(to, weight));
        }
        br.close();
        for (int i = 1; i < adjList.size(); i++) {
            System.out.print("Node " + i + " → ");
            for (Pair<Integer, Integer> p : adjList.get(i)) {
                System.out.print("(" + p.first + ", " + p.second + ") ");
            }
            System.out.println();
        }


        BellmanFord bolbol = new BellmanFord();
        int start = 3;
        bolbol.shortestPathRun(adjList, start);
        int end =9;
        System.out.println("Distance from " + start + " to " + end + ": " + bolbol.shortestPathDistance(start, end));
        System.out.println("Path from " + start + " to " + end + ": " + bolbol.shortestPath(start, end));

    }
}