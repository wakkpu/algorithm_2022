package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12851 {

    static int N, K;
    static int minSecond = 200_000;
    static int kind = 0;

    static class Visit {
        int i;
        int time;

        public Visit(int i, int time) {
            this.i = i;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(minSecond);
        System.out.println(kind);
    }
    public static void bfs() {
        Queue<Visit> q = new LinkedList<>();
        boolean[] visited = new boolean[100_001];

        visited[N] = true;
        q.offer(new Visit(N, 0));

        while(!q.isEmpty()) {
            Visit v = q.poll();

            int place = v.i;
            int time = v.time;
            visited[place] = true;

            if(place == K) {
                if(time <= minSecond) {
                    minSecond = time;
                    kind++;
                }
                continue;
            }

            int case1 = place-1;
            int case2 = place+1;
            int case3 = 2*place;

            if(0 <= case1 && !visited[case1]) {
                q.offer(new Visit(case1, time+1));
            }
            if(case2 <= 100_000 && !visited[case2]) {
                q.offer(new Visit(case2, time+1));
            }
            if(case3 <= 100_000 && !visited[case3]) {
                q.offer(new Visit(case3, time+1));
            }
        }
    }
}
