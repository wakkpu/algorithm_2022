package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2342 {

	static int[][] Weight = {{0, 2, 2, 2, 2},
			 				 {2, 1, 3, 4, 3},
			 				 {2, 3, 1, 3, 4},
			 				 {2, 4, 3, 1, 3},
			 				 {2, 3, 4, 3, 1}};
	
	static int[][][] DP;
	static int N;
	static ArrayList<Integer> input;
	
	static int DDR(int steps, int leftStep, int rightStep) {
		// N번째 step이면 종료
		if(steps == N)
			return 0;
		
		// DP table에 값이 있으면 return
		if(DP[steps][leftStep][rightStep] != 0)
			return DP[steps][leftStep][rightStep];
		
		// 왼발 옮기는 경우 vs 오른발 옮기는 경우 중 최소값으로 DP table 갱신
		int nextStep = input.get(steps);
		int leftScore = Weight[leftStep][nextStep] + DDR(steps+1, nextStep, rightStep);
		int rightScore = Weight[rightStep][nextStep] + DDR(steps+1, leftStep, nextStep);
		
		return DP[steps][leftStep][rightStep] = Math.min(leftScore, rightScore);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		input = new ArrayList<>();
		
		while(true) {
			int x = Integer.parseInt(st.nextToken());
			if(x == 0)
				break;
			input.add(x);
		}
		
		N = input.size();
		DP = new int[N+1][5][5];
		
		int sol = DDR(0, 0, 0);
		
		System.out.println(sol);
		
	}
}
