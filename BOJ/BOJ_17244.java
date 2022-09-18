package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17244 {

    static int N, M;
    static int[][] map;
    static int[] start;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    static class State {
        int r;
        int c;
        int items; // bitmask

        public State(int r, int c, int items) {
            this.r = r;
            this.c = c;
            this.items = items;
        }

        @Override
        public String toString() {
            return "State{" + "r=" + r + ", c=" + c + ", items=" + items + '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int num = 0;

        map = new int[M][N];
        for(int r=0; r<M; r++) {
            String str = br.readLine();
            for(int c=0; c<N; c++) {
                char ch = str.charAt(c);
                if(ch == 'S') { // 시작지점 -1
                    start = new int[]{r, c};
                    map[r][c] = -1;
                } else if(ch == 'E') { // 종료지점 -2
                    map[r][c] = -2;
                } else if(ch == '.') { // 빈칸 -1
                    map[r][c] = -1;
                } else if(ch == '#') { // 벽 -3
                    map[r][c] = -3;
                } else if(ch == 'X') { // 물건 번호 0번부터 시작
                    map[r][c] = num++;
                }
            }
        }

        boolean[][][] visited = new boolean[M][N][1<<num];
        visited[start[0]][start[1]][0] = true;

        Queue<State> q = new LinkedList<>();
        q.offer(new State(start[0], start[1], 0));

        int time = 0;
        while(!q.isEmpty()) {
            int qSize = q.size();

            while(qSize-->0) {
                State curr = q.poll();

                int sr = curr.r;
                int sc = curr.c;
                int sNum = curr.items;

                if(sNum == (1<<num)-1 && map[sr][sc] == -2) { // 종료 조건
                    System.out.println(time);
                    return;
                }

                for(int d=0; d<4; d++) {
                    int nr = sr+dr[d];
                    int nc = sc+dc[d];

                    // 맵 밖이거나 벽이면 안감
                    if(!isIn(nr, nc) || map[nr][nc] == -3) continue;

                    int nNum = sNum | (1 << map[nr][nc]);

                    if((map[nr][nc] == -1 || map[nr][nc] == -2) && !visited[nr][nc][sNum]) { // 빈칸 또는 도착지일 때
                        q.offer(new State(nr, nc, sNum));
                        visited[nr][nc][sNum] = true;
                    } else if(map[nr][nc] >= 0 && !visited[nr][nc][nNum]) { // 물건일 때
                        q.offer(new State(nr, nc, nNum));
                        visited[nr][nc][nNum] = true;
                    }
                }
            }
            time++;
        }
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < M && 0 <= c && c < N;
    }
}
