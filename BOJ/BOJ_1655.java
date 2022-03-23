package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // pq2 <-> pq1
        PriorityQueue<Integer> pq_min = new PriorityQueue<>(); // 얘가 뱉는 건 최소값
        PriorityQueue<Integer> pq_max = new PriorityQueue<>(Collections.reverseOrder()); // 최대값

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());



        }
    }
}
