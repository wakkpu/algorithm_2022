package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17281 {

    static int[][] players;
    static int N;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        players = new int[N+1][10];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=9; j++) {
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] lineup = new int[10];
        boolean[] selected = new boolean[10];

        lineup[4] = 1;
        selected[4] = true;

        makePermutation(2, lineup, selected);

        System.out.println(max);
    }

    public static void makePermutation(int toChoose, int[] lineup, boolean[] selected) {
        if(toChoose == 10) {
            int result = simulate(lineup);
            max = Math.max(result, max);
            return;
        }

        for(int i=1; i<=9; i++) {
            if(!selected[i]) {
                selected[i] = true;
                lineup[i] = toChoose;
                makePermutation(toChoose+1, lineup, selected);
                selected[i] = false;
            }
        }
    }

    public static int simulate(int[] lineup) {
        int score = 0;
        int startIdx = 1;
        for(int i=1; i<=N; i++) {
            int out = 0;
            boolean[] base = new boolean[4]; // home, 1, 2, 3루

            outerloop: while(true) {
                for(int j=startIdx; j<=9; j++) {
                    int hit = players[i][lineup[j]];

                    if(hit == 0) {
                        out++;
                    } else if(hit == 1) {
                        if(base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            base[3] = true;
                            base[2] = false;
                        }
                        if(base[1]) {
                            base[2] = true;
                            base[1] = false;
                        }
                         base[1] = true;
                    } else if(hit == 2) {
                        if(base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if(base[1]) {
                            base[3] = true;
                            base[1] = false;
                        }
                        base[2] = true;
                    } else if(hit == 3) {
                        if(base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if(base[1]) {
                            score++;
                            base[1] = false;
                        }
                        base[3] = true;
                    } else if(hit == 4) {
                        if(base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if(base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if(base[1]) {
                            score++;
                            base[1] = false;
                        }
                        score++;
                    }

                    if(out == 3) {
                        startIdx = j+1;
                        if(startIdx == 10) {
                            startIdx = 1;
                        }
                        break outerloop;
                    }
                }
                startIdx = 1;
            }
        }

        return score;
    }
}
