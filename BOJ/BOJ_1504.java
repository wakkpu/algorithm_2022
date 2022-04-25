package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1504 {

    static int N, E, INF = 200000000;
    static List<Edge>[] graph;

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int sol1 = 0; // 1 -> v1 -> v2 -> N
        int sol2 = 0; // 1 -> v2 -> v1 -> N

        int[] fromStart = dijkstra(1);
        int[] fromV1 = dijkstra(v1);
        int[] fromV2 = dijkstra(v2);

        sol1 += (fromStart[v1] + fromV1[v2] + fromV2[N]);
        sol2 += (fromStart[v2] + fromV2[v1] + fromV1[N]);

        int shortestPath = Math.min(sol1, sol2);
        if(shortestPath >= INF) System.out.println(-1);
        else System.out.println(shortestPath);
    }

    public static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        int[] minDistance = new int[N+1];
        Arrays.fill(minDistance, INF);
        minDistance[start] = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            int dist = edge.weight;

            int curr = edge.to;

            if(minDistance[curr] < dist) continue;

            for(Edge adj: graph[curr]) {
                int weight = minDistance[curr] + adj.weight;

                if(weight < minDistance[adj.to]) {
                    minDistance[adj.to] = weight;
                    pq.offer(new Edge(adj.to, weight));
                }
            }
        }
        return minDistance;
    }
}
