package BOJ.DynamicProgramming.Memoization;

import java.io.*;
import java.util.*;

public class BOJ_11049 {

    static int N;
    static int[][] matrix, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        matrix = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        // dp[i][j]: i번째 행렬부터 j번째 행렬까지의 행렬 곱 최소값
        dp = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                dp[i][j] = 0;
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i == j) continue;

                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;

                if(i+1 < N) left = matrix[i][0] * matrix[i][1] * matrix[i+1][0] + dp[i+1][j];
                if(j-1 >= 0) right = dp[i][j-1] + matrix[j-1][1] * matrix[j][0] * matrix[j][1];

                if(dp[i][j] == 0) dp[i][j] = Integer.min(left, right);
                else dp[i][j] = Integer.min(dp[i][j], Integer.min(left, right));
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(dp[0][N]);
    }
}
