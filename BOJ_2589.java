package jungol.language_coder;

import java.io.*;
import java.util.*;

public class BOJ_2589 {

	static int n;
	static int m;
	static int max_distance;
	
	static char[][] map;
	static int visited[][];
	
	// 위, 아래, 왼쪽, 오른쪽
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static int BFS(int i, int j) {
		int temp_max = 0;
		Queue<Integer> Qy = new LinkedList<Integer>();
		Queue<Integer> Qx = new LinkedList<Integer>();
		
		Qy.add(i);
		Qx.add(j);
		visited[i][j] = 1;
		
		while(!Qy.isEmpty()) {
			int y = Qy.poll();
			int x = Qx.poll();
			
			for(int k=0; k<4; k++) {
				int nextRow = y + dy[k];
				int nextCol = x + dx[k];
				
				if(0 <= nextCol && nextCol < m && 0 <= nextRow && nextRow < n) {
					if(visited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == 'L') {
						visited[nextRow][nextCol] = visited[y][x]+1;
						Qy.add(nextRow);
						Qx.add(nextCol);
						temp_max = Math.max(temp_max, visited[nextRow][nextCol]);
					}
				}
			}
		}
		return temp_max-1;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		max_distance = 0;
		
		map = new char[n][m];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 'L') {
					visited = new int[n][m];
					max_distance = Math.max(max_distance, BFS(i, j));
				}
			}
		}
		
		System.out.println(max_distance);
	}
}
