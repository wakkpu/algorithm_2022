

import java.io.*;
import java.util.*;

public class BOJ_2616 {

	static int[] train, aggSum;
	static int[][] DP;
	static int max;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		train = new int[n+1];
		aggSum = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			train[i] = Integer.parseInt(st.nextToken());
			aggSum[i] = aggSum[i-1]+train[i];
		}
		
		max = Integer.parseInt(br.readLine()); // 소형 기관차가 가져갈 수 있는 최대 객차 수
		
		DP = new int[3+1][n+1];
		
		for(int i=1; i<4; i++) {
			for(int j=i*max; j<=n; j++) {
				// DP[i][j]: i대의 기차의 j번째 객실까지의 최대 누적합은 아래 둘 중 더 큰 값
				// (i-1)대의 기차의 (j-1)번째 객실까지의 최대 누적합
				// (i-1)대의 기차의 (j-max)번째 객실의 누적합 + j-max ~ j번째 까지의 객실
				// => DP[3][n]: 3대의 기차의 n번째 객실까지의 최대 누적합
				DP[i][j] = Math.max(DP[i][j-1], DP[i-1][j-max] + (aggSum[j] - aggSum[j-max]));
			}
		}
		
		/*
		for(int i=0; i<4; i++) {
			for(int j=0; j<=n; j++) {
				System.out.print(DP[i][j]+" ");
			}
			System.out.println();
		}*/
		System.out.println(DP[3][n]);
		
	}

}
