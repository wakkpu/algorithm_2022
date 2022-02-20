

import java.util.*;

public class CompleteBinaryTreeTest {

	static char[] nodes;
	
	public static void makeTree() {
		nodes = new char[11];
		for(int i=1; i<nodes.length; i++) {
			nodes[i] = (char)('A'+i-1);
		}
		System.out.println("트리 "+Arrays.toString(nodes));
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		
		while(!q.isEmpty()) {
			int head = q.poll();
			System.out.println(head+" "+nodes[head]);
			
			if(2*head < nodes.length) {
				q.offer(2*head);
			}
			if(2*head+1 < nodes.length) {
				q.offer(2*head+1);
			}
		}
	}
	
	public static void dfsStack() {
		Stack<Integer> q = new Stack<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int head = q.pop();
			System.out.println(head+" "+nodes[head]);
			
			if(2*head+1 < nodes.length) {
				q.add(2*head+1);
			}
			if(2*head < nodes.length) {
				q.add(2*head);
			}
			
		}
	}
	
	public static void dfs(int i, String type) {
		if(i >= nodes.length) {
			return;
		}
		
		if(type.equals("pre")) {
			System.out.println(i+" "+nodes[i]);
			dfs(2*i, type);
			dfs(2*i+1, type);
		} else if(type.equals("in")) {
			dfs(2*i, type);
			System.out.println(i+" "+nodes[i]);
			dfs(2*i+1, type);
		} else if(type.equals("post")) {
			dfs(2*i, type);
			dfs(2*i+1, type);
			System.out.println(i+" "+nodes[i]);
		}
	}
	
	public static void main(String[] args) {
		
		makeTree();
		//bfs();
		//dfsStack();
		dfs(1, "pre");
		dfs(1, "in");
		dfs(1, "post");
	}

}
