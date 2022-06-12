package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1719 {

    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N+1][N+1];
        int[][] map = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from][to] = weight;
            graph[to][from] = weight;

            map[from][to] = from; // from -> to는 from을 거쳐간다
            map[to][from] = to; // to -> from은 to를 거쳐간다
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        map[i][j] = map[k][j]; // i -> j를 가려면 k를 거쳐간다
                    }
                }
            }
        }

        // i -> j로 가는 경로 중 첫 번째로 거쳐가는 곳을 찾아야 함
        // map[i][j]에는 i -> j로 갈 때 거쳐가는 곳이 갱신되어 저장되어 있음
        // 양방향 그래프이므로 j -> i를 보면 i -> j로 갈 때 가장 먼저 거쳐가는 곳이 나옴
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == j) bw.write("-"+" ");
                else bw.write(map[j][i]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
