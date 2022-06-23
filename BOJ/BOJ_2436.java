package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int gcd = Integer.parseInt(st.nextToken());
        int lcm = Integer.parseInt(st.nextToken());

        int temp = lcm / gcd;

        long resA = 0, resB = 0;
        for(int i=1; i*i <= temp; i++) {
            if(temp % i == 0) {
                int aa = i;
                int bb = temp / i;
                if(getGCD(aa, bb) == 1) {
                    resA = aa * gcd;
                    resB = bb * gcd;
                }
            }
        }
        System.out.println(resA+" "+resB);
    }

    public static int getGCD(int a, int b) {
        return a%b == 0 ? b : getGCD(b, a%b);
    }
}
