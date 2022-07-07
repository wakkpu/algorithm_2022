package BOJ.BFS;

import java.io.*;
import java.util.*;

public class BOJ_1463_1 {

    static Set<String> visited;
    static int N, min;

    static class Node implements Comparable<Node> {
        int depth;
        int value;

        public Node(int depth, int value) {
            this.depth = depth;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            if(this.depth == o.depth) {
                return this.value - o.value;
            } else {
                return this.depth - o.depth;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bfs(new Node(0, N));

        System.out.println(min);
    }

    public static void bfs(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new HashSet<>();

        pq.offer(start);
        visited.add(start.depth+" "+start.value);

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            int depth = curr.depth;
            int value = curr.value;

            if(value == 1) {
                min = depth;
                return;
            }

            if(!visited.contains((depth+1)+" "+(value-1))) {
                pq.offer(new Node(depth+1, value-1));
            }

            if(value%2 == 0 && !visited.contains((depth+1)+" "+(value/2))) {
                pq.offer(new Node(depth+1, value/2));
            }

            if(value%3 == 0 && !visited.contains((depth+1)+" "+(value/3))) {
                pq.offer(new Node(depth+1, value/3));
            }
        }
    }
}
