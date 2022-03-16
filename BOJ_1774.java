import java.io.*;
import java.util.*;

public class BOJ_1774 {

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public double distance(Pos o) {
            return Math.sqrt(Math.pow((this.r - o.r), 2) + Math.pow((this.c - o.c), 2));
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight < o.weight) return -1;
            else return 1;
        }
    }

    static int N, M;
    static Pos[] pos;
    static ArrayList<Edge> graph;
    static int[] rep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pos = new Pos[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pos[i] = new Pos(r, c);
        }

        graph = new ArrayList<>();
        for(int i=1; i<N; i++) {
            for(int j=i+1; j<=N; j++) {
                graph.add(new Edge(i, j, pos[i].distance(pos[j])));
            }
        }

        make();

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        double result = kruskal();
        System.out.printf("%.2f", result);
    }

    public static void make() {
        rep = new int[N+1];
        for(int i=1; i<=N; i++) {
            rep[i] = i;
        }
    }

    public static int find(int a) {
        if(rep[a] == a) return a;
        else return rep[a] = find(rep[a]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;
        else if(a > b) rep[a] = b;
        else rep[b] = a;
        return true;
    }

    public static double kruskal() {
        Collections.sort(graph);

        double dist = 0;

        for(int i=0; i<graph.size(); i++) {
            Edge edge = graph.get(i);
            if(union(edge.from, edge.to)) {
                dist += edge.weight;
            }
        }

        return dist;
    }
}
