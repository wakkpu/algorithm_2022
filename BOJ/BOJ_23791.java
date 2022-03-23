package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_23791 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 음식의 수

        st = new StringTokenizer(br.readLine());

        int[] A = new int[N+1]; // 한식의 맛 data
        for(int i=1; i<N+1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int[] B = new int[N+1]; // 양식의 맛 data
        for(int i=1; i<N+1; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int Q = Integer.parseInt(br.readLine()); // 질의의 갯수

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            /*
            맛 전체에 대해 이진 탐색. (1 ~ 2^N - 1)
             */
        }
    }
}
