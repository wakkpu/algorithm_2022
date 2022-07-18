package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_25307 {

    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static int minStep = Integer.MAX_VALUE;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] start = new int[2];

        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 4) {
                    start = new int[]{i, j};
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 3) {
                    mannequin(i, j);
                }
            }
        }

        //map[start[0]][start[1]] = 0;
        printMap();

        bfs(start);

        if(minStep == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(minStep);
        }
    }

    public static void bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start[0], start[1], 0});

        visited[start[0]][start[1]] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int sr = curr[0];
            int sc = curr[1];
            int step = curr[2];

            if(map[sr][sc] == 2) {
                minStep = step;
                return;
            }

            for(int d=0; d<4; d++) {
                int nr = sr + dr[d];
                int nc = sc + dc[d];

                if(isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == 2)) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, step + 1});
                }
            }
        }
    }

    public static void printMap() {
        System.out.println("-------------------------");
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    public static void mannequin(int r, int c) {
        for(int i=r-K; i<=r+K; i++) {
            for(int j=c-K; j<=c+K; j++) {
                if(isIn(i, j) && Math.abs(r-i) + Math.abs(c-j) <= K) {
                    map[i][j] = 1;
                }
            }
        }
    }
}
