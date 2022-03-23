package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2437 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int sum = 0;
        for(int i=0; i<N; i++) {
            int temp = nums[i];
            if(sum+1 < temp) break; // 다음 원소가 누적합보다 커져버리면 만들 수 없다.
            sum += temp;
        }

        System.out.println(sum+1);
    }
}
