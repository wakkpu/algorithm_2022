import java.io.*;
import java.util.*;

public class BOJ_1260 {
	
	static int N, M, V;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		// 그래프 인접 리스트
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph[from].add(to);
			graph[to].add(from);
		}
		
		// 노드 번호 작은 것부터 방문해야 하므로
		for(int i=1; i<N+1; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(new boolean[N+1], V);
		System.out.println();
		bfs(new boolean[N+1], V);
	}
	
	public static void dfs(boolean[] visited, int from) {
		
		
		visited[from] = true;
		System.out.print(from+" "); // 방문할 때 출력
		
		for(int next: graph[from]) {
			if(!visited[next]) {
				dfs(visited, next);
			}
		}
	}
	
	public static void bfs(boolean[] visited, int from) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(from);
		
		visited[from] = true;
		
		while(!q.isEmpty()) {
			from = q.poll();
			System.out.print(from+" "); // 방문할 때 출력
			
			for(int next: graph[from]) {
				if(!visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}
