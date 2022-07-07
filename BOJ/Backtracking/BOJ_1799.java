package BOJ.Backtracking;

import java.io.*;
import java.util.*;

public class BOJ_1799 {

    static int N;
    static int[][] map;
    static boolean[][] isBlack, visited;
    static int[] dr = {-1, -1};
    static int[] dc = {+1, -1};
    static int[] result = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        isBlack = new boolean[N][N];
        visited = new boolean[N][N];
        map = new int[N][N];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                isBlack[r][c] = (r%2 == 0 && c%2 == 0) || (r%2 != 0 && c%2 != 0);
            }
        }

        dfs(-1, true, 0);
        dfs(-1, false, 0);

        System.out.println(result[0]+result[1]);
    }

    public static void dfs(int idx, boolean black, int depth) {
        for(int i=idx+1; i<N*N; i++) {
            int r = i/N;
            int c = i%N;

            if(isBlack[r][c] != black || map[r][c] == 0 || !isOk(r, c)) continue;

            visited[r][c] = true;
            dfs(i, black, depth+1);
            visited[r][c] = false;
        }

        if(black) {
            result[0] = Math.max(depth, result[0]);
        } else {
            result[1] = Math.max(depth, result[1]);
        }
    }

    public static boolean isOk(int r, int c) {
        for(int i=0; i<2; i++) {
            int nr = r;
            int nc = c;

            while(true) {
                if(!isIn(nr, nc)) break;
                if(visited[nr][nc]) return false;

                nr += dr[i];
                nc += dc[i];
            }
        }
        return true;
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
