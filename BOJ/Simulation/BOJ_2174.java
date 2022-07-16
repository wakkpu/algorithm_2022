package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_2174 {

    static int[][] vector = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // N, E, S, W
    static Robot[] robots;
    static Mission[] missions;
    static int[][] map;
    static int A, B, N, M;

    static class Robot {
        int r;
        int c;
        int dir;

        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public void operate(Mission mission) {
            if(mission.order.equals("R")) {
                this.dir = (this.dir+mission.iter)%4;
//                System.out.println(this.dir);
            } else if(mission.order.equals("L")) {
                this.dir = (this.dir-mission.iter)%4;
                if(this.dir < 0) this.dir = 4 + this.dir;
//                System.out.println(this.dir);
            } else {
                while(mission.iter-->0) {
                    int sr = this.r;
                    int sc = this.c;

                    this.r += vector[this.dir][0];
                    this.c += vector[this.dir][1];

                    if(!this.isIn()) {
                        System.out.println("Robot "+map[sr][sc]+" crashes into the wall");
                        System.exit(0);
                    }

                    if(map[this.r][this.c] != 0) {
                        System.out.println("Robot "+map[sr][sc]+" crashes into robot "+map[this.r][this.c]);
                        System.exit(0);
                    }

                    map[this.r][this.c] = map[sr][sc];
                    map[sr][sc] = 0;
                }
            }
        }

        public boolean isIn() {
            return (1 <= this.c && this.c <= A) && (1 <= this.r && this.r <= B);
        }
    }

    static class Mission {
        int which;
        String order;
        int iter;

        public Mission(int which, String order, int iter) {
            this.which = which;
            this.order = order;
            this.iter = iter;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[B+1][A+1];

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        robots = new Robot[N];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            int dir = 0;
            if(str.equals("E")) {
                dir = 1;
            } else if(str.equals("S")) {
                dir = 2;
            } else if(str.equals("W")) {
                dir = 3;
            }
            robots[i-1] = new Robot(r, c, dir);
            map[r][c] = i;
        }

        missions = new Mission[M];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int which = Integer.parseInt(st.nextToken());
            String order = st.nextToken();
            int iter = Integer.parseInt(st.nextToken());
            missions[i] = new Mission(which, order, iter);
        }

        for(Mission mission: missions) {
            robots[mission.which-1].operate(mission);
        }
        System.out.println("OK");
    }
}