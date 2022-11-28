package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_8980 {

    /*
     박스를 트럭에 실으면, 박스는 받는 마을에서만 내린다
     트럭은 지나온 마을로 되돌아가지 않는다
     박스들 중 일부만 배송할 수도 있다

     트럭 한 대로 배송할 수 있는 최대 박스 수를 구하시오오
    */
    static class Delivery implements Comparable<Delivery> {
        int from;
        int to;
        int count;

        public Delivery(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Delivery{" +
                    "to=" + to +
                    ", count=" + count +
                    '}';
        }

        @Override
        public int compareTo(Delivery o) {
            int result = Integer.compare(this.to, o.to);
            if(result == 0) {
                result = Integer.compare(this.from, o.from);
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭 용량

        int M = Integer.parseInt(br.readLine()); // 박스 정보의 수

        List<Delivery> deliveries = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            deliveries.add(new Delivery(from, to, count));
        }
        Collections.sort(deliveries);

        int result = 0;
        int currPos = 1;
        int currWeight = 0;
        List<Delivery> currDeliveries = new ArrayList<>();

    }
}
