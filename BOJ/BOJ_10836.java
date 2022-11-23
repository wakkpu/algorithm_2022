package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10836 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] left = new int[M]; // 맨 왼쪽 줄
        int[] top = new int[M-1]; // 맨 윗 줄
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            for(int r=M-1; r>=0; r--) {
                if(zero != 0) {
                    zero--;
                } else if(one != 0) {
                    one--;
                    left[r]++;
                } else if(two != 0) {
                    two--;
                    left[r] += 2;
                }
            }

            for(int c=0; c<M-1; c++) {
                if(zero != 0) {
                    zero--;
                } else if(one != 0) {
                    one--;
                    top[c]++;
                } else if(two != 0) {
                    two--;
                    top[c] += 2;
                }
            }
        }

        for(int r=0; r<M; r++) {
            bw.write(1+left[r]+" ");
            for(int c=0; c<M-1; c++) {
                bw.write(1+top[c]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
