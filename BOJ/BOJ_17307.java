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

        // i번째 버튼이 인접한 버튼과 같은 숫자가 되기 위해 몇 번 바뀌어야 하는지
        long[] left = new long[N];
        for(int i=N-1; i>=1; i--) {
            if(init[i] <= init[i-1]) {
                left[i-1] = Math.abs(init[i] - init[i-1]);
            } else {
                left[i-1] = C - Math.abs(init[i] - init[i-1]);
            }
        }
//        System.out.println(Arrays.toString(left));

        long[] right = new long[N];
        for(int i=1; i<N; i++) {
            if(init[i-1] <= init[i]) {
                right[i] = Math.abs(init[i-1] - init[i]);
            } else {
                right[i] = C - Math.abs(init[i-1] - init[i]);
            }
        }
//        System.out.println(Arrays.toString(right));

        // 누적합
        long[] leftSum = new long[N];
        leftSum[N-1] = left[N-1];
        for(int i=N-1; i>=1; i--) {
            leftSum[i-1] = leftSum[i] + left[i-1];
        }
//        System.out.println(Arrays.toString(leftSum));


        long[] rightSum = new long[N];
        rightSum[0] = right[0];
        for(int i=1; i<N; i++) {
            rightSum[i] = rightSum[i-1] + right[i];
        }
//        System.out.println(Arrays.toString(rightSum));

        long minPush = Long.MAX_VALUE;
        long minButton = Long.MAX_VALUE;

        for(int start=0; start<N; start++) {
            long leftPush = leftSum[0] - leftSum[start];
            long rightPush = rightSum[N-1] - rightSum[start];

//            System.out.println("start : "+(start+1)+" leftPush : "+leftPush+" rightPush : "+rightPush);
            long push = Math.max(leftPush, rightPush);
            if(push < minPush) {
                minButton = (start+1);
                minPush = push;
            }
        }
        System.out.println(minButton);
        System.out.println(minPush);
    }
}
