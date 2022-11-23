package BOJ;

import java.io.*;

public class BOJ_14945 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][N+1];

        dp[2][1] = 2;
        for(int i=3; i<=N; i++) {
            for(int j=1; j<i; j++) {
                dp[i][j] = (dp[i-1][j-1] + 2 * dp[i-1][j] + dp[i-1][j+1]) % 10007;
            }
        }

        int result = 0;
        for(int j=1; j<N; j++) {
            result += dp[N][j];
        }
        System.out.println(result % 10007);

    }
}
