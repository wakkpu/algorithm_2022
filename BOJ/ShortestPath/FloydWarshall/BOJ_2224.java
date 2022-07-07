package BOJ.ShortestPath.FloydWarshall;

import java.io.*;
import java.util.*;

public class BOJ_2224 {
	
	static int len = 58;
	
	static int[][] table = new int[len][len];
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		
		int n = Integer.parseInt(tokens.nextToken());
		
		// 입력 + 테이블 생성. P => P일 때 제외
		for(int i=0; i<n; i++) {
			tokens = new StringTokenizer(input.readLine(), " => ");
			int x = tokens.nextToken().charAt(0)-65;
			int y = tokens.nextToken().charAt(0)-65;
			if(x != y)
				table[x][y] = 1;
		}
		
		// 참인 명제 찾기.
		for(int k=0; k<len; k++) {
			for(int i=0; i<len; i++) {
				for(int j=0; j<len; j++) {
					if(i != j && table[i][k] == 1 && table[k][j] == 1) {
						table[i][j] = 1;
					}
				}
			}
		}
		
		// 참인 명제 개수 세기.
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				if(table[i][j] == 1) {
					count++;
				}
			}
		}

		// 결과 출력
		System.out.println(count);
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				if(table[i][j] == 1) {
					System.out.printf("%s => %s\n", (char)(i+65), (char)(j+65));
				}
			}
		}
	}
}
