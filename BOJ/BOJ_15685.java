package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15685 {

    // 우, 상, 좌, 하
    static int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    static class curve {
        int si;
        int sj;
        int d;
        int g;

        public curve(int si, int sj, int d, int g) {
            this.si = si;
            this.sj = sj;
            this.d = d;
            this.g = g;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        curve[] curves = new curve[N];
        boolean[][] map = new boolean[101][101];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int sj = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curves[i] = new curve(si, sj, d, g);
        }

        for(curve c: curves) {
            ArrayList<Integer> directions = new ArrayList<>();
            directions.add(c.d);
            map[c.si][c.sj] = true;

            for(int i = 0; i<c.g; i++) {
                for (int j = directions.size() - 1; j >= 0; j--) {
                    int dir = (directions.get(j) + 1) % 4;
                    directions.add(dir);
                }
            }

            int si = c.si;
            int sj = c.sj;

            for (int j=0; j<directions.size(); j++) {
                int dir = directions.get(j);
                int ni = si + dirs[dir][0];
                int nj = sj + dirs[dir][1];
                map[ni][nj] = true;
                si = ni;
                sj = nj;
            }
        }


        int count = 0;

        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
