package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_18405 {
    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, K, S, X, Y;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(map[X][Y]);
    }

    public static void bfs() {
        Map<Integer, List<Point>> dict = new TreeMap<>();
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(map[r][c] > 0) {
                    if(!dict.containsKey(map[r][c])) {
                        dict.put(map[r][c], new ArrayList<>());
                    }
                    dict.get(map[r][c]).add(new Point(r, c));
                }
            }
        }

        while(S-->0) {
            for(Integer key: dict.keySet()) {
                List<Point> src = dict.get(key);
                List<Point> values = new ArrayList<>();
                for(Point p: src) {
                    int r = p.r;
                    int c = p.c;
                    values.add(new Point(r, c));
                }
                int size = values.size();

                dict.get(key).clear();

                for(int i=0; i<size; i++) {
                    Point curr = values.get(i);
                    int r = curr.r;
                    int c = curr.c;

                    for(int d=0; d<4; d++) {
                        int nr = r+dr[d];
                        int nc = c+dc[d];

                        if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] != 0) continue;
                        map[nr][nc] = map[r][c];
                        dict.get(map[r][c]).add(new Point(nr, nc));
                    }
                }
            }
        }
    }
}
