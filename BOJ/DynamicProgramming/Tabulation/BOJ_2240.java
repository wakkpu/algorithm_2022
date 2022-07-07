package BOJ.DynamicProgramming.Tabulation;

import java.io.*;
import java.util.*;

public class BOJ_2240 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] drops = new int[T+1];
        for(int t=1; t<=T; t++) {
            drops[t] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[T+1][W+1][2+1];
        if(drops[1] == 1) {
            dp[1][0][1] = 1;
            dp[1][1][2] = 0;
        } else if(drops[1] == 2) {
            dp[1][0][1] = 0;
            dp[1][1][2] = 1;
        }

        for(int t=2; t<=T; t++) {
            for(int w=0; w<=W; w++) {
                if(w >= 1) {
                    if(drops[t] == 1) {
                        dp[t][w][1] = Math.max(dp[t-1][w][1], dp[t-1][w-1][2]) + 1;
                        dp[t][w][2] = Math.max(dp[t-1][w][2], dp[t-1][w-1][1]);
                    } else if(drops[t] == 2) {
                        dp[t][w][1] = Math.max(dp[t-1][w][1], dp[t-1][w-1][2]);
                        dp[t][w][2] = Math.max(dp[t-1][w][2], dp[t-1][w-1][1]) + 1;
                    }
                } else {
                    if(drops[t] == 1) {
                        dp[t][0][1] = dp[t-1][0][1] + 1;
                        dp[t][0][2] = dp[t-1][0][2];
                    } else if(drops[t] == 2) {
                        dp[t][0][1] = dp[t-1][0][1];
                        dp[t][0][2] = dp[t-1][0][2] + 1;
                    }
                }
            }
        }

        int max = 0;
        for(int w=0; w<=W; w++) {
            max = Math.max(max, Math.max(dp[T][w][1], dp[T][w][2]));
        }
        System.out.println(max);
    }
}
