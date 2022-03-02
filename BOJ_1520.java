import java.io.*;
import java.util.*;

public class BOJ_1520 {

    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] di = {-1, 1, 0, 0}; // 상하좌우
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N]; // dp[i][j]: map[i][j]에서 map[M-1][N-1]에 도착하는 경우의 수
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
    }

    public static int dfs(int si, int sj) {
        if(si == M-1 && sj == N-1) {
            return 1;
        }

        // 이미 계산했던 경로라면 memoization
        if(dp[si][sj] != -1) {
            return dp[si][sj];
        }

        dp[si][sj] = 0; // 초기화.
        for(int d=0; d<4; d++) {
            int ni = si+di[d];
            int nj = sj+dj[d];

            if(ni < 0 || ni > M-1 || nj < 0 || nj > N-1) continue;

            if(map[si][sj] > map[ni][nj]) {
                dp[si][sj] += dfs(ni, nj);
            }
        }
        return dp[si][sj];
    }
}
