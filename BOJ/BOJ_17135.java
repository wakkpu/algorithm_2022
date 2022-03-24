package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17135 {

    static int N, M, D;
    static int[][] map;
    static int[][] copy;
    static int maxCount = 0;

    static int distance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        copy = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = map[i][j];
            }
        }

        ArrayList<Integer> archers = new ArrayList<>();

        setArcher(3, archers, 1);
        System.out.println(maxCount);

    }

    public static void setArcher(int toChoose, ArrayList<Integer> archers , int startIdx) {
        if(toChoose == 0) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++) {
                    copy[i][j] = map[i][j];
                }
            }

            int count = simulate(archers);
            maxCount = Math.max(count, maxCount);
            return;
        }

        for(int i=startIdx; i<=M; i++) {
            archers.add(i);
            setArcher(toChoose-1, archers, i+1);
            archers.remove(archers.size()-1);
        }
    }

    public static int simulate(ArrayList<Integer> archers) {
        //System.out.println(archers.toString());
        int count = 0;

        for(int n=1; n<=N; n++) {
            boolean[][] targeted = new boolean[N+1][M+1];
            for(int archer: archers) {
                int minDistance = Integer.MAX_VALUE;
                int minR = 0;
                int minC = 0;

                for(int i=1; i<=N; i++) {
                    for(int j=1; j<=M; j++) {
                        int dist = distance(N+1, archer, i, j);
                        if(copy[i][j] == 1 && dist <= D) {
                            if(dist < minDistance) {
                                minDistance = dist;
                                minR = i;
                                minC = j;
                            } else if(dist == minDistance) {
                                if(j < minC) {
                                    minR = i;
                                    minC = j;
                                }
                            }
                        }
                    }
                }
                targeted[minR][minC] = true;
            }

            for(int i=1; i<=N; i++) {
                for(int j=1; j<=M; j++) {
                    if(targeted[i][j]) {
                        copy[i][j] = 0;
                        count++;
                    }
                }
            }

            move();
        }
        //System.out.println("count: "+count);
        return count;
    }

    static void move() {
        for(int j=1; j<=M; j++) {
            copy[N][j] = 0;
        }

        for(int i=N; i>=1; i--) {
            for(int j=1; j<=M; j++) {
                copy[i][j] = copy[i-1][j];
            }
        }
    }
}
