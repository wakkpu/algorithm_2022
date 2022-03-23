package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2170 {
    static class Line implements Comparable<Line>{
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        @Override
        public int compareTo(Line o) {
            if(this.start == o.start) {
                return this.end - o.end;
            } else {
                return this.start - o.start;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Line> pq = new PriorityQueue<>();
        while(N-->0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new Line(start, end));
        }

        int length = 0;
        int left  = -1_000_000_001;
        int right = -1_000_000_001;

        while(!pq.isEmpty()) {
            Line curr = pq.poll();
            //System.out.print(curr.toString()+" : ");
            int currStart = curr.start;
            int currEnd = curr.end;

            if(right < currStart) { // 새로운 선 시작
                //System.out.println("case 1 "+"left: "+left+" right: "+right);
                left = currStart;
                right = currEnd;
                //System.out.print("length: "+length);
                length += (right - left);
                //System.out.println("-> "+length);
                continue;
            }

            // 선 연장
            if((left <= currStart && currStart <= right) && currEnd > right) {
                //System.out.println("case 2 "+"left: "+left+" right: "+right);
                //System.out.print("length: "+length);
                length += (currEnd - right);
                //System.out.println("-> "+length);
                right = currEnd;
                continue;
            }

            // 선 포함
            /*if((left <= currStart && currStart <= right) &&
                    (left <= currEnd && currEnd <= right)) {
                //System.out.println("case 3");
            }*/
        }

        System.out.println(length);
    }
}
