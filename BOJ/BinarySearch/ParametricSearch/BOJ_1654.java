package BOJ.BinarySearch.ParametricSearch;

import java.io.*;
import java.util.*;

public class BOJ_1654 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] lan = new int[K];
		
		int max = 0;
		for(int i=0; i<K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			if(lan[i] > max) max = lan[i];
		}
		
		long left = 1;
		long right = max;
		long result = 0;
		
		while(left <= right) {
			long mid = (left + right) / 2;
			
			int count = 0;
			for(int i=0; i<K; i++) {
				count += (lan[i] / mid);
			}
			
			if(count < N) { // count가 N개보다 적다 => 더 잘게 잘라야 한다
				right = mid-1;
			} else { // count가 N개보다 크다 => 종료.
				left = mid+1;
				result = mid;
			}
		}
		System.out.println(result);
	}
}
