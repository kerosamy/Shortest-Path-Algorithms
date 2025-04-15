package main.example;

import main.example.ShortestPathAlgo.BellmanFord;
import main.example.ShortestPathAlgo.Pair;

import main.example.ShortestPathAlgo.Dijkstra;

import main.example.ShortestPathAlgo.floydWarshall;

import java.io.BufferedReader;
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

        BufferedReader br = new BufferedReader(new FileReader("/media/caroline/New Volume/4th term/Data structures/lab2/Shortest-Path-Algorithms/src/main/java/main/example/ShortestPathAlgo/Dijkstra"));


        BufferedReader br = new BufferedReader(new FileReader("/home/ayman-kotb/Shortest-Path-Algorithms/src/main/java/main/example/ShortestPathAlgo/be"));

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

        Dijkstra dijkstra = new Dijkstra();
        int start = 1;
        dijkstra.shortestPathRun(adjList, start);
        int end = 5;
        System.out.println("Distance from " + start + " to " + end + ": " + dijkstra.shortestPathDistance(start, end));
        System.out.println("Path from " + start + " to " + end + ": " + dijkstra.shortestPath(start, end));

        Dijkstra dijkstra1 = new Dijkstra();
        int start1 = 2;
        dijkstra1.shortestPathRun(adjList, start1);
        int end1 = 5;
        System.out.println("Distance from " + start1 + " to " + end1 + ": " + dijkstra1.shortestPathDistance(start1, end1));
        System.out.println("Path from " + start1 + " to " + end1 + ": " + dijkstra1.shortestPath(start1, end1));

        Dijkstra dijkstra2 = new Dijkstra();
        int start2 = 10;
        dijkstra2.shortestPathRun(adjList, start2);
        int end2 = 5;
        System.out.println("Distance from " + start2 + " to " + end2+ ": " + dijkstra2.shortestPathDistance(start2, end2));
        System.out.println("Path from " + start2 + " to " + end2 + ": " + dijkstra2.shortestPath(start2, end2));

        Dijkstra dijkstra3= new Dijkstra();
        int start3 = 7;
        dijkstra3.shortestPathRun(adjList, start3);
        int end3 = 8;
        System.out.println("Distance from " + start3 + " to " + end3 + ": " + dijkstra3.shortestPathDistance(start3, end3));
        System.out.println("Path from " + start3 + " to " + end3 + ": " + dijkstra3.shortestPath(start3, end3));

        floydWarshall fw = new floydWarshall();
        fw.shortestPathRun(adjList, 1);


        BellmanFord bolbol = new BellmanFord();
        int start = 3;
        bolbol.shortestPathRun(adjList, start);
        int end =9;
        System.out.println("Distance from " + start + " to " + end + ": " + bolbol.shortestPathDistance(start, end));
        System.out.println("Path from " + start + " to " + end + ": " + bolbol.shortestPath(start, end));

    }
}