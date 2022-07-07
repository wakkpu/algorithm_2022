package BOJ.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[] ki = {-2, -2, -1, 1, 2, 2, 1, -1};
    static int[] kj = {-1, 1, 2, 2, 1, -1, -2, -2};
    static int K, N, M;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][][] visited;

    static class State {
        int i;
        int j;
        int count;
        int k;

        public State(int i, int j, int count, int k) {
            this.i = i;
            this.j = j;
            this.count = count;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[K+1][N][M];

        bfs(new State(0, 0, 0, K));

        if(min == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    }

    public static void bfs(State start) {
        Queue<State> q = new LinkedList<>();
        q.offer(start);
        visited[start.k][start.i][start.j] = true;

        while(!q.isEmpty()) {
            State curr = q.poll();

            if(curr.i == N-1 && curr.j == M-1) {
                min = Math.min(min, curr.count);
            }

            for(int d=0; d<4; d++) {
                int ni = curr.i+di[d];
                int nj = curr.j+dj[d];

                if(ni < 0 || ni >= N || nj < 0 || nj >= M || visited[curr.k][ni][nj] || map[ni][nj] == 1) continue;

                visited[curr.k][ni][nj] = true;
                q.offer(new State(ni, nj, curr.count+1, curr.k));
            }

            if(curr.k > 0) {
                for (int k = 0; k < 8; k++) {
                    int ni = curr.i + ki[k];
                    int nj = curr.j + kj[k];

                    if (ni < 0 || ni >= N || nj < 0 || nj >= M || visited[curr.k - 1][ni][nj] || map[ni][nj] == 1) continue;

                    visited[curr.k-1][ni][nj] = true;
                    q.offer(new State(ni, nj, curr.count+1, curr.k-1));
                }
            }
        }
    }
}
