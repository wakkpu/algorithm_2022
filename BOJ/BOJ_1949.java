package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1949 {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;
    static int[] weight;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        weight = new int[N+1];
        for(int i=1; i<=N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=1; i<=N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        /*for(int i=1; i<=N; i++) {
            System.out.println(graph.get(i).toString());
        }*/

        dp = new int[N+1][2];
        visited = new boolean[N+1];

        /*
        dp[i][0]: i번째 마을이 일반 마을일 때, i번째 마을을 root로 하는 우수 마을 인구 수 최대값
        dp[i][1]: i번째 마을이 우수 마을일 때, i번째 마을을 root로 하는 우수 마을 인구 수 최대값
         */

        dfs(1); // 아무데서나 시작해도 됨

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int curr) {
        if(visited[curr]) return;

        visited[curr] = true;
        dp[curr][0] = 0;
        dp[curr][1] = weight[curr]; // 현재 마을이 우수 마을인 경우므로 인구수

        for(Integer adj: graph[curr]) {
            if(visited[adj]) continue;

            dfs(adj);

            // 일반 마을은 우수 마을과 일반 마을과 모두 접할 수 있음
            dp[curr][0] += Math.max(dp[adj][0], dp[adj][1]);
            // 우수 마을은 일반 마을이랑만 접해야 함
            dp[curr][1] += dp[adj][0];
        }
    }
}
