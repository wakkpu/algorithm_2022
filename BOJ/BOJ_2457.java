package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2457 {

    static int N;

    static class Flower implements Comparable<Flower> {
        String startDate;
        String endDate;

        public Flower(int startMonth, int startDay, int endMonth, int endDay) {
            StringBuilder startSb = new StringBuilder();
            if(1 <= startMonth && startMonth <= 9) {
                startSb.append("0");
            }
            startSb.append(startMonth);

            if(1 <= startDay && startDay <= 9) {
                startSb.append("0");
            }
            startSb.append(startDay);
            startDate = startSb.toString();

            StringBuilder endSb = new StringBuilder();
            if(1 <= endMonth && endMonth <= 9) {
                endSb.append("0");
            }
            endSb.append(endMonth);

            if(1 <= endDay && endDay <= 9) {
                endSb.append("0");
            }
            endSb.append(endDay);
            endDate = endSb.toString();
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "startDate='" + startDate + '\'' +
                    ", endDate='" + endDate + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Flower o) {
            // 시작일 낮은 순, 종료일 높은 순
            int result = this.startDate.compareTo(o.startDate);
            if(result == 0) {
                result = o.endDate.compareTo(this.endDate);
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Flower> flowers = new PriorityQueue<>();
        while(N-->0) {
            st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());
            flowers.offer(new Flower(startMonth, startDay, endMonth, endDay));
        }

        int currStart = 1231;
        int currEnd = 1231;

        int targetStart = 301;
        int targetEnd = 1130;

        int count = 0;
        while(!flowers.isEmpty()) {
            Flower f = flowers.poll();


        }
        if(currStart <= targetStart && targetEnd <= currEnd) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }
    }
}
