import java.io.*;
import java.util.*;

public class BOJ_17404 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] homes = new int[N][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            // 빨간색으로 칠하는 비용
            homes[i][0] = Integer.parseInt(st.nextToken());
            // 초록색으로 칠하는 비용
            homes[i][1] = Integer.parseInt(st.nextToken());
            // 파란색으로 칠하는 비용
            homes[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] DP = new int[N][3];
        int min = Integer.MAX_VALUE;

        // R, G, B로 시작하는 케이스 3개
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(i == j) {
                    DP[0][j] = homes[0][j];
                } else {
                    DP[0][j] = 10_000;
                }
            }

            // DP[i][j] = k: 색 j로 i번째 집을 칠했을 때의 최소 비용 k
            // ex) 색 R로 i번째 집을 칠했을 때의 최솟값
            // = min(G로 i-1번째 집을 칠했을 때의 최솟값, B로 i-1번째 집을 칠했을 때의 최솟값)
            // + i번째 집을 R로 칠하는 비용
            for(int j=1; j<N; j++) {
                DP[j][0] = Math.min(DP[j-1][1], DP[j-1][2]) + homes[j][0];
                DP[j][1] = Math.min(DP[j-1][0], DP[j-1][2]) + homes[j][1];
                DP[j][2] = Math.min(DP[j-1][0], DP[j-1][1]) + homes[j][2];
            }

            // 문제의 조건: 0번 집의 색과 N-1번 집의 색이 같으면 안됨.
            for(int j=0; j<3; j++) {
                if(i != j) {
                    min = Math.min(min, DP[N-1][j]);
                }
            }
        }
        System.out.println(min);
    }
}
