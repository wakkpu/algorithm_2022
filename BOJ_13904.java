

import java.io.*;
import java.util.*;

/*
 * 그리디
 * 정렬해서 뒤에서부터 
 * 
 */
public class BOJ_13904 {

	static class HW implements Comparable<HW>{
		public int due;
		public int score;

		public HW(int due, int score) {
			this.due = due;
			this.score = score;
		}

		@Override
		public String toString() {
			return "HW [due=" + due + ", score=" + score + "]";
		}

		@Override
		public int compareTo(HW hw) {
			return hw.score - this.score;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		HW[] hw = new HW[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int due = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			hw[i] = new HW(due, score);
		}
		Arrays.sort(hw, (o1, o2) -> o2.due - o1.due);
		
		PriorityQueue<HW> solved = new PriorityQueue<>();

		int totalScore = 0;
		int day = hw[0].due; // 내림차순이므로 0번째 due가 최대 day
		int idx = 0;
		for(int i=day; i>0; i--) {
			//System.out.println("day: "+i);
			while(idx < n && hw[idx].due >= i) {
				//System.out.println("solving "+hw[idx].toString());
				solved.add(hw[idx++]);
			}
			if(!solved.isEmpty()) {
				HW sol = solved.poll();
				//System.out.println("solved "+sol.toString());
				totalScore += sol.score;
				//System.out.println("score: "+ totalScore);
			}
		}
		
		System.out.println(totalScore);
	}

}
