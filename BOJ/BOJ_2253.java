package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2253 {

    static int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> small = new ArrayList<>();
        while(M-->0) {
            small.add(Integer.parseInt(br.readLine()));
        }

        int[][] dp = new int[N+1][N+1];
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                dp[r][c] = INF;
            }
        }
        dp[1][0] = 0;

        for(int r=2; r<=N; r++) {
            if(small.contains(r)) continue;
            for(int c=1; c<=Math.sqrt(2*r); c++) {
                dp[r][c] = Math.min(dp[r-c][c-1], Math.min(dp[r-c][c], dp[r-c][c+1])) + 1;
            }
        }

        int result = INF;
        for(int c=1; c<=N; c++) {
            result = Math.min(result, dp[N][c]);
        }

        if(result == INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
