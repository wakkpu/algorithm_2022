

import java.io.*;
import java.util.*;

public class BOJ_2563 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] white = new int[100][100];
		int[][] black = new int[n][2];
		
		// 검은 종이의 x, y 좌표들 저장
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			black[i][0] = Integer.parseInt(st.nextToken());
			black[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 흰 종이에 검은 종이 덧대기
		for(int k=0; k<n; k++) {
			int x = black[k][0];
			int y = black[k][1];
			
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) {
					white[i][j] = 1;
				}
			}
		}
		
		// 검은 종이가 덧대진 부분 넓이 구하기
		int sol = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(white[i][j] == 1) {
					sol++;
				}
			}
		}
		
		System.out.println(sol);
	}

}
