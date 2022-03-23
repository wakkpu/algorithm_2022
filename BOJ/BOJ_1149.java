package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1149 {

    static int N;
    static int[][] homes;
    static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        homes = new int[N][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // 빨간색으로 칠하는 비용
            homes[i][0] = Integer.parseInt(st.nextToken());
            // 초록색으로 칠하는 비용
            homes[i][1] = Integer.parseInt(st.nextToken());
            // 파란색으로 칠하는 비용
            homes[i][2] = Integer.parseInt(st.nextToken());
        }

        DP = new int[N][3];
        DP[0] = homes[0];

        for(int i=1; i<N; i++) {
            DP[i][0] = Math.min(DP[i-1][1], DP[i-1][2]) + homes[i][0];
            DP[i][1] = Math.min(DP[i-1][0], DP[i-1][2]) + homes[i][1];
            DP[i][2] = Math.min(DP[i-1][0], DP[i-1][1]) + homes[i][2];
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            min = Math.min(DP[N-1][i], min);
        }
        System.out.println(min);
    }
}
