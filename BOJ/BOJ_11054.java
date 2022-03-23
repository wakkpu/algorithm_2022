package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11054 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		int[] num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] inc = new int[N];
		for(int i=0; i<N; i++) {
			inc[i] = 1;
			
			for(int j=0; j<i; j++) {
				if(num[i] > num[j]) {
					inc[i] = Math.max(inc[i], inc[j]+1);
				}
			}
		}
		
		int[] dec = new int[N];
		for(int i=N-1; i>=0; i--) {
			dec[i] = 1;
			
			for(int j=N-1; j>i; j--) {
				if(num[i] > num[j]) {
					dec[i] = Math.max(dec[i], dec[j]+1);
				}
			}
		}

		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(inc[i] + dec[i] - 1, max);
		}
		System.out.println(max);
	}
}
