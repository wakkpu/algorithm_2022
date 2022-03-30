package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        // 입력 원소들
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 구간의 누적 합
        List<Integer> prefixSumA = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                prefixSumA.add(sum);
            }
        }
        Collections.sort(prefixSumA);

        List<Integer> prefixSumB = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                prefixSumB.add(sum);
            }
        }
        Collections.sort(prefixSumB);

        //System.out.println(prefixSumA.toString());
        //System.out.println(prefixSumB.toString());

        // 투 포인터
        int left = 0;
        int right = prefixSumB.size()-1;
        long count = 0;
        while(left < prefixSumA.size() && right >= 0) {
            int a = prefixSumA.get(left);
            int b = prefixSumB.get(right);

            int aCount = 0;
            int bCount = 0;

            long sum = a + b;

            if(sum == T) {
                // a를 만들 수 있는 또 다른 구간 합 찾기
                while(left < prefixSumA.size() && prefixSumA.get(left) == a) {
                    aCount++;
                    left++;
                }

                // b를 만들 수 있는 또 다른 구간 합 찾기
                while(right >= 0 && prefixSumB.get(right) == b) {
                    bCount++;
                    right--;
                }
            } else if(sum < T) {
                left++;
            } else if(sum > T) {
                right--;
            }

            // T 만들 수 있는 쌍의 갯수
            count += (long) aCount * (long) bCount;
        }

        System.out.println(count);
    }
}
