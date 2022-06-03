package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10800 {

    static class Ball implements Comparable<Ball> {
        int number;
        int color;
        int size;
        int eaten;

        public Ball(int number, int color, int size, int eaten) {
            this.number = number;
            this.color = color;
            this.size = size;
            this.eaten = eaten;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "number=" + number +
                    ", color=" + color +
                    ", size=" + size +
                    ", eaten=" + eaten +
                    '}';
        }

        @Override
        public int compareTo(Ball o) {
            return Integer.compare(this.size, o.size);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Ball[] balls = new Ball[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i+1, color, size, 0);
        }

        Arrays.sort(balls);

        int prefixSum = 0;
        int[] colorSum = new int[N+1];
        int[] answer = new int[N+1];

        for(int i=0, j=0; i<N; i++) {
            while(balls[i].size > balls[j].size) {
                prefixSum += balls[j].size;
                colorSum[balls[j].color] += balls[j].size;
                j++;
            }
            answer[balls[i].number-1] = prefixSum - colorSum[balls[i].color];
        }

        for(int i=0; i<N; i++) {
            bw.write(answer[i]+"\n");
        }
        bw.flush();
        bw.close();

    }
}
