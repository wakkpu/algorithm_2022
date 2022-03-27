package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1039 {

    static int K;
    static String N;
    static Set<String> visited;
    static int max = -1;

    static class Tuple {
        int k;
        String n;

        public Tuple(int k, String n) {
            this.k = k;
            this.n = n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        visited = new HashSet<>();

        Tuple tuple = new Tuple(K, N);
        bfs(tuple);
        System.out.println(max);
    }

    public static void bfs(Tuple tuple) {
        Queue<Tuple> q = new LinkedList<>();
        q.offer(tuple);
        visited.add(tuple.k+" "+tuple.n);

        while (!q.isEmpty()) {
            Tuple curr = q.poll();
            int k = curr.k;
            String n = curr.n;

            if(k == 0) {
                max = Math.max(max, Integer.parseInt(n));
                continue;
            }

            int length = n.length();
            for(int i=0; i<length-1; i++) {
                for(int j=i+1; j<length; j++) {
                    String swapStr = swap(n, i, j);
                    if(swapStr.charAt(0) != '0' && !visited.contains((k-1)+" "+swapStr)) {
                        q.offer(new Tuple(k-1, swapStr));
                        visited.add((k-1)+" "+swapStr);
                    }
                }
            }
        }
    }

    public static String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);

        char temp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, temp);

        return sb.toString();
    }
}
