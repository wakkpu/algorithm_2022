package BOJ;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {

    private static class Point {
        int r;
        int c;
        int time;
        char val;

        public Point(int r, int c, int time, char val) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.val = val;
        }
    }

    private static int R, C, min = Integer.MAX_VALUE;
    private static char[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Point start = null;

        map = new char[R][C];
        for(int r=0; r<R; r++) {
           String line = br.readLine();
           for(int c=0; c<C; c++) {
               map[r][c] = line.charAt(c);
               if(map[r][c] == 'J') {
                   start = new Point(r, c, 1, map[r][c]);
                   map[r][c] = '.';
               }
           }
        }

        bfs(start);

        if(min == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(min);
        }
    }

    public static void bfs(Point start) {
        Queue<Point> queueJ = new LinkedList<>();
        queueJ.offer(start);

        boolean[][] visitedJ = new boolean[R][C];
        visitedJ[start.r][start.c] = true;

        Queue<Point> queueF = new LinkedList<>();
        boolean[][] visitedF = new boolean[R][C];
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] == 'F') {
                    visitedF[r][c] = true;
                    queueF.offer(new Point(r, c, 0, map[r][c]));
                }
            }
        }

        while(!queueJ.isEmpty()) {
            int fSize = queueF.size();
            for(int i=0; i<fSize; i++) {
                Point fire = queueF.poll();

                int r = fire.r;
                int c = fire.c;
                int time = fire.time;
                char val = fire.val;

                for(int d=0; d<4; d++) {
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || visitedF[nr][nc]) continue;
                    if(map[nr][nc] == '.' || map[nr][nc] == 'J') {
                        map[nr][nc] = 'F';
                        visitedF[nr][nc] = true;
                        queueF.offer(new Point(nr, nc, time+1, val));
                    }
                }
            }

            int jSize = queueJ.size();
            for(int i=0; i<jSize; i++) {
                Point curr = queueJ.poll();

                int r = curr.r;
                int c = curr.c;
                int time = curr.time;
                char val = curr.val;

                if(r == 0 || r == R-1 || c == 0 || c == C-1) {
                    min = Math.min(time, min);
                    return;
                }

                for(int d=0; d<4; d++) {
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || visitedJ[nr][nc]) continue;

                    if(map[nr][nc] == '.') {
                        queueJ.offer(new Point(nr, nc, time+1, val));
                        visitedJ[nr][nc] = true;
                    }
                }
            }
        }
    }
}
