package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2580 {

    static class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int N = 9;
    static int[][] map;
    static List<Pos> blanks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        blanks = new ArrayList<>();

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    blanks.add(new Pos(i, j));
                }
            }
        }

        fillBlanks(0);
    }

    public static boolean fillBlanks(int count) {
        if(count == blanks.size()) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
            return true;
        }

        Pos curr = blanks.get(count);
        for(int i=1; i<=N; i++) {
            map[curr.i][curr.j] = i;

            if(check(curr.i, curr.j, i)) {
                if(fillBlanks(count+1)) {
                    return true;
                }
            }
        }
        map[curr.i][curr.j] = 0;
        return false;
    }

    public static boolean check(int r, int c, int val) {
        // 가로
        for(int j=0; j<N; j++) {
            if(j == c) continue;
            if(map[r][j] == val) return false;
        }

        // 세로
        for(int i=0; i<N; i++) {
            if(i == r) continue;
            if(map[i][c] == val) return false;
        }

        // 3 x 3
        int sr = r/3 * 3;
        int sc = c/3 * 3;
        for(int i=sr; i<sr+3; i++) {
            for(int j=sc; j<sc+3; j++) {
                if(i == r && j == c) continue;
                if(map[i][j] == val) return false;
            }
        }

        return true;
    }
}
