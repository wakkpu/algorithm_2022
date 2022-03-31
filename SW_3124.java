import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_3124 {

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static Edge[] edges;
    static int[] reps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());

        reps = new int[V+1];
        for(int i=1; i<=V; i++) {
            reps[i] = i;
        }

        int E = Integer.parseInt(st.nextToken());
        edges = new Edge[E];
        for(int e=0; e<E; e++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[e] = new Edge(from, to, weight);
        }
        Arrays.sort(edges);

        int sol = kruskal();

        bw.write(sol+"\n");
        bw.flush();
        bw.close();
    }

    public static int find(int x) {
        if(x == reps[x]) return x;
        else return reps[x] = find(reps[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x > y) reps[x] = y;
        else if(x < y) reps[y] = x;
    }

    public static int kruskal() {
        int sum = 0;

        for(Edge edge: edges) {
            if(find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                sum += edge.weight;
            }
        }

        return sum;
    }
}
