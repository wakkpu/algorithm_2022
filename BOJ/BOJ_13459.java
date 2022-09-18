package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13459 {

    static char[][] map;
    static boolean[][][][] visited;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] balls = new int[4];

        map = new char[N][M];
        for(int r=0; r<N; r++) {
            String str = br.readLine();
            for(int c=0; c<M; c++) {
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'R') {
                    balls[0] = r;
                    balls[1] = c;
                    map[r][c] = '.';
                } else if(map[r][c] == 'B') {
                    balls[2] = r;
                    balls[3] = c;
                    map[r][c] = '.';
                }
            }
        }

        visited = new boolean[N][M][N][M];
        visited[balls[0]][balls[1]][balls[2]][balls[3]] = true;

        int depth = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(balls);
        while(!q.isEmpty()) {
            int qSize = q.size();

            while(qSize-->0) {
                int[] curr = q.poll();
                int redR = curr[0];
                int redC = curr[1];
                int blueR = curr[2];
                int blueC = curr[3];


                if(depth > 10) { // depth 10 초과면 실패
                    System.out.println("0");
                    return;
                }

                if(map[redR][redC] == 'O') { // 성공
                    System.out.println("1");
                    return;
                }

                for(int dir=0; dir<4; dir++) {
                    int[] red = moveBall(redR, redC, dir);
                    int[] blue = moveBall(blueR, blueC, dir);

                    // blue가 빠지는 경우면 무시
                    if(map[blue[0]][blue[1]] == 'O') continue;

                    // 공은 겹치면 안됨
                    if(red[0] == blue[0] && red[1] == blue[1]) {
                        if(dir == 0) { // 방향: 상
                            if(redR > blueR) { // 원래 red가 더 아래에 있었다면
                                red[0]++; // red가 한 칸 아래인 게 맞음
                            } else { // 원래 blue가 더 아래에 있었다면
                                blue[0]++; // blue가 한 칸 아래인 게 맞음
                            }
                        } else if(dir == 1) { // 방향: 하
                            if(redR > blueR) { // 원래 red가 더 아래에 있었다면
                                blue[0]--; // blue가 한 칸 위인 게 맞음
                            } else { // 원래 blue가 더 아래에 있었다면
                                red[0]--; // red가 한 칸 위인 게 맞음
                            }
                        } else if(dir == 2) { // 방향: 좌
                            if(redC > blueC) { // 원래 red가 더 오른쪽에 있었다면
                                red[1]++; // red가 한 칸 오른쪽인 게 맞음
                            } else { // 원래 blue가 더 오른쪽에 있었다면
                                blue[1]++; // blue가 한 칸 오른쪽인 게 맞음
                            }
                        } else { // 방향: 우
                            if(redC > blueC) { // 원래 red가 더 오른쪽에 있었다면
                                blue[1]--; // blue가 한 칸 왼쪽인 게 맞음
                            } else { // 원래 blue가 더 오른쪽에 있었다면
                                red[1]--; // red가 한 칸 왼쪽인 게 맞음
                            }
                        }
                    }

                    if(!visited[red[0]][red[1]][blue[0]][blue[1]]) {
                        visited[red[0]][red[1]][blue[0]][blue[1]] = true;
                        q.offer(new int[]{red[0], red[1], blue[0], blue[1]});
                    }
                }
            }
            depth++;
        }
        System.out.println("0"); // 성공 가능한 경우 없음
    }

    public static int[] moveBall(int r, int c, int dir) {
        int nr = r;
        int nc = c;

        // 구멍에 빠지거나 벽에 부딪히기 전까지 이동.
        while(map[nr][nc] != 'O' && map[nr+dr[dir]][nc+dc[dir]] != '#') {
            nr += dr[dir];
            nc += dc[dir];
        }

        return new int[]{nr, nc};
    }
}
