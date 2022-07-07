package BOJ.TopologicalSort;

import java.io.*;
import java.util.*;

public class BOJ_1516 {

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] times;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        for(int i=1; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        times = new int[N+1];
        depth = new int[N+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                graph[to].add(i);
                depth[i]++;
            }
        }

        /*for(int i=1; i<N+1; i++) {
            System.out.println(i+" : "+graph[i].toString());
        }*/
        //System.out.println("times : "+Arrays.toString(times));
        //System.out.println("depths : "+Arrays.toString(depth));

        topologicalSort(graph, depth);

    }

    public static void topologicalSort(ArrayList[] graph, int[] depth) {
        Queue<Integer> q = new LinkedList<>();

        int[] minTimes = new int[N+1];

        for(int i=1; i<N+1; i++) {
            if(depth[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();

            ArrayList<Integer> adjs = graph[curr];

            for(int adj: adjs) {
                depth[adj]--;

                minTimes[adj] = Math.max(minTimes[adj], minTimes[curr]+times[curr]);

                if(depth[adj] == 0) {
                    q.offer(adj);
                }
            }
        }

        for(int i=1; i<N+1; i++) {
            System.out.println(times[i]+minTimes[i]);
        }
    }
}
