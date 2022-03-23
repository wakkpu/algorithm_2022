package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {

    static int N, M, second=0;
    static int[][] map;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            int count = check();
            if(count == 0) {
                System.out.println(0);
                return;
            } else if(count == 1) {
                melt();
            }else if(count >= 2) {
                System.out.println(second);
                return;
            }
        }
    }

    // 빙하가 분리되었는지 확인.
    public static int check() {
        boolean[][] visited = new boolean[N][M];

        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] > 0 && !visited[i][j]) {
                    DFS(i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public static void DFS(int i, int j, boolean[][] visited) {
        visited[i][j] = true;

        for(int d=0; d<4; d++) {
            int ni = i+di[d];
            int nj = j+dj[d];

            if(ni < 0 || ni > N-1 || nj < 0 || nj > M-1) continue;

            if(map[ni][nj] != 0 && !visited[ni][nj]) {
                DFS(ni, nj, visited);
            }
        }
    }

    // 빙하를 동시에 녹임.
    public static void melt() {

        Queue<Pos> q = new LinkedList<>();
        int[][] tempMap = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] > 0) {
                    q.offer(new Pos(i, j));
                }
            }
        }

        while(!q.isEmpty()) {
            Pos p = q.poll();

            int value = map[p.i][p.j];

            for(int d=0; d<4; d++) {
                int ni = p.i + di[d];
                int nj = p.j + dj[d];

                if(ni < 0 || ni > N-1 || nj < 0 || nj > M-1) continue;

                if(map[ni][nj] == 0 && value > 0) {
                    value--;
                }
            }
            tempMap[p.i][p.j] = value;
        }
        second++;
        map = tempMap;
    }
}
