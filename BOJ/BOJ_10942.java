package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_10942 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		
		/*// Brute Force
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			boolean flag = true;
			while(s <= e) {
				if(numbers[s++] != numbers[e--]) {
					flag = false;
				}
			}
			if(flag == true) sb.append("1\n");
			else sb.append("0\n");
			
		}
		System.out.print(sb);*/
		// DP
		int[][] questions = new int[m][2];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			questions[i][0] = Integer.parseInt(st.nextToken());
			questions[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][j]: i부터 j까지 팰린드롬인지 아닌지
		boolean[][] dp = new boolean[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			dp[i][i] = true;
		}
		
	}
}
