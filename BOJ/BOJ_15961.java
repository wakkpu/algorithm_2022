package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_15961 {
	
	static int N, d, k, c, count, max;
	static int[] sushi;
	static int[] choosed;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[N];
		for(int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		// 1번부터 d번까지 초밥을 몇 개 먹는지 갯수
		choosed = new int[d+1];
		
		// 쿠폰 초밥 일단 먹음
		choosed[c]++;
		count = 1;
		
		// 첫번째 부터 k-1번째까지 초밥 k개 먹기
		for(int i=0; i<k; i++) {
			// 안 먹었던 거면 먹은 초밥 종류++
			if(choosed[sushi[i]] == 0) {
				count++;
			}
			choosed[sushi[i]]++;
		}
		
		// k번째 스시부터 먹기
		int start = k;
		while(true) {
			int tail = sushi[(start - k) % N]; // 맨 뒤 초밥
			if(--choosed[tail] == 0) { // 뺐을 때 그 초밥을 안먹게 되는거라면 종류--
				count--;
			}
			
			int head = sushi[start % N]; // 맨 앞 초밥
			if(choosed[head]++ == 0) { // 그 초밥이 안 먹었던거면 종류++
				count++;
			}
			
			max = Math.max(max, count);

			if(++start == (N-1) + k) break;
		}
		
		System.out.println(max);
	}
}
