

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2630 {

	static int blue = 0;
	static int white = 0;
	static int n;
	static int[][] paper;
	
	public static void cutPaper(int i, int j, int size) {
		if(size == 1) {
			if(paper[i][j] == 0) white++;
			else blue++;
			return;
		}
		
		if(blueOrWhite(i, j, size) == true) {
			if(paper[i][j] == 0) white++;
			else blue++;
			return;
			
		}
		
		cutPaper(i, j, size/2);
		cutPaper(i+size/2, j, size/2);
		cutPaper(i, j+size/2, size/2);
		cutPaper(i+size/2, j+size/2, size/2);
	}
	
	public static boolean blueOrWhite(int i, int j, int size) {
		boolean allSame = true;
		for(int x=i; x<i+size; x++) {
			for(int y=j; y<j+size; y++) {
				if(paper[x][y] != paper[i][j]) {
					allSame = false;
				}
			}
		}
		return allSame;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cutPaper(0, 0, n);
		System.out.println(white);
		System.out.println(blue);
	}
}
