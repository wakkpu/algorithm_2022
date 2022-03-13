import java.io.*;
import java.util.*;


public class SW_5215 {

	static Top[] toppings;
	static int N, M, maxRate;

	static class Top {

		public int rate, calories;

		Top(int rate, int calories) {
			this.rate = rate;
			this.calories = calories;
		}

		@Override
		public String toString() {
			return "Top [rate=" + rate + ", calories=" + calories + "]";
		}

	}

	public static void makeBurger(int num, int currRate, int currCal) {
		if(currCal > M)
			return;
		
		if(num == N) {
			maxRate = Math.max(maxRate, currRate);
			return;
		}
		
		makeBurger(num+1, currRate, currCal);
		makeBurger(num+1, currRate+toppings[num].rate, currCal+toppings[num].calories);
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // the number of topping
			M = Integer.parseInt(st.nextToken()); // max calories
			maxRate = 0;

			toppings = new Top[N];
			
			for(int n=0; n<N; n++) {
				st = new StringTokenizer(br.readLine());
				toppings[n] = new Top(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			makeBurger(0, 0, 0);
			
			System.out.println("#"+t+" "+maxRate);
			
		}
		
	}

}

