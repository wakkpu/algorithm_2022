package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1561 {

	static class Ride {
		int time;
		boolean using;
		
		public Ride(int time, boolean using) {
			this.time = time;
			this.using = using;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		if(N < M) {
			System.out.println(N);
			return;
		}
		
		int[] kids = new int[N+1];
		for(int i=1; i<=N; i++) {
			kids[i] = i;
		}
		
		Ride[] rides = new Ride[M+1];
		for(int i=1; i<=M; i++) {
			rides[i] = new Ride(i, false);
		}
		
		// N명의 아이가 1번부터 놀이기구름 탐
		for(int i=1; i<=N; i++) {
			// binary-search로 가장 번호가 빠른, using이 false인 놀이기구를 찾음
			
			// 찾아서 해당 놀이기구의 using=true
			
			// time 어떡하지?
			
		}
	}

}
