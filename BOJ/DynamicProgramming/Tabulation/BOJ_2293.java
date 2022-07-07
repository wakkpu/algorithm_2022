package BOJ.DynamicProgramming.Tabulation;

import java.io.*;
import java.util.*;

public class BOJ_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n+1];
        for(int i=1; i<=n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        int[][] dp = new int[n+1][k+1];
        for(int i=1; i<=n; i++) {
            dp[i][0] = 1;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                dp[i][j] = dp[i-1][j];
                if(j-coins[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + dp[i][j-coins[i]]);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
