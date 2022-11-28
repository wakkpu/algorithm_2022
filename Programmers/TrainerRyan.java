package Programmers;

import java.util.*;

public class TrainerRyan {

    public static void main(String[] args) {
        TrainerRyan Main = new TrainerRyan();
        int result = Main.solution(4, 5, new int[][]{{1140,1200},{1150,1200},{1100,1200},{1210,1300},{1220,1280}});
        System.out.println("result: "+result);
    }

    static class Time implements Comparable<Time>{
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            int result = Integer.compare(this.start, o.start);
            if(result == 0) {
                result = Integer.compare(this.end, o.end);
            }
            return result;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static int N, minDistance, maxPeople;

    public int solution(int n, int m, int[][] timetable) {
        N = n;

        minDistance = Integer.MAX_VALUE;
        maxPeople = 0;

        // 가장 사람이 많은 시간대를 찾아야 한다. 가장 사람이 많은 시간대에 몇 명인지
        PriorityQueue<Time> pq = new PriorityQueue<>();
        for(int[] times: timetable) {
            pq.offer(new Time(times[0], times[1]));
        }

        int count = 0;
        int minStart = 0;
        int maxEnd = 0;

        while(!pq.isEmpty()) {
            Time curr = pq.poll();

            if(minStart == 0 && maxEnd == 0) {
                minStart = curr.start;
                maxEnd = curr.end;
                count++;
//                System.out.println(curr+" count: "+count);
                continue;
            }

            // 겹치는 경우 사람 수 +1
            if(minStart <= curr.start && curr.start <= maxEnd) {
                if(curr.end >= maxEnd) {
                    minStart = curr.start;
                    maxEnd = curr.end;
                }
                count++;
            } else if(curr.start > maxEnd) { // 아예 안 겹치는 경우 사람 수 -1
                count = 0;
                minStart = curr.start;
                maxEnd = curr.end;
            }

            maxPeople = Math.max(count, maxPeople);
//            System.out.println(curr+" count: "+count);
        }

//        System.out.println("maxPeople: "+maxPeople);

        if(maxPeople == 1) return 0;
        if(maxPeople == 2) return (int) Math.sqrt(n*n*2);

        // 배치
        placeCustomer(new ArrayList<>());

        return minDistance;
    }

    public static void placeCustomer(List<Integer> places) {
        if(places.size() == maxPeople) {
//            System.out.print(places+" ");
            int dist = Integer.MAX_VALUE;
            for(int i=0; i<places.size()-1; i++) {
                for(int j=1; j<places.size(); j++) {
                    if(i == j) continue;
//                    System.out.println(i+" "+places.get(i)+" "+j+" "+places.get(j));
                    int curr = distance(i, places.get(i), j, places.get(j));
                    if(dist > curr) {
                        dist = curr;
                    }
                }
            }

            if(dist < minDistance) {
                minDistance = dist;
//                System.out.println(minDistance);
            }
//            System.out.println();
            return;
        }

        for(int i=0; i<N; i++) {
            places.add(i);
            placeCustomer(places);
            places.remove(places.size()-1);
        }
    }

    public static int distance(int a, int b, int c, int d) {
        return Math.abs(a - c) + Math.abs(b - d);
    }
}
