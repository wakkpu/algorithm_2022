package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_13975 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        long T = Long.parseLong(br.readLine());
        while(T-->0) {
            PriorityQueue<Long> pq = new PriorityQueue<>();

            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long sum = 0;
            while(pq.size() >= 2) {
                long num1 = pq.poll();
                long num2 = pq.poll();
                long result = num1+num2;

                sum += result;
                pq.offer(result);
            }

            bw.write(sum+"\n");
        }
        bw.flush();
        bw.close();
    }
}
