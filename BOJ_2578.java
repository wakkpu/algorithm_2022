package jungol.language_coder;

import java.io.*;
import java.util.*;

public class BOJ_2578 {
	
	static boolean[][] bingo;
	static int[][] ans;
	static int[][] sol;
	
	public static int checkBingo() {
		int nBingo = 0;
		for(int i=0; i<5; i++) {
			if(bingo[i][0] == true && bingo[i][1] == true && bingo[i][2] == true && bingo[i][3] == true && bingo[i][4] == true) {
				nBingo++;
			}
			if(bingo[0][i] == true && bingo[1][i] == true && bingo[2][i] == true && bingo[3][i] == true && bingo[4][i] == true) {
				nBingo++;
			}
		}
		if(bingo[0][0] == true && bingo[1][1] == true && bingo[2][2] == true && bingo[3][3] == true && bingo[4][4] == true) {
			nBingo++;
		}
		if(bingo[0][4] == true && bingo[1][3] == true && bingo[2][2] == true && bingo[3][1] == true && bingo[4][0] == true) {
			nBingo++;
		}
		return nBingo;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		bingo = new boolean[5][5];
		// 철수 답안
		ans = new int[5][5];
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				ans[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 해설자. 답
		sol = new int[5][5];
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				sol[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 해설자가 불러주는 몇 번째 숫자인지
		int num = 0;
		
		// 빙고 갯수
		int bingoNum = 0;
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				// 해설자가 답 부름.
				int call = sol[i][j];
				num++;
				// 철수 답안지와 비교.
				for(int ii=0; ii<5; ii++) {
					for(int jj=0; jj<5; jj++) {
						int answer = ans[ii][jj];
						// 답안지에 해설자가 부른게 있으면 체크
						if(answer == call) {
							bingo[ii][jj] = true;
						}
					}
				}
				// 빙고 갯수 세서 3이상이면 종료
				bingoNum = checkBingo();
				if(bingoNum >= 3) {
					System.out.println(num);
					return;
				}
			}
		}
	}

}
