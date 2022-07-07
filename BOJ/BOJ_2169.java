package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2169 {

    static int[] dr = {-1, 0, 0};
    static int[] dc = {0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N][M];
        int[][] map = new int[N][M];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for(int c=1; c<M; c++) {
            dp[0][c] += dp[0][c-1];
        }

        for(int r=1; r<N; r++) {
            dp[r][0] += dp[r-1][0];
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                int[][] before = new int[3][2];
                for(int d=0; d<3; d++) {
                    before[d][0] = r+dr[d];
                    before[d][1] = c+dc[d];
                }


            }
        }
    }
}
