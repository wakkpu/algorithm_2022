

import java.io.*;
import java.util.*;

public class BOJ_16938 {

	static int N, L, R, X;
	static int[] Quiz;
	static int properNum;
	
	public static void combi(int toChoose, int[] choosed, int startIdx) {
		if(toChoose == 0) {
			int sum = 0;
			int min = 1000001;
			int max = 0;
			for(int i=0; i<choosed.length; i++) {
				sum += choosed[i];
				if(choosed[i] < min) {
					min = choosed[i];
				}
				if(choosed[i] > max) {
					max = choosed[i];
				}
			}
			int diff = Math.abs(max - min);
			if(L <= sum && sum <= R && X <= diff) {
				properNum++;
			}
			return;
		}
		
		for(int i=startIdx; i<Quiz.length; i++) {
			choosed[choosed.length - toChoose] = Quiz[i];
			combi(toChoose-1, choosed, i+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Quiz = new int[N];
		for(int i=0; i<N; i++) {
			Quiz[i] = Integer.parseInt(st.nextToken());
		}
		
		properNum = 0;
		for(int i=2; i<=N; i++) {
			combi(i, new int[i], 0);
		}
		
		System.out.println(properNum);
	}

}
