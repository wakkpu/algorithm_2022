package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2961 {

	static int totalSour;
	static int totalBitter;
	static Food[] foods;
	static int min;

	static class Food {
		int sour;
		int bitter;

		public Food(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}

		@Override
		public String toString() {
			return "Food [sour=" + sour + ", bitter=" + bitter + "]";
		}
	}

	public static void combi(int toChoose, Food[] choosed, int startIdx) {
		if(toChoose == 0) {
			totalSour = 1;
			totalBitter = 0;
			for(int i=0; i<choosed.length; i++) {
				totalBitter += choosed[i].bitter;
				totalSour *= choosed[i].sour;
			}
			
			int diff = Math.abs(totalBitter-totalSour);
			if(min > diff) {
				min = diff;
			}
			return;
		}
		
		for(int i=startIdx; i<foods.length; i++) {
			choosed[choosed.length - toChoose] = foods[i];
			combi(toChoose-1, choosed, i+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		foods = new Food[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			foods[i] = new Food(sour, bitter);
		}		
		
		min = Integer.MAX_VALUE;
		
		for(int i=1; i<=n; i++) {
			combi(i, new Food[i], 0);
		}
		
		System.out.println(min);
	}

}
