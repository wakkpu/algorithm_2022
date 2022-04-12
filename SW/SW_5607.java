package SW;

import java.io.*;
import java.util.*;

public class SW_5607 {

    static final int X = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        long[] fact = new long[1000000+1];
        fact[0] = 1;
        for(int i=1; i<=1000000; i++) {
            fact[i] = (i * fact[i-1]) % X;
        }

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long first = fact[N] % X;

            long second = (fact[N-R] * fact[R]) % X;

            second = pow(second, X-2) % X;


            long third = (first * second) % X;
            bw.write("#"+t+" "+third+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static long pow(long a, int b) {
        if(b == 0) return 1L;
        else if(b == 1) return a;
        else {
            if(b % 2 == 0) {
                long result = pow(a, b/2) % X;
                return result * result;
            } else {
                long result = pow(a, b-1) % X;
                return a * result;
            }
        }
    }
}
