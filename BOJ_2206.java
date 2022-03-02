import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] destroyVisited;
    static int minCourse = Integer.MAX_VALUE;
    static Queue<Point> q;
    static int[] di = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dj = {0, 0, -1, 1}; // 상 하 좌 우

    static class Point {
        int i;
        int j;
        int count;
        boolean destroyed;

        public Point(int i, int j, int count, boolean destroyed) {
            this.i = i;
            this.j = j;
            this.count = count;
            this.destroyed = destroyed;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];
        visited = new boolean[N+1][M+1];
        destroyVisited = new boolean[N+1][M+1];

        for(int i=1; i<N+1; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j+1] = str.charAt(j);
            }
        }

        q = new LinkedList<>();
        q.offer(new Point(1, 1, 1, false));

        // dfs로 풀면 최단 경로를 찾지 못할 수 있다고 함. -> bfs로 코드 고치니깐 바로 맞음..
        bfs();

        if(minCourse == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minCourse);
    }

    public static void bfs() {
        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.i == N && p.j == M) {
                minCourse = Math.min(minCourse, p.count);
                return;
            }

            for(int i=0; i<4; i++) {
                int ni = p.i + di[i];
                int nj = p.j + dj[i];

                if(ni < 1 || ni > N || nj < 1 || nj > M) continue;

                if(map[ni][nj] == '0') {
                    if(!p.destroyed && !visited[ni][nj]) {
                        q.offer(new Point(ni, nj, p.count+1, false));
                        visited[ni][nj] = true;
                    } else if(p.destroyed && !destroyVisited[ni][nj]) {
                        q.offer(new Point(ni, nj, p.count+1, true));
                        destroyVisited[ni][nj] = true;
                    }
                } else if(map[ni][nj] == '1' && !p.destroyed) {
                    q.offer(new Point(ni, nj, p.count+1, true));
                    visited[ni][nj] = true;
                }
            }
        }
    }
}
