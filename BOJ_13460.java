import java.io.*;
import java.util.*;

public class BOJ_13460 {

    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    //static boolean[][] visitedB;
    //static boolean[][] visitedR;
    static Ball red;
    static Ball blue;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Ball {
        int i;
        int j;
        int count;

        public Ball(int i, int j, int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // i
        M = Integer.parseInt(st.nextToken()); // j

        map = new char[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R') {
                    red = new Ball(i, j, 0);
                } else if(map[i][j] == 'B') {
                    blue = new Ball(i, j, 0);
                }
            }
        }

        bfs(red, blue);

    }

    public static void bfs(Ball r, Ball b) {
        Queue<Ball[]> q = new LinkedList<>();
        q.offer(new Ball[]{r, b});

        // visited 나누면 31퍼에서 에러남
        // 다른 반례들 거의 다 맞는 거 같은데 visited 나누면 에러 나는 반례가 머지

        //visitedR = new boolean[N][M];
        //visitedB = new boolean[N][M];
        visited = new boolean[N][M][N][M];

        while (!q.isEmpty()) {
            Ball[] balls = q.poll();
            Ball red  = balls[0];
            Ball blue = balls[1];

            int[] sr = {red.i, red.j};
            int[] sb = {blue.i, blue.j};

            //visitedR[red.i] [red.j]  = true;
            //visitedB[blue.i][blue.j] = true;
            visited[red.i][red.j][blue.i][blue.j] = true;

            if(red.count >= 10 || blue.count >= 10) {
                System.out.println(-1);
                return;
            }

            // 상하좌우로 공 굴려봄
            for(int d=0; d<4; d++) {
                int[] nr = moveBall(red.i,  red.j,  d); // red 도착지점
                int[] nb = moveBall(blue.i, blue.j, d); // blue 도착지점

                if(map[nb[0]][nb[1]] == 'O') continue; // blue 들어가면 안됨

                if(map[nr[0]][nr[1]] == 'O') { // red만 들어가야 성공
                    //System.out.println("red 도착: " +nr[0]+", "+nr[1]+", "+map[nr[0]][nr[1]]);
                    System.out.println(red.count+1);
                    return;
                }

                // 공은 겹칠 수 없음
                if(nr[0] == nb[0] && nr[1] == nb[1]) {
                    if(d == 0) { // 상
                        if(red.i > blue.i) { // blue가 더 위에 있었다면
                            nr[0]++; // red가 한 칸 더 낮아야 함
                        } else if(red.i < blue.i) { // red가 더 위에 있었다면
                            nb[0]++; // blue가 한 칸 더 낮아야 함
                        }
                    } else if(d == 1) { // 하
                        if(red.i > blue.i) { // blue가 더 위에 있었다면
                            nb[0]--; // blue가 한 칸 더 높아야 함
                        } else if(red.i < blue.i) { // red가 더 위에 있었다면
                            nr[0]--; // red가 한 칸 더 높아야 함
                        }
                    } else if(d == 2) { // 좌
                        if(red.j > blue.j) { // red가 더 오른쪽에 있었다면
                            nr[1]++; // red가 한 칸 더 오른쪽에 있어야 함
                        } else if(red.j < blue.j) { // blue가 더 오른쪽에 있었다면
                            nb[1]++; // blue가 한 칸 더 오른쪽에 있어야 함
                        }
                    } else { // 우
                        if(red.j > blue.j) { // red가 더 오른쪽에 있었다면
                            nb[1]--; // blue가 한 칸 왼쪽에 있어야 함
                        } else if(red.j < blue.j) { // blue가 더 오른쪽에 있었다면
                            nr[1]--; // red가 한 칸 왼쪽에 있어야 함
                        }
                    }
                }
                // 중복 처리
                /*if(!visitedR[nr[0]][nr[1]] || !visitedB[nb[0]][nb[1]]) {
                    q.offer(new Ball[]{
                            new Ball(nr[0], nr[1], red.count+1),
                            new Ball(nb[0], nb[1], blue.count+1)
                    });
                }*/

                if(!visited[nr[0]][nr[1]][nb[0]][nb[1]]) {
                    q.offer(new Ball[]{
                            new Ball(nr[0], nr[1], red.count+1),
                            new Ball(nb[0], nb[1], blue.count+1)
                    });
                }
            }
        }
        System.out.println(-1);
    }

    public static int[] moveBall(int si, int sj, int dir) {
        int[] dest = new int[2];

        int ni = si;
        int nj = sj;

        while(map[ni+di[dir]][nj+dj[dir]] != '#') {
            ni += di[dir];
            nj += dj[dir];

            if(map[ni][nj] == 'O') break;
        }

        dest[0] = ni;
        dest[1] = nj;
        return dest;
    }
}
