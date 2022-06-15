package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int count = 0;


        for(int idx=0; idx<N; idx++) {
            int left = 0;
            int right = N-1;

            while(left < right) {
                if(left == idx) {
                    left++;
                    continue;
                }

                if(right == idx) {
                    right--;
                    continue;
                }

                if(nums[left]+nums[right] < nums[idx]) {
                    left++;
                } else if(nums[left]+nums[right] == nums[idx]) {
                    //System.out.println("idx: "+nums[idx]+" left: "+nums[left]+" right: "+nums[right]);
                    count++;
                    break;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
