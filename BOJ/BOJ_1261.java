package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1261 {

    static int N, M;
    static boolean[][] visited;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Point implements Comparable<Point> {
        int i;
        int j;
        int count;

        public Point(int i, int j, int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i=1; i<N+1; i++) {
            String str = br.readLine();
            for(int j=1; j<M+1; j++) {
                map[i][j] = str.charAt(j-1) - '0';
            }
        }

        visited = new boolean[N+1][M+1];

        System.out.println(bfs(new Point(1, 1, 0)));

    }

    public static int bfs(Point s) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(s);
        visited[s.i][s.j] = true;

        while(!pq.isEmpty()) {

            Point p = pq.poll();
            int si = p.i;
            int sj = p.j;
            int sc = p.count;

            if(si == N && sj == M) return sc;

            for(int d=0; d<4; d++) {
                int ni = si+di[d];
                int nj = sj+dj[d];

                if(ni < 1 || ni > N || nj < 1 || nj > M || visited[ni][nj]) continue;

                visited[ni][nj] = true;

                if(map[ni][nj] == 0) pq.offer(new Point(ni, nj, sc));

                if(map[ni][nj] == 1) pq.offer(new Point(ni, nj, sc+1));
            }
        }
        return 0;
    }
}
