package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_7576 {

	static int M, N;
	static int[][] map;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static class Pos {
		int i;
		int j;
		
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
	}
	
	public static void bfs() {
		
		Queue<Pos> tomatoes = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) {
					tomatoes.offer(new Pos(i, j));
				}
			}
		}
		
		while(!tomatoes.isEmpty()) {
			Pos tomato = tomatoes.poll();
			
			for(int k=0; k<4; k++) {
				int ni = tomato.i+di[k];
				int nj = tomato.j+dj[k];
				
				if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == -1)
					continue;
				
				if(map[ni][nj] == 0) {
					tomatoes.offer(new Pos(ni, nj));
					map[ni][nj] = map[tomato.i][tomato.j]+1;
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, map[i][j]);
			}
		}
		System.out.println(max-1);
	}
}
