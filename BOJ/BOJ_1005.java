package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1005 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int T = Integer.parseInt(st.nextToken());
	
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken()); // 건물의 번호는 1~N
			int K = Integer.parseInt(st.nextToken()); // 건물간의 건설순서 규칙 K개
			
			st = new StringTokenizer(br.readLine());
			int[] time = new int[N+1];
			for(int i=1; i<N+1; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
			for(int i=0; i<N+1; i++) {
				graph.add(new ArrayList<Integer>());
			}
			
			int[] depth = new int[N+1];
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
				depth[to]++;
			}
			
			int W = Integer.parseInt(br.readLine());
			
			int[] dp = new int[N+1];
			for(int i=1; i<N+1; i++) {
				dp[i] = time[i];
			}
			
			Queue<Integer> q = new LinkedList<Integer>();
			for(int i=1; i<depth.length; i++) {
				if(depth[i] == 0) {
					q.offer(i);
				}
			}
			
			while(!q.isEmpty()) {
				
				int curr = q.poll();
				List<Integer> list = graph.get(curr);
				for(int i=0; i<list.size(); i++) {
					int next = list.get(i);
					depth[next]--;
					
					if(depth[next] == 0) {
						q.offer(next);
					}
					
					dp[next] = Math.max(dp[next], dp[curr] + time[next]);
				}
			}
			
			System.out.println(dp[W]);
		}
	}
}
