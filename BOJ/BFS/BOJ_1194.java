package BOJ.BFS;

import java.io.*;
import java.util.*;

public class BOJ_1194 {

    static class State {
        int i;
        int j;
        int move;
        int keys;

        public State(int i, int j, int move, int keys) {
            this.i = i;
            this.j = j;
            this.move = move;
            this.keys = keys;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", j=" + j +
                    ", move=" + move +
                    ", keys='" + keys + '\'' +
                    '}';
        }
    }

    static int N, M;
    static char[][] map;
    static int shortest = Integer.MAX_VALUE;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        State start = null;
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0') {
                    start = new State(i, j, 0,0);
                    map[i][j] = '.';
                }
            }
        }
        /*System.out.println("init map");
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }*/
        bfs(start);
        if(shortest == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(shortest);
    }

    public static void bfs(State start) {
        Queue<State> q = new LinkedList<>();
        q.offer(start);
        boolean[][][] visited = new boolean[1<<6][N][M];

        while(!q.isEmpty()) {
            State curr = q.poll();
            int si = curr.i;
            int sj = curr.j;
            int move = curr.move;
            int keys = curr.keys;
            visited[keys][si][sj] = true;

            if(map[si][sj] == '1') {
                shortest = Math.min(move, shortest);
            }

            for(int d=0; d<4; d++) {
                int ni = si+di[d];
                int nj = sj+dj[d];
                int nkeys = keys;

                if(ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
                if(visited[nkeys][ni][nj]) continue;
                if(map[ni][nj] == '#') continue;

                // 열쇠
                if('a' <= map[ni][nj] && map[ni][nj] <= 'f') {
                    nkeys |= (1 << (map[ni][nj] - 'a'));
                }

                if('A' <= map[ni][nj] && map[ni][nj] <= 'F') {
                    if((nkeys & (1 << map[ni][nj] - 'A')) == 0) {
                        continue;
                    }
                }

                q.offer(new State(ni, nj, move+1, nkeys));
                visited[nkeys][ni][nj] = true;
            }
        }
    }
}
