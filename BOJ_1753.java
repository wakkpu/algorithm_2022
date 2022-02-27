import java.io.*;
import java.util.*;

public class BOJ_1753 {
	
	static int V, E;
	static ArrayList<LinkNode>[] graph;
	
	static class LinkNode {
		int to, weight;

		public LinkNode(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "LinkNode [to=" + to + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V+1];
		for(int i=0; i<V+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int start = Integer.parseInt(br.readLine());
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from   = Integer.parseInt(st.nextToken());
			int to     = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].add(new LinkNode(to, weight));
		}
		
		int[] result = Dijkstra(start);
		
		for(int i=1; i<V+1; i++) {
			if(result[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(result[i]);
			}
		}
	}
	
	public static int[] Dijkstra(int start) {
		
		int[] distance = new int[V+1]; // distance[V]: start에서 V까지 경로의 비용
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[start] = 0;
		
		boolean[] visited = new boolean[V+1];
		
		for(int i=1; i<V+1; i++) {
			int min = Integer.MAX_VALUE;
			int curr = 0;
			
			// 최소 비용이 확정되지 않은 정점 중 최소 비용의 정점 선택
			for(int j=1; j<V+1; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					curr = j;
				}
			}
			
			if(curr == 0) break;
			
			visited[curr] = true;
			// 선택된 정점을 경유지로 하여 아직 최소 비용이 확정되지 않은 다른 정점의 최소 비용을 고려
			for(LinkNode next: graph[curr]) {
				if(!visited[next.to] && distance[next.to] > distance[curr] + next.weight) {
					distance[next.to] = distance[curr] + next.weight;
				}
			}
			
		}
		
		return distance;
	}
}
