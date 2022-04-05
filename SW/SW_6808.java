package SW;

import java.io.*;
import java.util.*;

public class SW_6808 {

	static int kyWin; // 규영이 이긴 경우의 수
	static int[] ky, iy; // 규영, 인영이 갖는 카드
	static int[] result; // 인영이가 낸 카드
	static boolean[] visited; // 카드 순회
	
	public static void perm(int index) {
		if(index == 9) {
			int kyScore = 0;
			int iyScore = 0;
			
			for(int i=0; i<9; i++) {
				if(ky[i] > result[i]) {
					kyScore += ky[i]+result[i];
				} else {
					iyScore += ky[i]+result[i];
				}
			}
			if(kyScore > iyScore) {
				kyWin++;
			}
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(!visited[i]) { // 이 카드 안 냈으면
				visited[i] = true; // 낸 경우에 대해 재귀
				result[index] = iy[i]; // 냈으니깐 체크
				perm(index+1);
				visited[i] = false; // 다른 경우를 위해 다시 false로.
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			ky = new int[9];
			iy = new int[9];
			boolean[] cards = new boolean[19];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				int temp = Integer.parseInt(st.nextToken());
				cards[temp] = true;
			}
			
			int idx1 = 0;
			int idx2 = 0;
			for(int i=1; i<=18; i++) {
				if(cards[i] == true) {
					ky[idx1++] = i;
				} else {
					iy[idx2++] = i;
				}
			}
			
			kyWin = 0;
			result = new int[9];
			visited = new boolean[9];
			perm(0);
			System.out.println("#"+t+" "+kyWin+" "+(362880-kyWin));
		}
	}

}
