package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16724 {

    static int N, M;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 위, 아래, 왼쪽, 오른쪽
    static int[][] map;
    static int[] rep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                if(str.charAt(j) == 'U') {
                    map[i][j] = 0;
                } else if(str.charAt(j) == 'D') {
                    map[i][j] = 1;
                } else if(str.charAt(j) == 'L') {
                    map[i][j] = 2;
                } else {
                    map[i][j] = 3;
                }
            }
        }

        rep = new int[N*M];
        for(int i=0; i<N*M; i++) {
            rep[i] = i;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                int curr = i*M + j;
                int next = (i + dirs[map[i][j]][0])*M + (j + dirs[map[i][j]][1]);

                if(find(curr) != find(next)) {
                    union(curr, next);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i=0; i<N*M; i++) {
            set.add(find(i));
        }
        System.out.println(set.size());
    }

    public static int find(int a) {
        if(a == rep[a]) return a;
        return rep[a] = find(rep[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a > b) rep[a] = b;
        else if(a < b) rep[b] = a;
    }
}
