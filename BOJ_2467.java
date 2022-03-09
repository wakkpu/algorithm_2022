import java.io.*;
import java.util.*;

public class BOJ_2467 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int j = N-1;

        int closeZero = Integer.MAX_VALUE;
        int first = -1_000_000_000;
        int second = 1_000_000_000;

        while(i < j) {
            int left = nums[i];
            int right = nums[j];

            int mid = left+right;
            int abs = Math.abs(mid);

            if(abs < closeZero) {
                first = left;
                second = right;
                closeZero = abs;
            }

            if(mid < 0) { // 합이 0보다 작으니까 합을 더 키워야 함
                i++;
            } else if(mid == 0) { // 합이 0이면 바로 종료
                first = left;
                second = right;
                break;
            } else { // 합이 0보다 크니까 합을 줄여야 함
                j--;
            }
        }

        System.out.print(first+" "+second);
    }
}
