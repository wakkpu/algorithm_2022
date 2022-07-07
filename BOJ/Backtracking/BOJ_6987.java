package BOJ.Backtracking;

import java.io.*;
import java.util.*;

public class BOJ_6987 {

    static int[][][] inputs;
    static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};

    static int[][] result;

    static boolean flag;

    static final int win = 0;
    static final int draw = 1;
    static final int lose = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        inputs = new int[4][6][3];
        for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<6; j++) {
                for(int k=0; k<3; k++) {
                    inputs[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for(int i=0; i<4; i++) {
            flag = false;
            result = new int[6][3];
            dfs(i, 0);
            if(flag) System.out.print("1 ");
            else System.out.print("0 ");
        }
    }

    public static void dfs(int i, int count) {
        if(flag) return;

        if(count == 15) {
            for(int r=0; r<6; r++) {
                for(int c=0; c<3; c++) {
                    if(inputs[i][r][c] != result[r][c]) {
                        return;
                    }
                }
            }
            flag = true;
            return;
        }

        if(inputs[i][home[count]][win] > 0 && inputs[i][away[count]][lose] > 0) {
            result[home[count]][win]++;
            result[away[count]][lose]++;
            dfs(i, count+1);
            result[away[count]][lose]--;
            result[home[count]][win]--;
        }

        if(inputs[i][home[count]][draw] > 0 && inputs[i][away[count]][draw] > 0) {
            result[home[count]][draw]++;
            result[away[count]][draw]++;
            dfs(i, count+1);
            result[away[count]][draw]--;
            result[home[count]][draw]--;
        }

        if(inputs[i][home[count]][lose] > 0 && inputs[i][away[count]][win] > 0) {
            result[home[count]][lose]++;
            result[away[count]][win]++;
            dfs(i, count+1);
            result[away[count]][win]--;
            result[home[count]][lose]--;
        }
    }
}
