package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16236 {
	
	static int N;
	static int[][] map;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static class Fish implements Comparable<Fish> {
		int i; // r
		int j; // c
		int distance; // 이동 거리
		
		public Fish(int i, int j, int distance) {
			this.i = i;
			this.j = j;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Fish [i=" + i + ", j=" + j + ", distance=" + distance + "]";
		}

		@Override
		public int compareTo(Fish o) {
			if(this.distance == o.distance) {
				if(this.i == o.i) {
					return Integer.compare(this.j, o.j);
				} else {
					return Integer.compare(this.i, o.i);
				}
			} else {
				return Integer.compare(this.distance, o.distance);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		Fish shark = null;
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Fish(i, j, 0);
					map[i][j] = 0;
				}
			}
		}
		
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		Queue<Fish> q = new LinkedList<>();
		q.offer(shark);
		
		int eat = 0;
		int result = 0;
		int size = 2;
		
		while(true) {
			boolean[][] visited = new boolean[N][N];
			
			while(!q.isEmpty()) {
				Fish sh = q.poll();
				
				int si = sh.i;
				int sj = sh.j;
				visited[si][sj] = true;
				
				for(int i=0; i<4; i++) {
					
					int ni = si+di[i];
					int nj = sj+dj[i];
					
					if(ni < 0 || ni >= N || nj < 0 || nj >= N || map[ni][nj] > size || visited[ni][nj]) {
						continue;
					}
					
					if(map[ni][nj] < size && map[ni][nj] != 0) {
						pq.offer(new Fish(ni, nj, sh.distance+1));
					}
					
					q.offer(new Fish(ni, nj, sh.distance+1));
					visited[ni][nj] = true;
				}
			}
			
			if(pq.isEmpty()) {
				System.out.println(result);
				return;
			}
			
			Fish fish = pq.poll();
			eat++;
			
			if(size == eat) {
				size++;
				eat = 0;
			}
			
			map[fish.i][fish.j] = 0;
			result += fish.distance;
			q.offer(new Fish(fish.i, fish.j, 0));
			pq.clear();
		}
	}
}
