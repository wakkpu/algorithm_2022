package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13265 {

    static List<List<Integer>> graph;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        loop:
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            for(int i=1; i<=N; i++) {
                if(!bfs(i, 1)) {
                    bw.write("impossible\n");
                    continue loop;
                }
            }
            bw.write("possible\n");
        }
        bw.flush();
    }

    public static boolean bfs(int start, int color) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, color});

        int[] visited = new int[N+1];
        visited[start] = color;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int currNum = curr[0];
            int currCol = curr[1];

            for(int adj: graph.get(currNum)) {
                if(visited[adj] == 0) { // 인접한 곳이 안칠해졌다면
                    visited[adj] = currCol * -1; // 다른 색으로 칠한다 1, -1
                    q.offer(new int[]{adj, currCol * -1});
                } else { // 인접한 곳이 칠해져있다면
                    if(visited[adj] == visited[currNum]) { // 현재와 같은 색이면 안됨
                        //System.out.println(Arrays.toString(visited));
                        return false;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(visited));
        return true;
    }
}
