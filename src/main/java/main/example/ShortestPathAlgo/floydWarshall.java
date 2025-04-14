package main.example.ShortestPathAlgo;

import java.util.ArrayList;

public class floydWarshall implements ShortestPathStrategy {
    private int [][] graph;
    private int n ;
    private ArrayList<ArrayList<Integer>> path;

    public boolean hasNegativeCycle() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        return true;  // Negative cycle found
                    }
                }
            }
        }
        return false;
    }
    public String shortestPath(int i , int j) {
        if (hasNegativeCycle()) {
            System.out.println("Graph contains a negative cycle!");
            return "";
        }
        if (this.path.get(i-1).get(j-1)==-1) {
            return "-1";
        }
        ArrayList<Integer> nodes = new ArrayList<>();
        int current = i-1 ;
        int target = j-1;
        nodes.add(current+1);
        while (current != target) {
            current  = this.path.get(current).get(target);
            nodes.add(current+1);
        }
        nodes.add(target+1);
        String x = "";
        for (int k = 0; k < nodes.size(); k++) {
            x+= nodes.get(k).toString() + "--> " ;
        }
        return x;
    }
    public int shortestPathDistance(int i , int j) {
        return graph[i-1][j-1];
    }
    public void print(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((graph[i][j]==100000 ? "INF" : graph[i][j]) + " ");
            }
            System.out.println();
        }
    }
    public void shortestPathRun (ArrayList<ArrayList<Pair<Integer,Integer>>> Graph, int start) {
        this.n = Graph.size();
        this.graph = new int[n][n];
        this.path = new ArrayList<ArrayList<Integer>>(n);
        for (int i = 0; i < n; i++) {
            this.path.add(new ArrayList<>(n)) ;
            java.util.Arrays.fill(this.graph[i],100000);
            this.graph[i][i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for ( int j =0 ; j < n ; j++) {
                this.path.get(i).add(-1);
            }
        }
        for (int i = 0; i < n; i++) {
            int x = i;
            for (int j = 0; j < Graph.get(i).size(); j++) {
                int y = Graph.get(i).get(j).first;
                this.graph[x-1][y-1] = Graph.get(i).get(j).second;
                this.path.get(x-1).set(y-1,y-1);
            }
        }
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j] && graph[i][k] != 100000 && graph[k][j] != 100000) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        this.path.get(i).set(j,path.get(i).get(k));
                    }
                }
            }
        }
        print();
    }
}
