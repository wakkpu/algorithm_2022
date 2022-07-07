package BOJ.TwoPointer;

import java.io.*;
import java.util.*;

public class BOJ_7453 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] sumAB = new int[N*N];
        int[] sumCD = new int[N*N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sumAB[i*N+j] = A[i] + B[j];
                sumCD[i*N+j] = C[i] + D[j];
            }
        }
        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        int left = 0;
        int right = N*N-1;
        long result = 0;
        while(0 <= right && left < N*N) {
            int valAB = sumAB[left];
            int valCD = sumCD[right];

            int mid = valAB + valCD;

            if(mid < 0) {
                left++;
            } else if(mid > 0) {
                right--;
            } else {
                long cntAB = 0;
                long cntCD = 0;
                while(left < N*N && sumAB[left] == valAB) {
                    cntAB++;
                    left++;
                }

                while(0 <= right && sumCD[right] == valCD) {
                    cntCD++;
                    right--;
                }
                result += cntAB * cntCD;
            }
        }
        System.out.println(result);
    }
}
