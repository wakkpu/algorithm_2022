package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12931 {

    static int N;
    static int count = 0;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        while(true) {
            if(isEnd()) break;
            makeAllEven();
            if(isEnd()) break;
            makeAllHalf();
        }
        System.out.println(count);
    }

    public static boolean isEnd() {
        for(int i=0; i<N; i++) {
            if(nums[i] != 0) return false;
        }
        return true;
    }

    public static void makeAllEven() {
        for(int i=0; i<N; i++) {
            if(nums[i] % 2 != 0) {
                nums[i]--;
                count++;
            }
        }
    }

    public static void makeAllHalf() {
        for(int i=0; i<N; i++) {
            nums[i] /= 2;
        }
        count++;
    }
}
