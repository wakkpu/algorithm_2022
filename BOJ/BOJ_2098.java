package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2098 {
	
	static int N, maxBit;
	static int[][] map;
	static int[][] dp;
	static int MAX = 10000001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		maxBit = (1 << N) - 1;
		dp = new int[N][maxBit];
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], MAX);
		}
		
		// 0번 도시부터 시작. 0번 도시 방문한 것이므로 초기 상태 1.
		int minDistance = TSP(0, 1);
		System.out.println(minDistance);
	}
	
	public static int TSP(int curr, int bitmask) {
        
		// 모든 도시 방문 완료했을 때
		if(bitmask == maxBit) {
			if(map[curr][0] == 0) return MAX; // 경로 없으면
			else return map[curr][0]; // 경로가 존재하면
		}
        
		// 경로 상이라면 memoization 
		if(dp[curr][bitmask] != MAX) return dp[curr][bitmask];
		
		for(int i=0; i<N; i++) {
			// next : i 도시 방문
			int next = bitmask | (1<<i); 
            
			// 경로가 없거나 i 도시를 이미 방문했을 경우 continue
			if(map[curr][i] == 0 || (bitmask & (1<<i)) != 0) continue;
			
			dp[curr][bitmask] = Math.min(dp[curr][bitmask], TSP(i, next) + map[curr][i]);
		}
		
		return dp[curr][bitmask];
	}
}
