package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17136 {

    static int[][] board = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int r=0; r<10; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<10; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
