package BOJ.ShortestPath.Dijkstra;

import java.io.*;
import java.util.*;

public class BOJ_4485 {

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int N, min;

    static class Node implements Comparable<Node> {
        int i;
        int j;
        int val;

        public Node(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            visited = new boolean[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dijkstra();
            bw.write("Problem "+(T++)+": "+min+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void dijkstra() {
        min = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(curr.i == N-1 && curr.j == N-1) {
                min = Math.min(min, curr.val);
                return;
            }

            for(int d=0; d<4; d++) {
                int ni = curr.i+di[d];
                int nj = curr.j+dj[d];

                if(ni<0 || ni>=N || nj<0 || nj>=N || visited[ni][nj]) continue;

                visited[ni][nj] = true;
                pq.offer(new Node(ni, nj, curr.val+map[ni][nj]));
            }
        }
    }
}
