package BOJ.DynamicProgramming.Tabulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(str2.charAt(i-1) == str1.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        /*for(int i=0; i<=n; i++) {
            for(int j=0; j<=m; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/

        System.out.println(dp[n][m]);
    }
}
