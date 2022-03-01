import java.io.*;
import java.util.*;

public class BOJ_7579 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] mem = new int[N+1];
        for(int i=1; i<N+1; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int costSum = 0;
        int[] cost = new int[N+1];
        for(int i=1; i<N+1; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            costSum += cost[i];
        }

        int sol = 0;

        int[][] dp = new int[N+1][costSum+1];
        for(int i=1; i<N+1; i++) {
            for(int j=0; j<=costSum; j++) {
                if(j - cost[i] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + mem[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
                if(dp[i][j] >= M) {
                    sol = j;
                    break;
                }
            }
        }
        System.out.println(sol);

    }
}
