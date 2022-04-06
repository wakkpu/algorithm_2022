package BOJ;

import java.io.*;
import java.util.*;

/*
    사과를 먹으면 뱀 길이가 늘어남. 벽 또는 자기자신의 몸과 부딪히면 게임이 끝남.
    게임은 N x N 정사각 보드 위에서 진행. 몇몇 칸에는 사과가 놓여있음.
    게임이 시작할 때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1. 뱀은 맨 처음에 오른쪽 향함.

    매 초 마당 이동 시 규칙.
    1. 몸 길이를 늘려 머리를 다음 칸에 위치시킴.
    2. 이동한 칸에 사과가 있다면, 사과가 사라지고 꼬리는 움직이지 않는다. (길이 +1)
    3. 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. (길이 그대로)

    게임이 몇 초만에 끝나는지 계산하라.
 */
public class BOJ_3190 {

    static class Pos implements Comparable<Pos> {
        int r;
        int c;
        int time;

        public Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        @Override
        public int compareTo(Pos o) {
            return this.time - o.time;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", time=" + time +
                    '}';
        }
    }

    static class Vector {
        int sec;
        String LR;

        public Vector(int sec, String LR) {
            this.sec = sec;
            this.LR = LR;
        }
    }

    static int N, K, L;
    static int[][] map;
    static Queue<Vector> vectorQ;
    static PriorityQueue<Pos> snake;

    // 우, 하, 좌, 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        // 0: 빈칸, 1: 뱀, 2: 사과
        map = new int[N+1][N+1];
        while(K-->0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }

        L = Integer.parseInt(br.readLine());
        vectorQ = new LinkedList<>();
        while(L-->0) {
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            String LR = st.nextToken();
            vectorQ.offer(new Vector(sec, LR));
        }

        snake = new PriorityQueue<>();

        int nr = 1, nc = 1, time = 0;
        map[nr][nc] = 1;
        snake.offer(new Pos(nr, nc, time));

        int vector = 0;
        while(true) {
            nr += dr[vector];
            nc += dc[vector];
            time++;
            Pos next = new Pos(nr, nc, time);
            //System.out.println(next);

            if(nr < 1 || nr > N || nc < 1 || nc > N) {
                //System.out.println("crashed on wall: "+time);
                break;
            }

            if(map[nr][nc] == 0) {
                snake.offer(next);
                map[nr][nc] = 1;

                Pos tail = snake.poll();
                map[tail.r][tail.c] = 0;
            } else if(map[nr][nc] == 1) {
                //System.out.println("crashed on map["+nr+"]["+nc+"]: "+time);
                break;
            } else if(map[nr][nc] == 2) {
                snake.offer(next);
                map[nr][nc] = 1;
            }

            if(!vectorQ.isEmpty() && vectorQ.peek().sec == time) {
                Vector vec = vectorQ.poll();
                //System.out.print("vector changed from "+vector);
                if(vec.LR.equals("L")) {
                    if(vector == 0) {
                        vector = 3;
                    } else {
                        vector--;
                    }
                } else if(vec.LR.equals("D")) {
                    if(vector == 3) {
                        vector = 0;
                    } else {
                        vector++;
                    }
                }
                //System.out.println(" to "+vector+": "+time);
            }
        }

        System.out.println(time);
    }
}
