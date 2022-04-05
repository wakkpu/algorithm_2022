package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pos = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> neg = new PriorityQueue<>(Comparator.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if(cur > 0) {
                pos.offer(cur);
            } else if(cur < 0) {
                neg.offer(Math.abs(cur));
            }
        }

        int farthest = 0;
        if(pos.isEmpty()) {
            farthest = neg.peek();
        } else if(neg.isEmpty()) {
            farthest = pos.peek();
        } else {
            farthest = Math.max(neg.peek(), pos.peek());
        }

        int result = 0;
        while(!pos.isEmpty()) {
            int cur = pos.poll();
            for(int i=0; i<M-1; i++) {
                pos.poll();

                if(pos.isEmpty()) break;
            }
            result += 2*cur;
        }

        while(!neg.isEmpty()) {
            int cur = neg.poll();
            for(int i=0; i<M-1; i++) {
                neg.poll();

                if(neg.isEmpty()) break;
            }
            result += 2*cur;
        }

        result -= farthest;
        System.out.println(result);
    }
}
