package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16929 {

    static int N, M;
    static boolean flag;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int r=0; r<N; r++) {
            String str = br.readLine();
            for(int c=0; c<M; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        flag = false;

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                visited = new boolean[N][M];
                visited[r][c] = true;
                isCycle(r, c, r, c, 0);
                if(flag) {
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

    public static void isCycle(int sr, int sc, int r, int c, int depth) {
        if(flag) return;

        visited[r][c] = true;

        for(int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(nr == sr && nc == sc && depth >= 3) {
                flag = true;
                return;
            }

            if(0 <= nr && nr < N && 0 <= nc && nc < M) {
                if(!visited[nr][nc] && map[r][c] == map[nr][nc]) {
                    isCycle(sr, sc, nr, nc, depth+1);
                }
            }
        }
    }
}
