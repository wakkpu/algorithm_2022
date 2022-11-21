package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1091 {

    static int N;
    static int[] start, arr, P, S;

    static int count = 0;

    static final int INFINITE_LOOP = 0;
    static final int KEEP_GOING = 1;
    static final int BINGO = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        start = new int[N];
        arr = new int[N];
        P = new int[N];
        S = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            start[i] = num;
            arr[i] = num;
            P[i] = num;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        while(isEnd(arr) == KEEP_GOING) {
            shuffle();
            if(compareWithStart(arr) == INFINITE_LOOP) break;
            count++;
        }
        System.out.println(count);
    }

    public static int isEnd(int[] nums) {
        for(int i=0; i<N-2; i+=3) {
            if(nums[i] != 0 || nums[i+1] != 1 || nums[i+2] != 2) {
                return KEEP_GOING;
            }
        }

        return BINGO;
    }

    public static int compareWithStart(int[] nums) {
        boolean flag = true;
        for (int i=0; i<N; i++) {
            if (nums[i] != start[i]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            count = -1;
            return INFINITE_LOOP;
        } else {
            return KEEP_GOING;
        }
    }

    public static void shuffle() {
        int[] copy = new int[N];
        for(int i=0; i<N; i++) {
            copy[S[i]] = arr[i];
        }
        arr = copy;
    }
}
