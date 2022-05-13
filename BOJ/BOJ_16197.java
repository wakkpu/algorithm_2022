package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16197 {

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    private static class Coin {
        int r;
        int c;
        int depth;

        public Coin(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Coin[] coins = new Coin[2];

        map = new char[N][M];
        visited = new boolean[2][N][M];
        int k=0;
        for(int r=0; r<N; r++) {
            String str = br.readLine();
            for(int c=0; c<M; c++) {
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'o') {
                    coins[k++] = new Coin(r, c, 0);
                    map[r][c] = '.';
                }
            }
        }
        int count = bfs(coins);
        System.out.println(count);
    }

    public static int bfs(Coin[] coins) {
        Queue<Coin[]> q = new LinkedList<>();
        q.offer(coins);
        Coin s1 = coins[0];
        Coin s2 = coins[1];
        visited[0][s1.r][s1.c] = true;
        visited[1][s2.r][s2.c] = true;

        while(!q.isEmpty()) {
            int qSize = q.size();
            while(qSize-->0) {
                Coin[] curr = q.poll();
                Coin c1 = curr[0];
                Coin c2 = curr[1];

                if(c1.depth >= 10) return -1;

                for(int i=0; i<4; i++) {
                    int nr1 = dr[i]+c1.r;
                    int nc1 = dc[i]+c1.c;

                    int nr2 = dr[i]+c2.r;
                    int nc2 = dc[i]+c2.c;

                    if(nr1 == nr2 && nc1 == nc2) continue;

                    // 둘 다 범위 안일 때
                    if(isIn(nr1, nc1) && isIn(nr2, nc2)) {
                        if(map[nr1][nc1] == '.' && map[nr2][nc2] == '.') { // 둘 다 이동
                            visited[0][nr1][nc1] = true;
                            visited[1][nr2][nc2] = true;
                            q.offer(new Coin[]{new Coin(nr1, nc1, c1.depth+1), new Coin(nr2, nc2, c2.depth+1)});
                        } else if(map[nr1][nc1] == '.' && map[nr2][nc2] == '#') { // c1만 이동
                            visited[0][nr1][nc1] = true;
                            q.offer(new Coin[]{new Coin(nr1, nc1, c1.depth+1), new Coin(c2.r, c2.c, c2.depth+1)});
                        } else if(map[nr1][nc1] == '#' && map[nr2][nc2] == '.') { // c2만 이동
                            visited[1][nr2][nc2] = true;
                            q.offer(new Coin[]{new Coin(c1.r, c1.c, c1.depth+1), new Coin(nr2, nc2, c2.depth+1)});
                        }
                    }

                    // 둘 중 하나만 떨어질 때
                    if(isIn(nr1, nc1) ^ isIn(nr2, nc2)) {
                        return c1.depth+1;
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isIn(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }
}
