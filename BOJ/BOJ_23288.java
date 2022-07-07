package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_23288 {

    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken())-1;
        M = Integer.parseInt(st.nextToken())-1;
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


    }
}
