package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2636 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int count = 0;
    static int nextMelt = 0;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        while(isRemain()) {
            visited = new boolean[N][M];
            nextMelt = 0;
            dfs(0, 0);
            count++;
        }

        System.out.println(count);
        System.out.println(nextMelt);
    }

    public static void dfs(int si, int sj) {
        for(int d=0; d<4; d++) {
            int ni = si+di[d];
            int nj = sj+dj[d];

            if(ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj]) continue;

            visited[ni][nj] = true;
            if(map[ni][nj] == '1') {
                map[ni][nj] = 'c';
                nextMelt++;
            } else {
                dfs(ni, nj);
            }
        }
    }

    public static void printMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean isRemain() {
        boolean flag = false;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 'c') {
                    map[i][j] = '0';
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != '0') {
                    flag = true;
                }
            }
        }
        return flag;
    }
}
