package BOJ.Backtracking;

import java.io.*;
import java.util.*;

public class BOJ_2026 {

    static boolean[] visited;
    static List<List<Integer>> graph;
    static int K, N, F;
    static boolean finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<F; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        finished = false;
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            if(graph.get(i).size() < K-1) continue;

            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }

        if(!finished) System.out.println(-1);
    }

    public static void dfs(int curr, int depth) {
        if(finished) return;

        if(depth == K) {
            finished = true;
            for(int i=1; i<=N; i++) {
                if(visited[i]) {
                    System.out.println(i);
                }
            }
            return;
        }

        for(int adj=curr+1; adj<=N; adj++) {
            if(isFriend(adj)) {
                visited[adj] = true;
                dfs(adj, depth+1);
                visited[adj] = false;
            }
        }
    }

    public static boolean isFriend(int curr) {
        for(int i=1; i<=N; i++) {
            if(visited[i] && !graph.get(i).contains(curr)) {
                return false;
            }
        }
        return true;
    }
}
