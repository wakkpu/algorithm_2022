package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14600 {

    static int[][] map;
    static int num = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        N = 1 << K;
        map = new int[1+ N][1+ N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        map[y][x] = -1;

        putTile(1, 1, N);

        printMap();
    }

    public static void putTile(int r, int c, int size) {
        num++;
        int s = size/2;
        if(check(r, c, s)) map[r+s-1][c+s-1] = num;
        if(check(r+s, c, s)) map[r+s][c+s-1] = num;
        if(check(r, c+s, s)) map[r+s-1][c+s] = num;
        if(check(r+s, c+s, s)) map[r+s][c+s] = num;

//        printMap();
//        System.out.println();

        if(size == 2) return;

        putTile(r, c, s);
        putTile(r+s, c, s);
        putTile(r, c+s, s);
        putTile(r+s, c+s, s);
    }

    public static boolean check(int r, int c, int s) {
        for(int i=r; i<r+s; i++) {
            for(int j=c; j<c+s; j++) {
                if(map[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static void printMap() {
        for(int i = N; i>=1; i--) {
            for(int j = 1; j<= N; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
