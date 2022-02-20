

import java.io.*;
import java.util.*;

public class BOJ_1916 {

	static int n;
	static int m;
	static int[][] map;
	
	public static int Dijkstra(int x, int y) {
		boolean[] visited = new boolean[n+1];
		
		int[] distance = new int[n+1];
		for(int i=0; i<distance.length; i++) {
			distance[i] = Integer.MAX_VALUE-1; // 왜 -1?
		}
		
		distance[x] = 0;
		
		for(int i=1; i<n; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1;
			
			for(int j=1; j<n+1; j++) {
				if(!visited[j] && distance[j] < min) {
					min = distance[j];
					index = j;
				}
			}
			
			visited[index] = true;
			
			for(int j=1; j<n+1; j++) {
				if(!visited[j] && map[index][j] != -1 && map[index][j] + distance[index] < distance[j]) {
					distance[j] = distance[index] + map[index][j];
				}
			}
		}
		
		return distance[y];
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				map[i][j] = -1; // 버스 비용 0 이상이므로 -1로 초기화
			}
		}
		
		for(int i=0; i<m; i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			int c = Integer.parseInt(input[2]);
			
			if(map[a][b] == -1 || map[a][b] > c)
				map[a][b] = c;
		}
		
		String[] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);
		
		System.out.println(Dijkstra(start, end));
	}

}
