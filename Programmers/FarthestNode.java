package Programmers;

import java.util.*;

public class FarthestNode {
    public int solution(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int depth = 1;
        int maxDepth = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        int[] visited = new int[n+1];
        visited[1] = -1;

        while(!q.isEmpty()) {
            int qSize = q.size();
            while(qSize-->0) {
                int curr = q.poll();

                for(Integer adj: graph.get(curr)) {
                    if(visited[adj] == 0) {
                        q.offer(adj);
                        visited[adj] = depth;
                        maxDepth = Math.max(maxDepth, depth);
                    }
                }
            }
            depth++;
        }

        int result = 0;
        for(int i=2; i<=n; i++) {
            if(visited[i] == maxDepth) {
                result++;
            }
        }
        return result;
    }
}
