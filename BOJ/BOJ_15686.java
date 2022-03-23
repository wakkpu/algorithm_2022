package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15686 {
	
	static int[][] map;
	static int N, M;
	static ArrayList<Point> chicken;
	static ArrayList<Point> home;
	static Point[] choosed;
	static int minDistance;
	
	static class Point {
		int i;
		int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) home.add(new Point(i, j));
				if(map[i][j] == 2) chicken.add(new Point(i, j));
			}
		}
		
		choosed = new Point[M];
		minDistance = Integer.MAX_VALUE;
		
		selectChicken(0, 0);
		
		System.out.println(minDistance);
	}
	
	public static void selectChicken(int startIdx, int count) {
		if(count == M) {
			
			int distance = 0;
			
			for(Point h: home) {
				int chDist = Integer.MAX_VALUE;
				for(Point c: choosed) {
					chDist = Math.min(chDist, distance(c, h));
				}
				distance += chDist;
			}
			
			minDistance = Math.min(minDistance, distance);
			return;
		}
		
		if(startIdx == chicken.size())
			return;
		
		for(int i=startIdx; i<chicken.size(); i++) {
			choosed[count] = chicken.get(i);
			selectChicken(i+1, count+1);
		}
	}
	
	public static int distance(Point p1, Point p2) {
		return Math.abs(p1.i - p2.i) + Math.abs(p1.j - p2.j);
	}
}
