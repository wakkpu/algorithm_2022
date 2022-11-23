package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_3980 {

    static int C, max;
    static int[][] stats;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        C = Integer.parseInt(br.readLine());
        while(C-->0) {
            stats = new int[11][11];
            max = Integer.MIN_VALUE;

            for(int i=0; i<11; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++) {
                    stats[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backtrack(0, new ArrayList<>(), new boolean[11]);
            bw.append(String.valueOf(max)).append("\n");
        }
        bw.flush();
    }

    public static void backtrack(int toChoose, List<Integer> choosed, boolean[] visited) {
        if(choosed.size() == 11) {
            int result = 0;
            for(Integer num: choosed) {
                result += num;
            }
            max = Math.max(max, result);
            return;
        }

//        System.out.println("toChoose: "+toChoose+" choosed: "+choosed);
        for(int j=0; j<11; j++) {
            if(!visited[j]) {
                if(stats[toChoose][j] != 0) {
                    visited[j] = true;
                    choosed.add(stats[toChoose][j]);
                    backtrack(toChoose+1, choosed, visited);
                    choosed.remove(Integer.valueOf(stats[toChoose][j]));
                    visited[j] = false;
                }
            }
        }
    }
}
