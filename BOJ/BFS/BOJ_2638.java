package BOJ.BFS;

import java.io.*;
import java.util.*;

public class BOJ_2638 {

    static int N, M, second, count;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 1) count++;
            }
        }
        //System.out.println();
        int[][] param = map;
        while(count > 0) {
            int[][] tmp0 = check(param);
            //printMap(tmp0);
            int[][] tmp1 = melt(tmp0, param);
            //printMap(tmp1);
            param = tmp1;
            //System.out.println("count: "+count);
            second++;
        }
        System.out.println(second);
    }

    public static void printMap(int[][] map) {
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] check(int[][] map) {
        int[][] result = new int[N][M];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            //visited[r][c] = true;

            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;

                if(map[nr][nc] == 1) {
                    result[nr][nc]++;
                }

                if(map[nr][nc] == 0) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return result;
    }

    public static int[][] melt(int[][] temp, int[][] map) {
        int[][] result = new int[N][M];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(map[r][c] > 0) {
                    if(temp[r][c] >= 2) {
                        count--;
                        result[r][c] = 0;
                    } else {
                        result[r][c] = map[r][c];
                    }
                }
            }
        }
        return result;
    }
}
