package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];
        for(int i=0; i<N+1; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }

        int[][] items = new int[N+1][2];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[i] = new int[] {W, V};
        }

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<K+1; j++) {
                dp[i][j] = dp[i-1][j];
                if(0<=j-items[i][0]) {
                    int temp = dp[i-1][j - items[i][0]] + items[i][1];
                    dp[i][j] = Math.max(dp[i][j], temp);
                }
            }
        }

        /*for(int i=0; i<N+1; i++) {
            for(int j=0; j<K+1; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/

        System.out.println(dp[N][K]);

    }
}
