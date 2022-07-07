package BOJ.TwoPointer;

import java.io.*;
import java.util.*;

public class BOJ_2230 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n, m;
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int left = 0;
		int right = 0;
		int minDiff = arr[n-1] - arr[0] + 1;
		while(left < n && right < n) {
			int diff = arr[right] - arr[left];
			
			if(m == diff) { // m보다 두 수의 차가 더 작아질 수 없으므로 바로 종료
				minDiff = diff;
				break;
			}
			
			if(m <= diff) { // 두 수의 차가 더 작아져야 한다 -> left++
				left++;
				minDiff = Math.min(diff, minDiff);
			} else { // 두 수의 차가 더 커져야 한다 -> right++
				right++;
			}
		}
		
		System.out.println(minDiff);
	}

}
