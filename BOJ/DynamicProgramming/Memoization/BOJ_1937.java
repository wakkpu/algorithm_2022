package BOJ.DynamicProgramming.Memoization;

import java.io.*;
import java.util.*;

public class BOJ_1937 {

    static int N;
    static int[][] map;
    static int[][] dp;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);
    }

    public static int dfs(int r, int c) {
        if(dp[r][c] != 0) return dp[r][c];

        dp[r][c] = 1;

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if(map[nr][nc] > map[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
            }
        }

        return dp[r][c];
    }
}
