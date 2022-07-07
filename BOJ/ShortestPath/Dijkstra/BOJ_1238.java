package BOJ.ShortestPath.Dijkstra;

import java.io.*;
import java.util.*;

public class BOJ_1238 {

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node[" + "to=" + to + ", weight=" + weight + "]";
        }

        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }
    }

    static int N, M, X;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
        }

        // X부터 각 집까지의 최단 거리
        int[] toHome = Dijkstra(X);
        int maxLen = 0;

        for(int i=1; i<N+1; i++) {
            if(i == X) continue;

            // 집에서 파티까지의 최단거리
            int toParty = Dijkstra(i)[X];

            if(toParty + toHome[i] >= maxLen) {
                maxLen = toParty + toHome[i];
            }
        }
        System.out.println(maxLen);
    }

    public static int[] Dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        pq.offer(new Node(s, 0));
        distance[s] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            int curr = node.to;
            int weight = node.weight;

            if(distance[curr] < weight) continue;

            for(Node adj: graph.get(curr)) {
                int newDist = distance[curr] + adj.weight;
                if(newDist < distance[adj.to]) {
                    distance[adj.to] = newDist;
                    pq.offer(new Node(adj.to, newDist));
                }
            }
        }
        return distance;
    }
}
