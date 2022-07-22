package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_2528 {

    static class Ladder {
        int left;
        int right;
        int dir;

        public Ladder(int left, int right, int dir) {
            this.left = left;
            this.right = right;
            this.dir = dir;
        }

        public void move() {
            this.left += this.dir;
            this.right += this.dir;

            if(this.left == 0 || this.right == L) this.dir *= -1;
        }

        @Override
        public String toString() {
            return "Ladder{" +
                    "left=" + left +
                    ", right=" + right +
                    ", dir=" + dir +
                    '}';
        }
    }

    static int L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Ladder[] ladders = new Ladder[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            if(dir == 0) {
                ladders[i] = new Ladder(0, len, 1);
            } else {
                ladders[i] = new Ladder(L-len, L, -1);
            }
        }

        int height = 0;
        int time = 0;
        while(true) {
//            System.out.println("time: "+time+" height: "+height);
            while(true) { // 위층으로 더 이상 못 올라갈 때까지 계속 진행
                boolean flag = false;
                if(height >= N-1) break; // 꼭대기에 도착하면 멈춘다
                for (int i = ladders[height].left; i <= ladders[height].right; i++) { // 현재 층의 사다리에서 한 칸씩 이동하면서
                    if(ladders[height+1].left <= i && i <= ladders[height+1].right) { // 위층의 사다리가 겹치면
//                        System.out.print("height from "+height);
                        height++; // 위층으로 이동
//                        System.out.println(" to "+height);
                        flag = true;
                        break; // 위층으로 이동했으니 현재 층은 더 이상 볼 필요가 없다
                    }
                }
                if(!flag) break;
            }

            if(height >= N-1) break;

            for(Ladder ladder: ladders) {
//                System.out.println(ladder);
                ladder.move();
            }
            time++;
        }
        System.out.println(time);
    }

}
