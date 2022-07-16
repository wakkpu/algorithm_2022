package BOJ.BFS;

import java.io.*;
import java.util.*;

public class BOJ_3055 {

    static char[][] map;
    static int R, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[] start = new int[2];

        map = new char[R][C];
        for(int r=0; r<R; r++) {
            String str = br.readLine();
            for(int c=0; c<C; c++) {
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'S') {
                    start = new int[]{r, c};
                }
            }
        }

        bfs(start);
        System.out.print("KAKTUS");
    }

    public static void bfs(int[] start) {
        boolean[][] visited = new boolean[R][C];
        visited[start[0]][start[1]] = true;

        // q는 고슴도치의 위치 정보. map은 물의 정보.
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);

        int time = 0;
        while(!q.isEmpty()) {
            int qSize = q.size();

            map = next(map); // 물 먼저 불어나고 비버 이동 확인
//            printMap(map);

            while(qSize-->0) {
                int[] curr = q.poll();
//                System.out.println("curr: ("+curr[0]+", "+curr[1]+")");

                if(map[curr[0]][curr[1]] == 'D') {
                    System.out.println(time);
                    System.exit(0);
                }

                for(int d=0; d<4; d++) {
                    int nr = curr[0]+dr[d];
                    int nc = curr[1]+dc[d];

                    // (nr, nc)는 맵 밖이 아니고, 갔던 곳이 아니고, 빈 곳 또는 비버의 굴로만 갈 수 있다
                    if(isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == '.' || map[nr][nc] == 'D')) {
                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            time++;
        }
    }

    public static char[][] next(char[][] map) {
        char[][] nextMap = new char[R][C];
        for(int r=0; r<R; r++) {
            for (int c = 0; c < C; c++) {
                nextMap[r][c] = map[r][c]; // 맵 복사
            }
        }

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] == '*') { // 물은 확장
                    for(int d=0; d<4; d++) {
                        int nr = r+dr[d];
                        int nc = c+dc[d];

                        // 맵 바깥이거나, 비버의 굴이거나, 돌이면 무시
                        if(!isIn(nr, nc) || map[nr][nc] == 'D' || map[nr][nc] == 'X') continue;

                        nextMap[nr][nc] = '*';
                    }
                }
            }
        }
        return nextMap;
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public static void printMap(char[][] map) {
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
