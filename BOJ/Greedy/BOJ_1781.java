package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1781 {
    static class Info implements Comparable<Info> {
        int num;
        int deadLine;
        int reward;

        public Info(int num, int deadLine, int reward) {
            this.num = num;
            this.deadLine = deadLine;
            this.reward = reward;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "num=" + num +
                    ", deadLine=" + deadLine +
                    ", reward=" + reward +
                    '}';
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.deadLine, o.deadLine);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // deadLine에 대해 오름차순 정렬
        PriorityQueue<Info> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            pq.offer(new Info(i, deadLine, reward));
        }

        // reward에 대해 오름차순 정렬
        PriorityQueue<Info> pq2 = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return Integer.compare(o1.reward, o2.reward);
            }
        });

        long result = 0;
        for(int i=1; i<=N; i++) {
            Info curr = pq.poll();
            int deadLine = curr.deadLine;
            int reward = curr.reward;
            pq2.add(curr);
            while(!pq2.isEmpty() && pq2.size() > deadLine) pq2.poll();
        }

        while(!pq2.isEmpty()) {
            result += pq2.poll().reward;
        }
        System.out.println(result);
    }
}
