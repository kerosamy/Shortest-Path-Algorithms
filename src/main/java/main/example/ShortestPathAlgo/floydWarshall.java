package main.example.ShortestPathAlgo;

import java.util.ArrayList;
import java.util.List;

public class floydWarshall implements ShortestPathStrategy {
    private int [][] graph;
    private int n ;
    private ArrayList<ArrayList<Integer>> path;

    public boolean hasNegativeCycles() {
        for (int i = 0; i < n; i++) {
            if (graph[i][i] < 0) {
                return true; // 🔥 Negative cycle detected
            }
        }
        return false;
    }

    public String shortestPath(int i , int j) {
        if (hasNegativeCycles()) {
            return "Negative Cycle";
        }
        if (this.path.get(i-1).get(j-1)==-1) {
            return "No Path";
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
        List<Integer> x=new ArrayList<>();
        for (int k = 0; k < nodes.size(); k++) {
            x.add(nodes.get(k));
        }
        x.remove(nodes.get(nodes.size()-1));
        return x.toString();
    }
    @Override
    public boolean hasNegativeCycle(){
       return hasNegativeCycles();
    }
    public String shortestPathDistance(int i , int j) {
        if(graph[i-1][j-1]==100000){
            return "INF";
        }
        if(hasNegativeCycle()){
            return "Negative Cycle";
        }
        return String.valueOf(graph[i-1][j-1]);
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

    }
}
