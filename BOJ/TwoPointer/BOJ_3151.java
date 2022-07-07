package BOJ.TwoPointer;

import java.io.*;
import java.util.*;

public class BOJ_3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        long count = 0L;
        for(int idx=0; idx<N; idx++) {
            int left = idx+1;
            int right = N-1;

            while(left < right) {
                System.out.println(idx+" "+left+" "+right);
                int val = nums[idx] + nums[left] + nums[right];

                if(val < 0) {
                    left++;
                } else if(val > 0) {
                    right--;
                } else {
                    System.out.println("found! "+idx+" "+left+" "+right);
                    if(nums[left] == nums[right]) { // nums[left]와 nums[right]가 같다면 그 사이 숫자도 모두 같음. 조합 nC2
                        System.out.println("same left, right");
                        count += (long) (right - left + 1) * (right - left) / 2;
                        break;
                    }

                    int l = 1;
                    int r = 1;

                    while(nums[left] == nums[left+1]) {
                        l++;
                        left++;
                    }

                    while(nums[right] == nums[right-1]) {
                        r++;
                        right--;
                    }

                    count += (long) l * r;
                }
            }
        }
        System.out.println(count);
    }
}
