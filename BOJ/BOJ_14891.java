package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14891 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] gears = new int[5][8];
        for(int i=1; i<=4; i++) {
            String str = br.readLine();
            for(int j=0; j<8; j++) {
                gears[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        int K = Integer.parseInt(br.readLine());

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            int[] rotate = new int[5];
            rotate[num] = dir;

            for(int j=num+1; j<=4; j++) {
                if(gears[j-1][2] == gears[j][6]) {
                    break;
                } else {
                    rotate[j] = -rotate[j-1];
                }
            }

            for(int j=num-1; j>=1; j--) {
                if(gears[j][2] == gears[j+1][6]) {
                    break;
                } else {
                    rotate[j] = -rotate[j+1];
                }
            }

            for(int j=1; j<=4; j++) {
                if(rotate[j] == 1) {
                    clockwise(gears[j]);
                } else if(rotate[j] == -1) {
                    reverse(gears[j]);
                }
            }
        }

        int sum = 0;
        for(int i=1; i<=4; i++) {
            if(gears[i][0] == 1) {
                sum += 1 << (i-1);
            }
        }
        System.out.println(sum);
    }

    public static void clockwise(int[] arr) {
        int temp = arr[7];
        for(int i=7; i>0; i--) {
            arr[i] = arr[i-1];
        }
        arr[0] = temp;
    }

    public static void reverse(int[] arr) {
        int temp = arr[0];
        for(int i=0; i<7; i++) {
            arr[i] = arr[i+1];
        }
        arr[7] = temp;
    }
}
