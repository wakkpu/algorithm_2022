package BOJ.BinarySearch.ParametricSearch;

import java.io.*;
import java.util.*;

public class BOJ_2805 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		long max = 0;
		long[] tree = new long[N];
		for(int i=0; i<N; i++) {
			tree[i] = Long.parseLong(st.nextToken());
			if(tree[i] > max) max = tree[i];
		}
		
		long left = 0;
		long right = max;
		long result = 0;
		
		while(left <= right) {
			long mid = (left+right) / 2;
			
			long count = 0;
			for(int i=0; i<N; i++) {
				long cut = tree[i] - mid;
				if(cut > 0) count += cut;
			}
			
			if(count < M) { // 높이 더 높여야 함
				right = mid-1;
			} else {
				left = mid+1;
				result = mid;
			}
		}
		System.out.println(result);
	}

}
