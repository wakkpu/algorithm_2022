package SW;

import java.io.*;
import java.util.*;

public class SW_1249 {

    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;
    static int N;

    static int[][] dir = {{-1, 0},  {1, 0}, {0, -1}, {0, 1}};

    static class Node implements Comparable<Node>{
        int r;
        int c;
        int weight;

        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            dist = new int[N][N];

            for(int r=0; r<N; r++) {
                String str = br.readLine();
                for(int c=0; c<N; c++) {
                    map[r][c] = str.charAt(c) - '0';
                }
            }

            for(int i=0; i<N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            int ans = dijkstra(0, 0, N-1, N-1);

            bw.write("#"+t+" "+ans+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static int dijkstra(int sr, int sc, int dr, int dc) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(sr, sc, map[sr][sc]));
        dist[sr][sc] = map[sr][sc];
        visited[sr][sc] = true;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            int r = curr.r;
            int c = curr.c;
            int weight = curr.weight;

            if(r == dr && c == dc) {
                return weight;
            }

            for(int d=0; d<4; d++) {
                int nr = r+dir[d][0];
                int nc = c+dir[d][1];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new Node(nr, nc, weight+map[nr][nc]));
                }
            }
        }
        return 0;
    }
}
