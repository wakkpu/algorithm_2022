package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13549 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int count = bfs();
        System.out.println(count);
    }
    public static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[100_001]; // visited[i]: i에 몇 초 시점에 방문했는지
        Arrays.fill(visited, -1);

        visited[N] = 0;
        q.offer(N);

        while(!q.isEmpty()) {
            int x = q.poll();

            if(x == K) {
                return visited[K];
            }
            // 순간이동의 경우 0초가 걸리므로 이 부분을 고려해야 한다.
            // 0-1 BFS 유형
            if(2*x <= 100_000 && visited[2*x] == -1) {
                q.offer(2*x);
                visited[2*x] = visited[x];
            }
            if(0 <= x-1 && visited[x-1] == -1) {
                q.offer(x-1);
                visited[x-1] = visited[x]+1;
            }
            if(x+1 <= 100_000 && visited[x+1] == -1) {
                q.offer(x+1);
                visited[x+1] = visited[x]+1;
            }
        }
        return 0;
    }
}