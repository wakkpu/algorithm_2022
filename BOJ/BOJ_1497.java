package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1497 {

	static int n, m;
	static String[] input;
	static int MAX_MUSIC_CNT = 0;
	static int MIN_GUITAR_CNT = 11;
	
	public static void checkMaxSong(boolean[] checked) {
		Set<Integer> musicSet = new HashSet<>();
		int guitar = 0;
		for(int i=0; i<checked.length; i++) {
			if(checked[i]) {
				guitar++;
				for(int j=0; j<m; j++) {
					if(input[i].charAt(j) == 'Y') {
						musicSet.add(j);
					}
				}
			}
		}
		if(musicSet.size() >= MAX_MUSIC_CNT && MIN_GUITAR_CNT > guitar) {
			MAX_MUSIC_CNT = musicSet.size();
			MIN_GUITAR_CNT = guitar;
		}
		
	}
	
	public static void subset(int toCheck, int guitarNum, int[] songs) {
		if(toCheck == 0) {
			int musicCnt = 0;
			for(int i=0; i<songs.length; i++) {
				if(songs[i] > 0) {
					musicCnt++;
				}
			}
			if(musicCnt >= MAX_MUSIC_CNT && MIN_GUITAR_CNT > guitarNum) {
				MAX_MUSIC_CNT = musicCnt;
				MIN_GUITAR_CNT = guitarNum;
			}
			return;
		}
		
		// 현재 확인 중인 기타
		String guitar = input[n-toCheck];
		for(int i=0; i<input.length; i++) {
			if(guitar.charAt(i) == 'Y') {
				songs[i]++;
			}
		}
		
		// 기타를 선택한다면, guitar수 증가, 선택한 guitar에 대한 연주 가능 곡 목록 업데이트
		subset(toCheck-1, guitarNum+1, songs);
		
		for(int i=0; i<input.length; i++) {
			if(guitar.charAt(i) == 'Y') {
				songs[i]--;
			}
		}
		
		// 선택하지 않는다면, guitar수 그대로, 선택된 연주 가능 곡 목록 그대로
		subset(toCheck-1, guitarNum, songs);
	}
	
	public static void main(String[] args) throws Exception {
		// 어떻게 기타를 선택할 것인가? -> 완전 탐색: 순열, 조합, 부분 집합
		// 순열, 조합 -> 몇 개를 선택할 것인지 정해진 문제 nCr nPr
		// 부분 집합 -> 몇 개를 선택할 것인지 정해지지 않은 문제 -> O(2^n)
		// 1 <= N <= 10, 1 <= M <= 50이므로 완탐해도 시간 문제 X
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		input = new String[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			input[i] = st.nextToken();
		}
		
		subset(n, 0, new int[m]);
		System.out.println(MIN_GUITAR_CNT);
	}

}
