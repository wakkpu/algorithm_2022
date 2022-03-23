package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1068 {
	
	static int N;
	
	static ArrayList<Integer>[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N];
		for(int i=0; i<N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int root = -1;
		for(int i=0; i<N; i++) {
			int node = Integer.parseInt(st.nextToken());
			if(node == -1) {
				root = i;
				continue;
			}
			tree[node].add(i);
		}
		
		int toRemove = Integer.parseInt(br.readLine());
		
		if(toRemove == root) {
			System.out.print(0);
			return;
		}
		
		removeSubTree(toRemove);
		System.out.print(countLeaf(root));
	}
	
	public static void removeSubTree(int node) {
		// DFS로 자식 노드 다 찾음
		if(tree[node].size() > 0) {
			int size = tree[node].size();
			while(size > 0) {
				int child = tree[node].get(--size);
				removeSubTree(child);
			}
		}
		
		// 자식 노드 전부 삭제
		for(int i=0; i<N; i++) {
			if(tree[i].contains(node)) {
				for(int j=0; j<tree[i].size(); j++) {
					if(tree[i].get(j) == node) {
						tree[i].remove(j);
					}
				}
			}
		}
	}
	
	// BFS로 리프 노드 갯수 세기
	public static int countLeaf(int node) {
		
		int count = 0;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(node);
		
		while(!q.isEmpty()) {
			int head = q.poll();
			
			// 서브 트리를 갖지 않으면 리프 노드
			if(tree[head].size() == 0) {
				count++;
				continue;
			}
			
			// 서브 트리를 가지면 서브 트리에 대해 다시 BFS
			for(int subTree: tree[head]) {
				q.add(subTree);
			}
		}
		
		return count;
	}
}
