package SW;

import java.io.*;
import java.util.*;

public class SW_3307 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] num = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[N];
            int max = 0;
            Arrays.fill(dp, 1);
            // dp[i]: i번째 num을 마지막으로 하는 수열의 최장 부분 증가 수열의 길이
            for(int i=0; i<N; i++) {
                for(int j=0; j<i; j++) {
                    if(num[i] > num[j]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
                max = Math.max(max, dp[i]);
            }
            bw.write("#"+t+" "+max+"\n");
        }
        bw.flush();
        bw.close();
    }
}
