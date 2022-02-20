

import java.io.*;
import java.util.*;

public class BOJ_16926 {

	static int[][] arr;
	static int n, m, r, s;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void loop() {
		for(int i=0; i<s; i++) {
			int dir = 0;
			int sy = i;
			int sx = i;
			int temp = arr[sy][sx];
			
			while(dir < 4) {
				int cx = sx + dx[dir];
				int cy = sy + dy[dir];
				
				if(cx >= i && cy >= i && cx < n-i && cy < m-i) {
					arr[sx][sy] = arr[cx][cy];
					sx = cx;
					sy = cy;
				} else {
					dir++;
				}
			}
			arr[i+1][i] = temp;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		s = Math.min(n, m) / 2;
		
		arr = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(r-- > 0)
			loop();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
