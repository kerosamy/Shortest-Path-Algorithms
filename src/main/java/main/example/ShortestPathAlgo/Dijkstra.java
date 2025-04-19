package main.example.ShortestPathAlgo;

import java.util.*;

public class Dijkstra implements ShortestPathStrategy {
    private int[] dist;
    private int[] parent;
    private int n;
    @Override
    public void shortestPathRun(ArrayList<ArrayList<Pair<Integer,Integer>>>graph,int start) {
        this.n=graph.size();
        dist=new int[n];
        parent=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.fill(parent,-1);
        boolean[]visited=new boolean[n];
        dist[start]=0;
        PriorityQueue<Pair<Integer,Integer>>pq=new PriorityQueue<>(Comparator.comparingInt(a -> a.first));
        pq.add(new Pair<>(0,start));
        while (!pq.isEmpty()) {
            Pair<Integer, Integer>current=pq.poll();
            int node=current.second;
            int distance=current.first;
            if (visited[node]) continue;
            visited[node]=true;
            for (Pair<Integer,Integer> y:graph.get(node)) {
                int v=y.first;
                int weight=y.second;
                if (!visited[v] && dist[node]+weight<dist[v]) {
                    dist[v]=dist[node]+weight;
                    parent[v]=node;
                    pq.add(new Pair<>(dist[v],v));
                }
            }
        }
    }

    @Override
    public String shortestPath(int start, int end) {
        if (dist[end]==Integer.MAX_VALUE) {
            return "No Path";
        }
        List<Integer>path=new ArrayList<>();
        int x=end;
        while(x!=-1){
            path.add(Integer.valueOf(x));
            x=parent[x];
        }
        Collections.reverse(path);
        return path.toString();
    }

    @Override
    public boolean hasNegativeCycle(){
        return false;
    }

    @Override
    public String shortestPathDistance(int start, int end) {
        return dist[end]==Integer.MAX_VALUE ? "INF" : String.valueOf(dist[end]);
    }
}
