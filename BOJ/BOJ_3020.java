package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_3020 {

    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 석순
        int[] up = new int[N/2];

        // 종유석
        int[] down = new int[N/2];

        for(int i=0; i<N; i++) {
            if(i%2 == 0) up[i/2] = Integer.parseInt(br.readLine());
            else down[i/2] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = N;
        int count = 0;
        for(int h=1; h<=H; h++) {
            int num = binarySearch(h, down) + binarySearch(H-h+1, up);

            if(num == min) {
                count++;
            } else if(num < min) {
                min = num;
                count = 1;
            }
        }
        System.out.println(min+" "+count);
    }

    public static int binarySearch(int h, int[] arr) {
        int left = 0;
        int right = N/2;
        while(left < right) {
            int mid = (left + right) / 2;

            if(arr[mid] >= h) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return arr.length - right;
    }
}
