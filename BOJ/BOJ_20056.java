package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_20056 {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static int N, M, K;
    static Map<Integer, Queue<FireBall>> map;

    static class FireBall {
        int m, d, s;

        public FireBall(int m, int d, int s) {
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public String toString() {
            return "FireBall{" +
                    "m=" + m +
                    ", d=" + d +
                    ", s=" + s +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(!map.containsKey(r*N+c)) {
                map.put(r*N+c, new LinkedList<>());
            }
            map.get(r*N+c).offer(new FireBall(m, d, s));
        }

//        System.out.println("initial");
//        printMap();

        while(K-->0) {
            move();
//            System.out.println("after move");
//            printMap();

            doSomething();
//            System.out.println("after do something");
//            printMap();
        }

        int result = getResult();
        System.out.println(result);

    }

    public static void printMap() {
        for(Map.Entry<Integer, Queue<FireBall>> entry: map.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    public static void move() {
        Map<Integer, Queue<FireBall>> temp = new HashMap<>();

        for(Map.Entry<Integer, Queue<FireBall>> entry: map.entrySet()) {
            int sr = entry.getKey() / N;
            int sc = entry.getKey() % N;

            Queue<FireBall> q = entry.getValue();
            while(!q.isEmpty()) {
                FireBall fireBall = q.poll();

                int nr = sr + dr[fireBall.d] * (fireBall.s % N);
                int nc = sc + dc[fireBall.d] * (fireBall.s % N);

                if(nr > 0) nr %= N;
                if(nc > 0) nc %= N;

                if(nr < 0) nr = N - Math.abs(nr);
                if(nc < 0) nc = N - Math.abs(nc);

                if(!temp.containsKey(nr*N+nc)) {
                    temp.put(nr*N+nc, new LinkedList<>());
                }
                temp.get(nr*N+nc).offer(fireBall);
            }
        }

        map = new HashMap<>();
        for(Map.Entry<Integer, Queue<FireBall>> entry: temp.entrySet()) {
            map.put(entry.getKey(), new LinkedList<>());
            while(!entry.getValue().isEmpty()) {
                map.get(entry.getKey()).offer(entry.getValue().poll());
            }
        }
    }

    public static void doSomething() {
        for(Map.Entry<Integer, Queue<FireBall>> entry: map.entrySet()) {
            int val = entry.getKey();
            Queue<FireBall> q = entry.getValue();

            if(q.size() >= 2) {
                int m = 0;
                int s = 0;
                int qSize = q.size();

                int flag = 0; // -1: 홀, 0: 초기, 1: 짝
                boolean d = true; // true: 0246, false: 1357

                while(!q.isEmpty()) {
                    FireBall fireBall = q.poll();

                    m += fireBall.m;
                    s += fireBall.s;

                    if(flag == 0) {
                        if(fireBall.d % 2 != 0) {
                            flag = -1;
                        } else {
                            flag = 1;
                        }
                    } else if(flag == -1) {
                        if(fireBall.d % 2 == 0) {
                            d = false;
                        }
                    } else {
                        if(fireBall.d % 2 != 0) {
                            d = false;
                        }
                    }
                }

                m = m / 5;
                s = s / qSize;

                if(m > 0) {
                    if(d) {
                        for(int i=0; i<=6; i+=2) {
                            map.get(val).offer(new FireBall(m, i, s));
                        }
                    } else {
                        for(int i=1; i<=7; i+=2) {
                            map.get(val).offer(new FireBall(m, i, s));
                        }
                    }
                }
            }
        }
    }

    public static int getResult() {
        int result = 0;

        for(Map.Entry<Integer, Queue<FireBall>> entry: map.entrySet()) {
            while(!entry.getValue().isEmpty()) {
                result += entry.getValue().poll().m;
            }
        }

        return result;
    }
}
