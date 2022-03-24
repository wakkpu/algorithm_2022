package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int n = Integer.parseInt(br.readLine());

            int[][] stickers = new int[2][n+1];
            int[][] dp = new int[2][n+1];
            for(int i=0; i<2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=n; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = stickers[i][j];
                }
            }

            for(int j=2; j<=n; j++) {
                dp[0][j] = stickers[0][j] + Math.max(dp[1][j-1], dp[1][j-2]);
                dp[1][j] = stickers[1][j] + Math.max(dp[0][j-1], dp[0][j-2]);
            }

            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
