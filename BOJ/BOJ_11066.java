package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());

            int[][] dp = new int[N][N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                dp[i][i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<N; i++) {
                for(int from=0; from+i<N; from++) {

                }
            }
        }
        bw.flush();
    }
}
