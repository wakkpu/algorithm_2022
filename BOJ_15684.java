import java.io.*;
import java.util.*;

public class BOJ_15684 {
	
	static int N, M, H;
	static int[][] map;
	static boolean[][] visited;
	static int sol;
	
	// 전체 시뮬레이션 -> i에서 출발해서 i로 도착하는지
	public static boolean simul() {
		for(int i=1; i<=N; i++) {
			int r = 1;
			int c = i;
			
			for(int j=1; j<=H+1; j++) {
				if(map[r][c-1] == 1) {
					c--;
					r++;
					continue;
				}
				if(map[r][c] == 1) {
					c++;
					r++;
					continue;
				}
				r++;
			}
			// 하나라도 i로 도착 못하면 false
			if(c != i) return false;
		}
		// 다 i로 도착
		return true;
	}
	
	public static void dfs() {
		
	}

	public static void main(String[] args) throws Exception {
		// N개의 세로선과 M개의 가로선
		// 각각의 세로선마다 가로선을 놓을 수 있는 위치의 개수는 H
		
		// 가로선은 인접한 두 세로선을 연결해야 함.
		// 단 두 가로선이 연속하거나 서로 접하면 안됨.
		
		// 사다리에 가로선을 추가해서 i번 세로선의 결과가 i가 나오도록.
		// 그렇게 하기 위해 추가해야 하는 가로선 개수의 최솟값은?
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H+2][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}
		
		// dfs
		System.out.println(sol);
		
	}
}
