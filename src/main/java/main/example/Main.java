package main.example;

import main.example.ShortestPathAlgo.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static String AlgorithmName ;
    public static GraphSolver  solve (int c , boolean HasNeg){
        GraphSolver solver = new GraphSolver();
        if (HasNeg){
            switch (c){
                case 1:
                    solver.setStrategy(new BellmanFord());
                    AlgorithmName = "Bellman Ford";
                    break;
                 case 2:
                     solver.setStrategy(new floydWarshall());
                     AlgorithmName = "floyd Warshall";
                     break;
            }
        }
        else{
            switch (c){
                case 1:
                    solver.setStrategy(new Dijkstra());
                    AlgorithmName = "Dijkstra";
                    break;
                case 2:
                    solver.setStrategy(new BellmanFord());
                    AlgorithmName = "Bellman Ford";
                    break;
                case 3:
                    solver.setStrategy(new floydWarshall());
                    AlgorithmName = "floyd Warshall";
                    break;
            }
        }
        return solver;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the input file: ");
        String filePath = scanner.nextLine();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String[] firstLine = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        boolean hasNeg = false;
        ArrayList<ArrayList<Pair<Integer, Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            if(weight<0)hasNeg = true;
            adjList.get(from).add(new Pair<>(to, weight));
        }
        br.close();
        while (true){
            System.out.println("Choose the algorithm:");
            if(hasNeg){
                System.out.println("1 - Bellman-Ford");
                System.out.println("2 - Floyd-Warshall");
                System.out.println("3 - Exit");
                System.out.print("Enter your choice (1/2/3): ");
            }
            else {
                System.out.println("1 - Dijkstra");
                System.out.println("2 - Bellman-Ford");
                System.out.println("3 - Floyd-Warshall");
                System.out.println("4 - Exit");
                System.out.print("Enter your choice (1/2/3/4): ");
            }
            int choice = scanner.nextInt();
            if((choice==4&&!hasNeg)||(choice==3&&hasNeg)){break;}
            if((choice>4&&!hasNeg)||(choice>3&&hasNeg)||choice<=0){
                System.out.println("Invalid choice");
            }
            else{
               GraphSolver solver = solve(choice,hasNeg);
               int Source ;
                if((choice==2&&hasNeg)||(choice==3&&!hasNeg)){
                    Source = -1;
                }
                else {
                    System.out.print("Source: ");
                    Source = scanner.nextInt();
                }
                long startTime = System.currentTimeMillis();
                solver.run(adjList,Source);
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println("Running " +AlgorithmName+" take :"+duration+"ms");

               while (true){
                   int start=1 , end=1 ;
                   if(Source==-1) {
                       System.out.print("Source:");
                       start = scanner.nextInt();
                   }
                       System.out.println("For Exit input -1");
                       System.out.print("Destination: ");
                       end = scanner.nextInt();
                       if(end==-1)break;

                   if(start>n||end>n||start<=0||end<=0){
                       System.out.println("Invalid choice");
                       continue;
                   }
                   System.out.println("Choose what to display:");
                   System.out.println("1 - Path cost");
                   System.out.println("2 - Full path");
                   System.out.print("Enter your choice (1/2): ");
                   int choice2 = scanner.nextInt();
                   if(choice2<=0||choice2>2){
                       System.out.println("Invalid choice");
                       continue;
                   }
                   switch (choice2) {
                       case 1:
                           System.out.println("Cost: " + solver.getDistance(start, end));

                           break;
                       case 2:
                           System.out.println("Path: " + solver.getPath(start, end));
                           break;
                       default:
                           System.out.println("Invalid choice.");
                           break;
                   }
               }

            }




        }




    }
}