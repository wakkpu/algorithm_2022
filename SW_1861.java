import java.io.*;
import java.util.*;

public class SW_1861 {

	static int N, T;
	static int maxLen;
	static int minInit;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	/**
	 * 
	 * @param si 시작 i좌표
	 * @param sj 시작 j좌표
	 * @param len 얼마나 멀리 갔는지
	 * @param init 시작좌표의 값
	 */
	public static void traversal(int si, int sj, int len, int init) {
		
		visited[si][sj] = true;
		// 사방탐색
		for(int i=0; i<4; i++) {
			int ni = si+di[i];
			int nj = sj+dj[i];
			// 다음 단계 조건: map 범위 내여야 하고, 가지 않은 곳이어야 하고, 현재 좌표의 값보다 1 커야함
			if(0 <= ni && ni < N && 0 <= nj && nj < N) {
				if(!visited[ni][nj] && map[ni][nj] == map[si][sj]+1) {
					traversal(ni, nj, len+1, init);
				}
			}
		}
		// 최대 거리와 최소 시작점 저장
		if(len > maxLen) {
			maxLen = len;
			minInit = init;
		}
		
		// 이동 거리가 같은 경우 시작점 갱신
		if(len == maxLen) {
			minInit = Math.min(init, minInit);
		}
		visited[si][sj] = false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			maxLen = 0;
			minInit = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					traversal(i, j, 1, map[i][j]);
				}
			}
			
			System.out.println("#"+t+" "+minInit+" "+maxLen);
		}
	}

}
