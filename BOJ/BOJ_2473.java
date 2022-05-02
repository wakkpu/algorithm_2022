package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(nums);

        long far = Long.MAX_VALUE;
        long[] sols = new long[3];

        for(int i=0; i<N; i++) {
            int left = i+1;
            int right = N-1;

            while(left < right) {
                long sum = nums[i] + nums[left] + nums[right];

                if(Math.abs(sum) < far) {
                    far = Math.abs(sum);
                    sols[0] = nums[i];
                    sols[1] = nums[left];
                    sols[2] = nums[right];
                }

                if(sum > 0) {
                    right--;
                } else {
                    left++;
                }

            }
        }

        for(long sol: sols) {
            System.out.print(sol+" ");
        }
    }
}
