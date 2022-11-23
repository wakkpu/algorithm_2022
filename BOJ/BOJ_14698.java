package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14698 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        final int DIV = 1_000_000_007;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            Queue<Long> q = new LinkedList<>();

            st = new StringTokenizer(br.readLine());
            while(N-->0) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            while(pq.size() >= 2) {
                long num1 = pq.poll();
                long num2 = pq.poll();

                q.offer(num1 * num2 % DIV);
                pq.offer(num1 * num2);
            }

            long result = 1L;
            while(!q.isEmpty()) {
                long temp = q.poll();
                result = (result * (temp % DIV)) % DIV;
            }
            bw.write(result+"\n");
        }
        bw.flush();
    }
}
