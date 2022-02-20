

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	public int start;
	public int end;
	public int weight;
	
	public Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge edge) {
		return Integer.compare(this.weight, edge.weight);
	}
}

public class BOJ_1197 {
	
	static int vertex, edge;
	static Edge[] graph;
	static int[] parents;
	
	public static void make_set() {
		for(int i = 1; i <= vertex; i++) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return false;
		
		parents[x] = y;
		return true;
	}
	
	public static int find(int x) {
		if(parents[x] == x)
			return x;
		else
			return find(parents[x]);
	}
	
	public static int kruskal() {
		int sum = 0, count = 0;
		// sort
		Arrays.sort(graph);
		// make set
		make_set();
		// edge별로
		for(Edge edge: graph) {
			// if !cycle
			if(union(edge.start, edge.end)) {
				sum += edge.weight;
				
				// MST는 node수 -1개의 edge를 가짐
				if(++count == vertex-1)
					return sum;
			}
		}
		return sum;
	}	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		
		vertex = Integer.parseInt(tokens.nextToken());
		edge = Integer.parseInt(tokens.nextToken());
		
		graph = new Edge[edge];
		parents = new int[vertex+1];
		
		for(int i=0; i<edge; i++) {
			tokens = new StringTokenizer(input.readLine());
			
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			
			graph[i] = new Edge(a, b, c);
		}
		
		// kruskal 돌리면 끝
		System.out.println(kruskal());
	}
}
