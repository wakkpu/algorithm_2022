package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1107 {

    static boolean finished;
    static int M, result;
    static String N;
    static boolean[] broken = new boolean[12];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = br.readLine();

        M = Integer.parseInt(br.readLine());

        if(M > 0) {
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        find(new StringBuilder("100"), 0);

        System.out.println(result);
    }

    public static void find(StringBuilder sb, int depth) {
        System.out.println(depth+" : "+sb.toString());
        if(finished || sb.toString().length() > N.length()) return;

        if(sb.toString().equals(N)) {
            finished = true;
            result = depth;
            return;
        }

        for (int i = 0; i <= 11; i++) {
            if(i >= 10) {
                int curr = Integer.parseInt(sb.toString());
                // +
                find(new StringBuilder(String.valueOf(curr + 1)), depth + 1);
                // -
                find(new StringBuilder(String.valueOf(curr - 1)), depth + 1);
            } else {
                if (!broken[i]) {
                    sb.append(i);
                    find(sb, depth + 1);
                }
            }
        }
    }
}
