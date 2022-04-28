package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15683 {
    static class Point {
        int r;
        int c;
        int v;

        public Point(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }

    static int N, M;
    static int min = Integer.MAX_VALUE;

    static int[][] map;
    static boolean[][] bMap;
    static List<Point> cctv;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cctv = new ArrayList<>();
        map = new int[N][M];
        bMap = new boolean[N][M];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(1 <= map[r][c] && map[r][c] <= 5) {
                    cctv.add(new Point(r, c, map[r][c]));
                    bMap[r][c] = true;
                } else if(map[r][c] == 6) {
                    bMap[r][c] = true;
                }
            }
        }

        perm(cctv.size(), new int[cctv.size()]);

        System.out.println(min);
    }

    public static void perm(int toChoose, int[] choosed) {
        if(toChoose == 0) {
            min = Math.min(min, simul(choosed));
            return;
        }

        for(int i=0; i<4; i++) {
            choosed[cctv.size()-toChoose] = i;
            perm(toChoose-1, choosed);
        }
    }

    public static int simul(int[] choosed) {
        int sol = 0;
        boolean[][] copy = new boolean[N][M];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                copy[r][c] = bMap[r][c];
            }
        }

        for(int i=0; i<choosed.length; i++) {
            Point p = cctv.get(i);
            int d = choosed[i];
            if(p.v == 1) {
                copy = checkThrough(p.r, p.c, d, copy);
            } else if(p.v == 2) {
                copy = checkThrough(p.r, p.c, d, copy);
                copy = checkThrough(p.r, p.c, (d+2)%4, copy);
            } else if(p.v == 3) {
                copy = checkThrough(p.r, p.c, d, copy);
                copy = checkThrough(p.r, p.c, (d+1)%4, copy);
            } else if(p.v == 4) {
                copy = checkThrough(p.r, p.c, d, copy);
                copy = checkThrough(p.r, p.c, (d+1)%4, copy);
                copy = checkThrough(p.r, p.c, (d+2)%4, copy);
            } else if(p.v == 5) {
                copy = checkThrough(p.r, p.c, d, copy);
                copy = checkThrough(p.r, p.c, (d+1)%4, copy);
                copy = checkThrough(p.r, p.c, (d+2)%4, copy);
                copy = checkThrough(p.r, p.c, (d+3)%4, copy);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!copy[i][j]) {
                    sol++;
                }
            }
        }

        return sol;
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    public static boolean[][] checkThrough(int r, int c, int d, boolean[][] copy) {
        boolean[][] temp = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                temp[i][j] = copy[i][j];
            }
        }

        int nr = r;
        int nc = c;
        while(true) {
            nr += dir[d][0];
            nc += dir[d][1];
            if(!isIn(nr, nc) || map[nr][nc] == 6) break;
            temp[nr][nc] = true;
        }
        return temp;
    }
}
