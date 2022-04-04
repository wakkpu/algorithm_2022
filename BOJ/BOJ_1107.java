package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1107 {

    static int N, M;
    static int[] broken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if(M > 0) {
            broken = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < broken.length; i++) {
                System.out.print(broken[i]+" ");
            }
        }
    }
}
