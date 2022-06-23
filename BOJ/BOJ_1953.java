package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1953 {

    static int N;
    static boolean[] visited;
    static List<List<Integer>> graph;
    static Set<Integer> blue, white;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            while(n-->0) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

//        for(int i=1; i<=N; i++) {
//            System.out.println(graph.get(i).toString());
//        }

        blue = new TreeSet<>();
        white = new TreeSet<>();

        visited = new boolean[N+1];
        q = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                bfs(i);
            }
        }

        System.out.println(blue.size());
        for(Integer integer: blue) {
            System.out.print(integer+" ");
        }
        System.out.println();

        System.out.println(white.size());
        for(Integer integer: white) {
            System.out.print(integer+" ");
        }
    }

    public static void bfs(int start) {
        //boolean flag = true;
        visited[start] = true;

        q.offer(start);
        blue.add(start);

        while(!q.isEmpty()) {
            int qSize = q.size();
            //flag = !flag;
//            System.out.println("flag:"+flag);
            while(qSize-->0) {
                int curr = q.poll();
//                System.out.println("curr: "+curr);

                for(int adj: graph.get(curr)) {
                    if(visited[adj]) continue;

                    visited[adj] = true;
                    q.offer(adj);

                    if(blue.contains(curr)) white.add(adj);
                    else blue.add(adj);
                }
            }
        }
    }
}
