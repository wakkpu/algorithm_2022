package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13418 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int upDown;

        public Edge(int from, int to, int upDown) {
            this.from = from;
            this.to = to;
            this.upDown = upDown;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.upDown, o.upDown);
        }

        @Override
        public String toString() {
            return "Edge{" + "from=" + from + ", to=" + to + ", upDown=" + upDown + '}';
        }
    }

    static int N, M, min, max;
    static PriorityQueue<Edge> edgesMin, edgesMax;
    static int[] reps;
    static Edge start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        min = 0;
        max = 0;

        edgesMin = new PriorityQueue<>(Collections.reverseOrder());
        edgesMax = new PriorityQueue<>();

        for(int i=0; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int upDown = Integer.parseInt(st.nextToken()); // 0: 오르막, 1: 내리막
            edgesMin.add(new Edge(from, to, upDown));
            edgesMax.add(new Edge(from, to, upDown));
        }

        kruskal();
        System.out.println(Math.abs(max - min));
    }

    public static void kruskal() {
        int count = 0;

        reps = new int[N+1];
        for(int i=0; i<=N; i++) {
            reps[i] = i;
        }

        while(!edgesMin.isEmpty()) {
            Edge edge = edgesMin.poll();
//            System.out.println(edge);

            int a = find(edge.from);
            int b = find(edge.to);

            if(a == b) continue;

            union(edge.from, edge.to);
            if(edge.upDown == 0) count++;
        }
        min = (int) Math.pow(count, 2);
//        System.out.println("min: "+min);

        count = 0;

        reps = new int[N+1];
        for(int i=0; i<=N; i++) {
            reps[i] = i;
        }

        while(!edgesMax.isEmpty()) {
            Edge edge = edgesMax.poll();
//            System.out.println(edge);

            int a = find(edge.from);
            int b = find(edge.to);

            if(a == b) continue;

            union(edge.from, edge.to);
            if(edge.upDown == 0) count++;
        }
        max = (int) Math.pow(count, 2);
//        System.out.println("max: "+max);
    }

    public static int find(int a) {
        if(a == reps[a]) return a;
        return reps[a] = find(reps[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a > b) reps[a] = b;
        else if(a < b) reps[b] = a;
    }
}
