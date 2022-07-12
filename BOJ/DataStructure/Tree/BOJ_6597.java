package BOJ.DataStructure.Tree;

import java.io.*;
import java.util.*;

public class BOJ_6597 {

    static char[] pre, in;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            String input = br.readLine();
            if(input == null || input.equals("")) break;
            st = new StringTokenizer(input);

            pre = st.nextToken().toCharArray();
            in  = st.nextToken().toCharArray();

            // System.out.println(Arrays.toString(pre));
            // System.out.println(Arrays.toString(in));

            postOrder(0, 0, pre.length);
            System.out.println();
        }
    }

    public static void postOrder(int curr, int start, int end) {
        if(start >= end) return;

        for(int node=start; node<end; node++) {
            if(in[node] == pre[curr]) {
                postOrder(curr+1, start, node);
                postOrder(curr-start+node+1, node+1, end);
                System.out.print(pre[curr]);
            }
        }
    }
}
