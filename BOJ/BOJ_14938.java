package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14938 {

    static int INF = 16;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int[][] map = new int[N+1][N+1];
        for(int r=0; r<=N; r++) {
            for(int c=0; c<=N; c++) {
                map[r][c] = INF;
            }
        }

        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[from][to] = weight;
            map[to][from] = weight;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(map[i][k]+map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k]+map[k][j];
                    }
                }
            }
        }

        int result = 0;
        for(int r=1; r<=N; r++) {
            int temp = items[r];
            for(int c=1; c<=N; c++) {
                if(r == c) continue;
                if(map[r][c] <= M) temp += items[c];
            }
            result = Math.max(result, temp);
        }
        System.out.println(result);
    }
}
