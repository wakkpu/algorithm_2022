package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11404 {

    static int[][] graph;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];
        for(int i=0; i<N+1; i++) {
            for(int j=0; j<N+1; j++) {
                if(i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 100_000_000;
                }
            }
        }

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from][to] = Math.min(weight, graph[from][to]);
        }

        // floyd-warshall은 거쳐가는 경로와 직행하는 경로를 비교
        // 모든 정점에서 다른 모든 정점으로의 최단 경로를 구함
        for (int k=1; k<N+1; k++) {
            for(int i=1; i<N+1; i++) {
                for(int j=1; j<N+1; j++) {
                    if(graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(graph[i][j] == 100_000_000) {
                    System.out.print("0 ");
                } else {
                    System.out.print(graph[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
