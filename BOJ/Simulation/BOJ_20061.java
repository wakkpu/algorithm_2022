package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_20061 {

    public static int[][] board;
    public static int[][] info;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[10][10];

        info = new int[N][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            info[i] = new int[]{t, x, y};
        }

        for(int i=0; i<N; i++) {
            putBlock(info[i]);
        }
    }

    public static void putBlock(int[] info) {
        board[info[1]][info[2]] = 1;
        if(info[0] == 2) {
            board[info[1]][info[2]+1] = 1;
        } else if(info[0] == 3) {
            board[info[1]+1][info[2]] = 1;
        }
    }

    public static void dropBlock() {

    }
}
