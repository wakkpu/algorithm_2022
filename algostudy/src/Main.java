

import java.util.Scanner;

public class Main {
	
	public int n;
	public int k;
	public Graph graph;
	
	private int[][] vertex;
	private int[] nodes;
	
	private int[] DP;
	
	public int backtrack(int node) {
		
		if(DP[node] != -1)
			return DP[node];
		
		int max = -1;
		for(int j = 0; j < vertex[node].length; j++) {
			if(vertex[j][node] == 1) {
				int temp = backtrack(j);
				
				if(temp > max) {
					max = temp;
				}
			}
		}
		
		if(max == -1)
			DP[node] = nodes[node];
			
		else
			DP[node] = max + nodes[node];
		
		return DP[node];
	}
	
	public Main(int n, int k, int[] nodes, Graph graph) {
		this.n = n;
		this.k = k;
		
		this.graph = graph;
		this.nodes = nodes;
		this.vertex = graph.getGraph();
		this.DP = new int[this.n];
		
		for(int i = 0; i < this.n; i++) {
			DP[i] = -1;
		}
	}
	
	static class Graph {
		private int[][] graph;
		
		public Graph(int size) {
			this.graph = new int[size][size];
		}
		
		public int[][] getGraph() {
			return this.graph;
		}
		
		public void putNode(int x, int y) {
			graph[x][y] = 1;
		}
		
		public void printGraph() {
			for(int i = 0; i < graph.length; i++) {
				for(int j = 0; j < graph[i].length; j++) {
					System.out.print(" " + graph[i][j]);
				}
				System.out.println();
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		
		for(int j = 0; j < t; j++) {
			int n = input.nextInt();
			int k = input.nextInt();
			
			int[] nodes = new int[n];
			Graph graph = new Graph(n);
			
			for(int i = 0; i < n; i++) {
				nodes[i] = input.nextInt();
			}
			
			for(int i = 0; i < k; i++) {
				int x = input.nextInt() - 1;
				int y = input.nextInt() - 1;
				graph.putNode(x, y);
			}
			
			Main sol = new Main(n, k, nodes, graph);
			
			//sol.graph.printGraph();
			
			int target = input.nextInt() - 1;
			
			System.out.println(sol.backtrack(target));
		}
	}

}
