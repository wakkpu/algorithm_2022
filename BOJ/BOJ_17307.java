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

        // i가 j와 같은 숫자가 되기 위해 몇 번 바뀌어야 하는지
        int[] left = new int[N];
        for(int i=N-1; i>=1; i--) {
            if(init[i] <= init[i-1]) {
                left[i-1] = Math.abs(init[i] - init[i-1]);
            } else {
                left[i-1] = C - Math.abs(init[i] - init[i-1]);
            }
        }
//        System.out.println(Arrays.toString(left));

        int[] right = new int[N];
        for(int i=1; i<N; i++) {
            if(init[i-1] <= init[i]) {
                right[i] = Math.abs(init[i-1] - init[i]);
            } else {
                right[i] = C - Math.abs(init[i-1] - init[i]);
            }
        }
//        System.out.println(Arrays.toString(right));

        int minPush = Integer.MAX_VALUE;
        int minButton = Integer.MAX_VALUE;

        for(int start=0; start<N; start++) {
            int leftPush = 0;
            int leftC = start-1;
            while(leftC >= 0) {
                leftPush += left[leftC--];
            }

            int rightPush = 0;
            int rightC = start+1;
            while(rightC < N) {
                rightPush += right[rightC++];
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
