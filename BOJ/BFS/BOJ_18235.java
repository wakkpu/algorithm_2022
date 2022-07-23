package BOJ.BFS;

import java.io.*;
import java.util.*;

public class BOJ_18235 {

    static int N, A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(-1);
    }

    public static void bfs() {
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();

        boolean[][] visitedA = new boolean[20][N+1];
        boolean[][] visitedB = new boolean[20][N+1];

        queueA.offer(A);
        visitedA[0][A] = true;

        queueB.offer(B);
        visitedB[0][B] = true;

        int time = 0;
        while(!queueA.isEmpty()) {
            int aSize = queueA.size();
            while(aSize-->0) {
                int curr = queueA.poll();

                int left  = curr - (1<<time);
                int right = curr + (1<<time);

                if(isIn(left) && !visitedA[time+1][left]) {
                    queueA.offer(left);
                    visitedA[time+1][left] = true;
                }

                if(isIn(right) && !visitedA[time+1][right]) {
                    queueA.offer(right);
                    visitedA[time+1][right] = true;
                }
            }
            time++;
        }

        time = 0;
        while(!queueB.isEmpty()) {
            int bSize = queueB.size();
            while(bSize-->0) {
                int curr = queueB.poll();

                if(visitedA[time][curr]) {
                    System.out.println(time);
                    System.exit(0);
                }

                int left  = curr - (1<<time);
                int right = curr + (1<<time);

                if(isIn(left) && !visitedB[time+1][left]) {
                    queueB.offer(left);
                    visitedB[time+1][left] = true;
                }

                if(isIn(right) && !visitedB[time+1][right]) {
                    queueB.offer(right);
                    visitedB[time+1][right] = true;
                }
            }
            time++;
        }
    }

    public static boolean isIn(int curr) {
        return 1 <= curr && curr <= N;
    }
}
