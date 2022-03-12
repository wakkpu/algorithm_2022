import java.io.*;
import java.util.*;

public class BOJ_15684 {

	static int N, M, H;
	static int[][] map;
	static boolean flag = false;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H+1][N+1];

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // 오른쪽 이동
			map[a][b+1] = -1; // 왼쪽 이동
		}

		for(int i=0; i<=3; i++) {
			answer = i; // 사다리 갯수
			dfs(1, 0);
			if(flag) break;
		}

		if(flag) System.out.println(answer);
		else System.out.println(-1);
	}

	public static boolean simul() {
		for(int i=1; i<=N; i++) {
			int r=1;
			int c=i;

			for(int j=1; j<=H; j++) {

				if(map[r][c] == 1) c++; // 오른쪽 이동
				else if(map[r][c] == -1) c--; // 왼쪽 이동
				r++; // 아래로 이동
			}

			// 하나라도 i에서 출발해서 i로 도착 못하면 false
			if(i != c) return false;
		}
		return true;
	}

	public static void dfs(int h, int count) {
		if(flag) return;

		if(answer == count) {
			if(simul()) flag = true;
			return;
		}

		for(int i=h; i<H+1; i++) {
			for(int j=1; j<N; j++) {
				if(map[i][j] == 0 && map[i][j+1] == 0) {
					// 가로선 놓기
					map[i][j] = 1;
					map[i][j+1] = -1;

					dfs(i, count+1);

					// 가로선 빼기
					map[i][j] = 0;
					map[i][j+1] = 0;
				}
			}
		}
	}
}
