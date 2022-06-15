package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2661 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dfs("");
    }

    public static boolean ok(String str) {
        for(int i=0; i<str.length(); i++) {
            for(int j=1; i+2*j<=str.length(); j++) {
                if(str.substring(i, i+j).equals(str.substring(i+j, i+2*j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void dfs(String str) {
        if(str.length() == N) {
            System.out.println(str);
            System.exit(0);
        }

        for(int i=1; i<=3; i++) {
            if(ok(str+i)) dfs(str+i);
        }
    }
}
