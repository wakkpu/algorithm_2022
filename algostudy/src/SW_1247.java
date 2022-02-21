import java.io.*;
import java.util.*;

public class SW_1247 {
	
	static int min, N;
	static int[][] graph;
	static boolean[] visited;
	static Pos home, cmp;
	static Pos[] pos;
	
	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
		public int dist(Pos p) {
			return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
		}
	}

	public static void move(int num, Pos p, int weight) {
		if(num == N) {
			weight += p.dist(cmp); // 집 다 돌았으니까 마지막으로 회사 거리 더하기
			
			if(min > weight) min = weight; // 최솟값 갱신
			return;
		}
		
		for(int i=0; i<N; i++) {
			// weight+x.dist(pos[i]) < min: min보다 커져버리면 더할 이유가 없으므로 진행 X
			if(!visited[i] && weight+p.dist(pos[i]) < min) { 
				visited[i] = true;
				move(num+1, pos[i], weight+p.dist(pos[i])); // 뽑은수+1, 출발 지점 갱신, 거리 갱신
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			home = new Pos(x1, y1);
			
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			cmp = new Pos(x2, y2);
			
			pos = new Pos[N];
			for(int i=0; i<N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				pos[i] = new Pos(x, y);
			}
			
			min = 1000;
			visited = new boolean[N+2];
			move(0, home, 0); // 집에서 출발
			System.out.println("#"+t+" "+min);
		}
	}
}
