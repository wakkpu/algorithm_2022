package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1826 {

    private static class Info implements Comparable<Info> {
        int dist;
        int fuel;

        public Info(int dist, int fuel) {
            this.dist = dist;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.dist, o.dist);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "dist=" + dist +
                    ", fuel=" + fuel +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 주유소는 거리 가까운데부터 가야함.
        PriorityQueue<Info> gasStations = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());
            gasStations.offer(new Info(dist, fuel));
        }

        st = new StringTokenizer(br.readLine());
        int dest = Integer.parseInt(st.nextToken());
        int currFuel = Integer.parseInt(st.nextToken());

        // 연료는 큰 양부터 넣어야 최소 주유소 경유.
        PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder());

        int count = 0;
        while(currFuel < dest) { // 연료의 합이 목적지 거리 이상이면 도착 가능
            // 가까운 주유소부터 갈 수 있는 주유소 정보 모두 저장
            while(!gasStations.isEmpty() && gasStations.peek().dist <= currFuel) {
                // System.out.println("gas stations:"+gasStations);
                fuels.offer(gasStations.poll().fuel); // 연료 넣기.
                // System.out.println("fuels:"+fuels);
            }

            if(fuels.isEmpty()) {
                System.out.println(-1);
                return;
            }

            count++; // 연료 넣음
            currFuel += fuels.poll();
            // System.out.println("curr fuel:"+currFuel);
        }
        // 도착했으므로 연료 넣은 주유소 갯수 출력
        System.out.println(count);
    }
}
