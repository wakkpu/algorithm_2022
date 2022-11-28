package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12887 {
    static int M, whiteCount, maxCount;
    static int[][] map;

    static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        whiteCount = 0;

        map = new int[2][M]; // 입력 지도
        for(int r=0; r<2; r++) {
            String str = br.readLine();
            for(int c=0; c<M; c++) {
                if(str.charAt(c) == '.') { // white
                    map[r][c] = 0;
                    whiteCount++;
                } else { // black
                    map[r][c] = 1;
                }
            }
        }

        maxCount = 0;

        path(0);
        path(1);

        System.out.println(maxCount);
    }

    public static void path(int start) {
        if(map[start][0] == 1) return;

        boolean[][] visited = new boolean[2][M];
        visited[start][0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});

        int depth = 1;
        while(!q.isEmpty()) {
            int qSize = q.size();
            while(qSize-->0) {
                int[] curr = q.poll();

                if(curr[1] == M-1 && map[curr[0]][curr[1]] == 0) {
                    maxCount = Math.max(maxCount, whiteCount-depth);
                    return;
                }

                for(int[] dir: dirs) {
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];

                    if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            depth++;
        }
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < 2 && 0 <= c && c < M;
    }
}
