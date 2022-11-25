package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17307 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] init = new int[N];
        for(int i=0; i<N; i++) {
            init[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 1) {
            System.out.println("1");
            System.out.println("0");
            return;
        }

        // 숫자 변하는 패턴
        int[][] switches = new int[C][C];
        for(int j=0; j<C; j++) {
            switches[0][j] = j;
        }
        for(int i=1; i<C; i++) {
            switches[i][0] = switches[i-1][C-1];
            for(int j=1; j<C; j++) {
                switches[i][j] = switches[i-1][j-1];
            }
        }

        // i가 j와 같은 숫자가 되기 위해 몇 번 바뀌어야 하는지
        int[][] pushes = new int[N][N];
        for(int i=0; i<N-1; i++) {
            pushes[i][i+1] = switches[init[i]][init[i+1]];
        }
        for(int i=N-1; i>=1; i--) {
            pushes[i][i-1] = switches[init[i]][init[i-1]];
        }

        int minPush = Integer.MAX_VALUE;
        int minButton = Integer.MAX_VALUE;

        for(int start=0; start<N; start++) {

            int leftPush = 0;
            int leftR = start;
            int leftC = start-1;
            while(leftR >= 0 && leftC >= 0) {
                leftPush += pushes[leftR--][leftC--];
            }

            int rightPush = 0;
            int rightR = start;
            int rightC = start+1;
            while(rightR < N && rightC < N) {
                rightPush += pushes[rightR++][rightC++];
            }

            if(Math.max(leftPush, rightPush) < minPush) {
                minButton = start+1;
                minPush = Math.max(leftPush, rightPush);
            }
        }
        System.out.println(minButton);
        System.out.println(minPush);
    }
}
