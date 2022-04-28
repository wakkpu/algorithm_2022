package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] times = new long[N];
        long max_time = 0L;
        for(int i=0; i<N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            max_time = Long.max(max_time, times[i]);
        }

        long left = 0L;
        long right = max_time * 1_000_000_000L;

        long min = right;
        while(left <= right) {
            // mid초일 때 M명 이상 처리가 가능한지 확인
            long mid = (left + right) / 2;

            long curr = 0; // mid초 동안 몇명 처리하는지
            for(long time: times) {
                curr += mid/time; // 심사대별로 몇명씩 처리하는지 합산
            }

            if(curr >= M) { // M명 이상 처리했으면
                min = Math.min(min, mid); // mid초 저장
                right = mid-1; // 시간을 줄여서 다시 탐색
            } else { // M명 미만 처리했으면
                left = mid+1; // 시간 늘려서 다시 탐색
            }
        }
        System.out.println(min);
    }
}
