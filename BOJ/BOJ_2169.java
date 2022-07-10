package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][M+1];
        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[k][i][j]: k 방향에서 [i][j]에 왔을 때의 최대값 (k = 0(왼), 1(위), 2(오)
        int[][][] dp = new int[3][N+1][M+1];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=M; j++) {
                for(int k=0; k<3; k++) {
                    dp[k][i][j] = -100 * N * M - 1;
                }
            }
        }

        dp[0][1][1] = dp[1][1][1] = dp[2][1][1] = map[1][1];

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                // 왼쪽에서 오는 거
                if(c > 1) dp[0][r][c] = Math.max(dp[0][r][c-1], dp[1][r][c-1]) + map[r][c];
                // 위쪽에서 오는 거
                if(r > 1) dp[1][r][c] = Math.max(dp[0][r-1][c], Math.max(dp[1][r-1][c], dp[2][r-1][c])) + map[r][c];
            }

            // 오른쪽에서 오는 거
            for(int c=M-1; c>=1; c--) {
                dp[2][r][c] = Math.max(dp[1][r][c+1], dp[2][r][c+1]) + map[r][c];
            }

        }

        System.out.println(Math.max(dp[0][N][M], dp[1][N][M]));
    }
}
