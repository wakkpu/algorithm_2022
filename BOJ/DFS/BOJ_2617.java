package BOJ.DFS;

import java.io.*;
import java.util.*;

public class BOJ_2617 {

    static int N, M;
    static boolean[] visited;
    static int[] inCount, outCount;
    static List<List<Integer>> in, out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        out = new ArrayList<>();
        in = new ArrayList<>();

        inCount = new int[N+1];
        outCount = new int[N+1];

        for(int i=0; i<=N; i++) {
            out.add(new ArrayList<>());
            in.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            out.get(from).add(to);
            in.get(to).add(from);
        }

        for(int i=1; i<=N; i++) {
            visited = new boolean[N+1];
            inDfs(i, i);

            visited = new boolean[N+1];
            outDfs(i, i);
        }

        int count = 0;
        for(int i=1; i<=N; i++) {
            if(inCount[i] >= (N+1)/2 || outCount[i] >= (N+1)/2) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void inDfs(int curr, int start) {
        visited[curr] = true;

        for(int adj: in.get(curr)) {
            if(!visited[adj]) {
                inCount[adj]++;
                inDfs(adj, start);
            }
        }
    }

    public static void outDfs(int curr, int start) {
        visited[curr] = true;

        for(int adj: out.get(curr)) {
            if(!visited[adj]) {
                outCount[adj]++;
                outDfs(adj, start);
            }
        }
    }
}
