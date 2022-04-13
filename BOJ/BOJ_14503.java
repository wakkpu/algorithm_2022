package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14503 {

    static int N, M;
    static int[][] map;
    static int count = 0;
    static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북, 동, 남, 서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);
        System.out.println(count);
    }

    public static void dfs(int r, int c, int d) {
        if(map[r][c] == 1) {
            return;
        } else if(map[r][c] == 0) {
            map[r][c] = -1; // 청소한 곳
            count++;
        }

        int nd = d;

        for(int i=0; i<4; i++) {
            nd = (nd+3) % 4; // 왼쪽으로 회전

            int nr = r + dirs[nd][0];
            int nc = c + dirs[nd][1];

            if(map[nr][nc] == 0) {
                dfs(nr, nc, nd);
                return;
            }
        }
        dfs(r-dirs[d][0], c-dirs[d][1], d);
    }
}
