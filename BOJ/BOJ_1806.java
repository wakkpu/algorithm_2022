package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N+1];
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        //System.out.println("numbers: "+Arrays.toString(numbers));

        int head = 0;
        int tail = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while(head <= N && tail <= N) {
            if(sum < S) {
                sum += numbers[head++];

            } else {
                min = Math.min(min, (head - tail));
                sum -= numbers[tail++];
            }
            //System.out.println("head: "+head+", tail: "+tail+", sum: "+sum+", min: "+min);
        }

        if(min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}
