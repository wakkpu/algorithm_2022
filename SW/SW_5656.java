package SW;

import java.io.*;
import java.util.*;

public class SW_5656 {

    static int N, W, H;
    static int min;

    static int[][] map;
    static int[][] copy;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            for(int r=0; r<H; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            makePermutationDup(N, new int[N]);

            bw.write("#"+t+" "+ min +"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void copyMap() {
        copy = new int[H][W];
        for(int r=0; r<H; r++) {
            for(int c=0; c<W; c++) {
                copy[r][c] = map[r][c];
            }
        }
    }

    public static void makePermutationDup(int toChoose, int[] choosed) {
        if(toChoose == 0) {
            copyMap();
            int result = simulate(choosed);
            min = Math.min(min, result);
            return;
        }

        for(int i=0; i<W; i++) {
            choosed[choosed.length - toChoose] = i;
            makePermutationDup(toChoose-1, choosed);
        }
    }

    public static int simulate(int[] choosed) {
        for(int c: choosed) {
            int r = find(c);
            int depth = copy[r][c];
            bfs(new int[]{r, c, depth});
            drop();
        }

        return countBlock();
    }

    public static void bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        copy[start[0]][start[1]] = 0;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int i=0; i<4; i++) {
                int nr = curr[0];
                int nc = curr[1];
                int depth = curr[2];

                for(int j=1; j<depth; j++) {
                    nr += dr[i];
                    nc += dc[i];

                    if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;

                    if(copy[nr][nc] > 1) {
                        q.offer(new int[]{nr, nc, copy[nr][nc]});
                    }
                    copy[nr][nc] = 0;
                }
            }
        }
    }

    public static int countBlock() {
        int count = 0;
        for(int r=0; r<H; r++) {
            for(int c=0; c<W; c++) {
                if(copy[r][c] > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void drop() {
        for(int c=0; c<W; c++) {
            int r=H-1;
            while(r > 0) {
                if(copy[r][c] == 0) {
                    int nr = r-1;
                    while(nr > 0 && copy[nr][c] == 0) {
                        nr--;
                    }

                    copy[r][c] = copy[nr][c];
                    copy[nr][c] = 0;
                }
                r--;
            }
        }
    }

    public static int find(int c) {
        for(int r=0; r<H; r++) {
            if(copy[r][c] > 0) {
                return r;
            }
        }
        return 0;
    }
}
