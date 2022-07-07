package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1202 {

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Jewel [" + "weight=" + weight + ", value=" + value + ']';
        }

        @Override
        public int compareTo(Jewel o) {
            // 무게는 가볍고, 가치는 높은게 좋은거니까
            if(this.weight == o.weight) { // 무게 같으면
                return o.value - this.value; // 가치로 내림차순
            } else { // 무게 다르면
                return this.weight - o.weight; // 무게로 오름차순
            }
        }
    }

    static int N, K;
    static PriorityQueue<Jewel> jewels = new PriorityQueue<>(); // 보석 가게에 있는 보석
    static PriorityQueue<Integer> knapsacks = new PriorityQueue<>(Comparator.naturalOrder()); // 가벼운 가방부터 채운다.
    static PriorityQueue<Integer> steal = new PriorityQueue<>(Comparator.reverseOrder()); // 비싼 보석부터 챙긴다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels.offer(new Jewel(weight, value));
        }

        for(int i=0; i<K; i++) {
            knapsacks.offer(Integer.parseInt(br.readLine()));
        }

        long answer = 0;

        while(!knapsacks.isEmpty()) { // 가방에 대해 반복
            int knapsack = knapsacks.poll();

            // 가방 무게보다 가벼우면 챙길 수 있으니까 일단 훔칠 후보
            while(!jewels.isEmpty() && jewels.peek().weight <= knapsack) {
                steal.offer(jewels.poll().value);
            }

            // 후보 중 가장 비싼 보석 가방에 챙김
            if(!steal.isEmpty()) {
                answer += steal.poll();
            }
        }

        System.out.println(answer);
    }
}
