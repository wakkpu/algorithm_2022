package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_4195 {

    static int[] parents;
    static int[] levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int F = Integer.parseInt(br.readLine());

            parents = new int[F*2];
            levels = new int[F*2];

            for(int i=0; i<F*2; i++) {
               parents[i] = i;
               levels[i] = 1;
            }

            int idx = 0;
            Map<String, Integer> map = new HashMap<>();
            while(F-->0) {
                st = new StringTokenizer(br.readLine());

                String name1 = st.nextToken();
                String name2 = st.nextToken();

                if(!map.containsKey(name1)) {
                    map.put(name1, idx++);
                }

                if(!map.containsKey(name2)) {
                    map.put(name2, idx++);
                }

                bw.write(union(map.get(name1), map.get(name2)) + "\n");
            }
        }
        bw.flush();
    }

    public static int find(int x) {
        if(x == parents[x]) return x;

        return parents[x] = find(parents[x]);
    }

    public static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            parents[y] = x;
            levels[x] += levels[y];

            levels[y] = 1;
        }

        return levels[x];
    }
}
