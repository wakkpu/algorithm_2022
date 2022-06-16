package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }


        int left = 0;
        int right = Integer.MAX_VALUE;

        while(left <= right) {
            int mid = (left + right) / 2; // 그룹 내 맞은 개수의 상한선

            int count = 1; // 그룹 개수
            int sum = 0; // 그룹 내 맞은 개수
            int min = Integer.MIN_VALUE;

            // 그룹 개수 센다
            for(int i=0; i<N; i++) {
                sum += nums[i];

                if(sum >= mid) { // 그룹 내 맞은 개수 상한선을 넘겼으므로 count++
                    count++;
                    min = Math.min(min, sum);
                    sum = 0;
                }
            }

            // 그룹 개수(count)가 K를 넘겼으므로 그룹 내 맞은 개수의 상한선을 늘려야 함
            if(count > K) left = mid+1;
            // 그룹 개수가 K 미만이므로 그룹 내 맞은 개수의 상한선을 낮춰야 함
            else right = mid-1;
        }
        System.out.println(left-1);
    }
}
