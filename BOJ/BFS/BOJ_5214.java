package BOJ.BFS;

import java.io.*;
import java.util.*;

public class BOJ_5214 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] hyper = new int[K];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<K; j++) {
                hyper[j] = Integer.parseInt(st.nextToken());
            }

            for(int x=0; x<K-1; x++) {
                for(int y=x+1; y<K; y++) {
                    if(hyper[x] == hyper[y]) continue;

                    if(!graph.get(hyper[x]).contains(hyper[y]))
                        graph.get(hyper[x]).add(hyper[y]);

                    if(!graph.get(hyper[y]).contains(hyper[x]))
                        graph.get(hyper[y]).add(hyper[x]);
                }
            }
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        int num = 1;
        while(!q.isEmpty()) {
            int qSize = q.size();

            while(qSize-->0) {
                int curr = q.poll();

                if(curr == N) {
                    System.out.println(num);
                    return;
                }

                for(int adj: graph.get(curr)) {
                    if(visited[adj] || adj <= curr) continue;

                    visited[adj] = true;
                    q.offer(adj);
                }
            }
            num++;
        }
        System.out.println(-1);
    }
}
