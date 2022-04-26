package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_12919 {

    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        System.out.println(bfs(T));
        //System.out.println(dfs(S));
    }

    public static int bfs(String s) {
        Set<String> visited = new HashSet<>();
        visited.add(s);

        Queue<String> q = new LinkedList<>();
        q.offer(s);

        while(!q.isEmpty()) {
            String curr = q.poll();

            if(curr.equals(S)) {
                return 1;
            }

            if(curr.length() < S.length()) {
                return 0;
            }

            if(curr.charAt(curr.length()-1) == 'A') {
                String str0 = removeA(curr);
                if(!visited.contains(str0)) {
                    visited.add(str0);
                    q.offer(str0);
                }
            }

            if(curr.charAt(0) == 'B') {
                String str1 = reverseB(curr);
                if(!visited.contains(str1)) {
                    visited.add(str1);
                    q.offer(str1);
                }
            }

        }
        return 0;
    }

    public static String removeA(String src) {
        StringBuilder sb = new StringBuilder(src);
        return sb.deleteCharAt(src.length()-1).toString();
    }

    public static String reverseB(String src) {
        StringBuilder sb = new StringBuilder(src);
        return sb.deleteCharAt(0).reverse().toString();
    }
}
