package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2157 {

    static int[][] dp;

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge [to=" + to + ", weight=" + weight + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // [몇번 이동][현재 노드 번호]
        dp = new int[M+1][N+1];

        List<List<Edge>> graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(from > to) continue;

            graph.get(from).add(new Edge(to, weight));
        }

        // [현재 노드 번호]
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        int depth = 1;
        while(!q.isEmpty() && depth < M) {
            int qSize = q.size();

            while(qSize-->0) {
                int currNode = q.poll();

                for(Edge adj: graph.get(currNode)) {

                    int nextNode = adj.to;
                    int nextWeight = dp[depth][currNode] + adj.weight;

                    if(dp[depth+1][nextNode] < nextWeight) {
                        q.offer(nextNode);
                        dp[depth+1][nextNode] = nextWeight;
                    }
                }
            }
            depth++;
        }

        int result = 0;
        for(int i=2; i<=M; i++) {
            result = Math.max(result, dp[i][N]);
        }
        System.out.println(result);
    }
}
