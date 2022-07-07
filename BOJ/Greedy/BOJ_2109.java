package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_2109 {
    private static class Class implements Comparable<Class> {
        int p;
        int d;

        public Class(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Class o) {
            int result = Integer.compare(o.p, this.p);
            if(result == 0) {
                result = Integer.compare(this.d, o.d);
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Class> pq = new PriorityQueue<>();
        int maxDay = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, d);
            pq.offer(new Class(p, d));
        }

        int[] result = new int[maxDay+1];
        while(!pq.isEmpty()) {
            Class c = pq.poll();

            for(int i=c.d; i>0; i--) {
                if(result[i] < c.p) {
                    result[i] = c.p;
                    break;
                }
            }
        }
        int sum = 0;
        for(int i=1; i<=maxDay; i++) {
            sum += result[i];
        }
        System.out.println(sum);
    }
}
