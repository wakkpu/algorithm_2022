package SW;

import java.io.*;
import java.util.*;

public class SW_5604 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            long[] ans = new long[10];
            long point = 1;

            while (A <= B) {
                while (B % 10 != 9 && A <= B) {
                    cal(B, ans, point);
                    B--;
                }

                if (B < A) {
                    break;
                }

                while (A % 10 != 0 && A <= B) {
                    cal(A, ans, point);
                    A++;
                }

                A /= 10;
                B /= 10;

                for (int i = 0; i < 10; i++) {
                    ans[i] += (B - A + 1) * point;
                }
                point *= 10;
            }
            long sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (ans[i] * i);
            }

            bw.write("#"+tc+" "+sum+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void cal(long x, long[] ans, long point) {
        while (x > 0) {
            String s = String.valueOf(x);
            int xx = s.charAt(s.length()-1)-'0';
            ans[xx] += point;
            x /= 10;
        }
    }
}
