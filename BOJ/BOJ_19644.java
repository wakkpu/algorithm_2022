package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_19644 {

    static int L, Ml, Mk, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        L = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        Ml = Integer.parseInt(st.nextToken());
        Mk = Integer.parseInt(st.nextToken());

        C = Integer.parseInt(br.readLine());

        int[] zombies = new int[L+1];
        for(int i=1; i<L+1; i++) {
            zombies[i] = Integer.parseInt(br.readLine());
        }

        int pos = 0;

    }
}
