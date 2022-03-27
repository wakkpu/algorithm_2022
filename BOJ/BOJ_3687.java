package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_3687 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // dp[i]: 성냥개비 i로 만들 수 있는 가장 작은 수
        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);

        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;

        // 5, 3, 9은 사용할 일 X
        // 6도 6개 이상일 때 만들 일 없음
        int[] stick = {1, 7, 4, 2, 0, 8};
        for(int i=9; i<=100; i++) {
            for(int j=2; j<=7; j++) {
                String str = "" + dp[i - j] + stick[j - 2];
                dp[i] = Math.min(dp[i], Long.parseLong(str));
            }
        }

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());

            StringBuilder large = new StringBuilder();

            if(N%2 == 1) {
                large.append("7");
            } else {
                large.append("1");
            }

            for(int i=1; i<N/2; i++) {
                large.append("1");
            }

            System.out.println(dp[N]+" "+large.toString());
        }
    }
}
