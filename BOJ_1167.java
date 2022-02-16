import java.io.*;
import java.util.*;

public class BOJ_1167 {

	static int V, farthest, maxLen;
	static ArrayList<Node>[] graph;
	
	static class Node {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void dfs(int node, int parent, int weight) {
		if(weight > maxLen) {
			maxLen = weight;
			farthest = node;
		}
		
		for(int i=0; i<graph[node].size(); i++) {
			Node next = graph[node].get(i);
			if(next.to == parent) continue;
			dfs(next.to, node, next.weight+weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V+1];
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			
			while(true) {
				int to = Integer.parseInt(st.nextToken());
				if(to == -1) break;
				int weight = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(to, weight));
			}
		}
		
		dfs(1, 0, 0);
		dfs(farthest, 0, 0);
		System.out.println(maxLen);
	}
}
