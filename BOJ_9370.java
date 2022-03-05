import java.io.*;
import java.util.*;

public class BOJ_9370 {

    static class Node implements Comparable<Node>{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static ArrayList<Node>[] graph;
    static int n, m, t, s, g, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n+1];
            for(int i=1; i<n+1; i++) {
                graph[i] = new ArrayList<>();
            }

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));
            }

            int[] candidates = new int[t];
            for(int i=0; i<t; i++) {
                candidates[i] = Integer.parseInt(br.readLine());
            }

            // s->g와 s->h 중 더 먼 경로 찾기.
            int[] from_s = Dijkstra(s);

            int farther = 0;
            if(from_s[g] >= from_s[h]) farther = g;
            else                       farther = h;

            int[] from_farther = Dijkstra(farther);

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int x: candidates) {
                int path1 = from_s[farther] + from_farther[x];
                int path2 = from_s[x];
                if(path1 == path2) pq.offer(x);
            }

            while(!pq.isEmpty()) {
                System.out.print(pq.poll()+" ");
            }
            System.out.println();
        }
    }

    public static int[] Dijkstra(int v) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n+1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        pq.offer(new Node(v, 0));
        distance[v] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int curr = node.to;
            int weight = node.weight;

            if(distance[curr] < weight) continue;

            for(Node adj: graph[curr]) {
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
