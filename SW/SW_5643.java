package SW;

import java.io.*;
import java.util.*;

public class SW_5643 {

    static int N, M;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new int[N+1][N+1];

            for(int i=1; i<=M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = 1;
            }

            int result = 0;
            for(int i=1; i<=N; i++) {
                if(child(i)+parent(i) == N-1) result++;
            }
            bw.write("#"+t+" "+result+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static int parent(int start) {
        int result = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();

            for(int i=1; i<=N; i++) {
                if(graph[curr][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    result++;
                }
            }
        }
        return result;
    }

    public static int child(int start) {
        int result = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        boolean[] visited = new boolean[N+1];
        visited[start] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();

            for(int i=1; i<=N; i++) {
                if(graph[i][curr] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                    result++;
                }
            }
        }
        return result;
    }
}
