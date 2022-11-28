package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_4991 {

    static int W, H;
    static int[][] map;
    static int num, result;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0) break;

            map = new int[W][H];

            num = 0;
            result = -1;
            int[] start = new int[3];
            for(int r=0; r<W; r++) {
                String str = br.readLine();
                for(int c=0; c<H; c++) {
                    if(str.charAt(c) == '.') { // 깨끗한 칸
                        map[r][c] = -1;
                    } else if(str.charAt(c) == 'o') { // 청소기 시작 위치
                        map[r][c] = -1;
                        start = new int[]{r, c, 0};
                    } else if(str.charAt(c) == '*') { // 더러운 칸
                        map[r][c] = num++;
                    } else if(str.charAt(c) == 'x') { // 가구
                        map[r][c] = -2;
                    }
                }
            }

            bfs(start);
            bw.write(result+"\n");
        }
        bw.flush();
    }

    public static void bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);

        boolean[][][] visited = new boolean[W][H][1<<num];
        visited[start[0]][start[1]][start[2]] = true;

        int depth = 0;
        while(!q.isEmpty()) {
            int qSize = q.size();

            while(qSize-->0) {
                int[] curr = q.poll();

                int sr = curr[0];
                int sc = curr[1];
                int ss = curr[2];

                // 쓰레기 다 치웠으면 종료
                if(ss == (1<<num)-1) {
                    result = depth;
                    return;
                }

                for(int d=0; d<4; d++) {
                    int nr = sr+dr[d];
                    int nc = sc+dc[d];

                    // 맵 밖이거나 가구면 갈 수 없음
                    if(!isIn(nr, nc) || map[nr][nc] == -2) continue;

                    int ns = ss | (1 << map[nr][nc]);

                    if(map[nr][nc] == -1 && !visited[nr][nc][ss]) { // 빈 칸
                        q.offer(new int[]{nr, nc, ss});
                        visited[nr][nc][ss] = true;
                    } else if(map[nr][nc] >= 0 && !visited[nr][nc][ns]) { // 더러운 칸
                        q.offer(new int[]{nr, nc, ns});
                        visited[nr][nc][ns] = true;
                    }
                }
            }
            depth++;
        }
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < W && 0 <= c && c < H;
    }
}
