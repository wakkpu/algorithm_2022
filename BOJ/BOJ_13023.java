package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13023 {

    static int N, M;
    static boolean flag;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        flag = false;

        for(int i=0; i<N; i++) {
            dfs(0, i, new boolean[N]);
        }

        if(flag) System.out.println(1);
        else System.out.println(0);
    }

    public static void dfs(int depth, int curr, boolean[] visited) {
        if(flag) return;

        if(depth == 4) {
            flag = true;
            return;
        }

        visited[curr] = true;
        ArrayList<Integer> adj = graph[curr];
        for(int next: adj) {
            if(visited[next]) continue;
            dfs(depth+1, next, visited);
        }
        visited[curr] = false;
    }
}
