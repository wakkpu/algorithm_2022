package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17307 {

    static int N, C;
    static int[] init;
    static int[] led;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        init = new int[N];
        for(int i=0; i<N; i++) {
            init[i] = Integer.parseInt(st.nextToken());
        }

        int minPush = Integer.MAX_VALUE;
        int minButton = N+1;

        led = new int[N];
        for(int i=0; i<N; i++) {
            copy();
            int push = 0;
            while(!isEnd()) {
                pushButton(i);
                System.out.println((i+1)+" : "+Arrays.toString(led));
                push++;
            }

            if(push < minPush) {
                minButton = i+1;
                minPush = push;
            }
        }

        System.out.println(minButton);
        System.out.println(minPush);
    }
    public static void copy() {
        for(int i=0; i<N; i++) {
            led[i] = init[i];
        }
    }

    public static boolean isEnd() {
        for(int i=0; i<N-1; i++) {
            if(led[i] != led[i+1]) return false;
        }
        return true;
    }

    public static void pushButton(int buttonNumber) {
        int left = buttonNumber-1;
        while(left >= 0 && led[left] == led[buttonNumber]) {
            led[left] = (led[left]+1)%C;
            left--;
        }

        int right = buttonNumber+1;
        while(right < N && led[right] == led[buttonNumber]) {
            led[right] = (led[right]+1)%C;
            right++;
        }
        led[buttonNumber] = (led[buttonNumber]+1)%C;
    }
}
