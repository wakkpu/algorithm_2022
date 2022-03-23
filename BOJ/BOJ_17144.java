package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17144 {

	static int R, C, T;
	static int[][] map;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	static Queue<Dust> dusts;
	
	static class Dust {
		int i;
		int j;
		int w;
		
		public Dust(int i, int j, int w) {
			this.i = i;
			this.j = j;
			this.w = w;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		
		Dust air1 = null;
		Dust air2 = null;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(air1 == null) air1 = new Dust(i, j, 0);
					else air2 = new Dust(i, j, 0);
				}
			}
		}
		
		for(int t=0; t<T; t++) {
			findDust();
			
			Diffuse();
			//printMap();
			
			AirCleaner1(air1);
			AirCleaner2(air2);
			printMap();
		}
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}

		// 공기청정기 때문에 -2 됐음
		System.out.println(sum + 2);
	}
	
	public static void printMap() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void findDust() {
		dusts = new LinkedList<>();
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] > 0) {
					dusts.offer(new Dust(i, j, map[i][j]));
				}
			}
		}
	}
	
	public static void Diffuse() {
		while(!dusts.isEmpty()) {
			Dust dust = dusts.poll();
			
			int diffuseAmount = dust.w / 5;
			int diffuseCount = 0;
			for(int i=0; i<4; i++) {
				int ni = dust.i + di[i];
				int nj = dust.j + dj[i];
				
				if(ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == -1) continue;
				
				map[ni][nj] += diffuseAmount;
				diffuseCount += 1;
			}
			
			map[dust.i][dust.j] -= diffuseAmount * diffuseCount;
		}

	}

	public static void AirCleaner1(Dust air1) {
		int r = air1.i;
		
		// 하
		for(int i=r-1; i>0; i--) map[i][0] = map[i-1][0];
		
		// 좌
		for(int j=0; j<C-1; j++) map[0][j] = map[0][j+1];
		
		// 상
		for(int i=0; i<r; i++) map[i][C-1] = map[i+1][C-1];
		
		// 우
		for(int j=C-1; j>1; j--) map[r][j] = map[r][j-1];
		
		map[r][1] = 0;
		map[r][0] = -1;
	}

	public static void AirCleaner2(Dust air2) {
		int r = air2.i;

		// 상
		for(int i=r; i<R-1; i++) map[i][0] = map[i+1][0];

		// 좌
		for(int j=0; j<C-1; j++) map[R-1][j] = map[R-1][j+1];

		// 하
		for(int i=R-1; i>r; i--) map[i][C-1] = map[i-1][C-1];

		// 우
		for(int j=C-1; j>1; j--) map[r][j] = map[r][j-1];
		
		map[r][1] = 0;
		map[r][0] = -1;
	}
}
