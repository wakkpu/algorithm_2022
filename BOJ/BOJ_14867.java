package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14867 {
    static class Bottles {

        public int capacityA; // A 용량
        public int capacityB; // B 용량
        public int containA; // A의 현재 물 양
        public int containB; // B의 현재 물 양
        public int count; // 시행 횟수

        public Bottles(int capacityA, int capacityB, int containA, int containB, int count) {
            this.capacityA = capacityA;
            this.capacityB = capacityB;
            this.containA = containA;
            this.containB = containB;
            this.count = count;
        }
    }

    static int C, D;
    static int minCount = Integer.MAX_VALUE;
    static HashSet<String> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Bottles bottles = new Bottles(a, b, 0, 0, 0);

        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        bfs(bottles);

        if(minCount == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCount);
        }
    }

    public static boolean isVisited(int aContain, int bContain) {
        return visited.contains(aContain + " " + bContain);
    }

    public static void addVisited(int aContain, int bContain) {
        visited.add(aContain + " " + bContain);
    }

    public static void bfs(Bottles bt) {
        Queue<Bottles> q = new LinkedList<>();
        q.offer(bt);
        visited = new HashSet<>();
        addVisited(bt.containA, bt.containB);

        while(!q.isEmpty()) {

             Bottles bottles = q.poll();

             int containA = bottles.containA;
             int containB = bottles.containB;
             int capacityA = bottles.capacityA;
             int capacityB = bottles.capacityB;
             int count = bottles.count;

             // if(count >= minCount) continue;

             if(containA == C && containB == D) {
                 minCount = Math.min(minCount, count);
             }

             for(int c=0; c<6; c++) {
                 // A 비우기
                 if(c == 0 && !isVisited(0, containB)) {
                     addVisited(0, containB);
                     q.offer(new Bottles(capacityA, capacityB, 0, containB, count+1));
                 }

                 // A 채우기
                 if(c == 1 && !isVisited(capacityA, containB)) {
                     addVisited(capacityA, containB);
                     q.offer(new Bottles(capacityA, capacityB, capacityA, containB, count+1));
                 }

                 // B 비우기
                 if(c == 2 && !isVisited(containA, 0)) {
                     addVisited(containA, 0);
                     q.offer(new Bottles(capacityA, capacityB, containA, 0, count+1));
                 }

                 // B 채우기
                 if(c == 3 && !isVisited(containA, capacityB)) {
                     addVisited(containA, capacityB);
                     q.offer(new Bottles(capacityA, capacityB, containA, capacityB, count+1));
                 }

                 // A -> B
                 if(c == 4) {
                     int emptySpace = capacityB - containB;

                     if(containA <= emptySpace) {
                         containB += containA;
                         containA = 0;
                     } else {
                         containB = capacityB;
                         containA -= emptySpace;
                     }

                     if(!isVisited(containA, containB)) {
                         addVisited(containA, containB);
                         q.offer(new Bottles(capacityA, capacityB, containA, containB, count+1));
                     }
                 }

                 // B -> A
                 if(c == 5) {
                     int emptySpace = capacityA - containA;

                     if(containB <= emptySpace) {
                         containA += containB;
                         containB = 0;
                     } else {
                         containA = capacityA;
                         containB -= emptySpace;
                     }

                     if(!isVisited(containA, containB)) {
                         addVisited(containA, containB);
                         q.offer(new Bottles(capacityA, capacityB, containA, containB, count+1));
                     }
                 }
             }
        }
    }
}
