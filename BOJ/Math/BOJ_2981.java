package BOJ.Math;

import java.io.*;
import java.util.*;

public class BOJ_2981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        int num = nums[1] - nums[0];

        for(int i=2; i<N; i++) {
            num = gcd(num, nums[i]-nums[i-1]);
        }

        for(int i=2; i<=num; i++) {
            if(num % i == 0) {
                bw.write(i+" ");
            }
        }
        bw.flush();
    }

    static int gcd(int a, int b) {
        while(b != 0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}
