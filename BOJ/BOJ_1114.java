package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1114 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] cutPoint = new int[K];
        for(int i=0; i<K; i++) {
            cutPoint[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cutPoint);

        // 간격에 대해 이진 탐색
        int left = 1;
        int right = cutPoint[K-1];
        int first = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            first = cutPoint[0];
            int largest = 0;
            int count = 1;

            for(int i=1; i<K; i++) {
                if(cutPoint[i] - first >= mid) {
                    count++;
                    first = cutPoint[i];
                }
            }

            if(count >= C) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
    }
}
